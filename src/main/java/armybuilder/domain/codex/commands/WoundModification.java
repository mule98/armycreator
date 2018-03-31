package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Wound;
import lombok.Data;

@Data
public class WoundModification extends CodexEntryCommand {
    private final Wound wound;

    public WoundModification(CodexId codexId, EntryId entryId, Wound wound) {
        super(codexId, entryId);
        this.wound = wound;
    }
}
