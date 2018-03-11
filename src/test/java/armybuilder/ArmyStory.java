package armybuilder;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import armybuilder.domain.command.CreateArmy;
import armybuilder.domain.command.RenameArmy;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArmyStory {

	private Application application;

	@BeforeEach
	public void beforeEach() {
		application = Application.start(new EventStore());
	}

	@Test
	public void createArmyTest() {

		Name name = new Name("New army");
		ArmyId armyId = createArmy(name);


		Army army
				= application.getArmyReader().getById(armyId);
		assertEquals(name, army.name());

	}

	private ArmyId createArmy(Name new_army) {
		return application.getArmyService().createArmy(new CreateArmy(new_army));
	}

	@Test
	public void renameArmy() {

		Name name = new Name("New army");
		ArmyId armyId = createArmy(name);

		Name name_modified = new Name("name modified");
		renameArmy(armyId, name_modified);
		List<Army> armies = application.getArmyReader()
									   .findByName(name_modified);
		assertEquals(name_modified,
				armies.get(0)
					  .name());

	}

	private void renameArmy(ArmyId armyId, Name name_modified) {
		application.getArmyService()
				   .rename(new RenameArmy(armyId, name_modified));
	}

	@Test
	public Army createArmy() {

		Name new_army = new Name("New army");
		ArmyId armyId = createArmy(new_army);
		Army army = getArmy(armyId);
		assertEquals(new_army, army.name());
		assertEquals(armyId, army.getId());

		return army;
	}

	private Army getArmy(ArmyId armyId) {
		return application.getArmyReader().getById(armyId);
	}
}
