package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import armybuilder.domain.codex.attributes.Save;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SaveModification extends CodexEntryCommand {
    private final Save save;

    public SaveModification(CodexId codexId, EntryId entryId, Save save) {
        super(codexId, entryId);

        this.save = save;
    }
}
