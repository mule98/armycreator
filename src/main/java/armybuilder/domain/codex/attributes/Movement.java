package armybuilder.domain.codex.attributes;

import lombok.Data;

@Data
public class Movement extends EntryAttribute {
    //TODO: manage inconsitent movment
    private final int movement;
}
