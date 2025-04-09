package isaac.bridge;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.entity.Hand;
import isaac.bridge.service.GameService;
import isaac.bridge.service.HandService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class CreateHandsTest {

    @Autowired
    GameService gameService;
     
    @Autowired
    RoundService roundService;

    @Autowired 
    HandService handService;

    // @Test
    // public void handsCreatedOnRoundCreation() {
    //     Game game = gameService.createGame();

    //     Round noHandsRound = new Round(99999, game.getId(), 0, 1);
    //     roundService.updateRound(noHandsRound);


    //     int handsDealtBefore = 0;
        
    //     List<Hand> handsBefore = handService.getHandsByRoundId(99999);

    //     for (Hand hand : handsBefore) {
    //         if (hand.getCards().length() == 50) {
    //             handsDealtBefore++;
    //         }
    //     }

    //     handService.dealHands(noHandsRound, game);

    //     int handsDealtAfter = 0;

    //     List<Hand> handsAfter = handService.getHandsByRoundId(99999);

    //     for (Hand hand : handsAfter) {
    //         if (hand.getCards().length() == 50) {
    //             handsDealtAfter++;
    //         }
    //     }

    //     Assertions.assertEquals(handsDealtBefore + 4, handsDealtAfter, "4 new hands should be added.");
    // }

    @Test
    public void addHandsToRound() {
        Round round = roundService.getRoundById(11111);
        Game game = gameService.getGameById(round.getGameId());

        handService.dealHands(round, game);

        int actualNewHands = handService.getHandsByRoundId(round.getRoundId()).size();

        int expectedNewHands = 4;

        Assertions.assertEquals(expectedNewHands, actualNewHands, "Deal hands should create 4 new hand entries.");
    }
    
}
