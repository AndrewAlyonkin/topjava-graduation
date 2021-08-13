package edu.alenkin.topjavagraduation.repository;

import edu.alenkin.topjavagraduation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("DELETE FROM User u WHERE u.email=:email")
    User getByEmail(@Param("email") String email);
}
