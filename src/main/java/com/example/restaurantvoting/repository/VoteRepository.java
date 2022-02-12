package com.example.restaurantvoting.repository;

import com.example.restaurantvoting.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

    @Query("SELECT v FROM Vote v JOIN FETCH v.restaurant r WHERE r.id=:restaurantId AND v.registered=:localDate")
    List<Vote> findVotesByRestaurant(int restaurantId, LocalDate localDate);

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u WHERE v.id=:id AND u.id=:userId")
    Optional<Vote> findVoteByUser(int id, int userId);
}
