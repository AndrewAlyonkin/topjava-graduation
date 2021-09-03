package edu.alenkin.topjavagraduation.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 * <p>
 * The restaurant contains a menu that is filled in by the administrator and is updated every day.
 */

@ApiModel(value = "The restaurant contains a menu that is filled in by the administrator and is updated every day")
@Entity
@Table(name = "restaurant")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Restaurant extends AbstractNamedEntity implements HasId {

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    @Fetch(value = FetchMode.SUBSELECT)
    @JsonManagedReference(value = "menu")
    private List<Dish> menu;

    public Restaurant(String name) {
        this(null, name);
    }

    public Restaurant(Integer id, String name) {
        this(id, name, null);
    }

    public Restaurant(Integer id, String name, List<Dish> menu) {
        super(id, name);
        this.menu = menu;
    }
}
