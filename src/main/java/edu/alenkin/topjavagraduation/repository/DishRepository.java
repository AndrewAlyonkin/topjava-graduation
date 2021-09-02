package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("SELECT d FROM Dish d JOIN FETCH d.restaurant WHERE d.date =:date AND d.restaurant.id =:restaurantId")
    List<Dish> getAllForDate(@Param("restaurantId")Integer restaurant_id, @Param("date") LocalDate localDate);

    @Modifying
    @Transactional
    @Query("DELETE FROM Dish d WHERE d.id=:id AND d.restaurant.id =:restaurantId")
    int delete(@Param("id")int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.restaurant.id=:restaurantId")
    List<Dish> getAllByRestaurantId(@Param("restaurantId") int restaurantId);

    @Query("SELECT d FROM Dish d WHERE d.id=:id AND d.restaurant.id =:restaurantId")
    Optional<Dish> getByIdAndRestaurantId(int id,int restaurantId);
}
