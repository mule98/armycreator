package armybuilder;

import armybuilder.domain.ArmyRepository;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;
import armybuilder.domain.services.ArmyService;
import armybuilder.infra.projections.ArmyReader;

public class Application {
	private final ArmyRepository armyRepository;
	private final EventStore eventStore;
	private final ArmyReader reader;

	private Application(EventStore eventStore) {
		this.eventStore = eventStore;
		armyRepository = new ArmyRepository(this.eventStore);
		EventBus.instance().subscribe(eventStore);
		reader = new ArmyReader(armyRepository);
	}

	public static Application start(EventStore eventStore) {

		return new Application(eventStore);
	}

	public ArmyService getService() {
		return new ArmyService(armyRepository);
	}

	public ArmyReader getReader() {
		return this.reader;
	}
}
