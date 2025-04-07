package isaac.bridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import isaac.bridge.entity.Round;
import isaac.bridge.entity.Trick;
import isaac.bridge.exception.ClientErrorException;
import isaac.bridge.repository.TrickRepository;

@Service
public class TrickService {
    
    private TrickRepository trickRepository;
    private RoundService roundService;

    public TrickService(TrickRepository trickRepository, RoundService roundService) {
        this.trickRepository = trickRepository;
        this.roundService = roundService;
    }

    public List<Trick> getTricksByRoundId(int roundId) {
        return trickRepository.findAllByRoundId(roundId).orElse(null);
    }

    public Trick getTrickById(int id) {
        return trickRepository.findById(id).orElse(null);
    }

    public Trick saveTrick(Trick trick) {
        return trickRepository.save(trick);
    }

    public Trick createTrick(Round round) {
        // Validate round exists in db and bidding is done
        Round validatedRound = roundService.getRoundById(round.getRoundId());
        if (validatedRound == null) {
            throw new ClientErrorException("Round must already exist to add trick.");
        }
        if (!isBiddingDone(validatedRound)) {
            throw new ClientErrorException("Cannot add trick until bidding is complete.");
        }

        List<Trick> previousTricks = getTricksByRoundId(validatedRound.getRoundId());
        int trickNumber = previousTricks.size() + 1;
        int leaderDirection;

        if (trickNumber > 13) {
            throw new ClientErrorException("All 13 tricks have already been played.");
        }

        if (previousTricks.isEmpty()) {   // One left of the declarer
            leaderDirection = (round.getDeclarerDirection() + 1) % 4;  
        } else {                          // The winner of the previous trick
            Trick lastTrick = previousTricks.get(previousTricks.size() - 1);
            if (lastTrick.getWinningDirection() == -1) {
                throw new ClientErrorException("New trick cannot be added until previous winner is determined.");
            }

            leaderDirection = lastTrick.getWinningDirection();
        }

        Trick trick = new Trick(validatedRound.getRoundId(), trickNumber, leaderDirection);

        return trickRepository.save(trick);
    }

    /**
     * Returns false unless the bidding is done and winning contract is determined
     * Current implementation is questionable
     * 
     * @param round
     * @return
     */
    public boolean isBiddingDone(Round round) {
        return round.getContractLevel() != 0;
    }

}
