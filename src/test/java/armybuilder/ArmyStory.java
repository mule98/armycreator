package armybuilder;

import armybuilder.domain.Army;
import armybuilder.domain.Name;
import armybuilder.domain.command.CreateArmy;
import armybuilder.domain.command.CommandInterpreter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArmyStory {

	Application application;
	private CommandInterpreter commandInterpreter;

	@BeforeEach
	public void beforeEach(){
		application = Application.start();
		commandInterpreter = application.getService();
	}

	@Test
	public void createArmy() {

		application.getService().createArmy(new CreateArmy(new Name("New army")));
		Optional<Army> army = application.getReader().findByName("New army");
		assertTrue(army.isPresent());
		assertEquals("New army", army.get().name());

	}

}
