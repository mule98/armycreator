package armybuilder.domain.codex.service;

import armybuilder.domain.codex.*;
import armybuilder.domain.codex.events.CodexCreated;
import armybuilder.domain.codex.events.CodexEntryCreated;

public class CodexService {
    private final CodexRepository codexRepository;

    public CodexService(CodexRepository codexRepository) {

        this.codexRepository = codexRepository;
    }

    public CodexId create(CreateCodex createCommand) {
        if (codexRepository.findByName(createCommand.getName())
                           .isPresent())
            throw new RuntimeException("Codex " + createCommand.getName() + " already exists");
        return new CodexCreated(CodexId.next(), createCommand.getName()).apply(new Codex(), true)
                                                                        .getId();
    }

    public EntryId insertEntry(CreateEntry createEntry) {

        Codex codex = codexRepository.getById(createEntry.codexId);
        EntryId next = EntryId.next();
        new CodexEntryCreated(createEntry.codexId, createEntry.name, next).apply(codex, true);
        return next;
    }
}
