package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.RequiredArgsConstructor;

@Immutable
@RequiredArgsConstructor
public abstract class DomainEvent {

	public final ArmyId armyId;

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
