package armybuilder.domain.command;

import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.EventStore;

public class CommandInterpreter {

	private EventStore eventStore;

	public CommandInterpreter(EventStore eventStore) {
		this.eventStore = eventStore;
	}

	public void createArmy(CreateArmy createArmy) {
		eventStore.insertEvent(new ArmyCreated(createArmy.name));
	}
}
