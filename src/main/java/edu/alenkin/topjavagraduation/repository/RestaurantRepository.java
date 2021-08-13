package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
}
