# 🎮 **Bridge API Documentation**  
A Java Spring Boot application for playing the game of Bridge.

---

## 📚 **How to Use**

The project is currently under development. To run tests, use Maven with the following command:

```bash
mvn test
```

---

## 🟢 **Relational Database Schema**

---

### 🧑‍💻 **Player**
| Column    | Type        | Constraints                | Description          |
|-----------|-------------|----------------------------|----------------------|
| `playerId` | `integer`      | PRIMARY KEY AUTO_INCREMENT | Player identifier    |
| `botVersion` | `integer` | DEFAULT 1                  | 0 for human player   |
| `name`    | `TEXT      ` | DEFAULT 'Computer'      | Player name          |

---

### 🎮 **Game**
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| `gameId`             | `integer`    | PRIMARY KEY AUTO_INCREMENT     | Game identifier              |
| `northId`            | `integer`    | FOREIGN KEY                    | North player ID              |
| `eastId`             | `integer`    | FOREIGN KEY                    | East player ID               |
| `southId`            | `integer`    | FOREIGN KEY                    | South player ID              |
| `westId`             | `integer`    | FOREIGN KEY                    | West player ID               |
| `firstDealerDirection` | `integer` | DEFAULT 0                     | [0-3] refers to N, S, E, W   |
| `northSouthScore`    | `integer` | DEFAULT 0                      | Update after each hand       |
| `eastWestScore`      | `integer` | DEFAULT 0                      | Update after each hand       |

---

### 🃏 **Bid**
| Column      | Type    | Constraints                | Description                 |
|-------------|---------|----------------------------|-----------------------------|
| `bidId`     | `integer`  | PRIMARY KEY AUTO_INCREMENT | Bid identifier              |
| `direction` | `integer` | NOT NULL                    | [0-3] refers to N, S, E, W  |
| `suit`      | `integer` | NOT NULL                    | [0-7] C,D,H,S,NT,P,X,R      |
| `level`     | `integer` | NOT NULL                    | [1-7]                       |
| `isDoubled`  | `integer` | DEFAULT 0                  | 0 for false, 1 for true     |
| `isRedoubled` | `integer` | DEFAULT 0                | 0 for false, 1 for true     |

---

### 🔄 **Round**
| Column                 | Type      | Constraints                    | Description                  |
|------------------------|-----------|--------------------------------|------------------------------|
| `roundId`              | `integer`    | PRIMARY KEY AUTO_INCREMENT     | Round identifier             |
| `gameId`               | `integer`    | FOREIGN KEY                    | Associated game ID           |
| `winningBidId`         | `integer`    | FOREIGN KEY                    | Associated winning bid       |
| `dealerDirection`      | `integer` | NOT NULL                          | [0-3] refers to N, S, E, W   |
| `northSouthTricksTaken` | `integer` | DEFAULT 0                        | Tricks taken by N/S          |
| `eastWestTricksTaken`  | `integer` | DEFAULT 0                         | Tricks taken by E/W          |
| `bidHistory`           | `TEXT`    |                                | String of bids               |
| `trickHistory`         | `TEXT`    |                                | String of tricks             |

---

### 🃏 **Hand**
| Column   | Type      | Constraints               | Description                             |
|----------|-----------|---------------------------|-----------------------------------------|
| `roundId`  | `integer`      | FOREIGN KEY               | Associated round identifier            |
| `playerId` | `integer`      | FOREIGN KEY               | Associated player identifier           |
| `cards`    | `varchar(51)`    | NOT NULL                | A string representing the 13 cards dealt to the player (e.g., "2H, 3S, AH, KC, 7D, 9C, QH, JS, TS, 8D, KH, 4C, 6H") |

---

💚 **New Addition:**  
- `bidHistory` and `trickHistory` stores serialized bids as strings for easy retrieval and analysis.


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
    - Create 4 bot players.
2. **Initialize a new game**
    - Connect player IDs to the game and save to DB.
3. **Initialize a new round**
    - Deal 13 cards per player.
4. **Start the bidding process**
    - Get bids from each player until a final contract is determined.
    - Save the winning contract and the player who bid it to the hand.
    - Serialize bid history and add to round instance
5. **Start the trick-taking process**
    - Get one card from each player, starting with the left of the dealer.
    - Determine who won the trick and update the score.
    - Serialize trick history and add to round instance
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


