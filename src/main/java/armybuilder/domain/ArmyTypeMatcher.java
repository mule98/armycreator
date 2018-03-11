package armybuilder.domain;

import armybuilder.domain.events.Id;
import armybuilder.domain.events.IdTypeMatcher;

public class ArmyTypeMatcher implements IdTypeMatcher<ArmyId> {
	@Override
	public boolean matchesType(Id id) {
		return id instanceof ArmyId;
	}

	@Override
	public ArmyId transform(Id id) {
		return (ArmyId) id;
	}
}
