package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.entity.Vote;
import edu.alenkin.topjavagraduation.transferobject.VoteTo;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

    Map<String, Integer> getRatingInDateGroupByRestaurantName(LocalDate date);
    Map<String, Integer> getRatingsGroupByRestaurantName();

    List<VoteTo> getAllInDate(LocalDate date);
    List<VoteTo> getAll();

    Map<Restaurant, List<VoteTo>> getAllInDateGroupByRestaurant(LocalDate dateTime);
    Map<Restaurant, List<VoteTo>> getAllGroupByRestaurant();}
