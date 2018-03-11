package armybuilder.domain.codex;

import armybuilder.domain.events.Id;
import armybuilder.domain.events.IdTypeMatcher;

class CodexIdIdTypeMatcher implements IdTypeMatcher<CodexId> {
	@Override
	public boolean matchesType(Id id) {
		return id instanceof CodexId;
	}

	@Override
	public CodexId transform(Id id) {
		return (CodexId) id;
	}
}
