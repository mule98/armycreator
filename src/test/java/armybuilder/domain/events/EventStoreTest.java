package armybuilder.domain.events;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventStoreTest {


    private DomainEventPublisher publisher = new DomainEventPublisher();

    @Test
    public void testStoreEvent() {
        DomainEvent domainEvent = () -> null;
        publisher.publish(domainEvent);

        assertEquals(1, EventStore.instance().getAllEvents().count());
    }

}