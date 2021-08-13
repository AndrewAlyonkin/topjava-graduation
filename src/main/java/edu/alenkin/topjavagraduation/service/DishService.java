package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface DishService {
    Dish create(Dish dish, int restaurantId);

    Dish update(Dish dish, int restaurantId);

    void delete(int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll(int restaurantId);

    List<Dish> getByDate(LocalDate date, int restaurantId);

}
