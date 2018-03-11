package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexName;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public class CodexCreated extends Event<CodexId, Codex> {
	private final CodexId id;
	private final CodexName name;

	public CodexCreated(CodexId id, CodexName name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public Codex applyChange(Codex event) {
		return new Codex().apply(this);
	}

}
