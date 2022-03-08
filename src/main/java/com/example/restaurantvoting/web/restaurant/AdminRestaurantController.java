package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = AdminRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@CacheConfig(cacheNames = "restaurants")
@Tag(name = "AdminRestaurantController", description = "Allows the admin to manage the restaurants")
public class AdminRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by Id")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @GetMapping("/{id}/{localDate}")
    @Operation(summary = "Get restaurant by Date")
    public ResponseEntity<Restaurant> getByDate(@PathVariable int id, @PathVariable("localDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        log.info("get restaurant id={} by date {}", id, localDate);
        return ResponseEntity.of(repository.findRestaurantByDate(id, localDate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Delete restaurant by Id")
    public void delete(@PathVariable int id) {
        log.info("delete {}", id);
        repository.delete(id);
    }

    @Override
    @GetMapping
    @Cacheable
    @Operation(summary = "Get All restaurants")
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @GetMapping("/by/{localDate}")
    @Operation(summary = "Get All restaurants by Date")
    public List<Restaurant> getAllByDate(@PathVariable("localDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate localDate) {
        log.info("getAll by date {}", localDate);
        return repository.findRestaurantsByMenuByDate(localDate);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Update restaurant by Id")
    public void update(@RequestBody @Valid Restaurant restaurant, @PathVariable int id) {
        log.info("update {} with id={}", restaurant, id);
        assureIdConsistent(restaurant, id);
        repository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Create restaurant")
    public ResponseEntity<Restaurant> createWithLocation(@RequestBody @Valid Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        Restaurant created = repository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
