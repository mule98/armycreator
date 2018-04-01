package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.WeaponId;
import lombok.Data;

@Data
public class AssociateWeapon extends CodexEntryCommand {
    private final WeaponId weaponId;

    public AssociateWeapon(CodexId codexId, EntryId entryId, WeaponId weaponId) {
        super(codexId, entryId);
        this.weaponId = weaponId;
    }
}
