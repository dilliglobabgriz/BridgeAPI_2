package isaac.bridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import isaac.bridge.entity.Card;
import isaac.bridge.exception.ClientErrorException;
import isaac.bridge.repository.CardRepository;

@Service
public class CardService {

    private CardRepository cardRepository;
    private TrickService trickService;

    public CardService(CardRepository cardRepository, TrickService trickService) {
        this.cardRepository = cardRepository;
        this.trickService = trickService;
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
        List<Card> previousCards = getCardsByTrickId(card.getTrickId());

        if (previousCards.size() == 4) {
            throw new ClientErrorException("Cannot add a card to a full trick.");
        }

        return cardRepository.save(card);
    }
    
}
