package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface RestaurantService {

    Restaurant create(@NotNull Restaurant restaurant);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    Restaurant get(int id);

    Restaurant update(@NotNull Restaurant restaurant);

    List<Restaurant> getAll();

    void delete(int id);

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    List<Restaurant> getAllWithMenu(LocalDate date);

}
