package armybuilder;

import armybuilder.domain.ArmyRepository;
import armybuilder.domain.command.ArmyService;
import armybuilder.domain.events.DomainEventPublisher;

public class Application {
	private ArmyRepository armyRepository = new ArmyRepository();
	private DomainEventPublisher domainEventPublisher = new DomainEventPublisher();

	private Application(){}

	public static Application start() {
		return new Application();
	}

	public ArmyService getService() {
		return new ArmyService(armyRepository);
	}

	public ArmyRepository getReader() {
		return this.armyRepository;
	}
}
