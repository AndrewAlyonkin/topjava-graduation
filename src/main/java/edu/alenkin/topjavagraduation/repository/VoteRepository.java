package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.model.Vote;
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
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("""
            SELECT v FROM Vote v
            WHERE v.user.id =:userId AND v.voteDate=:voteDate
            """)
    Optional<Vote> getForUserInDate(@Param("userId") int userId, @Param("voteDate")LocalDate voteDate);

    @Query("""
            SELECT v FROM Vote v
            WHERE v.user.id =:userId
            """)
    List<Vote> getAllForUser(@Param("userId") int userId);

    @Query("""
            SELECT v FROM Vote v
            WHERE v.restaurant.id=:restaurantId
            AND v.voteDate >=:startDate
            AND v.voteDate <=:endDate
            """)
    List<Vote> getAllForRestaurantInBetween(@Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate,
                                            @Param("restaurantId") int restaurantId);

    @Query("""
            SELECT v FROM Vote v
            WHERE v.restaurant.id=:restaurantId
            AND v.voteDate =:voteDate
            """)
    List<Vote> getAllForRestaurantInDate(@Param("voteDate") LocalDate voteDate,
                                            @Param("restaurantId") int restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.restaurant.id=:restaurantId")
    List<Vote> getAllForRestaurant(@Param("restaurantId") int restaurantId);


    @Query("SELECT v FROM Vote v WHERE v.voteDate =:voteDate")
    List<Vote> getAllInDate(@Param("voteDate") LocalDate voteDate);
}
