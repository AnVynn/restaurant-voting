package com.example.restaurantvoting.util;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.to.RestaurantTo;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@UtilityClass
public class RestaurantUtil {

    public RestaurantTo createTo(Restaurant restaurant, int votesPerDay) {
        return new RestaurantTo(restaurant.getId(), restaurant.getName(), restaurant.getDescription(), restaurant.getMenu(), votesPerDay);
    }

    public List<RestaurantTo> getTos(Collection<Vote> votes) {
        Map<Restaurant, Long> votesCount = votes.stream().collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));

        return votesCount.entrySet()
                .stream()
                .map(vote -> createTo(vote.getKey(), Math.toIntExact(vote.getValue())))
                .toList();
    }
}
















