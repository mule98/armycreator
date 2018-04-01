package armybuilder.domain.codex.events;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class CodexEntryModified extends CodexModified {
    private final EntryId entryId;

    public CodexEntryModified(CodexId codexId, EntryId entryId) {
        super(codexId);
        this.entryId = entryId;
    }
}
