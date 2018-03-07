package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Data
@Immutable
public class ArmyCreated implements DomainEvent {
	private final Name name;

	public ArmyCreated(Name name) {this.name = name;}

	@Override
	public EventType type() {
		return EventType.ARMY;
	}

	@Override
	public void apply(Army army) {
	}
}
