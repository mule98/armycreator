package armybuilder.domain.events;

import armybuilder.domain.ArmyId;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class EventStore {

    private final static EventStore instance = new EventStore();
    private Map<ArmyId, LinkedList<DomainEvent>> eventsById = new HashMap<ArmyId, LinkedList<DomainEvent>>() {
        @Override
        public LinkedList<DomainEvent> get(Object key) {
            LinkedList<DomainEvent> events = super.get(key);
            if (events == null) {
                events = new LinkedList<>();
                put((ArmyId) key, events);
            }
            return events;
        }
    };

    private EventStore() {
    }

    public Stream<DomainEvent> getAllEvents() {
        return eventsById.values().stream().flatMap(LinkedList::stream);
    }

    public void insertEvent(ArmyId id, DomainEvent event) {
        eventsById.get(id).add(event);
    }

    public static EventStore instance() {
        return instance;
    }

    public Stream<ArmyId> getAllIds() {
        return eventsById.keySet().stream();
    }

    public void insertEvents(ArmyId id, DomainEvent event) {
        eventsById.get(id).add(event);
    }

    public LinkedList<DomainEvent> getAllEvents(ArmyId id) {
        LinkedList<DomainEvent> events = eventsById.get(id);
        if (events == null) return new LinkedList<>();
        else return events;
    }
}
