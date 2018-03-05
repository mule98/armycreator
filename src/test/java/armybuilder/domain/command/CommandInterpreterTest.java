package armybuilder.domain.command;

import armybuilder.domain.Name;
import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommandInterpreterTest {

	private EventStore eventSource = mock(EventStore.class);
	CommandInterpreter commandInterpreter = new CommandInterpreter(eventSource);

	@Test
	public void testCreateArmy(){
		CreateArmy createArmy = new CreateArmy(new Name("toto"));
		commandInterpreter.createArmy(createArmy);
		ArmyCreated armyCreated = new ArmyCreated(new Name("toto"));
		verify(eventSource).insertEvent(eq(armyCreated));
	}

}