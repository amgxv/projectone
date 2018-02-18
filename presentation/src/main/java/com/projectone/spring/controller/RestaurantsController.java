package com.projectone.spring.controller;

import com.projectone.core.base.ReadDB;
import com.projectone.core.dao.Restaurant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/rest/api/v1")
public class RestaurantsController {

    @RequestMapping(path = "/restaurants")//, method = RequestMethod.GET)
    public List<Restaurant> getAll(@RequestParam(value = "name", defaultValue = "") String name) {
        ReadDB dbManager = new ReadDB();
        List<Restaurant> lRestaurants = dbManager.readRestaurantAPI(false);
        return lRestaurants;
    }
}
