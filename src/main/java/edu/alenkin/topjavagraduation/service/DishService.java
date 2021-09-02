package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Dish;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface DishService {
    Dish create(@NotNull Dish dish, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAllForRestaurant(int restaurantId);

    Dish update(@NotNull Dish dish, int restaurantId);

    void delete(int id, int restaurantId);
}
