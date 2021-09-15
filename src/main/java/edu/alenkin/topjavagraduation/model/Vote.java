package edu.alenkin.topjavagraduation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents the vote given for a specific restaurant.
 * Users can vote on which restaurant they want to have lunch at.
 * Only one vote counted per user.
 */

@Entity
@Table(name = "vote")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    @Column(name = "vote_date")
    @NotNull
    private LocalDate voteDate;

    public Vote(Integer id, User user, Restaurant restaurant, LocalDate voteDate) {
        super(id);
        this.user = user;
        this.restaurant = restaurant;
        this.voteDate = voteDate;
    }

    public Vote(User user, Restaurant restaurant, LocalDate voteDate) {
        this(null, user, restaurant, voteDate);
    }
}
