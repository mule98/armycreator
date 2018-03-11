package armybuilder.domain.codex;

import armybuilder.domain.events.Id;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@Immutable
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CodexId extends Id{
	private final UUID id;

	public static CodexId next(){
		return new CodexId(UUID.randomUUID());
	}
}
