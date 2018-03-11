package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public class NameChanged extends DomainEvent {

    public final Name name;

	public NameChanged(ArmyId armyId, Name name) {
		super(armyId);
		this.name = name;
	}

	@Override
    public Army applyChange(Army event) {
        return event.applyChange(this);
    }
}
