package isaac.bridge;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;
import isaac.bridge.service.TrickService;

@SpringBootTest
public class CreateTrickTest {

    @Autowired
    GameService gameService;

    @Autowired
    RoundService roundService;

    @Autowired
    TrickService trickService;
    
    @Test
    public void createTrickTest() {

        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        round.setContractLevel(3);
        round.set

    }
}
