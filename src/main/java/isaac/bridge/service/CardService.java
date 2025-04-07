package isaac.bridge.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import isaac.bridge.entity.Card;
import isaac.bridge.entity.Game;
import isaac.bridge.entity.Round;
import isaac.bridge.entity.Trick;
import isaac.bridge.exception.ClientErrorException;
import isaac.bridge.repository.CardRepository;

@Service
public class CardService {

    private CardRepository cardRepository;
    private TrickService trickService;
    private RoundService roundService;
    private GameService gameService;
    private HandService handService;

    public CardService(CardRepository cardRepository, TrickService trickService, RoundService roundService, GameService gameService, HandService handService) {
        this.cardRepository = cardRepository;
        this.trickService = trickService;
        this.roundService = roundService;
        this.gameService = gameService;
        this.handService = handService;
    }

    public List<Card> getCardsByTrickId(int trickId) {
        return cardRepository.findAllByTrickId(trickId).orElse(null);
    }

    /**
     * Should make sure that connect trick id is valid and add to play if the card is valid
     * Needs to be implemented
     * 
     * @param card
     * @return
     */
    public Card addCard(Card card) {
        if (trickService.getTrickById(card.getTrickId()) == null) {
            throw new ClientErrorException("Cannot add card to a null trick.");
        };

        List<Card> previousCards = getCardsByTrickId(card.getTrickId());

        if (previousCards.size() == 4) {
            throw new ClientErrorException("Cannot add a card to a full trick.");
        }

        if (!isValidCard(previousCards, card)) {
            throw new ClientErrorException("Card is not valid to be played on this trick.");
        }

        Card addedCard = cardRepository.save(card);

        if (previousCards.size() == 3) {
            updateTrickWithWinningCard(getCardsByTrickId(card.getTrickId()));
        }
       

        return addedCard;
    }

    /**
     * Determines if the card is valid 
     * Check if the player even has the selected card
     * Check that correct player/sequence of the card is correct
     * Handle case where card finishes the trick, update trick table and make new trick
     * 
     * @param previousCards
     * @param card
     * @return
     */
    public boolean isValidCard(List<Card> previousCards, Card card) {
        previousCards.sort(Comparator.comparing(Card::getPlaySequence));

        if (!isSequenceAndDirectionCorrect(previousCards, card)) return false;

        if (!isCardInPlayerHand(card)) return false;


        return true;
        
    }

    public boolean isSequenceAndDirectionCorrect(List<Card> previousCards, Card card) {
        Card lastCard = previousCards.isEmpty() ? previousCards.get(previousCards.size() - 1) : null;

        if (lastCard != null) {
            return (card.getPlaySequence() - lastCard.getPlaySequence() == 1) &&
                   ((card.getPlayerDirection() - lastCard.getPlayerDirection() + 4) % 4 == 1);
        }

        // First card in trick
        int expectedPlayerDirection = trickService.getTrickById(card.getTrickId()).getLeaderDirection();

        return (card.getPlaySequence() == 1) &&
               (card.getPlayerDirection() == expectedPlayerDirection);
    }

    public boolean isCardInPlayerHand(Card card) {

        Trick trick = trickService.getTrickById(card.getTrickId());
        Round round = roundService.getRoundById(trick.getRoundId());
        Game game = gameService.getGameById(round.getGameId());
        int[] playerIds = {game.getNorthId(), game.getEastId(), game.getSouthId(), game.getWestId()};
        int playerId = playerIds[card.getPlayerDirection()];
        String cards = handService.getHandByRoundIdAndPlayerId(round.getRoundId(), playerId).getCards();
        String[] cardArray = handService.cardStringToArray(cards);
        // Make sure player was even dealt this card
        if (!Arrays.asList(cardArray).contains(card.toString())) return false;
        
        // Determine if this card has already been played this round
        List<Trick> allTricksThisRound = trickService.getTricksByRoundId(round.getRoundId());
        List<String> allCardPlayedByPlayerThisRound = new ArrayList<>();
        for (Trick t : allTricksThisRound) {
            List<Card> curCards = getCardsByTrickId(t.getTrickId());
            for (Card c : curCards) {
                if (c.getPlayerDirection() == card.getPlayerDirection()) {
                    allCardPlayedByPlayerThisRound.add(c.toString());
                }
            }
        }

        return !allCardPlayedByPlayerThisRound.contains(card.toString());

    }

    /**
     * Assumes it is passed a list of 4 cards
     * Determine which card won the trick and updates trick accordingly
     * Creates a new trick for next cards to be placed in
     * 
     * @param cards
     */
    public void updateTrickWithWinningCard(List<Card> cards) {
        cards.sort(Comparator.comparing(Card::getPlaySequence));
        Trick trick = trickService.getTrickById(cards.get(0).getTrickId());
        Round round = roundService.getRoundById(trick.getRoundId());
        int trump = round.getContractSuit();
        int leadSuit = cards.get(0).getSuit();

        Card winningCard = cards.get(0);
        for (Card c : cards) {
            if (c.getSuit() == trump) {
                if (winningCard.getSuit() != trump || c.getRank() > winningCard.getRank()) {
                    winningCard = c;
                }
            } else if (winningCard.getSuit() != trump && c.getSuit() == leadSuit && c.getRank() > winningCard.getRank()) {
                winningCard = c;
            }
        }

        trick.setWinningDirection(winningCard.getPlayerDirection());
        trickService.saveTrick(trick);

        List<Trick> roundTricks = trickService.getTricksByRoundId(round.getRoundId());
        if (roundTricks.size() == 13) {
            updateRoundAfterTricksDone(round, roundTricks);
        }
    }

    /**
     * Called after round has 13 associated tricks
     * This should score the round based on winner of each trick
     * Should update game state and vulnerabilities
     * 
     * @param round
     */
    public void updateRoundAfterTricksDone(Round round, List<Trick> tricks) {
        int [] NSTricksEWTricks = new int[2];

        for (Trick trick : tricks) {
            NSTricksEWTricks[trick.getWinningDirection() % 2]++;
        }

        round.setNorthSouthTricksTaken(NSTricksEWTricks[0]);
        round.setEastWestTricksTaken(NSTricksEWTricks[1]);

        Round updatedRound = roundService.updateRound(round);

        scoreRound(updatedRound);
    }

    /**
     * Needs implemented
     * Should update games NS EW scores based on contract and tricks taken
     * 
     * @param round
     */
    public void scoreRound(Round round) {

    }
    
}
