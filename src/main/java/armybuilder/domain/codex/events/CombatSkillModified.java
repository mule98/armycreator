package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.WeaponSkill;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CombatSkillModified extends CodexEntryModified {
    private final WeaponSkill weaponSkill;

    public CombatSkillModified(CodexId codexId, EntryId entryId, WeaponSkill weaponSkill) {
        super(codexId, entryId);
        this.weaponSkill = weaponSkill;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyCombatSkill(this);
    }
}
