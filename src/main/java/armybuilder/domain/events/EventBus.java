package armybuilder.domain.events;


import java.util.LinkedList;

public class EventBus {
	private static EventBus eventBus = new EventBus();
	private LinkedList<EventListener> subscribers = new LinkedList<>();


	private EventBus() {
	}

    public static EventBus instance() {
		return eventBus;
	}

    public void subscribe(EventListener eventListener) {
		subscribers.add(eventListener);
	}

    void publish(Event event) {
		subscribers.forEach(t -> t.propagate(event));
	}
}
