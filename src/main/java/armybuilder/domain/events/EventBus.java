package armybuilder.domain.events;


import java.util.LinkedList;

public class EventBus {
	private static EventBus eventBus = new EventBus();
	private LinkedList<DomainEventListener> subscribers = new LinkedList<>();


	private EventBus() {
	}

	public static EventBus instance() {
		return eventBus;
	}

	public void subscribe(DomainEventListener domainEventListener) {
		subscribers.add(domainEventListener);
	}

	public void publish(DomainEvent event) {
		subscribers.forEach(t -> t.propagate(event));
	}
}
