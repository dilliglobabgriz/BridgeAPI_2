package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.service.GameService;

@SpringBootTest
public class CreateGameTest {

    @Autowired
    private GameService gameService;

    @Test 
    public void createGameTest() {
        Game game = gameService.createGame();

        String actual = game.toString();
        String expected = "Game{id=1, dealerDirection=0, northSouthScore=0, eastWestScore=0}";

        Assertions.assertEquals(expected, actual, "Game setup incorrect.");
    }

    @Test 
    public void createSecondGameTest() {
        Game game = gameService.createGame();

        String actual = game.toString();
        String expected = "Game{id=2, dealerDirection=0, northSouthScore=0, eastWestScore=0}";

        Assertions.assertEquals(expected, actual, "Game setup incorrect.");
    }
    
}
