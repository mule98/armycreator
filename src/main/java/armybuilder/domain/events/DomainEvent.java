package armybuilder.domain.events;

import armybuilder.domain.Army;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public abstract class DomainEvent {

    public final Army apply(Army army, boolean newEvent) {
        Army result = applyChange(army);
        if (newEvent) {
            EventBus.instance()
                    .publish(this);
        }
        return result;
    }

    public abstract Army applyChange(Army event);


}
