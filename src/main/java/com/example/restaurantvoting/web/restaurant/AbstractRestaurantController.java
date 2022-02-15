package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import com.example.restaurantvoting.repository.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

@Slf4j
public abstract class AbstractRestaurantController {

    @Autowired
    protected RestaurantRepository repository;

    public ResponseEntity<Restaurant> get(int id) {
        log.info("get restaurant id={}", id);
        return ResponseEntity.of(repository.findRestaurantByDate(id, LocalDate.now()));
    }

    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.findRestaurantsByMenuByDate(LocalDate.now());
    }
}
