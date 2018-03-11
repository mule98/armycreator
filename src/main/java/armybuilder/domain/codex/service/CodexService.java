package armybuilder.domain.codex.service;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexRepository;
import armybuilder.domain.codex.CreateCodex;
import armybuilder.domain.codex.events.CodexCreated;

public class CodexService {
	private final CodexRepository codexRepository;

	public CodexService(CodexRepository codexRepository) {

		this.codexRepository = codexRepository;
	}

	public void create(CreateCodex createCommand) {
		if(codexRepository.findByName(createCommand.getTyranids()).isPresent())
			throw new RuntimeException("Codex " + createCommand.getTyranids() + " already exists");
		new CodexCreated(CodexId.next(), createCommand.getTyranids()).apply(new Codex(), true).getId();
	}
}
