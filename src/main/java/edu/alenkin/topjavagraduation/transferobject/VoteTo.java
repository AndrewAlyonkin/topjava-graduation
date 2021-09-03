package edu.alenkin.topjavagraduation.transferobject;

import edu.alenkin.topjavagraduation.model.Vote;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class VoteTo extends BaseTo{

    private LocalDateTime dateTime;

    @NotNull
    private Integer restaurantId;

    @NotNull
    private String restaurantName;

    @NotNull
    private String userName;

    public VoteTo(LocalDateTime dateTime, Integer restaurantId, String restaurantName, String userName) {
        this(null, dateTime, restaurantId, restaurantName, userName);
    }

    public VoteTo(Integer id, LocalDateTime dateTime, Integer restaurantId, String restaurantName, String userName) {
        super(id);
        this.dateTime = dateTime;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.userName = userName;
    }
    public VoteTo(Integer id, LocalDateTime dateTime, Integer restaurantId) {
        super(id);
        this.dateTime = dateTime;
        this.restaurantId = restaurantId;
    }
    public VoteTo(LocalDateTime dateTime, Integer restaurantId) {
        super(null);
        this.dateTime = dateTime;
        this.restaurantId = restaurantId;
    }

    public VoteTo(Vote vote) {
        super(vote.getId());
        this.dateTime = (vote.getVoteDateTime());
        this.restaurantId = vote.getRestaurant().getId();
        this.restaurantName = vote.getRestaurant().getName();
        this.userName = vote.getUser().getName();
    }
}
