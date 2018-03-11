package armybuilder.domain.events;

@FunctionalInterface
public interface DomainEventListener {
	void propagate(DomainEvent event);
}
