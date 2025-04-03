package isaac.bridge.entity;

import jakarta.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameId")
    private int gameId;

    @Column(name = "northId", nullable = false)
    private int northId;

    @Column(name = "southId", nullable = false)
    private int southId;

    @Column(name = "eastId", nullable = false)
    private int eastId;

    @Column(name = "westId", nullable = false)
    private int westId;
    
    @Column(name = "firstDealerDirection")
    private int firstDealerDirection = 0;

    @Column(name = "northSouthScore")
    private int northSouthScore = 0;

    @Column(name = "eastWestScore")
    private int eastWestScore = 0;

    @Transient
    private GameState state;

    @Transient
    private ArrayList<Round> rounds;

    public enum GameState {
        WAITING_FOR_PLAYERS, BIDDING, PLAYING, COMPLETED
    }

    public Game() {
        rounds = new ArrayList<>();
    }

    public Game(int northId, int eastId, int southId, int westId) {
        this.northId = northId;
        this.eastId = eastId;
        this.southId = southId;
        this.westId = westId;
        this.rounds = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return gameId;
    }

    public void setId(int id) {
        this.gameId = id;
    }

    public int getFirstDealerDirection() {
        return firstDealerDirection;
    }

    public void setFirstDealerDirection(int firstDealerDirection) {
        this.firstDealerDirection = firstDealerDirection;
    }

    public int getNorthSouthScore() {
        return northSouthScore;
    }

    public void setNorthSouthScore(int northSouthScore) {
        this.northSouthScore = northSouthScore;
    }

    public int getEastWestScore() {
        return eastWestScore;
    }

    public void setEastWestScore(int eastWestScore) {
        this.eastWestScore = eastWestScore;
    }

    // Only getters for player IDs
    public int getNorthId() {
        return northId;
    }

    public int getSouthId() {
        return southId;
    }

    public int getEastId() {
        return eastId;
    }

    public int getWestId() {
        return westId;
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }

    public Round getCurrentRound() {
        if (rounds.size() == 0) {
            return null;
        }
        return rounds.get(rounds.size() - 1);
    }
    
    public GameState getState() {
        return state;
    }
    
    public void setState(GameState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + gameId +
                ", firstDealerDirection=" + firstDealerDirection +
                ", northId=" + northId +
                ", southId=" + southId +
                ", eastId=" + eastId +
                ", westId=" + westId +
                ", northSouthScore=" + northSouthScore +
                ", eastWestScore=" + eastWestScore +
                '}';
    }
}