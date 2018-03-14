package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;
import lombok.NonNull;

@Data
@Immutable
public class CreateEntry {
    @NonNull
    public final CodexId codexId;
    @NonNull
    public final Name name;
}
