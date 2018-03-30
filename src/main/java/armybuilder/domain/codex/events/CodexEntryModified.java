package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.events.Event;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CodexEntryModified extends Event<CodexId, Codex> {
    private final CodexId codexId;
    private final EntryId entryId;

    public CodexEntryModified(CodexId codexId, EntryId entryId) {

        this.codexId = codexId;
        this.entryId = entryId;
    }

    @Override
    protected final CodexId getId() {
        return codexId;
    }
}
