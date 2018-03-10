package armybuilder.domain;

import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.NameChanged;
import lombok.*;
import lombok.experimental.Wither;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Getter
@Wither(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Army {

    @NonNull
    private ArmyId id;
    @NonNull
    @Builder.Default
    private Set<Unit> units = Collections.emptySet();
    @Builder.Default
    private Name name = null;

    public Army applyChange(ArmyCreated armyCreated) {
        return withId(armyCreated.armyId).withName(armyCreated.name);
    }


    public Army applyChange(NameChanged nameChanged) {
        return withName(nameChanged.name);
    }


    public Army add(Unit unit) {
        HashSet<Unit> tmpUnits = new HashSet<>();
        tmpUnits.addAll(tmpUnits);
        tmpUnits.add(unit);
        return withUnits(Collections.unmodifiableSet(tmpUnits));
    }


    public Stream<Unit> units() {
        return units.stream();
    }

    public Name name() {
        return name;
    }


    public void applyChange(DomainEvent event, boolean b) {

    }
}
