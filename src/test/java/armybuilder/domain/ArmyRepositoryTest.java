package armybuilder.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmyRepositoryTest {

    ArmyRepository repository = new ArmyRepository();

    @Test
    void findByName() {

        Name name = new Name("toto");
        new Army(name, ArmyId.next());

        List<Army> result = repository.findByName(name);
        assertEquals(name, result.get(0).name());
    }

    @Test
    void findByNameDontFind() {

        Name name = new Name("toto");
        new Army(new Name("false"), ArmyId.next());

        List<Army> result = repository.findByName(name);
        assertTrue(result.isEmpty());
    }
}