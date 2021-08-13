package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface RestaurantService {

    Restaurant create(Restaurant restaurant);

    void delete(int id);

    Restaurant get(int id);

    List<Restaurant> getAllWithRatingByDate(LocalDate date);

    void update(Restaurant restaurant);

    void enable(int id, boolean enabled);
}
