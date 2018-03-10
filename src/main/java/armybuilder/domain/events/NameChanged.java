package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Data
@Immutable
public class NameChanged extends DomainEvent {

    public final Name name;
    public final ArmyId armyId;


    @Override
    public Army applyChange(Army event) {
        return event.applyChange(this);
    }
}
