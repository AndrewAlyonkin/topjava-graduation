package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.dishDate =:date AND d.restaurant.id =:restaurantId")
    List<Dish> getAllForDate(@Param("restaurantId")Integer restaurant_id, @Param("date") LocalDate localDate);
}
