package armybuilder.domain.codex.commands;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.CodexId;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CreateAptitude extends CodexCommand {
    private final Name name;

    public CreateAptitude(CodexId codexId, Name name) {
        super(codexId);
        this.name = name;
    }
}
