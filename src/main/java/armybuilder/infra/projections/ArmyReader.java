package armybuilder.infra.projections;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.ArmyRepository;
import armybuilder.domain.Name;

import java.util.List;

public class ArmyReader {

	private final ArmyRepository armyRepository;

	public ArmyReader(ArmyRepository armyRepository) {this.armyRepository = armyRepository;}

    public List<Army> findByName(Name name) {
		return armyRepository.findByName(name);
	}

	public Army getById(ArmyId armyId) {
		return armyRepository.getById(armyId);
	}
}
