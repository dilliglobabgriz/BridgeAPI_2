package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hand")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "handId")
    private int handId;  // Unique ID for Hand, no inter using roundId as the primary key

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

    @Override
    public String toString() {
        return "Hand{" +
                "handId=" + handId +
                ", roundId=" + roundId +
                ", playerId=" + playerId +
                ", cards='" + cards + '\'' +
                '}';
    }
}