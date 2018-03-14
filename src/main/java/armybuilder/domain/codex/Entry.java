package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.*;
import lombok.experimental.Wither;

@Wither
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Immutable
public class Entry {
    @NonNull
    private EntryId id;
    private Name name;
}
