package armybuilder.domain.events;

import armybuilder.domain.ArmyId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventStoreTest {



    @Test
    public void testStoreEvent() {
        NameChanged domainEvent = Mockito.mock(NameChanged.class);
        EventStore.instance()
                  .insertEvent(ArmyId.next(), domainEvent);

        assertEquals(1, EventStore.instance().getAllEvents().count());
    }

}