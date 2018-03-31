package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Attack;
import lombok.Data;

@Data
public class AttackModification extends CodexEntryCommand {
    private final Attack attack;

    public AttackModification(CodexId codexId, EntryId entryId, Attack attack) {
        super(codexId, entryId);
        this.attack = attack;
    }
}
