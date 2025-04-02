package isaac.bridge.entity;

import java.util.Random;

import jakarta.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roundId")
    private int roundId;

    @Column(name = "gameId", nullable = false)
    private int gameId;

    @Column(name = "winningBidId", nullable = false)
    private int winningBidId;

    @Column(name = "dealerDirection", nullable = false)
    private int dealerDirection; // 0-3 for N, E, S, W

    @Column(name = "northSouthTricksTaken", nullable = false)
    private int northSouthTricksTaken = 0;

    @Column(name = "eastWestTricksTaken", nullable = false)
    private int eastWestTricksTaken = 0;

    @Column(name = "bidHistory")
    private String bidHistory;

    @Column(name = "trickHistory")
    private String trickHistory;

    /**
     * An array of 52 cards that is shuffled once when the round is initialized
     * Each player's hand is a 13 card section of the shuffled deck
     * After the round is completed the deck is converted into 4 strings and saved to the DB
     */
    @Transient
    private String[] deck;

    public Round() {
        // Initialize deck in try-catch to prevent issues during JPA entity loading
        try {
            deck = fillDeck();
        } catch (Exception e) {
            // Silent catch to allow JPA entity loading
            deck = new String[52];
        }
    }

    public Round(int gameId, int dealerDirection) {
        this.gameId = gameId;
        this.dealerDirection = dealerDirection;
        this.winningBidId = 0; // Default value to match NOT NULL constraint
        deck = fillDeck();
    }

    // Getters and Setters
    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getWinningBidId() {
        return winningBidId;
    }

    public void setWinningBidId(int winningBidId) {
        this.winningBidId = winningBidId;
    }

    public int getDealerDirection() {
        return dealerDirection;
    }

    public void setDealerDirection(int dealerDirection) {
        this.dealerDirection = dealerDirection;
    }

    public int getNorthSouthTricksTaken() {
        return northSouthTricksTaken;
    }

    public void setNorthSouthTricksTaken(int northSouthTricksTaken) {
        this.northSouthTricksTaken = northSouthTricksTaken;
    }
 
    public int getEastWestTricksTaken() {
        return eastWestTricksTaken;
    }

    public void setEastWestTricksTaken(int eastWestTricksTaken) {
        this.eastWestTricksTaken = eastWestTricksTaken;
    }

    public String getBidHistory() {
        return bidHistory;
    }

    public void setBidHistory(String bidHistory) {
        this.bidHistory = bidHistory;
    }

    public String getTrickHistory() {
        return trickHistory;
    }

    public void setTrickHistory(String trickHistory) {
        this.trickHistory = trickHistory;
    }

    public String[] getDeck() {
        return deck;
    }

    public String[] fillDeck() {
        String[] deck = new String[52];

        String[] suits = {"C", "D", "H", "S"};

        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (int rankIndex=0; rankIndex<13; rankIndex++) {
            for (int suitIndex=0; suitIndex<4; suitIndex++) {
                deck[rankIndex*4 + suitIndex] = ranks[rankIndex] + suits[suitIndex];
            }
        }

        shuffleDeck(deck);
        
        return deck;
    }

    /**
     * Fisher Yates random shuffle
     * 
     * @param deck
     */
    public void shuffleDeck(String[] deck) {
        Random rand = new Random();

        for (int i=deck.length-1; i>0; i--) {
            int j = rand.nextInt(i+1);

            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    /**
     * Finds the index of the next rounds dealer using mod operator
     * 
     * @return dealer direction 0-3
     */
    public int getNextDealerDirection() {
        return (dealerDirection + 1) % 4;
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", gameId=" + gameId + 
                ", winningBidId='" + winningBidId + '\'' +
                ", dealerDirection=" + dealerDirection +
                ", northSouthTricksTaken=" + northSouthTricksTaken +
                ", eastWestTricksTaken=" + eastWestTricksTaken +
                '}';
    }
}