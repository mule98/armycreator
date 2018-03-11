package armybuilder.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Immutable
@EqualsAndHashCode
public class ArmyId {
    private final UUID id;


    private ArmyId(UUID id) {
        this.id = id;
    }

    public static ArmyId next() {
        return new ArmyId(UUID.randomUUID());
    }
}
