package isaac.bridge.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import isaac.bridge.entity.Game;
import isaac.bridge.entity.Hand;
import isaac.bridge.entity.Round;
import isaac.bridge.repository.HandRepository;

@Service
public class HandService {

    @Autowired
    HandRepository handRepository;

    /**
     * This methods take the deck associated with the current round and that round's ID and deals out 13 cards to each player
     * The game is passed so that each hand can be associated with the correct playerId
     * 
     * @param round
     * @param game
     */
    public void dealHands(Round round, Game game) {

        int roundId = round.getRoundId();
        String[] deck = round.getDeck();
        int dealer = round.getDealerDirection();
        // List of player ids with north being at index 0
        int[] playerIds = {game.getNorthId(), game.getEastId(), game.getSouthId(), game.getWestId()};

        for (int i=0; i<4; i++) {
            int currentPlayerId = playerIds[(dealer + i) % 4];

            String cards = handToString(Arrays.copyOfRange(deck, i*13, (i+1)*13));
            Hand hand = new Hand(roundId, currentPlayerId, cards);

            handRepository.save(hand);
        }

        
    }

    public String handToString(String[] hand) {
        if (hand == null || hand.length == 0) {
            throw new RuntimeException("No cards were passed to handToString method.");
        }
        return String.join(", ", hand);
    }

    public String[] cardStringToArray(String cards) {
        return Arrays.stream(cards.split(","))
                     .map(String::trim)
                     .toArray(String[]::new);
    }

    public List<Hand> getAllHands() {
        return handRepository.findAll();
    }

    public List<Hand> getHandsByRoundId(int roundId) {
        return handRepository.findAllByRoundId(roundId).orElse(null);
    }
    
    public Hand getHandByRoundIdAndPlayerId(int roundId, int playerId) {
        return handRepository.findByRoundIdAndPlayerId(roundId, playerId).orElse(null);
    }
}
