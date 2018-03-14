package armybuilder.domain.game;

import java.util.UUID;

public class PlayerId {
    private final UUID uuid;

    private PlayerId(UUID
                             uuid) {

        this.uuid = uuid;
    }

    public static PlayerId next() {
        return new PlayerId(UUID.randomUUID());
    }
}
