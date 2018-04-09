package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.WeaponId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WeaponAssociated extends CodexEntryModified {
    private final WeaponId weaponId;

    public WeaponAssociated(CodexId codexId, EntryId entryId, WeaponId weaponId) {
        super(codexId, entryId);

        this.weaponId = weaponId;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.associateWeapon(this);
    }
}
