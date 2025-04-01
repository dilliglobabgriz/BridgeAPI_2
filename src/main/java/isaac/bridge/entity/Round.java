package isaac.bridge.entity;

import java.util.Random;

import jakarta.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roundId")
    private Long id;

    @Column(name = "gameId", nullable = false)
    private Long gameId;

    @Column(name = "winningBid")
    private String winningBid; // Store as a compact string

    @Column(name = "dealerDirection")
    private int dealerDirection; // 0-3 for N, E, S, W

    @Column(name = "northSouthTricksTaken", nullable = false)
    private int northSouthTricksTaken = 0;

    @Column(name = "eastWestTricksTaken", nullable = false)
    private int eastWestTricksTaken = 0;

    /**
     * An array of 52 cards that is shuffled once when the round is initialized
     * Each player's hand is a 13 card section of the shuffled deck
     * After the round is completed the deck is converted into 4 strings and saved to the DB
     */
    @Transient
    private String[] deck;

    public Round() {
        deck = fillDeck();
    }

    public Round(Long gameId, int dealerDirection) {
        this.gameId = gameId;
        this.dealerDirection = dealerDirection;
        deck = fillDeck();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public String getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(String winningBid) {
        this.winningBid = winningBid;
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

    public String[] getDeck() {
        return deck;
    }

    public String[] fillDeck() {
        String[] deck = new String[52];

        String[] suits = {"C", "D", "H", "S"};

        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (int rankIndex=0; rankIndex<13; rankIndex++) {
            for (int suitIndex=0; suitIndex<4; suitIndex++) {
                deck[rankIndex*ranks.length + suitIndex] = ranks[rankIndex] + suits[suitIndex];
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
                "id=" + id +
                ", gameId=" + gameId +
                ", winningBid='" + winningBid + '\'' +
                ", dealerDirection=" + dealerDirection +
                ", northSouthTricksTaken=" + northSouthTricksTaken +
                ", eastWestTricksTaken=" + eastWestTricksTaken +
                '}';
    }

    public static void main(String[] args) {
        Round round = new Round();
        for (String card : round.getDeck()) {
            System.out.println(card);
        }
    }
}
