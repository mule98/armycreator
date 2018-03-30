package armybuilder.domain.codex.service;

import armybuilder.domain.codex.*;
import armybuilder.domain.codex.commands.CombatSkillModification;
import armybuilder.domain.codex.commands.MovementModification;
import armybuilder.domain.codex.events.CodexCreated;
import armybuilder.domain.codex.events.CodexEntryCreated;
import armybuilder.domain.codex.events.CombatSkillModified;
import armybuilder.domain.codex.events.MovementModified;
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

        CodexId codexId = createEntry.getCodexId();
        Codex codex = getCodex(codexId);
        EntryId next = EntryId.next();
        new CodexEntryCreated(createEntry.getCodexId(), createEntry.getName(), next).apply(codex, true);
        return next;
    }

    private Codex getCodex(CodexId codexId) {
        return codexRepository.getById(codexId);
    }

    public void modifyMovement(MovementModification movementModification) {
        Codex codex = getCodex(movementModification.getCodexId());
        new MovementModified(movementModification.getCodexId(),
                             movementModification.getEntryId(),
                             movementModification.getMovement()).apply(codex, true);
    }

    public void modifyCombatSkill(CombatSkillModification combatSkillModification) {
        Codex codex = getCodex(combatSkillModification.getCodexId());
        new CombatSkillModified(combatSkillModification.getCodexId(),
                                combatSkillModification.getEntryId(),
                                combatSkillModification.getCombatSkill()).apply(codex, true);
    }
}
