package isaac.bridge.service;

import java.util.Comparator;
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
     * First make sure that the bidding is not done, 3 passes in a row, or hand is passed out
     * Check doubles, make sure only passes between double and last oponent bid
     * Check redoubles, make sure only passes between redouble and oponent double
     * Check suit bids, make sure level >= prev, and if level equal, suit is higher
     * As long as bidding is not over pass should be fine
     * 
     * @param bidHistory
     * @param bid
     * @return
     */
    public boolean isValidBid(List<Bid> bidHistory, Bid bid) {
        bidHistory.sort(Comparator.comparing(Bid::getBidSequence)); // Sort bids in order
        int size = bidHistory.size();
    
        if (isBiddingComplete(bidHistory)) {
            return false;
        }

        if (bid.getBidType() == 3) {     // Redouble
            Bid lastDouble = getLastBidOfType(2, bidHistory);
            if (lastDouble == null) {
                return false;
            }

            // Ensure that there were no suit or redoubles after the double
            
        }

        if (bid.getBidType() == 2) {     // Double

        }

        if (bid.getBidType() == 1) {     // Suit bid

        }

        return true;
    }

    /**
     * Takes a sorted bid history and determines if there have been 3 passes in a row
     * Also returns true if the hand is passed out, 4 passes to start bidding
     * 
     * @param bidHistory must be sorted by bid sequence
     * @return
     */
    public boolean isBiddingComplete(List<Bid> bidHistory) {
        if (bidHistory.size() < 4) {
            return false;
        }

        // Check for last 3 bids being passes
        // If any of the last 3 bids is not a pass the bidding is not over
        for (int i=bidHistory.size()-3; i<bidHistory.size(); i++) {
            if (bidHistory.get(i).getBidType() != 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Finds the last bid in a sorted bid or the specified type (pass, suit, X, XX)
     * 
     * @param bidType
     * @param bidHistory
     * @return The bid if exists, null otherwise
     */
    public Bid getLastBidOfType(int bidType, List<Bid> bidHistory) {
        for (int i=bidHistory.size()-1; i>=0; i--) {
            if (bidHistory.get(i).getBidType() == bidType) {
                return bidHistory.get(i);
            }
        }

        return null;
    }
}
