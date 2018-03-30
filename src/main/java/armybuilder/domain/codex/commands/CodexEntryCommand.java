package armybuilder.domain.codex.commands;

import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.EntryId;
import lombok.Data;

@Data
public class CodexEntryCommand {
    private final CodexId codexId;
    private final EntryId entryId;
}
