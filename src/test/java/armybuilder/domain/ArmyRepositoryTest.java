package armybuilder.domain;

import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.NameChanged;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmyRepositoryTest {

    ArmyRepository repository = new ArmyRepository();

    @Test
    void findByName() {

        Name name = new Name("toto");
        new ArmyCreated(name, ArmyId.next()).apply(new Army(), true);

        List<Army> result = repository.findByName(name);
        assertEquals(name,
                     result.get(0)
                           .name());
    }

    @Test
    void findByNameDontFind() {

        Army army = new Army().applyChange(new ArmyCreated(new Name("toto"), ArmyId.next()));
        army.applyChange(new NameChanged(new Name("other name"), army.getId()));

        List<Army> result = repository.findByName(new Name("toto"));
        assertTrue(result.isEmpty());
    }
}