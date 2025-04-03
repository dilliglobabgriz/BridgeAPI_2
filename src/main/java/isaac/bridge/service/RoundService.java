package isaac.bridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.exception.IllegalGameStateException;
import isaac.bridge.repository.RoundRepository;

@Service
public class RoundService {

    @Autowired
    private RoundRepository roundRepository;
    
    /**
     * Creates a new round instance connected to a game id
     * 
     * @param game includes the game id and the round history to determine next dealer
     * @return
     */
    public Round createRound(Game game) {

        if (game.getId() == 0) {
            throw new IllegalGameStateException("Game must be initialized before a round can begin.");
        }

        ArrayList<Round> rounds = game.getRounds();

        int newRoundDealer;

        if (rounds.isEmpty()) {
            newRoundDealer = game.getFirstDealerDirection();
        } else {
            newRoundDealer = rounds.get(rounds.size() - 1).getNextDealerDirection();
        }

        Round newRound = new Round(game.getId(), newRoundDealer, 1);

        return roundRepository.save(newRound);
    }

    public void populateHands(Game game, long roundId) {

    }

    public List<Round> getAllRounds() {
        return roundRepository.findAll();
    }

}
