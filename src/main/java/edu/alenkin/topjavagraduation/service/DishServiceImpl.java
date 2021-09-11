package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.exception.NotFoundException;
import edu.alenkin.topjavagraduation.model.Dish;
import edu.alenkin.topjavagraduation.model.Restaurant;
import edu.alenkin.topjavagraduation.repository.DishRepository;
import edu.alenkin.topjavagraduation.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.util.ValidationUtil.checkNotFoundWithId;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@Transactional
@RequiredArgsConstructor
public class DishServiceImpl implements DishService {

    private final DishRepository dishRepo;
    private final RestaurantRepository restRepo;

    @Override
    public Dish create(@NotNull Dish dish, int restaurantId) {
        Restaurant restaurant = restRepo
                .findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant not found"));

        dish.setRestaurant(restaurant);
        if (dish.getDate() == null) {
            dish.setDate(LocalDate.now());
        }
        return dishRepo.save(dish);
    }

    @Override
    public Dish get(int id, int restId) {
        return dishRepo.getByIdAndRestaurantId(id, restId)
                .orElseThrow(() -> new NotFoundException("Not found dish with id " + id));
    }

    @Override
    public List<Dish> getAllForRestaurant(int restaurantId) {
        return dishRepo.getAllByRestaurantId(restaurantId);
    }

    @Override
    public Dish update(@NotNull Dish dish, int restaurantId) {
        dish.setRestaurant(restRepo.getById(restaurantId));
        checkNotFoundWithId(dishRepo.save(dish), dish.getId());
        return dish;
    }

    @Override
    public void delete(int dishId, int restId) {
        dishRepo.delete(dishId, restId);
    }
}
