package armybuilder.domain.events;


public class EventBus {
    private static EventBus eventBus = new EventBus();

    private EventBus() {
    }

    public static EventBus instance() {
        return eventBus;
    }

    public void publish(DomainEvent event) {

    }
}
