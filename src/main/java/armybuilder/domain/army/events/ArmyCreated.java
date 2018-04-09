package armybuilder.domain.army.events;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.Name;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Immutable
@Data
@EqualsAndHashCode(callSuper = true)
public final class ArmyCreated extends Event<ArmyId, Army> {
    final ArmyId id;
    final Name name;

    protected final Army applyChange(Army army) {
		return army.applyChange(this);
	}

}
