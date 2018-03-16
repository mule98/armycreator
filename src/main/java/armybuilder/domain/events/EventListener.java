package armybuilder.domain.events;

@FunctionalInterface
interface EventListener {
	void propagate(Event event);
}
