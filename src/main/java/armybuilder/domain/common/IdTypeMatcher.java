package armybuilder.domain.common;

public interface IdTypeMatcher<T extends Id> {
	boolean matchesType(Id id);
	T transform(Id id);
}
