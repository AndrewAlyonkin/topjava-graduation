package edu.alenkin.topjavagraduation.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Entity
@Table(name = "vote")
@Getter
@Setter
@ToString
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "vote_datetime")
    @NotNull
    private LocalDateTime voteDateTime;

    public Vote(){}

    public Vote(User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        super(null);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDateTime = voteDateTime;
    }

    public Vote(Integer id, User user, Restaurant restaurant, LocalDateTime voteDateTime) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDateTime = voteDateTime;
    }

}
