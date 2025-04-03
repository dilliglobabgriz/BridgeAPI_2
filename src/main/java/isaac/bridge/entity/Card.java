package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "card")
public class Card {  // Specifically for cards played into a trick NOT for player hands

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cardId")
    private int cardId;

    @Column(name = "trickId", nullable = false)
    private int trickId;

    @Column(name = "playerDirection", nullable = false)
    private int playerDirection;

    @Column(name = "suit", nullable = false)
    private int suit;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "playSequence", nullable = false)
    private int playSequence; // Order in the trick (0-3)

    public Card() {
    }

    public Card(int trickId, int playerDirection, int suit, int rank, int playSequence) {
        this.trickId = trickId;
        this.playerDirection = playerDirection;
        this.suit = suit;
        this.rank = rank;
        this.playSequence = playSequence;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getTrickId() {
        return trickId;
    }

    public void setTrickId(int trickId) {
        this.trickId = trickId;
    }

    public int getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(int playerDirection) {
        this.playerDirection = playerDirection;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getPlaySequence() {
        return playSequence;
    }

    public void setPlaySequence(int playSequence) {
        this.playSequence = playSequence;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardId=" + cardId +
                ", trickId=" + trickId +
                ", playerDirection=" + playerDirection +
                ", suit=" + suit +
                ", rank=" + rank +
                ", playSequence=" + playSequence +
                '}';
    }
}
