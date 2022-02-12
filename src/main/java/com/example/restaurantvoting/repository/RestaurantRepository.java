package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Restaurant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface RestaurantRepository extends BaseRepository<Restaurant> {

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.menu m WHERE m.menuPerDay=:localDate")
    List<Restaurant> findRestaurantsByMenuByDate(LocalDate localDate);

    @Query("SELECT r FROM Restaurant r JOIN FETCH r.menu m WHERE r.id=:id AND m.menuPerDay=:localDate")
    Optional<Restaurant> findRestaurantByDate(int id, LocalDate localDate);
}
