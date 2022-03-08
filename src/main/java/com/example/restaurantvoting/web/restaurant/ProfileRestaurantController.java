package com.example.restaurantvoting.web.restaurant;

import com.example.restaurantvoting.model.Restaurant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "ProfileRestaurantController", description = "Users can view the list of restaurants")
public class ProfileRestaurantController extends AbstractRestaurantController {

    static final String REST_URL = "/api/profile/restaurants";

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get restaurant by Id")
    public ResponseEntity<Restaurant> get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    @Operation(summary = "Get All restaurants")
    public List<Restaurant> getAll() {
        return super.getAll();
    }
}
