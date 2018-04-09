package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.AptitudeId;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AssociateAptitude extends CodexEntryCommand {
    private final AptitudeId aptitudeId;

    public AssociateAptitude(CodexId codexId, EntryId entryId, AptitudeId aptitudeId) {
        super(codexId, entryId);
        this.aptitudeId = aptitudeId;
    }
}
