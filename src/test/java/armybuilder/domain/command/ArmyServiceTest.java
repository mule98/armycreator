package armybuilder.domain.command;

import armybuilder.domain.ArmyId;
import armybuilder.domain.ArmyRepository;
import armybuilder.domain.Name;
import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.EventStore;
import armybuilder.domain.events.NameChanged;
import org.junit.jupiter.api.Test;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ArmyServiceTest {

    private EventStore eventSource = mock(EventStore.class);
    private ArmyRepository armyRepository = new ArmyRepository();
    private ArmyService armyService = new ArmyService(armyRepository);

    @Test
    public void testCreateArmy() {
        CreateArmy createArmy = new CreateArmy(new Name("toto"));
        ArmyId armyId = armyService.createArmy(createArmy);
        ArmyCreated armyCreated = new ArmyCreated(new Name("toto"));
        verify(eventSource).insertEvent(armyId, eq((DomainEvent) armyCreated));
    }

    @Test
    public void testRenameArmy() {
        Name name = new Name("toto");

        ArmyId armyId = armyService.createArmy(new CreateArmy(name));


        RenameArmy renameArmy = new RenameArmy(armyId, name);
        armyService.rename(renameArmy);
        NameChanged nameChanged = new NameChanged(name);
        verify(eventSource).insertEvent(armyId, eq(nameChanged));
    }

}