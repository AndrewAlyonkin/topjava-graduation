package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import edu.alenkin.topjavagraduation.exception.NotFoundException;
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
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository repository;

    @Override
    public Restaurant create(@NotNull Restaurant restaurant) {
        return repository.save(restaurant);
    }


    @Override
    public Restaurant get(int id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Restaurant is not exists " + id));
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Restaurant> getAllWithMenu(LocalDate date) {
        return repository.getAllWithMenuInDate(date);
    }

    @Override
    public Restaurant update(Restaurant restaurant) {
        int restaurantId = restaurant.getId();
        repository
                .findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Restaurant is not exists " + restaurantId));
        return checkNotFoundWithId(repository.save(restaurant), restaurantId);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }
}
