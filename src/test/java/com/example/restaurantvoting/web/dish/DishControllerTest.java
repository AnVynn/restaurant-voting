package com.example.restaurantvoting.web.dish;

import com.example.restaurantvoting.model.Dish;
import com.example.restaurantvoting.repository.DishRepository;
import com.example.restaurantvoting.util.JsonUtil;
import com.example.restaurantvoting.web.AbstractControllerTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static com.example.restaurantvoting.web.dish.DishTestData.*;
import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.RESTAURANT_ID;
import static com.example.restaurantvoting.web.user.UserTestData.ADMIN_MAIL;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WithUserDetails(value = ADMIN_MAIL)
class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = "/api/admin/restaurant/" + RESTAURANT_ID + "/dishes/";

    @Autowired
    private DishRepository dishRepository;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + DISH_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dish1));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + DISH_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertFalse(dishRepository.get(DISH_ID, RESTAURANT_ID).isPresent());
    }

    @Test
    void getByCurrentDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishRepository.findDishesByDate(RESTAURANT_ID, LocalDate.now())));
    }

    @Test
    void getByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "by-date/2021-11-20"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(DISH_MATCHER.contentJson(dishRepository.findDishesByDate(RESTAURANT_ID, dish14.getMenuPerDay())));
    }

    @Test
    void update() throws Exception {
        Dish updated = getUpdate();
        perform(MockMvcRequestBuilders.put(REST_URL + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        DISH_MATCHER.assertMatch(dishRepository.getById(DISH_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        Dish newDish = getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newDish)));

        Dish created = DISH_MATCHER.readFromJson(action);
        int newId = created.id();
        newDish.setId(newId);
        DISH_MATCHER.assertMatch(newDish, created);
        DISH_MATCHER.assertMatch(dishRepository.getById(newId), newDish);
    }

    @Test
    void createInvalid() throws Exception {
        Dish invalid = new Dish(null, "New Dish", 34, null);
        perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void updateInvalid() throws Exception {
        Dish invalid = new Dish(DISH_ID, null, 87, null);
        perform(MockMvcRequestBuilders.put(REST_URL + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(invalid)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}