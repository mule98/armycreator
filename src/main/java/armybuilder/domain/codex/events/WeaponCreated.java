package armybuilder.domain.codex.events;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.WeaponId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class WeaponCreated extends CodexModified {
    @NonNull
    private final Name name;
    @NonNull
    private final WeaponId weaponId;

    public WeaponCreated(CodexId codexId, Name name, WeaponId weaponId) {
        super(codexId);
        this.name = name;
        this.weaponId = weaponId;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.createWeapon(this);
    }
}
