package armybuilder.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class ArmyRepositoryTest {

	ArmyRepository repository = new ArmyRepository();

	@Test
	void findByName() {

		Name name = new Name("toto");
		Army army = new Army(name);
		repository.save(army);

		Optional<Army> result = repository.findByName(name);
		assertTrue(result.isPresent());
		assertEquals(name, result.get().name());
	}

	@Test
	void findByNameDontFind() {

		Name name = new Name("toto");
		Army army = new Army(new Name("false"));
		repository.save(army);

		Optional<Army> result = repository.findByName(name);
		assertFalse(result.isPresent());
	}
}