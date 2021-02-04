package com.inflearn.springdatajpa.스프링JPA활용12.controller;

import com.inflearn.springdatajpa.스프링JPA활용12.controller.dto.BookForm;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.item.Book;
import com.inflearn.springdatajpa.스프링JPA활용12.domain.item.Item;
import com.inflearn.springdatajpa.스프링JPA활용12.service.ItemService;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String create(BookForm bookForm) {
        Book book = new Book();
        book.setName(bookForm.getName());
        book.setPrice(bookForm.getPrice());
        book.setStockQuantity(bookForm.getStockQuantity());
        book.setAuthor(bookForm.getAuthor());
        book.setIsbn(bookForm.getIsbn());

        itemService.saveItem(book);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String list(Model model) {
        List<Item> items = itemService.findItems();
        model.addAttribute("items", items);
        return "items/itemList";
    }

    @GetMapping("/items/{itemId}/edit")
    public String updateItemForm(@PathVariable Long itemId, Model model) {
        Book book = (Book) itemService.findItem(itemId);
        BookForm from = BookForm.from(book);

        model.addAttribute("form", from);
        return "items/updateItemForm";
    }

    @PostMapping("/items/{itemId}/edit")
    public String updateItemForm(@ModelAttribute("form") BookForm form) {
        Book book = BookForm.of(form);
        itemService.saveItem(book);
        return "redirect:/items";
    }
}
