package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.entity.Dish;
import edu.alenkin.topjavagraduation.exception.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static edu.alenkin.topjavagraduation.DishTestData.getNew;
import static edu.alenkin.topjavagraduation.DishTestData.getUpdated;
import static edu.alenkin.topjavagraduation.DishTestData.*;
import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.entity.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class DishServiceImplTest extends AbstractServiceTest {

    @Autowired
    private DishService service;

    @Test
    void create() {
        Dish created = service.create(getNew(), GOLDEN_ID);
        int newId = created.id();
        Dish newDish = getNew();
        newDish.setId(newId);
        assertEquals(newDish, created);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void createDuplicated() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Dish(null, "Soup Bison", 100, LocalDate.parse("2021-08-18"), BISON), BISON_ID));
    }

    @Test
    void get() {
        assertEquals(currDateSoupBison, service.get(START_SEQ + 5, BISON_ID));
    }

    @Test
    void getAllForRestaurant() {
        assertThat(service.getAllForRestaurant(GOLDEN_ID)).hasSameElementsAs(goldenMenu);
    }

    @Test
    void update() {
        Dish updated = getUpdated();
        service.update(updated, BISON_ID);
        assertEquals(getUpdated(), service.get(START_SEQ + 5, BISON_ID));
    }

    @Test
    void delete() {
        service.delete(START_SEQ + 5, BISON_ID);
        assertThrows(NotFoundException.class, () -> service.get(START_SEQ + 5, BISON_ID));
    }
}
