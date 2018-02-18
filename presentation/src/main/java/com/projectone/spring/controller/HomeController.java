package com.projectone.spring.controller;

import com.projectone.core.base.ReadDB;
import com.projectone.core.dao.Restaurant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * projectone
 * com.projectone.spring.controller
 * Created by : tech in 18/02/18.
 * Description :
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(@ModelAttribute("model") ModelMap model) {
        ReadDB dbManager = new ReadDB();
        List<Restaurant> lRestaurants = dbManager.readRestaurantAPI(false);
        model.addAttribute("list",lRestaurants);
        return "index";
    }
}
