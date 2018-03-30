package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.CombatSkill;
import lombok.Data;

@Data
public class CombatSkillModified extends CodexEntryModified {
    private final CombatSkill combatSkill;

    public CombatSkillModified(CodexId codexId, EntryId entryId, CombatSkill combatSkill) {
        super(codexId, entryId);
        this.combatSkill = combatSkill;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.apply(this);
    }
}
