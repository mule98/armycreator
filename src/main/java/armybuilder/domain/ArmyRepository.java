package armybuilder.domain;

import armybuilder.domain.events.EventStore;

import java.util.List;
import java.util.stream.Collectors;

public class ArmyRepository {


    public List<Army> findByName(Name name) {
        return eventStore().getAllIds().map(this::getById).filter(t -> t.name() == name).collect(Collectors.toList());
    }

    public void save(Army army) {
        army.events().forEach(t -> eventStore().insertEvent(army.id, t));
    }

    private EventStore eventStore() {
        return EventStore.instance();
    }

    public Army getById(ArmyId id) {
        Army army = new Army(id);
        eventStore().getAllEvents(id).forEach(t -> t.apply(army));
        return army;
    }
}
