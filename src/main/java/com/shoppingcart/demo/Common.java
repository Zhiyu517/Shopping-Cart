package com.shoppingcart.demo;

import com.shoppingcart.demo.models.Cart;
import com.shoppingcart.demo.models.CategoryRepository;
import com.shoppingcart.demo.models.PageRepository;
import com.shoppingcart.demo.models.data.Category;
import com.shoppingcart.demo.models.data.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class Common {

    @Autowired
    private PageRepository pageRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    @ModelAttribute
    public void shareData(Model model, HttpSession session, Principal principal) {

        if (principal != null) {
            model.addAttribute("principal", principal.getName()); //get the user name
        }

        List<Page> pages = pageRepo.findAllByOrderBySortingAsc();

        List<Category> categories = categoryRepo.findAllByOrderBySortingAsc();

        boolean cartActive = false;
        if (session.getAttribute("cart") != null) {
            Map<Integer, Cart> cart = (HashMap) session.getAttribute("cart");
            int size = 0;
            double total = 0;
            for (Cart value : cart.values()) {
                size += value.getQuantity();
                total += value.getQuantity() * Double.parseDouble(value.getPrice());

            }
            model.addAttribute("csize", size);
            model.addAttribute("ctotal", total);
            cartActive = true;
        }


        model.addAttribute("cpages", pages);
        model.addAttribute("cartActive", cartActive);
        model.addAttribute("ccategories", categories);

    }
}
