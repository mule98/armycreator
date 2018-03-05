package armybuilder.domain;

import java.util.Optional;

public class ArmyRepository {
	private Army army;

	public Optional<Army> findByName(Name name) {
		if (army.name().equals(name))
			return Optional.of(army);
		else return Optional.empty();
	}

	public void save(Army army) {
		this.army = army;
	}
}
