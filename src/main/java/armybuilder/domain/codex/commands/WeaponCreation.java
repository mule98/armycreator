package armybuilder.domain.codex.commands;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.CodexId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WeaponCreation extends CodexCommand {
    private final Name name;

    public WeaponCreation(CodexId codex, Name name) {
        super(codex);

        this.name = name;
    }
}
