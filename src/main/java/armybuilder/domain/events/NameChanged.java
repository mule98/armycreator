package armybuilder.domain.events;

import armybuilder.domain.Army;
import armybuilder.domain.Name;
import lombok.Data;

@Data
public class NameChanged implements DomainEvent {
    public final Name name;

    @Override
    public EventType type() {
        return EventType.ARMY;
    }

    @Override
    public void apply(Army army) {
        army.applyChange(this, false);
    }
}
