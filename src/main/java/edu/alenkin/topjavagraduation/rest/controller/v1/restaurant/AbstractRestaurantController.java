package edu.alenkin.topjavagraduation.rest.controller.v1.restaurant;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.util.ValidationUtil.assureIdConsistent;
import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNew;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RequiredArgsConstructor
@Slf4j
public abstract class AbstractRestaurantController {

    private final RestaurantService service;

    public Restaurant doCreate(Restaurant restaurant) {
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public Restaurant get(int id) {
        log.info("Get restaurant with id {}", id);
        return service.get(id);
    }

    public List<Restaurant> getAll() {
        log.info("Get all restaurants");
        return service.getAll();
    }

    public List<Restaurant> getAllWithDishes(LocalDate date) {
        log.info("Get all restaurants with menu for {}", date);
        return service.getAllWithMenu(date);
    }

    public Restaurant update(Restaurant restaurant, int id) {
        assureIdConsistent(restaurant, id);
        log.info("Update restaurant with id {}", id);
        return service.update(restaurant);
    }

    public void delete(int id) {
        log.info("Delete restaurant with id {}", id);
        service.delete(id);
    }
}
