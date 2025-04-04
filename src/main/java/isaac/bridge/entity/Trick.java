package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "trick")
public class Trick {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "trickId")
    private int trickId;

    @Column(name = "roundId", nullable = false)
    private int roundId;

    @Column(name = "trickNumber", nullable = false)
    private int trickNumber;

    @Column(name = "leaderDirection", nullable = false)
    private int leaderDirection;

    @Column(name = "winningDirection")
    private int winningDirection;

    public Trick() {
    }

    public Trick(int roundId, int trickNumber, int leaderDirection) {
        this.roundId = roundId;
        this.trickNumber = trickNumber;
        this.leaderDirection = leaderDirection;
    }

    public Trick(int roundId, int trickNumber, int leaderDirection, int winningDirection) {
        this.roundId = roundId;
        this.trickNumber = trickNumber;
        this.leaderDirection = leaderDirection;
        this.winningDirection = winningDirection;
    }

    public int getTrickId() {
        return trickId;
    }

    public void setTrickId(int trickId) {
        this.trickId = trickId;
    }

    public int getRoundId() {
        return roundId;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    public int getTrickNumber() {
        return trickNumber;
    }

    public void setTrickNumber(int trickNumber) {
        this.trickNumber = trickNumber;
    }

    public int getLeaderDirection() {
        return leaderDirection;
    }

    public void setLeaderDirection(int leaderDirection) {
        this.leaderDirection = leaderDirection;
    }

    public int getWinningDirection() {
        return winningDirection;
    }

    public void setWinningDirection(int winningDirection) {
        this.winningDirection = winningDirection;
    }

    @Override
    public String toString() {
        return "Trick{" +
                "trickId=" + trickId +
                ", roundId=" + roundId +
                ", trickNumber=" + trickNumber +
                ", leaderDirection=" + leaderDirection +
                ", winningDirection=" + winningDirection +
                '}';
    }
}
