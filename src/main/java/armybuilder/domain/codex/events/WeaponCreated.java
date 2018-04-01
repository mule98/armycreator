package armybuilder.domain.codex.events;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.WeaponId;
import lombok.Data;

@Data
public class WeaponCreated extends CodexModified {
    private final Name name;
    private final WeaponId weaponId;

    public WeaponCreated(CodexId codexId, Name name, WeaponId weaponId) {
        super(codexId);
        this.name = name;
        this.weaponId = weaponId;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.apply(this);
    }
}
