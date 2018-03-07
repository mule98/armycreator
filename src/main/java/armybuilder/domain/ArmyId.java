package armybuilder.domain;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.UUID;

@Immutable
public class ArmyId {
    public final UUID id;


    private ArmyId(UUID id) {
        this.id = id;
    }

    public static ArmyId next() {
        return new ArmyId(UUID.randomUUID());
    }
}
