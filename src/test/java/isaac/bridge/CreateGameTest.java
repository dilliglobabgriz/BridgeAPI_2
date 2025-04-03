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
    public void createGameIdIncrementTest() {
        Game game = gameService.createGame();
        Game game2 = gameService.createGame();

        int game1Id = game.getId();
        int game2Id = game2.getId();

        int actual = game2Id - game1Id;
        int expected = 1;

        Assertions.assertEquals(expected, actual, "Game ids should increment by one for each creation.");
    }

    
}
