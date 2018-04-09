package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.events.Event;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Immutable
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public abstract class CodexModified extends Event<CodexId, Codex> {
    @NonNull
    private final CodexId id;
}
