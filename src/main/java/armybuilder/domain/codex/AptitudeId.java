package armybuilder.domain.codex;

import lombok.Data;

import java.util.UUID;

@Data
public class AptitudeId {
    private final UUID id = UUID.randomUUID();
}
