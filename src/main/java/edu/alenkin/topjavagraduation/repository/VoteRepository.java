package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.user.id =:userId")
    List<Vote> getAllForUser(@Param("userId") int userId);

    @Query("""
            SELECT v FROM Vote v
            WHERE v.user.id =:userId
            AND v.voteDateTime>=:startDateTime
            AND v.voteDateTime<=:endDateTime
            """)
    Vote getAllForUserBetween(@Param("userId") int userId,
                             @Param("startDateTime") LocalDateTime startDateTime,
                             @Param("endDateTime") LocalDateTime endDateTime);

}
