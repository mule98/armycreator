package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Strength;
import lombok.Data;

@Data
public class StrengthModification extends CodexEntryCommand {
    private final Strength strength;

    public StrengthModification(CodexId codexId, EntryId entryId, Strength strength) {
        super(codexId, entryId);
        this.strength = strength;
    }
}
