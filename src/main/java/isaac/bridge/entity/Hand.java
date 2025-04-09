package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hand")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "handId")
    private int handId;

    // Round and player id make up primary key
    @Column(name = "roundId", nullable = false)  // Foreign key to Round
    private int roundId;

    @Column(name = "playerId", nullable = false)  // Foreign key to Player
    private int playerId;

    @Column(name = "cards")
    private String cards; // A single string representing the 13 cards

    // Constructors
    public Hand() {}

    public Hand(int roundId, int playerId, String cards) {
        this.roundId = roundId;
        this.playerId = playerId;
        this.cards = cards;
    }

    // Getters and Setters
    public int getHandId() {
        return handId;
    }

    public void setHandId(int handId) {
        this.handId = handId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getCards() {
        return cards;
    }

    public void setCards(String cards) {
        this.cards = cards;
    }

    /**
     * Convert hand into ints useful for adding to DB
     * 
     * @return
     */
    public int[] getCardArray() {
        if (cards == null) {
            throw new IllegalArgumentException("Card string is null.");
        }
    
        String[] cardTokens = cards.split(", ");
        if (cardTokens.length != 13) {
            throw new IllegalArgumentException("Expected 13 cards separated by ', '. Found: " + cardTokens.length);
        }
    
        int[] cardArray = new int[26];
    
        for (int i = 0; i < 13; i++) {
            String card = cardTokens[i];
            if (card.length() != 2) {
                throw new IllegalArgumentException("Invalid card format: " + card);
            }
    
            char rankChar = card.charAt(0);
            char suitChar = card.charAt(1);
    
            int rank = switch (rankChar) {
                case '2' -> 2;
                case '3' -> 3;
                case '4' -> 4;
                case '5' -> 5;
                case '6' -> 6;
                case '7' -> 7;
                case '8' -> 8;
                case '9' -> 9;
                case 'T' -> 10;
                case 'J' -> 11;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                default -> throw new IllegalArgumentException("Invalid rank: " + rankChar);
            };
    
            int suit = switch (suitChar) {
                case 'S' -> 0;
                case 'H' -> 1;
                case 'D' -> 2;
                case 'C' -> 3;
                default -> throw new IllegalArgumentException("Invalid suit: " + suitChar);
            };
    
            cardArray[i * 2] = rank;
            cardArray[i * 2 + 1] = suit;
        }
    
        return cardArray;
    }
    
    

    @Override
    public String toString() {
        return "Hand{" +
                ", roundId=" + roundId +
                ", playerId=" + playerId +
                ", cards='" + cards + '\'' +
                '}';
    }
}