package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.web.MatcherFactory;

import java.time.LocalDate;

import static com.example.restaurantvoting.web.restaurant.RestaurantTestData.*;
import static com.example.restaurantvoting.web.user.UserTestData.*;

public class VoteTestData {

    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class, "restaurant", "user");

    public static final int VOTE_ID = 1;

    public static final Vote vote1 = new Vote(VOTE_ID, LocalDate.now(), res4, user);
    public static final Vote vote2 = new Vote(VOTE_ID + 1, LocalDate.now(), res5, admin);
    public static final Vote vote3 = new Vote(VOTE_ID + 2, LocalDate.of(2021, 11, 20), res1, user);
    public static final Vote vote4 = new Vote(VOTE_ID + 3, LocalDate.of(2021, 11, 20), res1, admin);

    public static Vote getNew() {
        return new Vote(null, LocalDate.now(), res1, user2);
    }

    public static Vote getUpdate() {
        return new Vote(VOTE_ID, LocalDate.now(), res2, user);
    }
}
