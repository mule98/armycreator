package armybuilder.domain.codex.events;

import armybuilder.domain.codex.AptitudeId;
import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AptitudeAssociated extends CodexEntryModified {

    private final AptitudeId aptitudeId;

    public AptitudeAssociated(CodexId codexId, EntryId entryId, AptitudeId aptitudeId) {
        super(codexId, entryId);
        this.aptitudeId = aptitudeId;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.associateAptitude(this);
    }
}
