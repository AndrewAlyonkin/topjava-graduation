package edu.alenkin.topjavagraduation;

import edu.alenkin.topjavagraduation.entity.Dish;
import edu.alenkin.topjavagraduation.entity.Restaurant;

import java.time.LocalDate;
import java.util.List;

import static edu.alenkin.topjavagraduation.RestaurantTestData.*;
import static edu.alenkin.topjavagraduation.RestaurantTestData.BISON;
import static edu.alenkin.topjavagraduation.entity.AbstractBaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public class DishTestData {
    public static Dish currDateSoupBison = new Dish(START_SEQ + 5, "Current date soup Bison", 500, LocalDate.now(), BISON);
    public static Dish currDateSoupPrestige = new Dish(START_SEQ + 6, "Current date soup Prestige", 600, LocalDate.now(), PRESTIGE);
    public static Dish currDateSoupGolden = new Dish(START_SEQ + 7, "Current date soup Golden", 700, LocalDate.now(), GOLDEN);

    public static Dish currDateSaladBison = new Dish(START_SEQ + 8, "Current date salad Bison", 500, LocalDate.now(), BISON);
    public static Dish currDateSaladPrestige = new Dish(START_SEQ + 9, "Current date salad Prestige", 600, LocalDate.now(), PRESTIGE);
    public static Dish currDateSaladGolden = new Dish(START_SEQ + 10, "Current date salad Golden", 700, LocalDate.now(), GOLDEN);

    public static Dish currDateMeatBison = new Dish(START_SEQ + 11, "Current date meat Bison", 500, LocalDate.now(), BISON);
    public static Dish currDateMeatPrestige = new Dish(START_SEQ + 12, "Current date meat Prestige", 600, LocalDate.now(), PRESTIGE);
    public static Dish currDateMeatGolden = new Dish(START_SEQ + 13, "Current date meat Golden", 700, LocalDate.now(), GOLDEN);

    public static Dish currDateDessertBison = new Dish(START_SEQ + 14, "Current date dessert Bison", 500, LocalDate.now(), BISON);
    public static Dish currDateDessertPrestige = new Dish(START_SEQ + 15, "Current date dessert Prestige", 600, LocalDate.now(), PRESTIGE);
    public static Dish currDateDessertGolden = new Dish(START_SEQ + 16, "Current date dessert Golden", 700, LocalDate.now(), GOLDEN);


    public static Dish soupBison = new Dish(START_SEQ + 17, "Soup Bison", 100, LocalDate.parse("2021-08-18"), BISON);
    public static Dish soupPrestige = new Dish(START_SEQ + 18, "Soup Prestige", 200, LocalDate.parse("2021-08-18"), PRESTIGE);
    public static Dish soupGolden = new Dish(START_SEQ + 19, "Soup Golden", 300, LocalDate.parse("2021-08-19"), GOLDEN);

    public static Dish saladBison = new Dish(START_SEQ + 20, "Salad Bison", 100, LocalDate.parse("2021-08-18"), BISON);
    public static Dish saladPrestige = new Dish(START_SEQ + 21, "Salad Prestige", 200, LocalDate.parse("2021-08-18"), PRESTIGE);
    public static Dish saladGolden = new Dish(START_SEQ + 22, "Salad Golden", 300, LocalDate.parse("2021-08-19"), GOLDEN);

    public static Dish meatBison = new Dish(START_SEQ + 23, "Meat Bison", 100, LocalDate.parse("2021-08-18"), BISON);
    public static Dish meatPrestige = new Dish(START_SEQ + 24, "Meat Prestige", 200, LocalDate.parse("2021-08-18"), PRESTIGE);
    public static Dish meatGolden = new Dish(START_SEQ + 25, "Meat Golden", 300, LocalDate.parse("2021-08-19"), GOLDEN);

    public static Dish dessertBison = new Dish(START_SEQ + 26, "Dessert Bison", 100, LocalDate.parse("2021-08-18"), BISON);
    public static Dish dessertPrestige = new Dish(START_SEQ + 27, "Dessert Prestige", 200, LocalDate.parse("2021-08-19"), PRESTIGE);
    public static Dish dessertGolden = new Dish(START_SEQ + 28, "Dessert Golden", 300, LocalDate.parse("2021-08-18"), GOLDEN);

    public static List<Dish> goldenMenu = List.of(currDateSoupGolden, currDateSaladGolden, currDateMeatGolden, currDateDessertGolden,
            soupGolden, saladGolden, meatGolden, dessertGolden);

    public static void assertNoRestaurantEquals(Dish actual, Dish expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("restaurant").isEqualTo(expected);
    }

    public static Dish getNew() {
        return new Dish( "New Dish", 999, LocalDate.now(), null);
    }

    public static Dish getUpdated() {
        return new Dish(START_SEQ + 5, "Updated Bison", 666, LocalDate.now(), BISON);
    }
}
