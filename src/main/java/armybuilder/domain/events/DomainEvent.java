package armybuilder.domain.events;

import armybuilder.domain.Army;
import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public interface DomainEvent {

    EventType type();

    void apply(Army army);
}
