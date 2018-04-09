package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Attack;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AttackModified extends CodexEntryModified {
    private final Attack attack;

    public AttackModified(CodexId codexId, EntryId entryId, Attack attack) {
        super(codexId, entryId);
        this.attack = attack;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.modifyAttack(this);
    }
}
