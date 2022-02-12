package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.error.IllegalRequestDataException;
import com.example.restaurantvoting.model.Dish;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface DishRepository extends BaseRepository<Dish> {

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId AND d.menuPerDay=:localDate")
    List<Dish> findDishesByDate(int restaurantId, LocalDate localDate);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id=:restaurantId")
    Optional<Dish> get(int id, int restaurantId);

    default Dish checkBelong(int id, int restaurantId) {
        return get(id, restaurantId).orElseThrow(
                () -> new IllegalRequestDataException("Dish id= " + id + " doesn't belong Restaurant id= " + restaurantId));
    }
}
