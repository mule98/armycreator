package armybuilder;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import armybuilder.domain.command.ArmyService;
import armybuilder.domain.command.CreateArmy;
import armybuilder.domain.command.RenameArmy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyStory {

	Application application;
	private ArmyService armyService;

	@BeforeEach
	public void beforeEach(){
		application = Application.start();
		armyService = application.getService();
	}


	@Test
	public void createArmy() {

		Name new_army = new Name("New army");
		ArmyId armyId = application.getService().createArmy(new CreateArmy(new_army));
		Army army = application.getReader().getById(armyId);
		assertEquals(new_army, army.name());
		assertEquals(armyId, army.id);

	}

	@Test
	public void renameArmy() {

		Name name = new Name("New army");
		ArmyId armyId = application.getService().createArmy(new CreateArmy(name));
		List<Army> army = application.getReader().findByName(name);

		Name name_modified = new Name("name modified");
		application.getService().rename(new RenameArmy(army.get(0).id, name_modified));
		assertEquals(name_modified, army.get(0).name());

	}
}
