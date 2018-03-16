package armybuilder.domain.codex.reader;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexName;
import armybuilder.domain.codex.CodexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodexReader {
    private final CodexRepository codexRepository;

    @Autowired
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
