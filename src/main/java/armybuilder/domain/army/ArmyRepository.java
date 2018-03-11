package armybuilder.domain.army;

import armybuilder.domain.events.Event;
import armybuilder.domain.events.EventStore;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ArmyRepository {


	private EventStore eventStore;

	public ArmyRepository(EventStore eventStore) {

		this.eventStore = eventStore;
	}

	public List<Army> findByName(Name name) {
		return eventStore.getAllIds(new ArmyTypeMatcher())
						 .map(this::getById)
						 .filter(t -> t.name() == name)
						 .collect(Collectors.toList());
	}

	public Army getById(ArmyId id) {
		Army army = new Army();
		LinkedList<Event> allEvents = eventStore.getAllEvents(id);
		if (allEvents.isEmpty()) {
			throw new RuntimeException("No army found for " + id);
		}
		for (Event event : allEvents) {
			army = ((Event<ArmyId,Army>) event).apply(army, false);
		}
		return army;
	}
}
