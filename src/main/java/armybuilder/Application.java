package armybuilder;

import armybuilder.domain.ArmyRepository;
import armybuilder.domain.command.ArmyService;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;

public class Application {
	private ArmyRepository armyRepository;
	private EventStore eventStore;

	private Application(EventStore eventStore) {
		this.eventStore = eventStore;
		armyRepository = new ArmyRepository(this.eventStore);
		EventBus.instance().subscribe(eventStore);
	}

	public static Application start(EventStore eventStore) {

		return new Application(eventStore);
	}

	public ArmyService getService() {
		return new ArmyService(armyRepository);
	}

	public ArmyRepository getReader() {
		return this.armyRepository;
	}
}
