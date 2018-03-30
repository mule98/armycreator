package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import lombok.Data;

@Data
public class CodexEntryCommand extends CodexCommand {
    private final EntryId entryId;

    public CodexEntryCommand(CodexId codexId, EntryId entryId) {
        super(codexId);
        this.entryId = entryId;
    }
}
