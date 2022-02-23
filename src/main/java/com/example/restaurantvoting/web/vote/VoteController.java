package com.example.restaurantvoting.web.vote;

import com.example.restaurantvoting.model.Vote;
import com.example.restaurantvoting.repository.RestaurantRepository;
import com.example.restaurantvoting.repository.VoteRepository;
import com.example.restaurantvoting.to.RestaurantTo;
import com.example.restaurantvoting.util.RestaurantUtil;
import com.example.restaurantvoting.web.AuthUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static com.example.restaurantvoting.util.validation.ValidationUtil.*;

@RestController
@Slf4j
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class VoteController {

    static final String REST_URL = "/api/votes";

    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    @GetMapping
    public List<Vote> getAll(@RequestParam int restaurantId) {
        log.info("getAll for restaurant id={}", restaurantId);
        return voteRepository.findVotesByRestaurant(restaurantId, LocalDate.now());
    }

    @GetMapping("/by-date/{localDate}")
    public List<Vote> getAllByDate(@RequestParam int restaurantId, @PathVariable ("localDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate localDate) {
        log.info("getAll by date={} for restaurant id={}", localDate, restaurantId);
        return voteRepository.findVotesByRestaurant(restaurantId, localDate);
    }

    @GetMapping("/count")
    public List<RestaurantTo> getAllCount(@RequestParam int restaurantId) {
        log.info("getAll count votes for restaurant id={}", restaurantId);
        return RestaurantUtil.getTos(voteRepository.findVotesByRestaurant(restaurantId, LocalDate.now()));
    }

    @GetMapping("/count/by-date/{localDate}")
    public List<RestaurantTo> getAllCountByDate(@RequestParam int restaurantId, @PathVariable ("localDate") @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate localDate) {
        log.info("getAll count votes by date={} for restaurant id={}", localDate, restaurantId);
        return RestaurantUtil.getTos(voteRepository.findVotesByRestaurant(restaurantId, localDate));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete vote by id={}", id);
        voteRepository.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> get(@AuthenticationPrincipal AuthUser authUser, @PathVariable int id) {
        log.info("get vote with id={} by user id={}", id, authUser.id());
        return ResponseEntity.of(voteRepository.findVoteByUser(id, authUser.id()));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid Vote vote, @PathVariable int id) {
        log.info("update {} with id={}", vote, id);
        assureIdConsistent(vote, id);
        checkTime(LocalTime.now());
        voteRepository.save(vote);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Vote> createWithLocation(@AuthenticationPrincipal AuthUser authUser, @RequestParam int restaurantId) {
        log.info("create vote by user id={} for restaurant id={}", authUser.id(), restaurantId);

        Vote created = voteRepository.save(new Vote(LocalDate.now(), restaurantRepository.getById(restaurantId), authUser.getUser()));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}