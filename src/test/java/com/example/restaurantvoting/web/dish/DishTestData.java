package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;
import java.util.List;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> DISH_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Dish.class, "restaurant");

    public static final int DISH_ID = 1;

    public static final Dish dish1 = new Dish(DISH_ID, "Pickled peppers salad with white radish", 96, LocalDate.now());
    public static final Dish dish2 = new Dish(DISH_ID + 1, "Minced Beef Steak with roasted oyster mushrooms and pepper", 168, LocalDate.now());
    public static final Dish dish3 = new Dish(DISH_ID + 2, "Zenyk from Lviv", 48, LocalDate.now());
//    public static final Dish dish4 = new Dish(DISH_ID + 3, "Nihoshi ramen with teriyaki chicken", 129, LocalDate.now());
//    public static final Dish dish5 = new Dish(DISH_ID + 4,"Kaiso with nut sauce", 99, LocalDate.now());
//    public static final Dish dish6 = new Dish(DISH_ID + 5,"Salad Dario", 98, LocalDate.now());
//    public static final Dish dish7 = new Dish(DISH_ID + 6,"Hummus with crackers", 38, LocalDate.now());
//    public static final Dish dish8 = new Dish(DISH_ID + 7, "Burger with \"steaks\"", 87, LocalDate.now());
//    public static final Dish dish9 = new Dish(DISH_ID + 8, "Pan of varenyky", 98, LocalDate.now());
//    public static final Dish dish10 = new Dish(DISH_ID + 9, "Backed chicken salad", 88, LocalDate.now());
//    public static final Dish dish11 = new Dish(DISH_ID + 10,"Ramen with shrimps", 166, LocalDate.now());
//    public static final Dish dish12 = new Dish(DISH_ID + 11,"Mochi", 55, LocalDate.now());
    public static final Dish dish13 = new Dish(DISH_ID + 12,"Bograch", 92, LocalDate.of(2021, 11, 20));
    public static final Dish dish14 = new Dish(DISH_ID + 13,"Vegetable salad with feta cheese", 82, LocalDate.of(2021, 11, 20));
//    public static final Dish dish15 = new Dish(DISH_ID + 14,"Green Salad", 99, LocalDate.of(2021, 11, 20));
//    public static final Dish dish16 = new Dish(DISH_ID + 15,"Green Roll", 219, LocalDate.of(2021, 11, 20));
//    public static final Dish dish17 = new Dish(DISH_ID + 16,"Berry cheesecake", 89, LocalDate.of(2021, 11, 20));
//    public static final Dish dish18 = new Dish(DISH_ID + 17,"Amigo-bowl", 108, LocalDate.of(2021, 11, 20));
//    public static final Dish dish19 = new Dish(DISH_ID + 18,"Pancakes", 91, LocalDate.of(2021, 11, 20));
//    public static final Dish dish20 = new Dish(DISH_ID + 19,"Chicken sizzling pan", 109, LocalDate.of(2021, 11, 20));
//    public static final Dish dish21 = new Dish(DISH_ID + 20,"Chemical experiments", 140, LocalDate.of(2021, 11, 20));
//    public static final Dish dish22 = new Dish(DISH_ID + 21,"Cottage cheese spreads with baguette", 92, LocalDate.of(2021, 11, 20));
//    public static final Dish dish23 = new Dish(DISH_ID + 22,"Noodles with vegetables and tofu", 138, LocalDate.of(2021, 11, 20));
//    public static final Dish dish24 = new Dish(DISH_ID + 23,"Dumplings with pork", 98, LocalDate.of(2021, 11, 20));
//    public static final Dish dish25 = new Dish(DISH_ID + 24,"Ice-cream", 65, LocalDate.of(2021, 11, 20));

   // public static final List<Dish> dishes = List.of(dish25, dish24, dish23, dish22, dish21, dish20, dish19, dish18, dish17, dish16, dish15, dish14, dish13, dish12, dish11, dish10, dish9, dish8, dish7, dish6, dish5, dish4, dish3, dish2, dish1);

    public static Dish getNew() {
        return new Dish(null, "New dish", 145, LocalDate.now());
    }

    public static Dish getUpdate() {
        return new Dish(DISH_ID, "Update dish", 159, LocalDate.now());
    }
}
