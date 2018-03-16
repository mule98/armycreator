package armybuilder;

import armybuilder.domain.army.*;
import armybuilder.domain.army.command.CreateArmy;
import armybuilder.domain.army.command.RenameArmy;
import armybuilder.domain.army.services.ArmyService;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArmyStory {

    private ArmyService armyService;
    private ArmyReader armyReader;

    @BeforeEach
    void beforeEach() {
        EventStore store = new EventStore();
        EventBus.instance()
                .subscribe(store);
        ArmyRepository repo = new ArmyRepository(store);
        armyReader = new ArmyReader(repo);
        armyService = new ArmyService(repo);
    }

    @Test
    void createArmyTest() {

        Name name = new Name("New army");
        ArmyId armyId = createArmy(name);


        Army army
                = armyReader.getById(armyId);
        assertEquals(name, army.name());

    }

    private ArmyId createArmy(Name new_army) {
        return armyService
                .createArmy(new CreateArmy(new_army));
    }

    @Test
    void renameArmy() {

        Name name = new Name("New army");
        ArmyId armyId = createArmy(name);

        Name name_modified = new Name("name modified");
        renameArmy(armyId, name_modified);
        List<Army> armies = armyReader
                .findByName(name_modified);
        assertEquals(name_modified,
                     armies.get(0)
                           .name());

    }

    private void renameArmy(ArmyId armyId, Name name_modified) {
        armyService
                .rename(new RenameArmy(armyId, name_modified));
    }

    @Test
    Army createArmy() {

        Name new_army = new Name("New army");
        ArmyId armyId = createArmy(new_army);
        Army army = getArmy(armyId);
        assertEquals(new_army, army.name());
        assertEquals(armyId, army.getId());

        return army;
    }

    private Army getArmy(ArmyId armyId) {
        return armyReader.getById(armyId);
    }
}
