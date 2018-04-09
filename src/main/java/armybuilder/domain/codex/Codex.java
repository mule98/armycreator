package armybuilder.domain.codex;

import armybuilder.domain.codex.events.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Wither(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Immutable
public class Codex {
    private CodexName name;
    private CodexId id;


    //todo: Only ID and Name are discriminant in the set for replacement.
    @Getter(AccessLevel.NONE)
    private Set<Entry> entries = Collections.EMPTY_SET;
    @Getter(AccessLevel.NONE)
    private Set<Weapon> weapons = Collections.EMPTY_SET;
    @Getter(AccessLevel.NONE)
    private Set<Aptitude> aptitudes = Collections.EMPTY_SET;

    public Stream<Entry> getEntries() {
        return entries.stream();
    }

    public Codex create(CodexCreated codexCreated) {
        return withId(codexCreated.getId()).withName(codexCreated.getName());
    }

    Stream<Entry> entries() {
        return entries.stream();
    }


    //todo: Error when trying to add an existing entry
    public Codex addEntry(Entry entry) {
        Set<Entry> collect = Stream.concat(entries.stream(), Stream.of(entry))
                                   .collect(Collectors.toSet());
        return this.withEntries(Collections.unmodifiableSet(collect));
    }

    public Codex modifyMovement(MovementModified movementModified) {
        return entryModification(movementModified, t -> t.withMovement(movementModified.getMovement()));
    }

    private Entry getEntry(CodexEntryModified codexEntryModified) {
        return entries.stream()
                      .filter(t -> t.getId()
                                    .equals(codexEntryModified.getEntryId()))
                      .findFirst()
                      .orElseThrow(RuntimeException::new);
    }

    public Codex modifyCombatSkill(CombatSkillModified combatSkillModified) {
        return entryModification(combatSkillModified, t -> t.withWeaponSkill(combatSkillModified.getWeaponSkill()));
    }

    private Codex entryModification(CodexEntryModified codexEntryModified, Function<Entry, Entry> modification) {
        Entry entry = getEntry(codexEntryModified);
        Set<Entry> entries = new HashSet<>(this.entries);
        entries.remove(entry);
        entries.add(modification.apply(entry));
        return withEntries(Collections.unmodifiableSet(entries));
    }

    public Codex modifiyBallisticSkill(BallisticSkillModified ballisticSkillModified) {
        return entryModification(ballisticSkillModified,
                                 t -> t.withBallisticSkill(ballisticSkillModified.getBallisticSkill()));
    }

    public Codex modifyStrength(StrengthModified strengthModified) {
        return entryModification(strengthModified, entry -> entry.withStrength(strengthModified.getStrength()));
    }

    public Codex modifyWound(WoundModified woundModified) {
        return entryModification(woundModified, entry -> entry.withWound(woundModified.getWound()));
    }

    public Codex modifyAttack(AttackModified attackModified) {

        return entryModification(attackModified, entry -> entry.withAttack(attackModified.getAttack()));

    }

    public Codex modifyLead(LeadModified leadModified) {
        return entryModification(leadModified, entry -> entry.withLead(leadModified.getLead()));
    }

    public Codex modifySave(SaveModified saveModified) {
        return entryModification(saveModified, entry -> entry.withSave(saveModified.getSave()));
    }

    public Stream<Weapon> getWeapons() {
        return weapons.stream();
    }

    public Codex createWeapon(WeaponCreated weaponCreated) {
        Set<Weapon> weapons = new HashSet<>(this.weapons);
        weapons.add(new Weapon().withId(weaponCreated.getWeaponId())
                                .withName(weaponCreated.getName()));
        return withWeapons(Collections.unmodifiableSet(weapons));
    }

    public Codex associateWeapon(WeaponAssociated weaponAssociated) {
        Weapon weapon = getWeapon(weaponAssociated.getWeaponId());
        return entryModification(weaponAssociated, entry -> entry.addWeapon(weapon));
    }

    private Weapon getWeapon(WeaponId weaponId) {
        return getWeapons().filter(t -> t.getId()
                                         .equals(weaponId))
                           .findFirst()
                           .orElseThrow(RuntimeException::new);
    }

    public Codex createAptitude(AptitudeCreated aptitudeCreated) {
        Set<Aptitude> workingAptitudes = new HashSet<>(aptitudes);
        workingAptitudes.add(new Aptitude(aptitudeCreated.getAptitudeId(), aptitudeCreated.getName()));
        return withAptitudes(workingAptitudes);
    }

    public Codex associateAptitude(final AptitudeAssociated aptitudeAssociated) {
        Aptitude aptitude = getAptitude(aptitudeAssociated.getAptitudeId());
        return entryModification(aptitudeAssociated, entry -> entry.addAptitude(aptitude));
    }

    private Aptitude getAptitude(AptitudeId aptitudeId) {
        return aptitudes.stream()
                        .filter(t -> t.id.equals(aptitudeId))
                        .findFirst()
                        .orElseThrow(RuntimeException::new);
    }

    public Stream<Aptitude> getAptitudes() {
        return aptitudes.stream();
    }
}
