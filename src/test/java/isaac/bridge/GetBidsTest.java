package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Bid;
import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.service.BidService;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class GetBidsTest {

    @Autowired
    GameService gameService;

    @Autowired
    RoundService roundService;

    @Autowired 
    BidService bidService;
    
    @Test
    public void getAllBidsTest() {
        int bidsBefore = bidService.getAllBids().size();

        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        Bid bid = new Bid(round.getRoundId(), 0, 0, 1, 1, 1);

        bidService.addBid(bid);

        int bidsAfter = bidService.getAllBids().size();

        boolean isOneBidAdded = (bidsAfter - bidsBefore) == 1;

        Assertions.assertTrue(isOneBidAdded, "One new bid should be added to db.");
    }
}
