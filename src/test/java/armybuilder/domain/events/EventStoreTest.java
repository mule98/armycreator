package armybuilder.domain.events;

import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.events.NameChanged;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventStoreTest {



    @Test
    void testStoreEvent() {
        NameChanged domainEvent = Mockito.mock(NameChanged.class);
        EventStore.instance()
                  .insertEvent(ArmyId.next(), domainEvent);

        assertEquals(1, EventStore.instance().getAllEvents().count());
    }

}