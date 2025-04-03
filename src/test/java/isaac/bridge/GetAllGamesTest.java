package isaac.bridge;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class GetAllGamesTest {

    @Autowired 
    private GameService gameService;
    
    @Test
    public void getNumberOfGamesTest() {
        List<Game> initialGames = gameService.getAllGames();

        gameService.createGame();
        gameService.createGame();
        gameService.createGame();
        gameService.createGame();

        List<Game> afterGames = gameService.getAllGames();
        
        int actual = afterGames.size() - initialGames.size();
        int expected = 4;

        Assertions.assertEquals(expected, actual, "4 new games should be added to db.");
        
        
    }
}
