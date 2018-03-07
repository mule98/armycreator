package armybuilder.domain;

import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.NameChanged;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Stream;

@Immutable
public class Army {

	private final LinkedList<DomainEvent> events = new LinkedList<>();

	private final Set<Unit> units = new HashSet<>();
	public ArmyId id;
	private Name name;

	public Army(Name name, ArmyId armyId) {
		this(armyId);
		applyChange(new NameChanged(name));
	}

	public Army(ArmyId id) {
		this.id = id;
	}

	public void applyChange(NameChanged nameChanged) {
		rename(nameChanged.name);
		store(nameChanged);
	}

	private void store(DomainEvent nameChanged) {
		events.add(nameChanged);
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

	private void rename(Name name) {
		this.name = name;
	}

	public Stream<DomainEvent> events() {
		return events.stream();
	}

}
