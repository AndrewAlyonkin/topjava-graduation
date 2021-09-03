package edu.alenkin.topjavagraduation.transferobject;

import edu.alenkin.topjavagraduation.model.HasId;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseTo implements HasId {
    protected Integer id;
}
