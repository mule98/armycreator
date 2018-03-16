package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.reader.CodexReader;
import armybuilder.domain.codex.service.CodexService;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CodexStory {

    private CodexService codexService;
    private CodexReader codexReader;

    @BeforeEach
    void beforeEach() {
        EventStore eventStore = new EventStore();
        EventBus.instance()
                .subscribe(eventStore);
        CodexRepository codexRepository = new CodexRepository(eventStore);
        codexService = new CodexService(codexRepository);
        codexReader = new CodexReader(codexRepository);

    }

    @Test
    void testCreateCodex() {
        final CodexName tyranids = new CodexName("Tyranids");
        codexService.create(new CreateCodex(tyranids));
        Optional<Codex> codex = codexReader.getCodex("Tyranids");
        assertTrue(codex.isPresent());
        assertEquals(tyranids,
                     codex.get()
                          .getName());

    }

    @Test
    void testGetCodexNotExist() {
        Optional<Codex> faction = codexReader.getCodex("Tyranids");
        assertFalse(faction.isPresent());
    }

    @Test
    void testCreateCodexTwice() {
        createCodex();
        assertThrows(RuntimeException.class,
                     this::createCodex,
                     "Codex Tyranids already exists");
    }

    private CodexId createCodex() {
        return codexService.create(new CreateCodex(new CodexName("Tyranids")));
    }

    @Test
    void testInsertEntry() {
        CodexId codexId = createCodex();
        CreateEntry createEntry = new CreateEntry(codexId, new Name("Lictor"));
        codexService.insertEntry(createEntry);
        Codex codex = codexReader.getCodex(codexId);
        assertTrue(codex.entries()
                        .anyMatch(t -> new Name("Lictor").equals(t.getName())));
    }
}
