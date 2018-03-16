package armybuilder.domain.events;

import armybuilder.domain.common.Id;
import armybuilder.domain.common.IdTypeMatcher;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

@Repository
public class EventStore implements EventListener {

    private final static EventStore instance = new EventStore();
    private Map<Id, LinkedList<Event>> eventsById = new HashMap<Id, LinkedList<Event>>() {
        @Override
        public LinkedList<Event> get(Object key) {
            LinkedList<Event> events = super.get(key);
            if (events == null) {
                events = new LinkedList<>();
                put((Id) key, events);
            }
            return events;
        }
    };

    public EventStore() {
    }

    static EventStore instance() {
        return instance;
    }

    Stream<Event> getAllEvents() {
        return eventsById.values()
                         .stream()
                         .flatMap(LinkedList::stream);
    }

    public <T extends Id> Stream<T> getAllIds(IdTypeMatcher<T> idTypeMatcher) {
        return eventsById.keySet()
                         .stream()
                         .filter(idTypeMatcher::matchesType)
                         .map(idTypeMatcher::transform);
    }

    public LinkedList<Event> getAllEvents(Id id) {
        LinkedList<Event> events = eventsById.get(id);
        if (events == null) return new LinkedList<>();
        else return events;
    }

    @Override
    public void propagate(Event event) {
        insertEvent(event.getId(), event);
    }

    void insertEvent(Id id, Event event) {
        eventsById.get(id)
                  .add(event);
    }
}
