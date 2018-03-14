package armybuilder.domain.game;

import armybuilder.domain.Player;

public class Game {
    public GameId id;
    private Phase phase;

    public void placeBattleField() {

    }

    public Phase getPhase() {
        return phase;
    }

    public Player randomBeginner() {
        return null;
    }
}
