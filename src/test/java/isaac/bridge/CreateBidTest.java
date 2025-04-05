package isaac.bridge;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import isaac.bridge.entity.Bid;
import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.exception.ClientErrorException;
import isaac.bridge.service.BidService;
import isaac.bridge.service.GameService;
import isaac.bridge.service.RoundService;

@SpringBootTest
public class CreateBidTest {

    @Autowired
    BidService bidService;

    @Autowired
    GameService gameService;

    @Autowired
    RoundService roundService;

    @Test 
    public void addBidToRound() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);

        int bidsBefore = bidService.getBidsByRoundId(round.getRoundId()).size();
        Bid bid = new Bid(round.getRoundId(), 0, 3, 1, 0, 1);

        bidService.addBid(bid);
        int bidsAfter = bidService.getBidsByRoundId(round.getRoundId()).size();

        boolean isOneBidAdded = (bidsAfter - bidsBefore) == 1;

        Assertions.assertTrue(isOneBidAdded, "One new bid should be added to db."); 
    }

    @Test
    public void addBidToInvalidRound() {
        Round round = new Round();
        round.setRoundId(8888);

        Bid bid = new Bid(8888, 0, 0, 1, 1, 1 );

        Exception exception = Assertions.assertThrows(ClientErrorException.class, () -> {
            bidService.addBid(bid);
        });

        Assertions.assertEquals("Cannot add bid to a null round.", exception.getMessage());

    }

    @Test 
    public void addInvalidBidToRound() {
        Game game = gameService.createGame();
        Round round = roundService.createRound(game);
        Bid validBid1 = new Bid(round.getRoundId(), 0, 1, 1, 1, 1);  // N 1D
        Bid validBid2 = new Bid(round.getRoundId(), 1, 4, 1, 1, 2);  // E 1NT
        Bid validBid3 = new Bid(round.getRoundId(), 2, 1, 3, 1, 3);  // S 3D
        Bid validBid4 = new Bid(round.getRoundId(), 3, 0, 3, 2, 4);  // W X
        Bid validBid5 = new Bid(round.getRoundId(), 0, 0, 3, 0, 5);  // N P
        // East tries to bid 3 club over previous 3 diamond bid
        Bid invalidBid = new Bid(round.getRoundId(), 1, 0, 3, 1, 6);  // E 3C

        bidService.addBid(validBid1);
        bidService.addBid(validBid2);
        bidService.addBid(validBid3);
        bidService.addBid(validBid4);
        bidService.addBid(validBid5);

        Exception exception = Assertions.assertThrows(ClientErrorException.class, () -> {
            bidService.addBid(invalidBid);
        });

        Assertions.assertEquals("Bid is not valid given previous bids.", exception.getMessage());

    }
    
}
