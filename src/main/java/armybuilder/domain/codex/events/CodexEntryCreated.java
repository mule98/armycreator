package armybuilder.domain.codex.events;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.Entry;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.events.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class CodexEntryCreated extends Event<CodexId, Codex> {

    private final CodexId id;
    private final Name name;
    private final EntryId entryId;


    @Override
    public Codex applyChange(Codex codex) {
        Entry entry = new Entry().withId(entryId)
                                 .withName(name);
        return codex.addEntry(entry);
    }
}
