package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Lead;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class LeadModified extends CodexEntryModified {
    private final Lead lead;

    public LeadModified(CodexId codexId, EntryId entryId, Lead lead) {
        super(codexId, entryId);
        this.lead = lead;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyLead(this);
    }
}
