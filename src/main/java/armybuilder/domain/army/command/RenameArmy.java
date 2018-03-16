package armybuilder.domain.army.command;

import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public class RenameArmy {
    final ArmyId id;
    final Name name;

}
