package armybuilder.domain.codex.events;

import armybuilder.domain.army.Name;
import armybuilder.domain.codex.AptitudeId;
import armybuilder.domain.codex.Codex;
import armybuilder.domain.codex.CodexId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper = true)
public class AptitudeCreated extends CodexModified {

    @NonNull
    private final AptitudeId aptitudeId;
    @NonNull
    private final Name name;

    public AptitudeCreated(CodexId id, AptitudeId aptitudeId, Name name) {
        super(id);
        this.aptitudeId = aptitudeId;
        this.name = name;
    }

    @Override
    protected Codex applyChange(Codex element) {
        return element.createAptitude(this);
    }
}
