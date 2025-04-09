package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Card;
import isaac.bridge.entity.Game;
import isaac.bridge.entity.Hand;
import isaac.bridge.entity.Round;
import isaac.bridge.entity.Trick;
import isaac.bridge.service.CardService;
import isaac.bridge.service.GameService;
import isaac.bridge.service.HandService;
import isaac.bridge.service.RoundService;
import isaac.bridge.service.TrickService;

@SpringBootTest
public class CreateCardTest {

    @Autowired
    GameService gameService;

    @Autowired
    RoundService roundService;

    @Autowired
    TrickService trickService;

    @Autowired
    HandService handService;

    @Autowired 
    CardService cardService;

    @Test
    public void addCardToTrick() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        // Imitate bidding
        round.setContractLevel(3);
        round.setContractSuit(3);    
        round.setDeclarerDirection(2);   // S 3S
        round.setContractModifier(0);

        roundService.updateRound(round);
        Trick trick = trickService.createTrick(round);

        int westId = game.getWestId();

        Hand hand = handService.getHandByRoundIdAndPlayerId(round.getRoundId(), westId);
        int[] cards = hand.getCardArray();
        
        Card card = new Card(trick.getTrickId(), 3, cards[1], cards[0], 1);
        Card addedCard = cardService.addCard(card);

        boolean didCardAdd = addedCard != null;

        Assertions.assertTrue(didCardAdd, "Card should add to db with no exceptions.");

    }
    
}
