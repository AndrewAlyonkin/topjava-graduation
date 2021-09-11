package edu.alenkin.topjavagraduation.dto;

import edu.alenkin.topjavagraduation.model.HasId;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public abstract class BaseTo implements HasId {
    protected Integer id;
}
