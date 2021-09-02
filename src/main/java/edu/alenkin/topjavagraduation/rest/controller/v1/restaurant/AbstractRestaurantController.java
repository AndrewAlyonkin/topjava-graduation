package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.service.RestaurantService;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.util.ValidationUtil.assureIdConsistent;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNew;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RequiredArgsConstructor
public class AbstractRestaurantController {

    private final RestaurantService service;

    public Restaurant doCreate(Restaurant restaurant) {
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public Restaurant getById(int id) {
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        return service.getAll();
    }

    public List<Restaurant> gwtAllWithDishes(LocalDate date) {
        return service.getAllWithMenu(date);
    }

    public Restaurant update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        return service.update(restaurant);
    }

    public void delete(int id) {
        service.delete(id);
    }
}
