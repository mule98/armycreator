package armybuilder.domain.codex.reader;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexName;
import armybuilder.domain.codex.CodexRepository;

import java.util.Optional;

public class CodexReader {
	private final CodexRepository codexRepository;

	public CodexReader(CodexRepository codexRepository) {

		this.codexRepository = codexRepository;
	}

	public Optional<Codex> getCodex(String name) {
		return codexRepository.findByName(new CodexName(name));
	}

    public Codex getCodex(CodexId codexId) {
        return codexRepository.getById(codexId);
    }
}
