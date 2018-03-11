package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public final class ArmyCreated extends DomainEvent<ArmyId, Army> {
	public final ArmyId id;
	public final Name name;

	public final Army applyChange(Army army) {
		return army.applyChange(this);
	}

}
