package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "hand")
public class Hand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roundId")
    private Long roundId;  // Foreign key for round

    @Column(name = "playerId")
    private Long playerId; // Foreign key for player

    @Column(name = "cards")
    private String cards; // A single string representing the 13 cards

    // Constructors
    public Hand() {}

    public Hand(Long roundId, Long playerId, String cards) {
        this.roundId = roundId;
        this.playerId = playerId;
        this.cards = cards;
    }

    // Getters and Setters
    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
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
                "roundId=" + roundId +
                ", playerId=" + playerId +
                ", cards='" + cards + '\'' +
                '}';
    }
}

