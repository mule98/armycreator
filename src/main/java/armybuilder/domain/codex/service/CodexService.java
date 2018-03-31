package armybuilder.domain.codex.service;

import armybuilder.domain.codex.*;
import armybuilder.domain.codex.commands.*;
import armybuilder.domain.codex.events.*;
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

    private Codex getCodex(CodexCommand codexCommand) {
        return codexRepository.getById(codexCommand.getCodexId());
    }

    private Codex getCodex(CodexId codexId) {
        return codexRepository.getById(codexId);
    }

    public void modifyMovement(MovementModification mCommand) {
        Codex codex = getCodex(mCommand);
        new MovementModified(mCommand.getCodexId(), mCommand.getEntryId(), mCommand.getMovement()).apply(codex, true);
    }

    public void modifyCombatSkill(CombatSkillModification csCommand) {
        Codex codex = getCodex(csCommand);
        new CombatSkillModified(csCommand.getCodexId(), csCommand.getEntryId(), csCommand.getWeaponSkill()).apply(codex,
                                                                                                                  true);
    }

    public void modifyBallisticSkill(BallisticSkillModification bsCommand) {
        Codex codex = getCodex(bsCommand);
        new BallisticSkillModified(bsCommand.getCodexId(), bsCommand.getEntryId(), bsCommand.getBallisticSkill()).apply(
                codex,
                true);
    }

    public void modifyStrength(StrengthModification strengthModification) {
        Codex codex = getCodex(strengthModification);
        new StrengthModified(strengthModification.getCodexId(),
                             strengthModification.getEntryId(),
                             strengthModification.getStrength()).apply(codex, true);
    }

    public void modifyWound(WoundModification woundModification) {
        Codex codex = getCodex(woundModification);
        new WoundModified(woundModification.getCodexId(),
                          woundModification.getEntryId(),
                          woundModification.getWound()).apply(codex, true);
    }

    public void modifyAttack(AttackModification attackModification) {
        Codex codex = getCodex(attackModification);
        new AttackModified(attackModification.getCodexId(),
                           attackModification.getEntryId(),
                           attackModification.getAttack()).apply(codex, true);
    }
}
