package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.BallisticSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BallisticSkillModification extends CodexEntryCommand {
    private final BallisticSkill ballisticSkill;

    public BallisticSkillModification(CodexId codexId, EntryId entryId, BallisticSkill ballisticSkill) {
        super(codexId, entryId);
        this.ballisticSkill = ballisticSkill;
    }
}
