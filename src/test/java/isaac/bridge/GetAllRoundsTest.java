package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class GetAllRoundsTest {

    @Autowired
    GameService gameService;

    @Autowired 
    RoundService roundService;

    @Test
    public void getNumberOfRoundsTest() {
        Game game = gameService.createGame();

        int roundsBefore = roundService.getAllRounds().size();

        roundService.createRound(game);
        roundService.createRound(game);
        roundService.createRound(game);
        roundService.createRound(game);
        roundService.createRound(game);

        int roundsAfter = roundService.getAllRounds().size();

        int actual = roundsAfter - roundsBefore;
        int expected = 5;

        Assertions.assertEquals(expected, actual, "5 new rounds should be created with the associated game.");

    }

    @Test
    public void getRoundsByGameIdTest() {
        Game dummy = gameService.createGame();
        Game game = gameService.createGame();


        int roundsBefore = roundService.getRoundsByGameId(game.getId()).size();

        roundService.createRound(dummy);
        roundService.createRound(dummy);
        roundService.createRound(game);
        roundService.createRound(game);
        roundService.createRound(game);

        int roundsAfter = roundService.getRoundsByGameId(game.getId()).size();

        int actual = roundsAfter - roundsBefore;
        int expected = 3;    // 3 rounds are added to our target, but 5 to all games

        Assertions.assertEquals(expected, actual, "3 new rounds should be added to the game object.");

    }
    
}
