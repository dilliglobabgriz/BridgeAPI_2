package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bidId")
    private int bidId;

    @Column(name = "roundId") 
    private int roundId;

    @Column(name = "direction")
    private int direction; // 0 for North, 1 for East, 2 for South, 3 for West

    /**
     * 0 for Clubs, 1 for Diamonds, 2 for Hearts, 3 for Spades, 4 for No Trump
     * Null for pass/double
     */
    @Column(name = "suit")
    private int suit;

    /**
     * 1-7 for suit bid, otherwise null
     */
    @Column(name = "level")
    private int level;

    /**
     * 0 for pass, 1 for suit bid, 2 for double, 3 for redouble
     * If pass or double, suit/level can be null
     */
    @Column(name = "bidType")
    private int bidType;

    @Column(name = "bidSequence") 
    private int bidSequence;

    // Constructors
    public Bid() {
    }

    public Bid(int roundId, int direction, int suit, int level, int bidType, int bidSequence) {
        this.roundId = roundId;
        this.direction = direction;
        this.suit = suit;
        this.level = level;
        this.bidType = bidType;
        this.bidSequence = bidSequence;
    }

    // Getters and Setters
    public int getRoundId() {
        return roundId;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        this.suit = suit;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getBidType() {
        return bidType;
    }

    public void setBidType(int bidType) {
        this.bidType = bidType;
    }

    public int getBidSequence() {
        return bidSequence;
    }

    public void setBidSequence(int bidSequence) {
        this.bidSequence = bidSequence;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bidId=" + bidId +
                ", direction=" + direction +
                ", suit=" + suit +
                ", level=" + level +
                ", bidType=" + bidType +
                ", bidSequence=" + bidSequence +
                '}';
    }
}
