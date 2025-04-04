# 🎮 **Bridge API Documentation**  
A Java Spring Boot application for playing the game of Bridge.

---

## 📚 **How to Use**

The project is currently under development. To try out the API, use Maven with the following command:

```bash
mvn spring-boot:run
```

This will create an Apache Tomcat server on port 8080 where you can do the following
1. Create new games with ```POST /api/games```
2. Fetch all existing games with ```GET /api/games```

---

## 🟢 **Relational Database Schema**

---

### 🧑‍💻 **Player**
| Column      | Type          | Constraints                     | Description            |
|-------------|---------------|----------------------------------|------------------------|
| `playerId`  | `INTEGER`     | PRIMARY KEY, AUTO_INCREMENT      | Player identifier      |
| `botVersion`| `INTEGER`     | DEFAULT 1                        | 0 for human player     |
| `name`      | `VARCHAR(255)`| DEFAULT 'Computer'               | Player name            |

---

### 🎮 **Game**
| Column                | Type        | Constraints                           | Description                      |
|-----------------------|-------------|---------------------------------------|----------------------------------|
| `gameId`              | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT           | Game identifier                  |
| `northId`             | `INTEGER`   | FOREIGN KEY                           | North player ID                  |
| `eastId`              | `INTEGER`   | FOREIGN KEY                           | East player ID                   |
| `southId`             | `INTEGER`   | FOREIGN KEY                           | South player ID                  |
| `westId`              | `INTEGER`   | FOREIGN KEY                           | West player ID                   |
| `firstDealerDirection`| `INTEGER`   | DEFAULT 0                             | [0-3] refers to N, S, E, W       |
| `northSouthScore`     | `INTEGER`   | DEFAULT 0                             | North-South team score           |
| `eastWestScore`       | `INTEGER`   | DEFAULT 0                             | East-West team score             |

---

### 🔄 **Round**
| Column                 | Type        | Constraints                           | Description                      |
|------------------------|-------------|---------------------------------------|----------------------------------|
| `roundId`              | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT           | Round identifier                 |
| `gameId`               | `INTEGER`   | FOREIGN KEY                           | Associated game ID               |
| `roundNumber`          | `INTEGER`   | NOT NULL                              | Sequence number within the game  |
| `dealerDirection`      | `INTEGER`   | NOT NULL                              | [0-3] refers to N, S, E, W       |
| `declarerDirection`    | `INTEGER`   | NOT NULL                              | [0-3] refers to N, S, E, W       |
| `northSouthVulnerable` | `INTEGER`   | DEFAULT 0                             | 0=No, 1=Yes                      |
| `eastWestVulnerable`   | `INTEGER`   | DEFAULT 0                             | 0=No, 1=Yes                      |
| `contractSuit`         | `INTEGER`   |                                       | e.g., 0=C, 1=D, 2=H, 3=S, 4=NT  |
| `contractLevel`        | `INTEGER`   |                                       | 1-7                              |
| `contractModifier`     | `INTEGER`   | DEFAULT 0                             | 0=Undoubled, 1=Doubled, 2=Redoubled |
| `northSouthTricksTaken`| `INTEGER`   | DEFAULT 0                             | Tricks taken by N/S              |
| `eastWestTricksTaken`  | `INTEGER`   | DEFAULT 0                             | Tricks taken by E/W              |

---

### 🃏 **Hand**
| Column      | Type        | Constraints                     | Description                                               |
|-------------|-------------|----------------------------------|-----------------------------------------------------------|
| `handId`    | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT      | Hand identifier                                           |
| `roundId`   | `INTEGER`   | FOREIGN KEY                      | Associated round identifier                               |
| `playerId`  | `INTEGER`   | FOREIGN KEY                      | Associated player identifier                              |
| `cards`     | `VARCHAR(255)` | NOT NULL                      | A string representing the 13 cards dealt to the player (e.g., "2H, 3S, AH, KC, 7D, 9C, QH, JS, TS, 8D, KH, 4C, 6H") |

---

### 🃏 **Bid**
| Column      | Type        | Constraints                     | Description                     |
|-------------|-------------|----------------------------------|---------------------------------|
| `bidId`     | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT      | Bid identifier                  |
| `roundId`   | `INTEGER`   | FOREIGN KEY                      | Associated round identifier     |
| `direction` | `INTEGER`   | NOT NULL                         | [0-3] refers to N, S, E, W      |
| `suit`      | `INTEGER`   |                                  | [0-4] C, D, H, S, NT            |
| `level`     | `INTEGER`   |                                  | [1-7]                            |
| `bidType`   | `INTEGER`   | NOT NULL                         | [0-3] P, Bid, X, XX              |
| `bidSequence` | `INTEGER` | NOT NULL                         | Order bid is added to round      |

---

### 🃏 **Trick**
| Column            | Type        | Constraints                     | Description                                               |
|-------------------|-------------|----------------------------------|-----------------------------------------------------------|
| `trickId`         | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT      | Trick identifier                                           |
| `roundId`         | `INTEGER`   | FOREIGN KEY                      | Associated round identifier                               |
| `trickNumber`     | `INTEGER`   | NOT NULL                         | Trick number (1-13)                                        |
| `leaderDirection` | `INTEGER`   | NOT NULL                         | Player direction leading the trick (0-3)                  |
| `winningDirection`| `INTEGER`   |                                  | Player direction winning the trick (0-3)                  |

---

### 🃏 **Card**
| Column            | Type        | Constraints                     | Description                                               |
|-------------------|-------------|----------------------------------|-----------------------------------------------------------|
| `cardId`          | `INTEGER`   | PRIMARY KEY, AUTO_INCREMENT      | Card identifier                                            |
| `trickId`         | `INTEGER`   | FOREIGN KEY                      | Associated trick identifier                               |
| `playerDirection` | `INTEGER`   | NOT NULL                         | Direction of the player playing the card (0-3)            |
| `suit`            | `INTEGER`   | NOT NULL                         | e.g., 0=C, 1=D, 2=H, 3=S                                 |
| `rank`            | `INTEGER`   | NOT NULL                         | e.g., 2-10, 11=J, 12=Q, 13=K, 14=A                       |
| `playSequence`    | `INTEGER`   | NOT NULL                         | Order card played in the trick (0-3)                      |

---

💚 **New Addition:**  
- Added Trick and Bid tables with more detail to accurately track the history of the bids and each card played in a round


## ⚙️ **Project Overview**
Full stack web application using Java's Spring Boot framework. Allows users to play and enjoy the game of Bridge.

---

## 📄 **Requirements**
All dependencies can be found in the `pom.xml` file.  
Currently using:
- Java 17
- Spring Boot 3.4.3

---

## 🚀 **Steps to Play**
1. **Initialize 4 players**
    - CREATE 4 bot players.
2. **Initialize a new game**
    - Connect player IDs to the game and save to DB.
3. **Initialize a new round**
    - CREATE a round with with only game id initialized
    - Deal 13 cards per player
4. **Start the bidding process**
    - Get bids from each player until a final contract is determined.
    - UPDATE the winning contract and the player who bid it to the hand.
    - Serialize bid history and UPDATE round
5. **Start the trick-taking process**
    - Get one card from each player, starting with the left of the dealer.
    - Determine who won the trick and update the score.
    - Serialize trick history and UPDATE to round
6. **Score the round**
    - Determine the score based on the contract, tricks taken, and vulnerability.
7. **Save round to the DB**
8. **Save hands to the DB**
    - Serialize hands and add connect them with playerId in DB
9. **Start new round**
    - Until game is over start a new round

---

## 🎯 **Possible Future Features**
- Support for one or more human players.
- Improved UI/UX.

---

## 📏 **Dev Log**


