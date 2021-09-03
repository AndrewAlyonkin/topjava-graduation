package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.model.Restaurant;

import java.util.List;

import static edu.alenkin.topjavagraduation.DishTestData.*;
import static edu.alenkin.topjavagraduation.model.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class RestaurantTestData {

    public static final int BISON_ID = START_SEQ + 2;
    public static final int PRESTIGE_ID = START_SEQ + 3;
    public static final int GOLDEN_ID = START_SEQ + 4;

    public static final Restaurant BISON = new Restaurant(BISON_ID, "Bison");

    public static final Restaurant PRESTIGE = new Restaurant(PRESTIGE_ID, "Prestige");

    public static final Restaurant GOLDEN = new Restaurant(GOLDEN_ID, "Golden");

    public static final Restaurant BISON_WITH_MENU_IN_CURR_DAY = new Restaurant(BISON_ID, "Bison");

    public static final Restaurant PRESTIGE_WITH_MENU_IN_CURR_DAY = new Restaurant(PRESTIGE_ID, "Prestige");

    public static final Restaurant GOLDEN_WITH_MENU_IN_CURR_DAY = new Restaurant(GOLDEN_ID, "Golden");

    public static final List<Restaurant> allRestaurants = List.of(BISON, PRESTIGE, GOLDEN);

    public static final List<Restaurant> allRestaurantsWithMenuInCurrDay = List.of(BISON_WITH_MENU_IN_CURR_DAY,
            GOLDEN_WITH_MENU_IN_CURR_DAY, PRESTIGE_WITH_MENU_IN_CURR_DAY);

    //this method for initializing the menu before tests is necessary due to the circular dependency with dish test data
    public static void initRestaurantsMenu() {
        BISON.setMenu(List.of(
                currDateSoupBison,
                currDateSaladBison,
                currDateMeatBison,
                currDateDessertBison,
                soupBison,
                saladBison,
                meatBison,
                dessertBison));

        PRESTIGE.setMenu(List.of(
                currDateSoupPrestige,
                currDateSaladPrestige,
                currDateMeatPrestige,
                currDateDessertPrestige,
                soupPrestige,
                saladPrestige,
                meatPrestige,
                dessertPrestige));

        GOLDEN.setMenu(List.of(
                currDateSoupGolden,
                currDateSaladGolden,
                currDateMeatGolden,
                currDateDessertGolden));

        BISON_WITH_MENU_IN_CURR_DAY.setMenu(List.of(
                currDateSoupBison,
                currDateSaladBison,
                currDateMeatBison,
                currDateDessertBison));

        PRESTIGE_WITH_MENU_IN_CURR_DAY.setMenu(List.of(
                currDateSoupPrestige,
                currDateSaladPrestige,
                currDateMeatPrestige,
                currDateDessertPrestige));

        GOLDEN_WITH_MENU_IN_CURR_DAY.setMenu(List.of(
                currDateSoupGolden,
                currDateSaladGolden,
                currDateMeatGolden,
                currDateDessertGolden));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(GOLDEN_ID, "New Name For Golden", List.of(
                currDateSoupGolden,
                currDateMeatGolden,
                currDateDessertGolden));
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static void assertEquals(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("menu.restaurant").isEqualTo(expected);
    }

    public static void assertNoMenuEquals(Restaurant actual, Restaurant expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("menu").isEqualTo(expected);
    }
}
