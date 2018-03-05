package armybuilder;

import armybuilder.domain.ArmyRepository;
import armybuilder.domain.events.EventStore;
import armybuilder.infra.projections.ArmyReader;
import armybuilder.domain.command.CommandInterpreter;

public class Application {
	private ArmyRepository armyRepository = new ArmyRepository();
	private EventStore eventSource = new EventStore();

	private Application(){}

	public static Application start() {
		return new Application();
	}

	public CommandInterpreter getService() {
		return new CommandInterpreter(eventSource);
	}

	public ArmyReader getReader() {
		return new ArmyReader(this.armyRepository);
	}
}
