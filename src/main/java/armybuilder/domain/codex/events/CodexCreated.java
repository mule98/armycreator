package armybuilder.domain.codex.events;

import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import armybuilder.domain.codex.CodexName;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Immutable
@Data
@EqualsAndHashCode(callSuper = true)
public class CodexCreated extends CodexModified {
    @NonNull
    private final CodexName name;

    public CodexCreated(CodexId id, CodexName name) {
        super(id);
        this.name = name;
    }

    @Override
    protected Codex applyChange(Codex event) {
        return new Codex().create(this);
	}

}
