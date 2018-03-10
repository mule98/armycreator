package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Immutable
public final class ArmyCreated extends DomainEvent {
	public final Name name;
	public final ArmyId armyId;

	@Override
	public final Army applyChange(Army army) {
		return army.applyChange(this);
	}
}
