package armybuilder.domain.codex;

import armybuilder.domain.codex.events.CodexCreated;
import armybuilder.domain.codex.events.CodexEntryModified;
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
        return entryModification(movementModified, t -> t.withMovement(movementModified.getMovement()));
    }

    private Entry getEntry(CodexEntryModified codexEntryModified) {
        return entries.stream()
                      .filter(t -> t.getId()
                                    .equals(codexEntryModified.getEntryId()))
                      .findFirst()
                      .orElseThrow(RuntimeException::new);
    }

    public Codex apply(CombatSkillModified combatSkillModified) {
        return entryModification(combatSkillModified, t -> t.withCombatSkill(combatSkillModified.getCombatSkill()));
    }

    private Codex entryModification(CodexEntryModified codexEntryModified, Function<Entry, Entry> modification) {
        Entry entry = getEntry(codexEntryModified);
        Set<Entry> entries = new HashSet<>(this.entries);
        entries.remove(entry);
        entries.add(modification.apply(entry));
        return withEntries(Collections.unmodifiableSet(entries));
    }
}
