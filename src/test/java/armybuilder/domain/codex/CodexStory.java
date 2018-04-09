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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CodexStory {

    public static final String FUSIL_KROOT = "Fusil Kroot";
    private static final String TYRANID_ENTRY = "Tyranids";
    private static final String TAU_CODEX = "T'au";
    private static final String KROOT_ENTRY = "Kroot carnivores";
    private CodexService codexService;
    private CodexReader codexReader;

    @Test
    void associateAptitude() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        AptitudeId aptitudeId = createAptitude(codexId, "Chasseur discret");
        associateAptitude(codexId, entryId, aptitudeId);
        Aptitude aptitude = getCodex(TAU_CODEX).get()
                                               .getEntries()
                                               .findFirst()
                                               .get()
                                               .aptitudes()
                                               .findFirst()
                                               .get();

        assertNotNull(aptitude);
        assertEquals(aptitudeId, aptitude.id);
    }

    @Test
    void associateTwoAptitudeToTwoEntry() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        EntryId entryId2 = createEntry(codexId, "Riptide");
        AptitudeId aptitudeId = createAptitude(codexId, "Chasseur discret");
        AptitudeId aptitudeId2 = createAptitude(codexId, "Kau yon");
        associateAptitude(codexId, entryId, aptitudeId);
        associateAptitude(codexId, entryId2, aptitudeId2);
        List<Entry> entries = getCodex(TAU_CODEX).get()
                                                 .getEntries()
                                                 .collect(Collectors.toList());
        Entry entry1 = entries.get(1);
        Aptitude aptitude = entry1.aptitudes()
                                  .findFirst()
                                  .get();

        assertEquals(1,
                     entry1.aptitudes()
                           .count());

        Entry entry2 = entries.get(0);
        Aptitude aptitude2 = entry2.aptitudes()
                                   .findFirst()
                                   .get();


        assertEquals(1,
                     entry2.aptitudes()
                           .count());


        assertNotNull(aptitude);
        assertEquals(aptitudeId, aptitude.id);
        assertEquals(aptitudeId2, aptitude2.id);
    }

    @Test
    void associateAptitudeTwice() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        AptitudeId aptitudeId = createAptitude(codexId, "Chasseur discret");
        associateAptitude(codexId, entryId, aptitudeId);
        associateAptitude(codexId, entryId, aptitudeId);

        assertEquals(1,
                     getCodex(TAU_CODEX).get()
                                        .getEntries()
                                        .findFirst()
                                        .get()
                                        .aptitudes()
                                        .count());
        Aptitude aptitude = getCodex(TAU_CODEX).get()
                                               .getEntries()
                                               .findFirst()
                                               .get()
                                               .aptitudes()
                                               .findFirst()
                                               .get();

        assertNotNull(aptitude);
        assertEquals(aptitudeId, aptitude.id);
    }

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
        setLead(codexId, entryId, 6);
        setSave(codexId, entryId, "6+");
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);
        associateWeapon(codexId, entryId, weaponId);
        AptitudeId aptitudeId = createAptitude(codexId, "Chasseur discret");
        associateAptitude(codexId, entryId, aptitudeId);
//        setAddKeyWord(entryid,etFactionKeyWord("T'au Empire"));
//        setAddKeyWord(entryid,getKeyWord("Infanterie"));
//        setAddKeyWord(entryid,getKeyWord("Kroot Carnivores"));


    }

    private void associateAptitude(CodexId codexId, EntryId entryId, AptitudeId aptitudeId) {
        codexService.associateAptitude(new AssociateAptitude(codexId, entryId, aptitudeId));
    }


    @Test
    void associateWeaponTwice() {
        CodexId codexId = createCodex(TAU_CODEX);
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);
        EntryId entry = createEntry(codexId, KROOT_ENTRY);

        associateWeapon(codexId, entry, weaponId);
        associateWeapon(codexId, entry, weaponId);

        long count = getCodex(TAU_CODEX).get()
                                        .getEntries()
                                        .findFirst()
                                        .get()
                                        .weapons()
                                        .count();

        assertEquals(1, count);
    }

    @Test
    void createAptitude() {
        CodexId codexId = createCodex(TAU_CODEX);
        EntryId entryId = createEntry(codexId, KROOT_ENTRY);
        String name = "Chasseur discret";
        AptitudeId chasseur_discretId = createAptitude(codexId, name);
        assertNotNull(chasseur_discretId);
        Aptitude chasseur_discret = getCodex(TAU_CODEX).get()
                                                       .getAptitudes()
                                                       .findFirst()
                                                       .get();
        assertEquals(chasseur_discretId, chasseur_discret.id);
        assertEquals(new Name(name), chasseur_discret.name);


    }

    private AptitudeId createAptitude(CodexId codexId, String name) {
        return codexService.createAptitude(new CreateAptitude(codexId, new Name(name)));
    }

    @Test
    void associateWeapon() {
        CodexId codexId = createCodex(TAU_CODEX);
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);
        EntryId entry = createEntry(codexId, KROOT_ENTRY);

        associateWeapon(codexId, entry, weaponId);

        Weapon weapon = getCodex(TAU_CODEX).get()
                                           .getEntries()
                                           .findFirst()
                                           .get()
                                           .weapons()
                                           .findFirst()
                                           .get();
        assertEquals(weaponId, weapon.getId());
    }

    @Test
    void associateTwoWeapons() {
        CodexId codexId = createCodex(TAU_CODEX);
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);
        WeaponId weapon2Id = createWeapon(codexId, "Railgun");
        EntryId entry = createEntry(codexId, KROOT_ENTRY);

        associateWeapon(codexId, entry, weaponId);
        associateWeapon(codexId, entry, weapon2Id);

        List<Weapon> weapons = getCodex(TAU_CODEX).get()
                                                  .getEntries()
                                                  .findFirst()
                                                  .get()
                                                  .weapons()
                                                  .collect(Collectors.toList());
        assertEquals(2, weapons.size());
        assertEquals(weaponId,
                     weapons.get(1)
                            .getId());
        assertEquals(weapon2Id,
                     weapons.get(0)
                            .getId());
    }

    @Test
    void associateTwoWeaponsTwoEntries() {
        CodexId codexId = createCodex(TAU_CODEX);
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);
        WeaponId weapon2Id = createWeapon(codexId, "Railgun");
        EntryId entry = createEntry(codexId, KROOT_ENTRY);
        EntryId entry2 = createEntry(codexId, "Shas'ui");

        associateWeapon(codexId, entry, weaponId);
        associateWeapon(codexId, entry2, weapon2Id);
        List<Weapon> weapons = getCodex(TAU_CODEX).get()
                                                  .getEntries()
                                                  .filter(t -> t.getId()
                                                                .equals(entry))
                                                  .findFirst()
                                                  .get()
                                                  .weapons()
                                                  .collect(Collectors.toList());
        List<Weapon> weapons2 = getCodex(TAU_CODEX).get()
                                                   .getEntries()
                                                   .filter(t -> t.getId()
                                                                 .equals(entry2))
                                                   .findFirst()
                                                   .get()
                                                   .weapons()
                                                   .collect(Collectors.toList());

        assertEquals(1, weapons.size());
        assertEquals(weaponId,
                     weapons.get(0)
                            .getId());
        assertEquals(1, weapons2.size());
        assertEquals(weapon2Id,
                     weapons2.get(0)
                             .getId());
    }

    private void associateWeapon(CodexId codexId, EntryId entryId, WeaponId weaponId) {
        codexService.associateWeapon(new AssociateWeapon(codexId, entryId, weaponId));
    }

    @Test
    void createWeapon() {
        CodexId codexId = createCodex(TAU_CODEX);
        WeaponId weaponId = createWeapon(codexId, FUSIL_KROOT);

        assertNotNull(weaponId);

        Codex codex = getCodex(TAU_CODEX).get();
        Weapon weapon = codex.getWeapons()
                             .findFirst()
                             .get();
        Name nameResult = weapon.getName();
        assertEquals(weaponId, weapon.getId());
        assertEquals(new Name(FUSIL_KROOT), nameResult);
    }

    private WeaponId createWeapon(CodexId codex, String name) {
        return codexService.createWeapon(new WeaponCreation(codex, new Name(name)));
    }

    private Weapon getWeapon(CodexId codexId, String name) {
        return getCodex(codexId).getWeapons()
                                .filter(t -> t.getName()
                                              .equals(name))
                                .findFirst()
                                .get();
    }

    private Codex getCodex(CodexId codexId) {
        return codexReader.getCodex(codexId

        );
    }

    @Test
    void setSave() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setSave(codex, entry, "2+");

        Save result = getCodex(TAU_CODEX).get()
                                         .getEntries()
                                         .findFirst()
                                         .get()
                                         .getSave();
        assertEquals(new Save("2+"), result);

    }

    private void setSave(CodexId codexId, EntryId entryId, String s) {
        codexService.modifySave(new SaveModification(codexId, entryId, new Save(s)));
    }

    @Test
    void setLead() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setLead(codex, entry, 2);

        Lead result = getCodex(TAU_CODEX).get()
                                         .getEntries()
                                         .findFirst()
                                         .get()
                                         .getLead();
        assertEquals(new Lead(2), result);

    }

    private void setLead(CodexId codexId, EntryId entryId, int i) {
        codexService.modifyLead(new LeadModification(codexId, entryId, new Lead(i)));
    }

    @Test
    void setAttacks() {
        CodexId codex = createCodex(TAU_CODEX);
        EntryId entry = createEntry(codex, KROOT_ENTRY);
        setAttacks(codex, entry, 6);

        Attack result = getCodex(TAU_CODEX).get()
                                           .getEntries()
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
    void modifyEntryWeaponSkill() {
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
