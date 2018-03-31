package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Save;
import lombok.Data;

@Data
public class SaveModified extends CodexEntryModified {
    private final Save save;

    public SaveModified(CodexId codexId, EntryId entryId, Save save) {
        super(codexId, entryId);
        this.save = save;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.apply(this);
    }
}
