package isaac.bridge.entity;

import java.util.Random;
import jakarta.persistence.*;

@Entity
@Table(name = "round")
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "roundId")
    private int roundId;

    @Column(name = "gameId", nullable = false)
    private int gameId;

    @Column(name = "roundNumber")
    private int roundNumber;

    @Column(name = "dealerDirection")
    private int dealerDirection; // 0-3 for N, E, S, W

    @Column(name = "declarerDirection") 
    private int declarerDirection;

    @Column(name = "northSouthVulnerable") 
    private int northSouthVulnerable = 0;
    
    @Column(name = "eastWestVulnerable") 
    private int eastWestVulnerable = 0;

    // Contract information
    @Column(name = "contractSuit")
    private int contractSuit;

    @Column(name = "contractLevel")
    private int contractLevel;

    @Column(name = "contractModifier")
    private int contractModifier = 0;

    // Update after hand is played
    @Column(name = "northSouthTricksTaken")
    private int northSouthTricksTaken = 0;

    @Column(name = "eastWestTricksTaken")
    private int eastWestTricksTaken = 0;

    @Transient
    private transient String[] deck;

    public Round() {
        try {
            deck = fillDeck();
        } catch (Exception e) {
            deck = new String[52];
        }
    }

    public Round(int gameId, int dealerDirection, int roundNumber) {
        this.gameId = gameId;
        this.dealerDirection = dealerDirection; 
        this.roundNumber = roundNumber;
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

    public int getRoundNumber() {
        return roundNumber;
    }

    public int getDealerDirection() {
        return dealerDirection;
    }

    public void setDealerDirection(int dealerDirection) {
        this.dealerDirection = dealerDirection;
    }

    public int getDeclarerDirection() {
        return declarerDirection;
    }

    public void setDeclarerDirection(int declarerDirection) {
        this.declarerDirection = declarerDirection;
    }

    public int getNorthSouthVulnerable() {
        return northSouthVulnerable;
    }

    public void setNorthSouthVulnerable(int northSouthVulnerable) {
        this.northSouthVulnerable = northSouthVulnerable;
    }

    public int getEastWestVulnerable() {
        return eastWestVulnerable;
    }

    public void setEastWestVulnerable(int eastWestVulnerable) {
        this.eastWestVulnerable = eastWestVulnerable;
    }

    public int getContractSuit() {
        return contractSuit;
    }

    public void setContractSuit(int contractSuit) {
        this.contractSuit = contractSuit;
    }

    public int getContractLevel() {
        return contractLevel;
    }

    public void setContractLevel(int contractLevel) {
        this.contractLevel = contractLevel;
    }

    public int getContractModifier() {
        return contractModifier;
    }

    public void setContractModifier(int contractModifier) {
        this.contractModifier = contractModifier;
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

        for (int rankIndex = 0; rankIndex < 13; rankIndex++) {
            for (int suitIndex = 0; suitIndex < 4; suitIndex++) {
                deck[rankIndex * 4 + suitIndex] = ranks[rankIndex] + suits[suitIndex];
            }
        }

        shuffleDeck(deck);
        return deck;
    }

    public void shuffleDeck(String[] deck) {
        Random rand = new Random();
        for (int i = deck.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }
    }

    public int getNextDealerDirection() {
        return (dealerDirection + 1) % 4;
    }

    @Override
    public String toString() {
        return "Round{" +
                "roundId=" + roundId +
                ", gameId=" + gameId + 
                ", dealerDirection=" + dealerDirection +
                ", declarerDirection=" + declarerDirection +
                ", northSouthVulnerable=" + northSouthVulnerable +
                ", eastWestVulnerable=" + eastWestVulnerable +
                ", contractSuit=" + contractSuit +
                ", contractLevel=" + contractLevel +
                ", contractModifier=" + contractModifier +
                ", northSouthTricksTaken=" + northSouthTricksTaken +
                ", eastWestTricksTaken=" + eastWestTricksTaken +
                '}';
    }
}
