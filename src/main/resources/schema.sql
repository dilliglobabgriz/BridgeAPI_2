-- =============================================
-- Drop Tables if they exist to avoid errors
-- =============================================

-- -- Drop the tables if they already exist
-- DROP TABLE IF EXISTS hand;
-- DROP TABLE IF EXISTS round;
-- DROP TABLE IF EXISTS bid;
-- DROP TABLE IF EXISTS player;  -- Drop player table first
-- DROP TABLE IF EXISTS game;    -- Then drop game table

-- =============================================
-- Create Tables
-- =============================================

-- Create Player Table (must be first since game references player)
CREATE TABLE IF NOT EXISTS "player" (
	"playerId"	    INTEGER,
	"botVersion"	INTEGER DEFAULT 1,
	"name"	TEXT DEFAULT 'Computer',
	PRIMARY KEY("playerId" AUTO_INCREMENT)
);

CREATE TABLE IF NOT EXISTS "game" (
	"gameId"	        INTEGER,
	"northId"	        INTEGER NOT NULL,
	"eastId"	        INTEGER NOT NULL,
	"southId"	        INTEGER NOT NULL,
	"westId"	        INTEGER NOT NULL,
	"dealerDirection"	INTEGER DEFAULT 0,
	"northSouthScore"	INTEGER DEFAULT 0,
	"eastWestScore"	    INTEGER DEFAULT 0,
	PRIMARY KEY("gameId" AUTO_INCREMENT),
	FOREIGN KEY("eastId") REFERENCES "player"("playerId"),
	FOREIGN KEY("northId") REFERENCES "player"("playerId"),
	FOREIGN KEY("southId") REFERENCES "player"("playerId"),
	FOREIGN KEY("westId") REFERENCES "player"("playerId")
);

CREATE TABLE IF NOT EXISTS "bid" (
	"bidId"	        INTEGER,
	"direction"	    INTEGER NOT NULL,
	"suit"	        INTEGER NOT NULL,
	"level"	        INTEGER NOT NULL,
	"isDoubled"	    INTEGER DEFAULT 0,
	"isRedoubled"	INTEGER DEFAULT 0,
	PRIMARY KEY("bidId" AUTO_INCREMENT)
);

CREATE TABLE IF NOT EXISTS "round" (
	"roundId"	            INTEGER,
	"gameId"	            INTEGER NOT NULL,
	"winningBidId"	        INTEGER NOT NULL,
	"dealerDirection"	    INTEGER NOT NULL,
	"northSouthTricksTaken"	INTEGER DEFAULT 0,
	"eastWestTricksTaken"	INTEGER DEFAULT 0,
	"bidHistory"	        TEXT,
	"trickHistory"	        TEXT,
	PRIMARY KEY("roundId" AUTO_INCREMENT),
	FOREIGN KEY("gameId") REFERENCES "game"("gameId"),
	FOREIGN KEY("winningBidId") REFERENCES "bid"("bidId")
);

CREATE TABLE IF NOT EXISTS "hand" (
	"handId"	INTEGER,
	"roundId"	INTEGER NOT NULL,
	"playerId"	INTEGER NOT NULL,
	"cards"	    TEXT NOT NULL,
	PRIMARY KEY("handId" AUTOINCREMENT),
	FOREIGN KEY("playerId") REFERENCES "player"("playerId"),
	FOREIGN KEY("roundId") REFERENCES "round"("roundId")
);

-- -- =============================================
-- -- Add Foreign Keys (Separate Statements)
-- -- =============================================

-- -- Add foreign key for northId
-- ALTER TABLE game 
--     ADD CONSTRAINT fk_game_northId FOREIGN KEY (northId) REFERENCES player(playerId);

-- -- Add foreign key for eastId
-- ALTER TABLE game 
--     ADD CONSTRAINT fk_game_eastId FOREIGN KEY (eastId) REFERENCES player(playerId);

-- -- Add foreign key for southId
-- ALTER TABLE game 
--     ADD CONSTRAINT fk_game_southId FOREIGN KEY (southId) REFERENCES player(playerId);

-- -- Add foreign key for westId
-- ALTER TABLE game 
--     ADD CONSTRAINT fk_game_westId FOREIGN KEY (westId) REFERENCES player(playerId);

-- -- Add foreign key for roundId in round table
-- ALTER TABLE round
--     ADD CONSTRAINT fk_round_gameId FOREIGN KEY (gameId) REFERENCES game(gameId);

-- -- Add foreign key for winningBidId in round table
-- ALTER TABLE round
--     ADD CONSTRAINT fk_round_winningBidId FOREIGN KEY (winningBidId) REFERENCES bid(bidId);

-- -- Add foreign key for roundId in hand table
-- ALTER TABLE hand
--     ADD CONSTRAINT fk_hand_roundId FOREIGN KEY (roundId) REFERENCES round(roundId);

-- -- Add foreign key for playerId in hand table
-- ALTER TABLE hand
--     ADD CONSTRAINT fk_hand_playerId FOREIGN KEY (playerId) REFERENCES player(playerId);


