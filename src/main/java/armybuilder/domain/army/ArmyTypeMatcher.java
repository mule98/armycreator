package armybuilder.domain.army;

import armybuilder.domain.common.Id;
import armybuilder.domain.common.IdTypeMatcher;

class ArmyTypeMatcher implements IdTypeMatcher<ArmyId> {
	@Override
    public boolean matchesType(Id id) {
		return id instanceof ArmyId;
	}

	@Override
    public ArmyId transform(Id id) {
		return (ArmyId) id;
	}
}
