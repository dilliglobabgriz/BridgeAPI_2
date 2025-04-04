package isaac.bridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import isaac.bridge.entity.Bid;
import isaac.bridge.exception.ClientErrorException;
import isaac.bridge.repository.BidRepository;

@Service
public class BidService {
    
    private BidRepository bidRepository;
    private RoundService roundService;

    public BidService(BidRepository bidRepository, RoundService roundService) {
        this.bidRepository = bidRepository;
        this.roundService = roundService;
    }

    public List<Bid> getAllBids() {
        return bidRepository.findAll();
    }

    public List<Bid> getBidsByRoundId(int roundId) {
        return bidRepository.findAllByRoundId(roundId).orElse(null);
    }

    public Bid addBid(Bid bid) {
        if (roundService.getRoundById(bid.getRoundId()) == null) {
            throw new ClientErrorException("Cannot add bid to a null round.");
        }
        // Verify bid is legal given rest of bidding sequence
        List<Bid> bidHistory = getBidsByRoundId(bid.getRoundId());
        if (!isValidBid(bidHistory, bid)) {
            throw new ClientErrorException("Bid is not valid given previous bids.");
        }

        return bidRepository.save(bid);

    }

    /**
     * Should ensure that bid is of correct level, suit, and type
     * Just passing true for now for testing purposes
     * 
     * @param bidHistory
     * @param bid
     * @return
     */
    public boolean isValidBid(List<Bid> bidHistory, Bid bid) {
        return true;
    }
}
