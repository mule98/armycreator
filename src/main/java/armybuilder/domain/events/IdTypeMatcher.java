package armybuilder.domain.events;

public interface IdTypeMatcher<T> {
	boolean matchesType(Id id);
	T transform(Id id);
}
