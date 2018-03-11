package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public class NameChanged extends DomainEvent<ArmyId, Army> {

	public final ArmyId id;
    public final Name name;

    public Army applyChange(Army event) {
        return event.applyChange(this);
    }
}
