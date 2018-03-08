package armybuilder.domain;

import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.EventStore;
import armybuilder.domain.events.NameChanged;
import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Immutable
public class Army {

    private final Set<Unit> units = new HashSet<>();
    public ArmyId id;
    private Name name;

    public Army(Name name, ArmyId armyId) {
        this(armyId);
        applyChange(new NameChanged(name), true);
    }

    public Army(ArmyId id) {
        this.id = id;
    }

    public void applyChange(NameChanged nameChanged, boolean newEvent) {
        rename(nameChanged.name);
        if (newEvent)
            store(nameChanged);
    }

    private void store(DomainEvent event) {
        EventStore.instance().insertEvent(id, event);
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


}
