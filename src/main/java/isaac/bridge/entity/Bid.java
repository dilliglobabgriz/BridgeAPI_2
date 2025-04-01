package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bidId")
    private Long bidId;

    @Column(name = "direction")
    private int direction; // 0 for North, 1 for East, 2 for South, 3 for West

    @Column(name = "suit")
    private int suit; // 0 for Clubs, 1 for Diamonds, 2 for Hearts, 3 for Spades, 4 for No Trump

    @Column(name = "level")
    private int level; // 1 to 7 for bid levels

    @Column(name = "isDouble")
    private int isDouble; // 0 for false, 1 for true

    @Column(name = "isRedoubled")
    private int isRedoubled; // 0 for false, 1 for true

    // Constructors
    public Bid() {
    }

    public Bid(int direction, int suit, int level, int isDouble, int isRedoubled) {
        this.direction = direction;
        this.suit = suit;
        this.level = level;
        this.isDouble = isDouble;
        this.isRedoubled = isRedoubled;
    }

    // Getters and Setters
    public Long getBidId() {
        return bidId;
    }

    public void setBidId(Long bidId) {
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

    public int getIsDouble() {
        return isDouble;
    }

    public void setIsDouble(int isDouble) {
        this.isDouble = isDouble;
    }

    public int getIsRedoubled() {
        return isRedoubled;
    }

    public void setIsRedoubled(int isRedoubled) {
        this.isRedoubled = isRedoubled;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "bidId=" + bidId +
                ", direction=" + direction +
                ", suit=" + suit +
                ", level=" + level +
                ", isDouble=" + isDouble +
                ", isRedoubled=" + isRedoubled +
                '}';
    }
}
