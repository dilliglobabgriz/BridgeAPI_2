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

### 🎮 **Game**
| Column               | Type      | Constraints                    | Description                  |
|----------------------|-----------|--------------------------------|------------------------------|
| `gameId`             | `long`    | PRIMARY KEY AUTO_INCREMENT     | Game identifier              |
| `northId`            | `long`    | FOREIGN KEY                    | North player ID              |
| `eastId`             | `long`    | FOREIGN KEY                    | East player ID               |
| `southId`            | `long`    | FOREIGN KEY                    | South player ID              |
| `westId`             | `long`    | FOREIGN KEY                    | West player ID               |
| `firstDealerDirection` | `integer` | DEFAULT 0                     | [0-3] refers to N, S, E, W   |
| `northSouthScore`    | `integer` | DEFAULT 0                      | Update after each hand       |
| `eastWestScore`      | `integer` | DEFAULT 0                      | Update after each hand       |

---

### 🔄 **Round**
| Column                 | Type      | Constraints                    | Description                  |
|------------------------|-----------|--------------------------------|------------------------------|
| `roundId`              | `long`    | PRIMARY KEY AUTO_INCREMENT     | Round identifier             |
| `gameId`               | `long`    | FOREIGN KEY                    | Associated game ID           |
| `winningBidId`         | `long`    | FOREIGN KEY                    | Associated winning bid       |
| `dealerDirection`      | `integer` |                                | [0-3] refers to N, S, E, W   |
| `northSouthTricksTaken` | `integer` |                                | Tricks taken by N/S          |
| `eastWestTricksTaken`  | `integer` |                                | Tricks taken by E/W          |
| `bidHistory`           | `TEXT`    |                                | JSON string of bid history   |

💚 **New Addition:**  
- `bidHistory` stores serialized bids as a JSON string for easy retrieval and analysis.

---

### 🃏 **Bid**
| Column      | Type    | Constraints                | Description                 |
|-------------|---------|----------------------------|-----------------------------|
| `bidId`     | `long`  | PRIMARY KEY AUTO_INCREMENT | Bid identifier              |
| `direction` | `integer` |                            | [0-3] refers to N, S, E, W  |
| `type`      | `integer` |                            | [0-7] C,D,H,S,NT,P,X,R      |
| `level`     | `integer` |                            | [1-7]                       |
| `isDouble`  | `integer` | DEFAULT 0                  | 0 for false, 1 for true     |
| `isRedoubled` | `integer` | DEFAULT 0                | 0 for false, 1 for true     |

---

### 🧑‍💻 **Player**
| Column    | Type        | Constraints                | Description          |
|-----------|-------------|----------------------------|----------------------|
| `playerId` | `long`      | PRIMARY KEY AUTO_INCREMENT | Player identifier    |
| `botVersion` | `integer` | DEFAULT 1                  | 0 for human player   |
| `name`    | `varchar(255)` | DEFAULT 'Computer'      | Player name          |

---

### 🃏 **Hand**
| Column   | Type      | Constraints               | Description                             |
|----------|-----------|---------------------------|-----------------------------------------|
| `roundId`  | `long`      | FOREIGN KEY               | Associated round identifier            |
| `playerId` | `long`      | FOREIGN KEY               | Associated player identifier           |
| `cards`    | `varchar(51)`    |                           | A string representing the 13 cards dealt to the player (e.g., "2H, 3S, AH, KC, 7D, 9C, QH, JS, TS, 8D, KH, 4C, 6H") |


---

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
5. **Start the trick-taking process**
    - Get one card from each player, starting with the left of the dealer.
    - Determine who won the trick and update the score.
6. **Score the round**
    - Determine the score based on the contract, tricks taken, and vulnerability.
7. **Save game/round to the DB**

---

## 🎯 **Possible Future Features**
- Support for one or more human players.
- Adding more extensive trick and bid history to the DB.
- Improved UI/UX.

---

## 📏 **Dev Notes**
- Implement BidType to Suit conversion to handle NT bids and trump cases.
- Create `Hand` (for game) and `Hand` (for players) as separate classes.

