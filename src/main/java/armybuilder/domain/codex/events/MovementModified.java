package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Movement;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class MovementModified extends CodexEntryModified {
    private final Movement movement;

    public MovementModified(CodexId codexId, EntryId entryId, Movement movement) {

        super(codexId, entryId);
        this.movement = movement;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyMovement(this);
    }

}
