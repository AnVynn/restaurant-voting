package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.web.MatcherFactory;

import java.util.List;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Restaurant.class, "menu");
    public static MatcherFactory.Matcher<RestaurantTo> RESTAURANT_TO_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(RestaurantTo.class, "menu");

    public static final int RESTAURANT_ID = 1;

    public static final Restaurant res1 = new Restaurant(RESTAURANT_ID, "Kryjivka", "Ukrainian restaurant serving only ukrainian food, not pelmeni");
    public static final Restaurant res2 = new Restaurant(RESTAURANT_ID + 1, "NOA", "Asian restaurant serving sushi, ramen and poke");
    public static final Restaurant res3 = new Restaurant(RESTAURANT_ID + 2, "Green Caffe", "Vegetarian restaurant serving vegetarian, vegan, raw food, gluten-free and sugar-free sweets");
    public static final Restaurant res4 = new Restaurant(RESTAURANT_ID + 3, "Gas Lamp", "Ukrainian restaurant-museum serving chemical experiments");
    public static final Restaurant res5 = new Restaurant(RESTAURANT_ID + 4, "Ramen Mo", "Japanese restaurant serving ramen, wok and gyoza");

    public static final List<Restaurant> restaurants = List.of(res1, res2, res3, res4, res5);

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant", "New Description");
    }

    public static Restaurant getUpdate() {
        return new Restaurant(RESTAURANT_ID, "Update Restaurant", "Update Description");
    }
}
