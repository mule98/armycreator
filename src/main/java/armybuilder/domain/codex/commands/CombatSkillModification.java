package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.CombatSkill;
import lombok.Data;

@Data
public class CombatSkillModification extends CodexEntryCommand {
    private final CombatSkill combatSkill;

    public CombatSkillModification(CombatSkill combatSkill, CodexId codexId, EntryId entryId) {
        super(codexId, entryId);
        this.combatSkill = combatSkill;
    }
}
