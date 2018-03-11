package armybuilder.domain.events;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Stream;

public class EventStore implements DomainEventListener {

	private final static EventStore instance = new EventStore();
	private Map<Id, LinkedList<DomainEvent>> eventsById = new HashMap<Id, LinkedList<DomainEvent>>() {
		@Override
		public LinkedList<DomainEvent> get(Object key) {
			LinkedList<DomainEvent> events = super.get(key);
			if (events == null) {
				events = new LinkedList<>();
				put((Id) key, events);
			}
			return events;
		}
	};

	public EventStore() {
	}

	public static EventStore instance() {
		return instance;
	}

	public Stream<DomainEvent> getAllEvents() {
		return eventsById.values().stream().flatMap(LinkedList::stream);
	}

	public <T> Stream<T> getAllIds(IdTypeMatcher<T> idTypeMatcher) {
		return eventsById.keySet().stream().filter(idTypeMatcher::matchesType).map(idTypeMatcher::transform);
	}

	public LinkedList<DomainEvent> getAllEvents(Id id) {
		LinkedList<DomainEvent> events = eventsById.get(id);
		if (events == null) return new LinkedList<>();
		else return events;
	}

	@Override
	public void propagate(DomainEvent event) {
		insertEvent(event.getId(), event);
	}

	public void insertEvent(Id id, DomainEvent event) {
		eventsById.get(id).add(event);
	}
}
