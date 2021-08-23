package edu.alenkin.topjavagraduation.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@ToString(includeFieldNames = false)
public class Dish extends AbstractNamedEntity {

    @Column(name = "price")
    private Integer price;

    @Column(name = "dish_date")
    @NotNull
    private LocalDate dishDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    protected Dish() {
    }

    public Dish(String name, Integer price, LocalDate dishDate) {
        super(null, name);
        this.price = price;
        this.dishDate = dishDate;
    }

}
