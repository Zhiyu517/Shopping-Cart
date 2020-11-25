package com.shoppingcart.demo.controllers;

import com.shoppingcart.demo.models.PageRepository;
import com.shoppingcart.demo.models.data.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("admin/pages")
public class AdminPagesController {

    @Autowired
    private PageRepository pageRepo;

//    public AdminPagesController(PageRepository pageRepo) {
//        this.pageRepo = pageRepo;
//    }

    @GetMapping
    public String index(Model model) {
        List<Page> pages = pageRepo.findAll();

        model.addAttribute("pages", pages);

        return "admin/pages/index";
    }

    @GetMapping("/add")
    public String add(@ModelAttribute Page page) {
//        model.addAttribute("page", new Page());

        return "admin/pages/add";
    }

    @PostMapping("/add")
    public String add(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        if (bindingResult.hasErrors()) {
            return "admin/pages/add";
        }

        redirectAttributes.addFlashAttribute("message", "Page added");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-")
                : page.getSlug().toLowerCase().replace(" ", "-");

        Page slugExists = pageRepo.findBySlug(slug);

        if (slugExists != null) {
            redirectAttributes.addFlashAttribute("message", "Slug exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("page", page);
        } else {
            page.setSlug(slug);
            page.setSorting(100);
            pageRepo.save(page);
        }

        return "redirect:/admin/pages/add";
    }

    @PostMapping("/edit")
    public String edit(@Valid Page page, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {

        Page pageCurrent = pageRepo.getOne(page.getId());


        if (bindingResult.hasErrors()) {
            model.addAttribute("pageTitle", pageCurrent.getTitle());
            return "admin/pages/edit";
        }

        redirectAttributes.addFlashAttribute("message", "Page edited");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        String slug = page.getSlug() == "" ? page.getTitle().toLowerCase().replace(" ", "-")
                : page.getSlug().toLowerCase().replace(" ", "-");

        Page slugExists = pageRepo.findBySlugAndIdNot(slug, page.getId());

        if (slugExists != null) {
            redirectAttributes.addFlashAttribute("message", "Slug exists, choose another");
            redirectAttributes.addFlashAttribute("alertClass", "alert-danger");
            redirectAttributes.addFlashAttribute("page", page);
        } else {
            page.setSlug(slug);
            pageRepo.save(page);
        }

        return "redirect:/admin/pages/edit/" + page.getId();
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Page page = pageRepo.getOne(id);

        model.addAttribute("page", page);
        return "admin/pages/edit";
    }


    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id, RedirectAttributes redirectAttributes) {
        pageRepo.deleteById(id);

        redirectAttributes.addFlashAttribute("message", "Page deleted");
        redirectAttributes.addFlashAttribute("alertClass", "alert-success");

        return "redirect:/admin/pages";
    }
}
