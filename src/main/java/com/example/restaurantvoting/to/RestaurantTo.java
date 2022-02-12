package com.example.restaurantvoting.to;

import com.example.restaurantvoting.model.Dish;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RestaurantTo extends NamedTo {

    String description;

    List<Dish> menu;

    int votesPerDay;

    public RestaurantTo(Integer id, String name, String description, List<Dish> menu, int votesPerDay) {
        super(id, name);
        this.description = description;
        this.menu = menu;
        this.votesPerDay = votesPerDay;
    }
}
