package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.BallisticSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BallisticSkillModified extends CodexEntryModified {
    private final BallisticSkill ballisticSkill;

    public BallisticSkillModified(CodexId codexId, EntryId entryId, BallisticSkill ballisticSkill) {
        super(codexId, entryId);
        this.ballisticSkill = ballisticSkill;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifiyBallisticSkill(this);
    }
}
