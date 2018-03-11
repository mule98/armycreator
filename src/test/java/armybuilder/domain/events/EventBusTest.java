package armybuilder.domain.events;

import org.junit.jupiter.api.Test;

class EventBusTest {

    @Test
    void publish() {
        EventBus eventBus = EventBus.instance();

        DomainEvent receivedEvent;
        eventBus.subscribe(t -> receivedEvent = t);


    }
}