package armybuilder.domain.codex;

import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Immutable
@Data
public class CreateCodex {
    public final CodexName name;

}
