package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Strength;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StrengthModified extends CodexEntryModified {
    private final Strength strength;

    public StrengthModified(CodexId codexId, EntryId entryId, Strength strength) {
        super(codexId, entryId);

        this.strength = strength;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyStrength(this);
    }
}
