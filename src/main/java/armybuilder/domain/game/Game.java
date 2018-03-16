package armybuilder.domain.game;

import armybuilder.domain.Player;
import lombok.Data;

@Data
public class Game {
    GameId id;
    private Phase phase;

    void placeBattleField() {

    }


    public Player randomBeginner() {
        return null;
    }
}
