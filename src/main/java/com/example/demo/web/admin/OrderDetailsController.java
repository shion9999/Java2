package com.example.demo.web.admin;

import com.example.demo.common.FlashData;
import com.example.demo.entity.Order;
import com.example.demo.entity.OrderDetail;
import com.example.demo.service.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin/orderdetails")
public class OrderDetailsController {
    @Autowired
    BaseService<Order> orderService;
    @Autowired
    BaseService<OrderDetail> orderDetailService;

    /*
     * 受注詳細新規登録
     */
    @GetMapping(value = "/create/{id}")
    public String form(@PathVariable Integer id, Model model, OrderDetail orderDetail, RedirectAttributes ra) {
        try {
            Order order = orderService.findById(id);
            model.addAttribute("order", order);
            model.addAttribute("orderDetail", orderDetail);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/orders";
        }
        return "admin/orderdetails/create";
    }

    @PostMapping(value = "/create/{id}")
    public String register(@PathVariable Integer id, @Valid OrderDetail orderDetail, BindingResult result, Model model, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                return "admin/orders";
            }
            // 新規登録
            Order order = orderService.findById(id);
            model.addAttribute("order", order);
            orderDetailService.save(orderDetail);
            flash = new FlashData().success("新規作成しました");
        } catch (Exception e) {
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/orders/view/{id}";
    }

    @GetMapping(value = "/edit/{id}")
    public String edit(@PathVariable Integer id, Model model, RedirectAttributes ra) {
        try {
            OrderDetail orderDetail = orderDetailService.findById(id);
            model.addAttribute("orderdetail", orderDetail);
        } catch (Exception e) {
            FlashData flash = new FlashData().danger("該当データがありません");
            ra.addFlashAttribute("flash", flash);
            return "redirect:/admin/orders";
        }
        return "/admin/orderdetails/edit";
    }

    @PostMapping(value = "/edit/{id}")
    public String update(@PathVariable Integer id, @Valid OrderDetail orderDetail, BindingResult result, RedirectAttributes ra) {
        FlashData flash;
        try {
            if (result.hasErrors()) {
                // エラーがある場合は、適切な処理を行う
                return "/admin/orders";
            }
            // 受注詳細の更新処理を行う
            orderDetailService.save(orderDetail);
            flash = new FlashData().success("更新しました");
        } catch (Exception e) {
            // エラーが発生した場合は、適切な処理を行う
            flash = new FlashData().danger("処理中にエラーが発生しました");
        }
        ra.addFlashAttribute("flash", flash);
        return "redirect:/admin/orders/view/{id}";
    }
}
