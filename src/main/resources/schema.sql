-- H2 Dialect Schema for Spring Boot

DROP TABLE IF EXISTS CARD;
DROP TABLE IF EXISTS TRICK;
DROP TABLE IF EXISTS BID;
DROP TABLE IF EXISTS HAND;
DROP TABLE IF EXISTS ROUND;
DROP TABLE IF EXISTS GAME;
DROP TABLE IF EXISTS PLAYER;

--|================================================================================================|
CREATE TABLE PLAYER (
    PLAYERID    INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    BOTVERSION  INTEGER DEFAULT 1,
    PLAYERNAME  VARCHAR(255) DEFAULT 'Computer'
);

--|================================================================================================|
CREATE TABLE GAME (
    GAMEID                  INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    NORTHID                 INTEGER NOT NULL,
    EASTID                  INTEGER NOT NULL,
    SOUTHID                 INTEGER NOT NULL,
    WESTID                  INTEGER NOT NULL,
    FIRSTDEALERDIRECTION    INTEGER DEFAULT 0,   -- Optional, will default to 0 (North)
    NORTHSOUTHSCORE         INTEGER DEFAULT 0,
    EASTWESTSCORE           INTEGER DEFAULT 0,

    FOREIGN KEY(EASTID) REFERENCES PLAYER(PLAYERID),
    FOREIGN KEY(NORTHID) REFERENCES PLAYER(PLAYERID),
    FOREIGN KEY(SOUTHID) REFERENCES PLAYER(PLAYERID),
    FOREIGN KEY(WESTID) REFERENCES PLAYER(PLAYERID),

    CONSTRAINT GAME_DEALER_CHECK CHECK (FIRSTDEALERDIRECTION BETWEEN 0 AND 3) -- Example Check
);

--|================================================================================================|
CREATE TABLE ROUND (
    ROUNDID                 INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    GAMEID                  INTEGER NOT NULL,
    ROUNDNUMBER             INTEGER NOT NULL, -- Sequence number within the game

    DEALERDIRECTION         INTEGER,
    DECLARERDIRECTION       INTEGER,
    NORTHSOUTHVULNERABLE    INTEGER DEFAULT 0, -- Typically 0=No, 1=Yes
    EASTWESTVULNERABLE      INTEGER DEFAULT 0, -- Typically 0=No, 1=Yes

    -- Contract information (denormalized, updated after bidding)
    CONTRACTSUIT            INTEGER,          -- e.g., 0=C, 1=D, 2=H, 3=S, 4=NT
    CONTRACTLEVEL           INTEGER,          -- 1-7
    CONTRACTMODIFIER        INTEGER DEFAULT 0,-- 0=Undoubled, 1=Doubled, 2=Redoubled

    -- Results (updated after play)
    NORTHSOUTHTRICKSTAKEN   INTEGER DEFAULT 0,
    EASTWESTTRICKSTAKEN     INTEGER DEFAULT 0,

    FOREIGN KEY(GAMEID) REFERENCES GAME(GAMEID)

    -- -- Example Check Constraints
    -- CONSTRAINT ROUND_DEALER_CHECK CHECK (DEALERDIRECTION BETWEEN 0 AND 3),
    -- CONSTRAINT ROUND_DECLARER_CHECK CHECK (DECLARERDIRECTION BETWEEN 0 AND 3 OR DECLARERDIRECTION IS NULL),
    -- CONSTRAINT ROUND_VULNERABLE_NS_CHECK CHECK (NORTHSOUTHVULNERABLE IN (0, 1)),
    -- CONSTRAINT ROUND_VULNERABLE_EW_CHECK CHECK (EASTWESTVULNERABLE IN (0, 1)),
    -- CONSTRAINT ROUND_CONTRACT_SUIT_CHECK CHECK (CONTRACTSUIT BETWEEN 0 AND 4 OR CONTRACTSUIT IS NULL),
    -- CONSTRAINT ROUND_CONTRACT_LEVEL_CHECK CHECK (CONTRACTLEVEL BETWEEN 1 AND 7 OR CONTRACTLEVEL IS NULL),
    -- CONSTRAINT ROUND_CONTRACT_MODIFIER_CHECK CHECK (CONTRACTMODIFIER IN (0, 1, 2)),
    -- -- Check trick sum only if a contract was played (contractBidId is not NULL)
    -- CONSTRAINT ROUND_TRICKS_CHECK CHECK (
    --     (CONTRACTBIDID IS NULL AND NORTHSOUTHTRICKSTAKEN = 0 AND EASTWESTTRICKSTAKEN = 0) OR
    --     (CONTRACTBIDID IS NOT NULL AND NORTHSOUTHTRICKSTAKEN + EASTWESTTRICKSTAKEN = 13) -- Assuming 13 tricks per round
    -- )
);

--|================================================================================================|
CREATE TABLE HAND (
    HANDID      INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    ROUNDID     INTEGER NOT NULL,
    PLAYERID    INTEGER NOT NULL,
    CARDS       TEXT NOT NULL,  -- String like "AH,TD,5S,KC..." (always 13 cards)

    FOREIGN KEY(PLAYERID) REFERENCES PLAYER(PLAYERID),
    FOREIGN KEY(ROUNDID) REFERENCES ROUND(ROUNDID)
);

--|================================================================================================|
CREATE TABLE BID (
    BIDID           INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    ROUNDID         INTEGER NOT NULL,
    DIRECTION       INTEGER NOT NULL, -- Player direction making the bid (0-3)
    SUIT            INTEGER,          -- e.g., 0=C, 1=D, 2=H, 3=S, 4=NT (NULL for Pass/Double/Redouble)
    LEVEL           INTEGER,          -- 1-7 (NULL for Pass/Double/Redouble)
    BIDTYPE         INTEGER NOT NULL, -- e.g., 0=Pass, 1=Bid, 2=Double, 3=Redouble
    BIDSEQUENCE     INTEGER NOT NULL, -- Order of bids within the round (0, 1, 2...)

    FOREIGN KEY(ROUNDID) REFERENCES ROUND(ROUNDID)

    -- UNIQUE(ROUNDID, BIDSEQUENCE), -- Ensure bid sequence is unique within a round

    -- -- Example Check Constraints
    -- CONSTRAINT BID_DIRECTION_CHECK CHECK (DIRECTION BETWEEN 0 AND 3),
    -- CONSTRAINT BID_SUIT_CHECK CHECK (SUIT BETWEEN 0 AND 4 OR SUIT IS NULL),
    -- CONSTRAINT BID_LEVEL_CHECK CHECK (LEVEL BETWEEN 1 AND 7 OR LEVEL IS NULL),
    -- CONSTRAINT BID_TYPE_CHECK CHECK (BIDTYPE BETWEEN 0 AND 3) -- Adjust if using different codes
);

--|================================================================================================|
CREATE TABLE TRICK (
    TRICKID             INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    ROUNDID             INTEGER NOT NULL,
    TRICKNUMBER         INTEGER NOT NULL, -- 1-13
    LEADERDIRECTION     INTEGER NOT NULL, -- Player direction leading the trick (0-3)
    WINNINGDIRECTION    INTEGER NOT NULL, -- Player direction winning the trick (0-3)

    FOREIGN KEY(ROUNDID) REFERENCES ROUND(ROUNDID)

    -- UNIQUE(ROUNDID, TRICKNUMBER), -- Ensure trick numbers are unique within a round

    -- -- Example Check Constraints
    -- CONSTRAINT TRICK_NUMBER_CHECK CHECK (TRICKNUMBER BETWEEN 1 AND 13), -- Assuming 13 tricks
    -- CONSTRAINT TRICK_LEADER_CHECK CHECK (LEADERDIRECTION BETWEEN 0 AND 3),
    -- CONSTRAINT TRICK_WINNER_CHECK CHECK (WINNINGDIRECTION BETWEEN 0 AND 3)
);

--|================================================================================================|
CREATE TABLE CARD (
    CARDID          INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, -- Use H2 standard identity
    TRICKID         INTEGER NOT NULL,
    PLAYERDIRECTION INTEGER NOT NULL, -- Direction of the player playing the card (0-3)
    SUIT            INTEGER NOT NULL, -- e.g., 0=C, 1=D, 2=H, 3=S
    RANK            INTEGER NOT NULL, -- e.g., 2-10, 11=J, 12=Q, 13=K, 14=A
    PLAYSEQUENCE    INTEGER NOT NULL, -- Order card played in the trick (0-3)

    FOREIGN KEY(TRICKID) REFERENCES TRICK(TRICKID)

    -- UNIQUE(TRICKID, PLAYSEQUENCE), -- Card play order is unique within a trick
    -- UNIQUE(TRICKID, PLAYERDIRECTION), -- Each player plays only one card per trick

    -- -- Example Check Constraints
    -- CONSTRAINT CARD_DIRECTION_CHECK CHECK (PLAYERDIRECTION BETWEEN 0 AND 3),
    -- CONSTRAINT CARD_SUIT_CHECK CHECK (SUIT BETWEEN 0 AND 3), -- Assuming 4 suits
    -- CONSTRAINT CARD_RANK_CHECK CHECK (RANK BETWEEN 2 AND 14), -- Assuming standard ranks A=14
    -- CONSTRAINT CARD_PLAY_SEQ_CHECK CHECK (PLAYSEQUENCE BETWEEN 0 AND 3)
);

-- Add Indexes for common lookups (Optional but recommended)
CREATE INDEX IDX_ROUND_GAMEID ON ROUND(GAMEID);
CREATE INDEX IDX_HAND_ROUNDID ON HAND(ROUNDID);
CREATE INDEX IDX_HAND_PLAYERID ON HAND(PLAYERID);
CREATE INDEX IDX_BID_ROUNDID ON BID(ROUNDID);
CREATE INDEX IDX_TRICK_ROUNDID ON TRICK(ROUNDID);
CREATE INDEX IDX_CARD_TRICKID ON CARD(TRICKID);