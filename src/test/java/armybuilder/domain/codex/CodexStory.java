package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.attributes.*;
import armybuilder.domain.codex.commands.*;
import armybuilder.domain.codex.reader.CodexReader;
import armybuilder.domain.codex.service.CodexService;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CodexStory {

    private static final String TYRANID_ENTRY = "Tyranids";
    private static final String TAU_CODEX = "T'au";
    private static final String KROOT_ENTRY = "Kroot carnivores";
    private CodexService codexService;
    private CodexReader codexReader;

    @Test
    void setMovement() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        int movement = 8;
        setMovement(codexId, entryId, movement);

        Entry entry = getEntry(codexId, entryId);
        assertEquals(new Movement(movement), entry.getMovement());
    }

    @BeforeEach
    void beforeEach() {
        EventStore eventStore = new EventStore();
        EventBus.instance()
                .subscribe(eventStore);
        CodexRepository codexRepository = new CodexRepository(eventStore);
        codexService = new CodexService(codexRepository);
        codexReader = new CodexReader(codexRepository);

    }

    void setMovement(CodexId codexId, EntryId entryId, int movement) {
        codexService.modifyMovement(new MovementModification(codexId, entryId, new Movement(movement)));
    }

    @Test
    void testCreateEntry() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, "Kroot carnivores");
        setMovement(codexId, entryId, 8);
        setCombatSkill(codexId, entryId, "3+");
        setBalisticSkill(codexId, entryId, "4+");
        setStrength(codexId, entryId, 3);
        setWoundPoint(codexId, entryId, 1);
        setAttacks(codexId, entryId, 1);
//        setCommand(entryid,6);
//        setSave(entryid,"6+");
//        setAddWeapon(entryid,getWeapon("Fusil Kroot"));
//        setAddAptitude(entryid,getAptitude("Chasseur discret"));
//        setAddKeyWord(entryid,etFactionKeyWord("T'au Empire"));
//        setAddKeyWord(entryid,getKeyWord("Infanterie"));
//        setAddKeyWord(entryid,getKeyWord("Kroot Carnivores"));


    }

    @Test
    void setAttacks() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setAttacks(codex, entry, 6);

        Attack result = getCodex(TAU_CODEX).get()
                                           .getEntries()
                                           .stream()
                                           .findFirst()
                                           .get()
                                           .getAttack();
        assertEquals(new Attack(6), result);

    }

    private void setAttacks(CodexId codexId, EntryId entryId, int i) {
        codexService.modifyAttack(new AttackModification(codexId, entryId, new Attack(i)));
    }

    @Test
    void setWound() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setWoundPoint(codex, entry, 2);

        Wound result = getCodex(TAU_CODEX).get()
                                          .getEntries()
                                          .stream()
                                          .findFirst()
                                          .get()
                                          .getWound();
        assertEquals(new Wound(2), result);

    }

    private void setWoundPoint(CodexId codexId, EntryId entryId, int i) {
        codexService.modifyWound(new WoundModification(codexId, entryId, new Wound(i)));
    }

    @Test
    void setStrength() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setStrength(codex, entry, 2);

        Strength result = getCodex(TAU_CODEX).get()
                                             .getEntries()
                                             .stream()
                                             .findFirst()
                                             .get()
                                             .getStrength();
        assertEquals(new Strength(2), result);
    }

    private void setStrength(CodexId codexId, EntryId entryId, int i) {
        codexService.modifyStrength(new StrengthModification(codexId, entryId, new Strength(i)));
    }


    @Test
    void setBalisticSkill() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        String bs = "3+";
        setBalisticSkill(codexId, entryId, bs);

        BallisticSkill result = getCodex(TAU_CODEX).get()
                                                   .entries()
                                                   .findFirst()
                                                   .get()
                                                   .getBallisticSkill();
        assertEquals(new BallisticSkill(bs), result);
    }

    private void setBalisticSkill(CodexId codexId, EntryId entryId, String s) {
        codexService.modifyBallisticSkill(new BallisticSkillModification(codexId, entryId, new BallisticSkill(s)));
    }


    @Test
    void modifyEntryCombatSkill() {
        CodexId codexId = createCodex(TAU_CODEX);

        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        String cs = "3+";
        setCombatSkill(codexId, entryId, cs);
        Optional<Codex> codex = getCodex(TAU_CODEX);
        WeaponSkill result = codex.get()
                                  .entries()
                                  .findFirst()
                                  .get()
                                  .getWeaponSkill();
        assertEquals(new WeaponSkill(cs), result);

    }

    private void setCombatSkill(CodexId codexId, EntryId entryId, String cs) {
        codexService.modifyCombatSkill(new CombatSkillModification(new WeaponSkill(cs), codexId, entryId));
    }


    private Entry getEntry(CodexId codexId, EntryId entryId) {
        return codexReader.getCodex(codexId)
                          .getEntries()
                          .stream()
                          .filter(entry -> entry.getId()
                                                .equals(entryId))
                          .findFirst()
                          .get();
    }

    private void setMovement(EntryId entryId, int i) {

    }

    private CodexId getCodexId(String codexName) {
        return getCodex(codexName).get()
                                  .getId();
    }

    private Optional<Codex> getCodex(String codexName) {
        return codexReader.getCodex(codexName);
    }

    private EntryId createEntry(CodexId codexId, String entryName) {
        return codexService.insertEntry(new CreateEntry(codexId, new Name(entryName)));
    }

    @Test
    public void testCreateCodexEntry() {
        String entryName = "Kroot Carnivore";

        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, entryName);
        Optional<Codex> codex = getCodex(TAU_CODEX);
        Entry result = codex.get()
                            .getEntries()
                            .stream()
                            .findFirst()
                            .get();
        assertEquals(entryName,
                     result.getName()
                           .getName());
        assertEquals(entryId, result.getId());
    }

    @Test
    void testCreateCodex() {
        final CodexName tyranids = new CodexName("Tyranids");
        codexService.create(new CreateCodex(tyranids));
        Optional<Codex> codex = getCodex("Tyranids");
        assertTrue(codex.isPresent());
        assertEquals(tyranids,
                     codex.get()
                          .getName());

    }

    @Test
    void testGetCodexNotExist() {
        Optional<Codex> faction = getCodex("Tyranids");
        assertFalse(faction.isPresent());
    }

    @Test
    void testCreateCodexTwice() {
        createCodex(TAU_CODEX);
        assertThrows(RuntimeException.class, () -> createCodex(TAU_CODEX), "Codex Tyranids already exists");
    }

    private CodexId createCodex(String codexName) {
        return codexService.create(new CreateCodex(new CodexName(codexName)));
    }

    @Test
    void testInsertEntry() {
        CodexId codexId = createCodex(TAU_CODEX);
        CreateEntry createEntry = new CreateEntry(codexId, new Name("Lictor"));
        codexService.insertEntry(createEntry);
        Codex codex = codexReader.getCodex(codexId);
        assertTrue(codex.entries()
                        .anyMatch(t -> new Name("Lictor").equals(t.getName())));
    }
}
