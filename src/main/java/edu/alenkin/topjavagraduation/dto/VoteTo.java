package edu.alenkin.topjavagraduation.dto;

import edu.alenkin.topjavagraduation.model.Vote;
import lombok.*;

import java.time.LocalDate;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString
public class VoteTo extends BaseTo {

    private LocalDate voteDate;

    private Integer restaurantId;

    private String restaurantName;

    private String userName;

    public VoteTo(Integer id, LocalDate voteDate, Integer restaurantId, String restaurantName, String userName) {
        super(id);
        this.voteDate = voteDate;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.userName = userName;
    }

    public VoteTo(Vote vote) {
        super(vote.getId());
        this.voteDate = (vote.getVoteDate());
        this.restaurantId = vote.getRestaurant().getId();
        this.restaurantName = vote.getRestaurant().getName();
        this.userName = vote.getUser().getName();
    }
}
