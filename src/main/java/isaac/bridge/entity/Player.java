package isaac.bridge.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "playerId")
    private int playerId;

    @Column(name = "botVersion")
    private int botVersion = 1; // 0 for human player, 1 for bot

    @Column(name = "playerName")
    private String playerName = "Computer"; // Player's name, default to "Computer"

    // Constructors
    public Player() {
    }

    public Player(String name, int botVersion) {
        this.playerName = name;
        this.botVersion = botVersion;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getBotVersion() {
        return botVersion;
    }

    public void setBotVersion(int botVersion) {
        this.botVersion = botVersion;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + playerName + '\'' +
                ", botVersion=" + botVersion +
                '}';
    }
}

