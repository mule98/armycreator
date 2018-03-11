package armybuilder.domain.codex;

import armybuilder.domain.codex.events.CodexCreated;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Wither;

@Getter
@Wither(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Immutable
public class Codex {
	private CodexName name;
	private CodexId id;

	public Codex apply(CodexCreated codexCreated) {
		return withId(codexCreated.getId()).withName(codexCreated.getName());
	}
}
