package isaac.bridge.entity;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "gameId")
    private Long id;

    @Column(name = "dealerDirection")
    private int dealerDirection;

    @Column(name = "northId")
    private long northId;

    @Column(name = "southId")
    private long southId;

    @Column(name = "eastId")
    private long eastId;

    @Column(name = "westId")
    private long westId;

    @Column(name = "northSouthScore")
    private int northSouthScore;

    @Column(name = "eastWestScore")
    private int eastWestScore;

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

    public Game(long northId, long eastId, long southId, long westId) {
        this.northId = northId;
        this.eastId = eastId;
        this.southId = southId;
        this.westId = westId;
        this.rounds = new ArrayList<>();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    /**
     * Only to be used for testing!
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    public int getDealerDirection() {
        return dealerDirection;
    }

    public void setDealerDirection(int dealerDirection) {
        this.dealerDirection = dealerDirection;
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

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void addRound(Round round) {
        rounds.add(round);
    }

    public Round getCurrentRound() {
        return rounds.get(rounds.size() - 1);
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", dealerDirection=" + dealerDirection +
                ", northSouthScore=" + northSouthScore +
                ", eastWestScore=" + eastWestScore +
                '}';
    }
}
