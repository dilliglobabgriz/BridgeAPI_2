package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.entity.Trick;
import isaac.bridge.exception.ClientErrorException;
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
        // Imitate bidding
        round.setContractLevel(3);
        round.setContractSuit(3);    
        round.setDeclarerDirection(2);   // S 3S
        round.setContractModifier(0);

        roundService.updateRound(round);

        Trick trick = trickService.createTrick(round);

        // System.out.println("================================================");
        // System.out.println(trick.toString());
        // System.out.println("================================================");

        boolean isTrickAsExpected = trick.getRoundId() == round.getRoundId() &&
                                    trick.getLeaderDirection() == 3 &&
                                    trick.getTrickNumber() == 1;

        Assertions.assertTrue(isTrickAsExpected, "Trick not initialized as expected with createTrick method.");

    }

    @Test 
    public void createMultipleTricks() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        // Imitate bidding
        round.setContractLevel(3);
        round.setContractSuit(3);    
        round.setDeclarerDirection(2);   // S 3S
        round.setContractModifier(0);

        roundService.updateRound(round);

        Trick trick1 = trickService.createTrick(round);
        trick1.setWinningDirection(2);
        trickService.saveTrick(trick1);

        Trick trick2 = trickService.createTrick(round);
        trick2.setWinningDirection(2);
        trickService.saveTrick(trick2);

        Trick trick3 = trickService.createTrick(round);
        trick3.setWinningDirection(3);
        trickService.saveTrick(trick3);

        

        int actualTricksCreated = trickService.getTricksByRoundId(round.getRoundId()).size();
        int expectedTricksCreated = 3;

        Assertions.assertEquals(expectedTricksCreated, actualTricksCreated, "3 new tricks should exist in the round.");
    }

    @Test 
    public void addTrickToRoundBiddingNotDone() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);

        Exception exception = Assertions.assertThrows(ClientErrorException.class, () -> {
            trickService.createTrick(round);
        });

        Assertions.assertEquals("Cannot add trick until bidding is complete.", exception.getMessage());
    }

    @Test 
    public void addFourteenTricksToRound() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        // Imitate bidding
        round.setContractLevel(3);
        round.setContractSuit(3);    
        round.setDeclarerDirection(2);   // S 3S
        round.setContractModifier(0);

        roundService.updateRound(round);

        Trick trick1 = trickService.createTrick(round);
        trick1.setWinningDirection(2);
        trickService.saveTrick(trick1);

        Trick trick2 = trickService.createTrick(round);
        trick2.setWinningDirection(2);
        trickService.saveTrick(trick2);

        Trick trick3 = trickService.createTrick(round);
        trick3.setWinningDirection(3);
        trickService.saveTrick(trick3);

        Trick trick4 = trickService.createTrick(round);
        trick4.setWinningDirection(2);
        trickService.saveTrick(trick4);

        Trick trick5 = trickService.createTrick(round);
        trick5.setWinningDirection(2);
        trickService.saveTrick(trick5);

        Trick trick6 = trickService.createTrick(round);
        trick6.setWinningDirection(3);
        trickService.saveTrick(trick6);

        Trick trick7 = trickService.createTrick(round);
        trick7.setWinningDirection(2);
        trickService.saveTrick(trick7);

        Trick trick8 = trickService.createTrick(round);
        trick8.setWinningDirection(2);
        trickService.saveTrick(trick8);

        Trick trick9 = trickService.createTrick(round);
        trick9.setWinningDirection(3);
        trickService.saveTrick(trick9);

        Trick trick10 = trickService.createTrick(round);
        trick10.setWinningDirection(2);
        trickService.saveTrick(trick10);

        Trick trick11 = trickService.createTrick(round);
        trick11.setWinningDirection(2);
        trickService.saveTrick(trick11);

        Trick trick12 = trickService.createTrick(round);
        trick12.setWinningDirection(3);
        trickService.saveTrick(trick12);

        Trick trick13 = trickService.createTrick(round);
        trick13.setWinningDirection(2);
        trickService.saveTrick(trick13);

        Exception exception = Assertions.assertThrows(ClientErrorException.class, () -> {
            trickService.createTrick(round);
        });

        Assertions.assertEquals("All 13 tricks have already been played.", exception.getMessage());
        
    }
}
