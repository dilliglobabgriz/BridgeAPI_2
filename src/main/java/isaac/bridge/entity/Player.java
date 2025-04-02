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
    private int botVersion; // 0 for human player, 1 for bot

    @Column(name = "name")
    private String name; // Player's name, default to "Computer"

    // Constructors
    public Player() {
        this.name = "Computer"; // Default name for bot players
        this.botVersion = 1;    // Default to bot version
    }

    public Player(String name, int botVersion) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId=" + playerId +
                ", name='" + name + '\'' +
                ", botVersion=" + botVersion +
                '}';
    }
}

