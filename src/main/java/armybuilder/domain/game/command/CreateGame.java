package armybuilder.domain.game.command;

import armybuilder.domain.army.ArmyId;
import armybuilder.domain.game.PlayerId;

public class CreateGame {
    private final ArmyId armyId;
    private final PlayerId playerId;

    public CreateGame(ArmyId armyId, PlayerId playerId) {
        this.armyId = armyId;
        this.playerId = playerId;
    }

    ArmyId getArmyId() {
        return armyId;
    }

    PlayerId getPlayerId() {
        return playerId;
    }
}
