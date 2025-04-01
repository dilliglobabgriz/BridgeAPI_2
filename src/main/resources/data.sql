-- =============================================
-- Drop Tables if they exist to avoid errors
-- =============================================

-- Drop the tables if they already exist
DROP TABLE IF EXISTS hand;
DROP TABLE IF EXISTS round;
DROP TABLE IF EXISTS bid;
DROP TABLE IF EXISTS player;  -- Drop player table first
DROP TABLE IF EXISTS game;    -- Then drop game table

-- =============================================
-- Create Tables
-- =============================================

-- Create Player Table (must be first since game references player)
CREATE TABLE IF NOT EXISTS player (
    playerId BIGINT AUTO_INCREMENT PRIMARY KEY,
    botVersion INT DEFAULT 1, -- 0 for human, 1 for bot
    name VARCHAR(255) DEFAULT 'Computer'
);

-- Create Game Table (now player table exists)
CREATE TABLE IF NOT EXISTS game (
    gameId BIGINT AUTO_INCREMENT PRIMARY KEY,
    northId BIGINT NOT NULL,
    eastId BIGINT NOT NULL,
    southId BIGINT NOT NULL,
    westId BIGINT NOT NULL,
    dealerDirection INT DEFAULT 0, -- 0 for North, 1 for East, 2 for South, 3 for West
    northSouthScore INT DEFAULT 0,
    eastWestScore INT DEFAULT 0
);

-- Create Round Table
CREATE TABLE IF NOT EXISTS round (
    roundId BIGINT AUTO_INCREMENT PRIMARY KEY,
    gameId BIGINT NOT NULL,
    winningBidId BIGINT NOT NULL,
    dealerDirection INT NOT NULL, -- 0 for North, 1 for East, 2 for South, 3 for West
    northSouthTricksTaken INT DEFAULT 0,
    eastWestTricksTaken INT DEFAULT 0
);

-- Create Bid Table
CREATE TABLE IF NOT EXISTS bid (
    bidId BIGINT AUTO_INCREMENT PRIMARY KEY,
    direction INT NOT NULL, -- 0 for North, 1 for East, 2 for South, 3 for West
    suit INT NOT NULL, -- 0 for Clubs, 1 for Diamonds, 2 for Hearts, 3 for Spades, 4 for No Trump
    level INT NOT NULL, -- 1 to 7
    isDouble INT DEFAULT 0, -- 0 for false, 1 for true
    isRedoubled INT DEFAULT 0 -- 0 for false, 1 for true
);

-- Create Hand Table
CREATE TABLE IF NOT EXISTS hand (
    roundId BIGINT NOT NULL,
    playerId BIGINT NOT NULL,
    cards VARCHAR(255) NOT NULL -- Cards stored as a comma-separated string (e.g., "AS,10S,7D,...")
);

-- =============================================
-- Add Foreign Keys (Separate Statements)
-- =============================================

-- Add foreign key for northId
ALTER TABLE game 
    ADD CONSTRAINT fk_game_northId FOREIGN KEY (northId) REFERENCES player(playerId);

-- Add foreign key for eastId
ALTER TABLE game 
    ADD CONSTRAINT fk_game_eastId FOREIGN KEY (eastId) REFERENCES player(playerId);

-- Add foreign key for southId
ALTER TABLE game 
    ADD CONSTRAINT fk_game_southId FOREIGN KEY (southId) REFERENCES player(playerId);

-- Add foreign key for westId
ALTER TABLE game 
    ADD CONSTRAINT fk_game_westId FOREIGN KEY (westId) REFERENCES player(playerId);

-- Add foreign key for roundId in round table
ALTER TABLE round
    ADD CONSTRAINT fk_round_gameId FOREIGN KEY (gameId) REFERENCES game(gameId);

-- Add foreign key for winningBidId in round table
ALTER TABLE round
    ADD CONSTRAINT fk_round_winningBidId FOREIGN KEY (winningBidId) REFERENCES bid(bidId);

-- Add foreign key for roundId in hand table
ALTER TABLE hand
    ADD CONSTRAINT fk_hand_roundId FOREIGN KEY (roundId) REFERENCES round(roundId);

-- Add foreign key for playerId in hand table
ALTER TABLE hand
    ADD CONSTRAINT fk_hand_playerId FOREIGN KEY (playerId) REFERENCES player(playerId);


