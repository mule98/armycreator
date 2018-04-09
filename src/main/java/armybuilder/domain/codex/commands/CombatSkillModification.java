package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.WeaponSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CombatSkillModification extends CodexEntryCommand {
    private final WeaponSkill weaponSkill;

    public CombatSkillModification(WeaponSkill weaponSkill, CodexId codexId, EntryId entryId) {
        super(codexId, entryId);
        this.weaponSkill = weaponSkill;
    }
}
