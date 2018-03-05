package armybuilder.infra.projections;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyRepository;
import armybuilder.domain.Name;

import java.util.Optional;

public class ArmyReader {

	private final ArmyRepository armyRepository;

	public ArmyReader(ArmyRepository armyRepository) {this.armyRepository = armyRepository;}

	public Optional<Army> findByName(String name) {
		return armyRepository.findByName(new Name(name));
	}
}
