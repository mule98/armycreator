package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Lead;
import lombok.Data;

@Data
public class LeadModification extends CodexEntryCommand {
    private final Lead lead;

    public LeadModification(CodexId codexId, EntryId entryId, Lead lead) {
        super(codexId, entryId);

        this.lead = lead;
    }
}
