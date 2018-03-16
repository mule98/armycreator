package armybuilder.domain;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.ArmyRepository;
import armybuilder.domain.army.Name;
import armybuilder.domain.army.events.ArmyCreated;
import armybuilder.domain.army.events.NameChanged;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmyRepositoryTest {

	private final static EventStore eventStore = new EventStore();
	private final static ArmyRepository repository = new ArmyRepository(eventStore);

	@BeforeAll
    static void beforeAll() {
		EventBus.instance().subscribe(eventStore);
	}
	@Test
	void findByName() {

		Name name = new Name("toto");
		new ArmyCreated(ArmyId.next(), name).apply(new Army(), true);

		List<Army> result = repository.findByName(name);
		assertEquals(name, result.get(0).name());
	}

	@Test
	void findByNameDontFind() {

		Army army = new Army().applyChange(new ArmyCreated(ArmyId.next(), new Name("toto")));
		army.applyChange(new NameChanged(army.getId(), new Name("other name")));

		List<Army> result = repository.findByName(new Name("toto"));
		assertTrue(result.isEmpty());
	}
}