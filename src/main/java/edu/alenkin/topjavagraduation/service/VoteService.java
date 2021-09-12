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

    VoteTo getByUserAndDate(int userId, LocalDate date);

    List<VoteTo> getByDateAndRestaurantId(LocalDate date, int restaurantId);

    List<VoteTo> getAllInDate(LocalDate date);

    void setVoteTimeExpiration(LocalTime VOTE_TIME_EXPIRATION);

    LocalTime getVoteTimeExpiration();
}
