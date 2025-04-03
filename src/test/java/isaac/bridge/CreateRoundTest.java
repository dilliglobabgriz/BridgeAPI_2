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
import isaac.bridge.service.HandService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class CreateRoundTest {
    
    @Autowired
    private RoundService roundService;

    @Autowired
    private GameRepository gameRepo;

    @Autowired 
    private GameService gameService;

    @Autowired 
    private HandService handService;

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
    public void createRoundAndCreateHandsTest() {
        Game game = gameService.createGame();

        int totalHandsBefore = handService.getAllHands().size();

        roundService.createRound(game);

        int totalHandsAfter = handService.getAllHands().size();

        int actual = totalHandsAfter - totalHandsBefore;
        int expected = 4;

        Assertions.assertEquals(expected, actual, "There should be 4 new hands added to DB.");
    }

    @Test void getHandsByRoundIdTest() {
        Game game = gameService.createGame();

        Round round = roundService.createRound(game);

        System.out.println("Cards: " + handService.getHandsByRoundId(round.getRoundId()).get(0).getCards());

        int handsForCurrentRound = handService.getHandsByRoundId(round.getRoundId()).size();

        boolean hasCorrectNumberOfHands = handsForCurrentRound == 4;

        Assertions.assertTrue(hasCorrectNumberOfHands, "Is false if there are not 4 new hands associated with current round");
    }


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
