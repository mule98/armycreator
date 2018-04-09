package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.attributes.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.*;
import lombok.experimental.Wither;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Wither
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Immutable
public class Entry {
    @NonNull
    private EntryId id;
    private Name name;
    private Movement movement;
    private WeaponSkill weaponSkill;
    private BallisticSkill ballisticSkill;
    private Strength strength;
    private Wound wound;
    private Attack attack;
    private Lead lead;
    private Save save;
    @Getter(AccessLevel.NONE)
    private Set<Weapon> weapons = Collections.EMPTY_SET;
    @Getter(AccessLevel.NONE)
    @Wither(AccessLevel.PUBLIC)
    private Set<Aptitude> aptitudes = Collections.EMPTY_SET;

    public Stream<Weapon> weapons() {
        return weapons.stream();
    }

    public Stream<Aptitude> aptitudes() {
        return aptitudes.stream();
    }

    public Entry addAptitude(Aptitude aptitude) {
        Set<Aptitude> entries = new HashSet<>(aptitudes);
        entries.add(aptitude);
        return withAptitudes(Collections.unmodifiableSet(entries));
    }

    public Entry addWeapon(Weapon weapon) {
        HashSet<Weapon> newWeapons = new HashSet<>(weapons);
        newWeapons.add(weapon);
        return withWeapons(Collections.unmodifiableSet(newWeapons));
    }
}
