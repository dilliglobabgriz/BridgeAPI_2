package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Player;
import isaac.bridge.entity.Round;
import isaac.bridge.repository.GameRepository;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class CreateRoundTest {
    
    @Autowired
    private RoundService roundService;

    @Autowired
    private GameRepository gameRepo;

    @Autowired 
    private GameService gameService;

    // @Test 
    // public void createRoundIdTest() {
    //     Game newGame = gameService.createGame();

    //     newGame.setId(9999);
    //     gameRepo.save(newGame);

    //     Round round = roundService.createRound(newGame);

    //     long actual = round.getGameId();
    //     long expected = 9999;

    //     Assertions.assertEquals(expected, actual, "Game id should match with round FK 9999");
    // }

    @Test 
    public void transientDeckFillTest() {
        Round round = new Round();

        String[] deck = round.getDeck();

        boolean deckHasAceOfHearts = false;

        for (String card : deck) {
            if (card.equals("AH")) {
                deckHasAceOfHearts = true;
            }
        }

        Assertions.assertTrue(deckHasAceOfHearts, "One of the cards in the deck should be the AH.");
    }

    // NEED TO ADD ASSERTTHROWS 
    // @Test 
    // public void createRoundDefaultIdTest() {
    //     Game game = new Game(9999, 9999, 9999, 9999);

    //     Round round = roundService.createRound(game);

    //     long actual = round.getGameId();
    //     long expected = 1;

    //     Assertions.assertEquals(expected, actual, "Game id should match with round FK 1");
    // }
}
