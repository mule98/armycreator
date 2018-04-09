package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Movement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovementModification extends CodexEntryCommand {
    private final Movement movement;

    public MovementModification(CodexId codexId, EntryId entryId, Movement movement) {
        super(codexId, entryId);
        this.movement = movement;
    }
}
