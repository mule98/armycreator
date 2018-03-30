package armybuilder.domain.codex;

import armybuilder.domain.codex.events.CodexCreated;
import armybuilder.domain.codex.events.CombatSkillModified;
import armybuilder.domain.codex.events.MovementModified;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
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
    private Set<Entry> entries = Collections.unmodifiableSet(Collections.EMPTY_SET);

    public Codex apply(CodexCreated codexCreated) {
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

    public Codex apply(MovementModified movementModified) {
        Entry entry = getEntry(movementModified.getEntryId());
        Set<Entry> entries = new HashSet<>(this.entries);
        entries.remove(entry);
        entry = entry.withMovement(movementModified.getMovement());
        entries.add(entry);
        return withEntries(Collections.unmodifiableSet(entries));
    }

    private Entry getEntry(EntryId entryId) {
        return entries.stream()
                      .filter(t -> t.getId()
                                    .equals(entryId))
                      .findFirst()
                      .orElseThrow(RuntimeException::new);
    }

    public Codex apply(CombatSkillModified combatSkillModified) {

        Entry entry = getEntry(combatSkillModified.getEntryId());
        Set<Entry> entries = new HashSet<>(this.entries);
        entries.remove(entry);
        entry = entry.withCombatSkill(combatSkillModified.getCombatSkill());
        entries.add(entry);
        return withEntries(Collections.unmodifiableSet(entries));
    }

}
