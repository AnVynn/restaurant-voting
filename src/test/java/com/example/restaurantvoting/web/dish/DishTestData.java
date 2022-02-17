package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH_ID = 1;

    public static final Dish dish1 = new Dish(DISH_ID, "Pickled peppers salad with white radish", 96, LocalDate.now());
    public static final Dish dish2 = new Dish(DISH_ID + 1,"Vegetable salad with feta cheese", 82, LocalDate.of(2021, 11, 20));

    public static Dish getNew() {
        return new Dish(null, "New dish", 145, LocalDate.now());
    }

    public static Dish getUpdate() {
        return new Dish(DISH_ID, "Update dish", 159, LocalDate.now());
    }
}
