package armybuilder.domain.codex;

import armybuilder.domain.common.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.UUID;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class EntryId extends Id {

    private final UUID uuid;

    public static EntryId next() {
        return new EntryId(UUID.randomUUID());
    }
}
