package armybuilder.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;


public class Army {
	private final Set<Unit> units = new HashSet<>();
	private final Name name;

	public Army(Name name) {

		this.name = name;
	}

	public void add(Unit unit) {
		units.add(unit);
	}


	public Stream<Unit> units() {
		return units.stream();
	}

	public Name name() {
		return name;
	}
}
