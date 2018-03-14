package armybuilder;

import armybuilder.domain.Player;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.events.EventStore;
import armybuilder.domain.game.Game;
import armybuilder.domain.game.Phase;
import armybuilder.domain.game.PlayerId;
import armybuilder.domain.game.command.CreateGame;
import armybuilder.domain.game.service.GameService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class GameStory {

    private EventStore eventStore = new EventStore();
    Application application = new Application(eventStore);
    private ArmyId player1ArmyId = ArmyId.next();
    private ArmyId player2ArmyId = ArmyId.next();

    public void testTwoPLayerGamePhasis() {

        Game gamePlayer1 = service().prepareGame(new CreateGame(player1ArmyId, PlayerId.next()));
        Game gamePlayer2 = service().joinGame(player1ArmyId, PlayerId.next(), gamePlayer1.id);
        assertNotNull(gamePlayer1.id);
        assertEquals(gamePlayer1.id, gamePlayer2.id);
        Phase phase1 = gamePlayer1.getPhase();
        Phase phase2 = gamePlayer1.getPhase();
        assertEquals(Phase.CHECK, phase1);
        assertEquals(phase2, phase1);
        Player player = gamePlayer1.randomBeginner();
//        Player player = gamePlayer2.getBeginner();
//        gamePlayer1.placeUnit(game.getUnitType(UnitType.QG)
//                                  .get(0));
//        game.placeUnit(game.getUnitType(UnitType.TROOP)
//                           .get(0));
//        game.reserveUnit(game.getUnitType(UnitType.TROOP)
//                             .get(0));
//        game.start();
//        Set<Unit> units = game.getMoveAbleUnits();
//        assertEquals(2, unit.size());
//        assertTrue(units.contains());

    }

    private GameService service() {
        return application.getGameService();
    }


}
