package com.example.restaurantvoting.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", indexes = @Index(name = "vote_unique_user_per_day_idx", columnList = "user_id, registered_vote", unique = true))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@ToString(callSuper = true, exclude = {"restaurant", "user"})
public class Vote extends BaseEntity {

    @Column(name = "registered_vote", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate registered;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    public Vote(Integer id, LocalDate registered, Restaurant restaurant, User user) {
        super(id);
        this.registered = registered;
        this.restaurant = restaurant;
        this.user = user;
    }
}
