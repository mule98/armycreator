package armybuilder.domain.events;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.RequiredArgsConstructor;

@Immutable
@RequiredArgsConstructor
public abstract class DomainEvent<V extends Id, T> {

	public final T apply(T rootAggregate, boolean newEvent) {
        T result = applyChange(rootAggregate);
        if (newEvent) {
            EventBus.instance()
                    .publish(this);
        }
        return result;
    }

    public abstract T applyChange(T event);

	public abstract V getId();


}
