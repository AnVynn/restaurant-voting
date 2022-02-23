package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.service.DishService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.assureIdConsistent;
import static com.example.restaurantvoting.util.validation.ValidationUtil.checkNew;

@RestController
@Slf4j
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@CacheConfig(cacheNames = "dishes")
public class DishController {

    static final String REST_URL = "/api/admin/restaurant/{restaurantId}/dishes";

    private final DishRepository repository;

    private final DishService service;

    @GetMapping("/{id}")
    @Cacheable
    public ResponseEntity<Dish> get(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("get dish with id={} for restaurant {}", id, restaurantId);
        return ResponseEntity.of(repository.get(id, restaurantId));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void delete(@PathVariable int restaurantId, @PathVariable int id) {
        log.info("delete dish with id={} for restaurant {}", id, restaurantId);
        Dish dish = repository.checkBelong(id, restaurantId);
        repository.delete(dish);
    }

    @GetMapping()
    public List<Dish> getByCurrentDate(@PathVariable int restaurantId) {
        log.info("getByCurrentDate for restaurant {}", restaurantId);
        return repository.findDishesByDate(restaurantId, LocalDate.now());
    }

    @GetMapping("/by-date/{localDate}")
    public List<Dish> getByDate(@PathVariable int restaurantId, @PathVariable ("localDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate localDate) {
        log.info("getByDate for restaurant {}", restaurantId);
        return repository.findDishesByDate(restaurantId, localDate);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE) //?
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    public void update(@RequestBody @Valid Dish dish, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("update {} with id={} for restaurant {}", dish, id, restaurantId);
        assureIdConsistent(dish, id);
        repository.checkBelong(id, restaurantId);
        service.save(dish, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    public ResponseEntity<Dish> createWithLocation(@RequestBody @Valid Dish dish, @PathVariable int restaurantId) {
        log.info("create {} for restaurant {}", dish, restaurantId);
        checkNew(dish);
        Dish created = service.save(dish, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId(), created.getRestaurant().getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
