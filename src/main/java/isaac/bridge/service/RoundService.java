package isaac.bridge.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

        if (game.getId() == null) {
            throw new IllegalGameStateException("Game must be initialized before a round can begin.");
        }
        long gameId = game.getId();

        ArrayList<Round> rounds = game.getRounds();

        int newRoundDealer;

        if (rounds.isEmpty()) {
            newRoundDealer = game.getDealerDirection();
        } else {
            newRoundDealer = rounds.get(rounds.size() - 1).getNextDealerDirection();
        }

        Round newRound = new Round(gameId, newRoundDealer);

        return newRound;
    }

    public void populateHands(Game game, long roundId) {

    }

}
