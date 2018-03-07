package armybuilder.domain.command;

import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class RenameArmy {
    public final ArmyId id;
    public final Name name;

    public RenameArmy(ArmyId armyId, Name name) {
        id = armyId;
        this.name = name;
    }
}
