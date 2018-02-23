package com.projectone.spring.controller;

import com.projectone.core.base.ReadDB;
import com.projectone.core.dao.Restaurant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * projectone
 * com.projectone.spring.controller
 * Created by : tech in 18/02/18.
 * Description :
 */


// Controlador "home"
@Controller
public class HomeController {

    // Mapea nuestra página de inicio en la raíz. Lee los restaurantes en la BD, y les da el atrubuto "list" para la lectura con Freemarker
    @RequestMapping("/")
    public String index(@ModelAttribute("model") ModelMap model) {
        ReadDB dbManager = new ReadDB();
        List<Restaurant> lRestaurants = dbManager.readRestaurantAPI(false);
        model.addAttribute("list", lRestaurants);
        return "index";
    }

}
