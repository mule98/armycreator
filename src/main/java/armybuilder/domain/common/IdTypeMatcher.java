package armybuilder.domain.common;

public interface IdTypeMatcher<T> {
	boolean matchesType(Id id);
	T transform(Id id);
}