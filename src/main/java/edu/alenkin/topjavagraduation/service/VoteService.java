package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.dto.VoteTo;
import edu.alenkin.topjavagraduation.model.Vote;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface VoteService {

    Vote create(int userId, int restaurantId);

    List<VoteTo> getByUserIdAndDate(int userId, LocalDate date);

    List<VoteTo> getAllByUserId(int userId);

    List<VoteTo> getByRestaurantBetween(LocalDate startDate, LocalDate endDate, int restaurantId);

    List<VoteTo> getByDateAndRestaurantId(LocalDate date, int restaurantId);

    List<VoteTo> getByRestaurantId(int restaurantId);

    List<VoteTo> getAllInDate(LocalDate date);

    List<VoteTo> getAll();

    void setVoteTimeExpiration(LocalTime VOTE_TIME_EXPIRATION);

    LocalTime getVoteTimeExpiration();
}
