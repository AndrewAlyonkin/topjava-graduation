package edu.alenkin.topjavagraduation.service;

import edu.alenkin.topjavagraduation.RestaurantTestData;
import edu.alenkin.topjavagraduation.exception.NotFoundException;
import edu.alenkin.topjavagraduation.model.Restaurant;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static edu.alenkin.topjavagraduation.MatcherUtils.assertMatch;
import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
class RestaurantServiceImplTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @BeforeAll
    static void beforeAll() {
        initRestaurantsMenu();
    }

    @Test
    void create() {
        Restaurant created = service.create(getNew());
        int newId = created.id();
        Restaurant newRestaurant = getNew();
        newRestaurant.setId(newId);
        assertMatch(newRestaurant, created);
    }

    @Test
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    void duplicateNameCreate() {
        assertThrows(DataAccessException.class, () ->
                service.create(new Restaurant(null, "Prestige")));
    }

    @Test
    void get() {
        assertMatch(BISON, service.get(BISON_ID));
    }

    @Test
    void getAll() {
        assertThat(service.getAll()).hasSameElementsAs(allRestaurants);
    }

    @Test
    void getAllWithMenu() {
        assertMatch(service.getAllWithMenu(LocalDate.now()), allRestaurantsWithMenuInCurrDay, RestaurantTestData::assertNoMenuRestaurantMatch);
    }

    @Test
    void update() {
        Restaurant updated = getUpdated();
        service.update(updated);
        assertNoMenuRestaurantMatch(getUpdated(), service.get(GOLDEN_ID));
    }

    @Test
    void delete() {
        service.delete(GOLDEN_ID);
        assertThrows(NotFoundException.class, () -> service.get(GOLDEN_ID));
    }
}
