package com.inflearn.springdatajpa.controller;

import com.inflearn.springdatajpa.domain.item.Item;
import com.inflearn.springdatajpa.domain.member.Member;
import com.inflearn.springdatajpa.service.ItemService;
import com.inflearn.springdatajpa.service.MemberService;
import com.inflearn.springdatajpa.service.OrderService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    public OrderController(OrderService orderService, MemberService memberService, ItemService itemService) {
        this.orderService = orderService;
        this.memberService = memberService;
        this.itemService = itemService;
    }

    @GetMapping("/order")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam Long memberId, @RequestParam Long itemId, @RequestParam int count) {
        orderService.order(memberId, itemId, count);

        return "redirect:/orders";
    }
}
