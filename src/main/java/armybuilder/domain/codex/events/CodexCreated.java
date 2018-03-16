package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexName;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Immutable
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CodexCreated extends Event<CodexId, Codex> {
	private final CodexId id;
	private final CodexName name;


	@Override
    protected Codex applyChange(Codex event) {
		return new Codex().apply(this);
	}

}
