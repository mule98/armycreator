package armybuilder.domain;

import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.EventStore;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArmyRepository {


    public List<Army> findByName(Name name) {
        return eventStore().getAllIds()
                           .map(this::getById)
                           .filter(t -> t.name() == name)
                           .collect(Collectors.toList());
    }

    private EventStore eventStore() {
        return EventStore.instance();
    }

    public Army getById(ArmyId id) {
        Army army = new Army();
        LinkedList<DomainEvent> allEvents = eventStore().getAllEvents(id);
        if (allEvents.isEmpty()) {
            throw new RuntimeException("No army found for " + id);
        }
        for (DomainEvent domainEvent : allEvents) {
            army = domainEvent.apply(army, false);
        }
        return army;
    }
}
