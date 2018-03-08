package armybuilder.domain.events;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventStoreTest {


    private DomainEventPublisher publisher = new DomainEventPublisher();

    @Test
    public void testStoreEvent() {
        DomainEvent domainEvent = Mockito.mock(DomainEvent.class);
        publisher.publish(domainEvent);

        assertEquals(1, EventStore.instance().getAllEvents().count());
    }

}