package armybuilder;

import armybuilder.domain.army.ArmyReader;
import armybuilder.domain.army.ArmyRepository;
import armybuilder.domain.army.services.ArmyService;
import armybuilder.domain.codex.CodexRepository;
import armybuilder.domain.codex.reader.CodexReader;
import armybuilder.domain.codex.service.CodexService;
import armybuilder.domain.events.EventBus;
import armybuilder.domain.events.EventStore;

public class Application {
	private final ArmyRepository armyRepository;
	private final EventStore eventStore;
	private final ArmyReader reader;
	private final CodexRepository codexRepository;
	private CodexService codexService;
	private CodexReader codexReader;
	private ArmyService armyService;

	private Application(EventStore eventStore) {
		EventBus.instance().subscribe(eventStore);
		this.eventStore = eventStore;
		armyRepository = new ArmyRepository(eventStore);
		armyService = new ArmyService(armyRepository);
		reader = new ArmyReader(armyRepository);
		codexRepository = new CodexRepository(eventStore);
		codexService = new CodexService(codexRepository);
		codexReader = new CodexReader(codexRepository);
	}

	public static Application start(EventStore eventStore) {

		return new Application(eventStore);
	}

	public ArmyService getArmyService() {
		return armyService;
	}

	public ArmyReader getArmyReader() {
		return this.reader;
	}

	public CodexService getCodexService() {
		return codexService;
	}

	public CodexReader getCodexReader() {
		return codexReader;
	}
}
