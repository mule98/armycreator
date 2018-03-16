package armybuilder.domain.codex.service;

import armybuilder.domain.codex.*;
import armybuilder.domain.codex.events.CodexCreated;
import armybuilder.domain.codex.events.CodexEntryCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodexService {
    private final CodexRepository codexRepository;

    @Autowired
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

        Codex codex = codexRepository.getById(createEntry.getCodexId());
        EntryId next = EntryId.next();
        new CodexEntryCreated(createEntry.getCodexId(), createEntry.getName(), next).apply(codex, true);
        return next;
    }
}
