package edu.alenkin.topjavagraduation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * Represents food on the restaurant menu.
 */

@Entity
@Table(name = "dish")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(includeFieldNames = false, exclude = "restaurant")
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    private Integer price;

    @Column(name = "date")
    @NotNull
    private LocalDate date = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonBackReference(value = "menu")
    private Restaurant restaurant;

    public Dish(String name, Integer price, LocalDate date, Restaurant restaurant) {
        this(null, name, price, date, restaurant);
    }

    public Dish(Integer id, String name, Integer price, LocalDate date, Restaurant restaurant) {
        super(id, name);
        this.price = price;
        this.date = date;
        this.restaurant = restaurant;
    }
}
