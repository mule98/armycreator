package armybuilder.domain.codex;

import armybuilder.Application;
import armybuilder.domain.codex.reader.CodexReader;
import armybuilder.domain.codex.service.CodexService;
import armybuilder.domain.events.EventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CodexStory {

	Application application;
	private CodexService codexService;
	private CodexReader codexReader;

	@BeforeEach
	public void beforeEach(){
		application= Application.start(new EventStore());
		codexService= application.getCodexService();
		codexReader = application.getCodexReader();

	}

	@Test
	public void testCreateCodex() {
		final CodexName tyranids = new CodexName("Tyranids");
		codexService.create(new CreateCodex(tyranids));
		Optional<Codex> codex = codexReader.getCodex("Tyranids");
		assertTrue(codex.isPresent());
		assertEquals(tyranids, codex.get().getName());

	}

	@Test
	public void testGetCodexNotExist() {
		Optional<Codex> faction = codexReader.getCodex("Tyranids");
		assertFalse(faction.isPresent());
	}

	@Test
	public void testCreateCodexTwice() {
		codexService.create(new CreateCodex(new CodexName("Tyranids")));
		assertThrows(RuntimeException.class, () ->codexService.create(new CreateCodex(new CodexName("Tyranids"))), "Codex Tyranids already exists");
	}
}
