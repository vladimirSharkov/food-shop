package com.example.foodshop.web;

import com.example.foodshop.model.view.ViewInterceptor;
import com.example.foodshop.service.ViewCategoriService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategoryStatsController {

 private final ViewCategoriService viewCategoriService;

    public CategoryStatsController(ViewCategoriService viewCategoriService) {
        this.viewCategoriService = viewCategoriService;
    }

    @GetMapping("/statistic/category")
    public String cartStatistic(Model model){
        ViewInterceptor stats = viewCategoriService.getStats();
        model.addAttribute("stats",stats);
        return "category-stats";
    }
}
