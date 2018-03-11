package armybuilder.domain.army.events;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.Name;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public class NameChanged extends Event<ArmyId, Army> {

	public final ArmyId id;
    public final Name name;

    public Army applyChange(Army event) {
        return event.applyChange(this);
    }
}
