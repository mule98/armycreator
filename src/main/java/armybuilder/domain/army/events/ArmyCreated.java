package armybuilder.domain.army.events;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.Name;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public final class ArmyCreated extends Event<ArmyId, Army> {
	public final ArmyId id;
	public final Name name;

	public final Army applyChange(Army army) {
		return army.applyChange(this);
	}

}
