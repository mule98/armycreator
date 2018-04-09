package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Wound;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WoundModified extends CodexEntryModified {
    private final Wound wound;

    public WoundModified(CodexId codexId, EntryId entryId, Wound wound) {
        super(codexId, entryId);
        this.wound = wound;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyWound(this);
    }
}
