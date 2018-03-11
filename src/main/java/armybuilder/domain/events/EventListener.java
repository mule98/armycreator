package armybuilder.domain.events;

@FunctionalInterface
public interface EventListener {
	void propagate(Event event);
}
