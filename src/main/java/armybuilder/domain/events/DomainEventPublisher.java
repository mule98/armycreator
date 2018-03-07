package armybuilder.domain.events;

public class DomainEventPublisher {


    private DomainEventListener listener;

    public void publish(DomainEvent domainEvent) {
        if (listener != null)
            listener.propagate(domainEvent);
    }

    public void subscibe(DomainEventListener listener) {
        this.listener = listener;
    }
}
