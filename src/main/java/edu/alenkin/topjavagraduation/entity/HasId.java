package edu.alenkin.topjavagraduation.entity;

import org.springframework.util.Assert;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */

public interface HasId {
    Integer getId();

    void setId(Integer id);

    default boolean isNew() {
        return getId() == null;
    }

    default int id() {
        Assert.notNull(getId(), "Entity must has id");
        return getId();
    }
}
