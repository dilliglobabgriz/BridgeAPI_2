-- ############################     Completed round     ##################################
INSERT INTO player (playerId) VALUES (9996);
INSERT INTO player (playerId) VALUES (9997);
INSERT INTO player (playerId) VALUES (9998);
INSERT INTO player (playerId) VALUES (9999);

INSERT INTO game (gameId, northId, eastId, southId, westId) VALUES(8888, 9996, 9997, 9998, 9999);

INSERT INTO round (roundId, gameId, roundNumber, dealerDirection) VALUES(7777, 8888, 1, 0);

INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6663, 7777, 9996, '2C, 4C, 9C, TC, QC, AC, 4D, 9D, AD, 5H, 7H, QH, QS');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6664, 7777, 9997, '5C, 8C, KC, 7D, 8D, JD, QD, 2H, KH, AH, 4S, TS, JS');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6665, 7777, 9998, '3C, JC, 3D, 5D, TD, KD, 9H, TH, 2S, 5S, 6S, 7S, KS');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6666, 7777, 9999, '6C, 7C, 2D, 6D, 3H, 4H, 6H, 8H, JH, 3S, 8S, 9S, AS');

INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5550, 7777, 0, 0, 1, 1, 1);  -- N 1C
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5551, 7777, 1, 0, 1, 2, 2);  -- E X
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5552, 7777, 2, 0, 1, 3, 3);  -- S XX
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5553, 7777, 3, 4, 1, 1, 4);  -- W 1NT    Contract
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5554, 7777, 0, 0, 1, 2, 5);  -- N X
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5555, 7777, 1, 0, 1, 0, 6);  -- E P
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5556, 7777, 2, 0, 1, 0, 7);  -- S P
INSERT INTO bid (bidId, roundId, direction, suit, level, bidType, bidSequence)
VALUES(5557, 7777, 3, 0, 1, 0, 8);  -- W P

UPDATE round 
SET declarerDirection = 3, contractSuit = 4, contractLevel = 1, contractModifier = 1
WHERE roundId = 7777;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4440, 7777, 1, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3330, 4440, 0, 0, 2, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3331, 4440, 1, 0, 5, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3332, 4440, 2, 0, 11, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3333, 4440, 3, 0, 6, 4);

UPDATE trick 
SET winningDirection = 2 
WHERE trickId = 4440;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4441, 7777, 2, 2);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3334, 4441, 2, 0, 3, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3335, 4441, 3, 0, 7, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3336, 4441, 0, 0, 9, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3337, 4441, 1, 0, 13, 4);

UPDATE trick 
SET winningDirection = 1 
WHERE trickId = 4441;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4442, 7777, 3, 1);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3338, 4442, 1, 0, 8, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3339, 4442, 2, 3, 2, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3340, 4442, 3, 1, 2, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3341, 4442, 0, 0, 10, 4);

UPDATE trick 
SET winningDirection = 0 
WHERE trickId = 4442;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4443, 7777, 4, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3342, 4443, 0, 0, 14, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3343, 4443, 1, 1, 7, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3344, 4443, 2, 1, 3, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3345, 4443, 3, 2, 3, 4);

UPDATE trick 
SET winningDirection = 0
WHERE trickId = 4443;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4444, 7777, 5, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3346, 4444, 0, 0, 12, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3347, 4444, 1, 3, 4, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3348, 4444, 2, 3, 5, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3349, 4444, 3, 3, 3, 4);

UPDATE trick 
SET winningDirection = 0
WHERE trickId = 4444;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4445, 7777, 6, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3350, 4445, 0, 0, 4, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3351, 4445, 1, 2, 2, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3352, 4445, 2, 3, 6, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3353, 4445, 3, 2, 4, 4);

UPDATE trick 
SET winningDirection = 0
WHERE trickId = 4445;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4446, 7777, 7, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3354, 4446, 0, 1, 14, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3355, 4446, 1, 1, 8, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3356, 4446, 2, 1, 5, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3357, 4446, 3, 1, 6, 4);

UPDATE trick 
SET winningDirection = 0
WHERE trickId = 4446;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4447, 7777, 8, 0);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3358, 4447, 0, 2, 5, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3359, 4447, 1, 2, 13, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3360, 4447, 2, 2, 9, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3361, 4447, 3, 2, 6, 4);

UPDATE trick 
SET winningDirection = 1
WHERE trickId = 4447;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4448, 7777, 9, 1);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3362, 4448, 1, 2, 14, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3363, 4448, 2, 2, 10, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3364, 4448, 3, 2, 8, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3365, 4448, 0, 2, 7, 4);

UPDATE trick 
SET winningDirection = 1
WHERE trickId = 4448;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4449, 7777, 10, 1);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3366, 4449, 1, 1, 11, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3367, 4449, 2, 1, 13, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3368, 4449, 3, 3, 8, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3369, 4449, 0, 1, 4, 4);

UPDATE trick 
SET winningDirection = 2
WHERE trickId = 4449;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4450, 7777, 11, 2);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3370, 4450, 2, 3, 7, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3371, 4450, 3, 3, 14, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3372, 4450, 0, 3, 12, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3373, 4450, 1, 3, 10, 4);

UPDATE trick 
SET winningDirection = 3
WHERE trickId = 4450;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4451, 7777, 12, 3);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3374, 4451, 3, 3, 9, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3375, 4451, 0, 1, 9, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3376, 4451, 1, 3, 11, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3377, 4451, 2, 3, 13, 4);

UPDATE trick 
SET winningDirection = 2
WHERE trickId = 4451;

INSERT INTO trick (trickId, roundId, trickNumber, leaderDirection) VALUES (4452, 7777, 13, 2);

INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3378, 4452, 2, 1, 10, 1);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3379, 4452, 3, 2, 11, 2);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3380, 4452, 0, 2, 12, 3);
INSERT INTO card (cardId, trickId, playerDirection, suit, rank, playSequence) 
VALUES (3381, 4452, 1, 1, 12, 4);

UPDATE trick 
SET winningDirection = 1
WHERE trickId = 4452;

UPDATE round
SET northSouthTricksTaken = 8, eastWestTricksTaken = 5
WHERE roundId = 7777;

-- ############################### Uncompleted Round ####################################
INSERT INTO player (playerId) VALUES (10996);
INSERT INTO player (playerId) VALUES (10997);
INSERT INTO player (playerId) VALUES (10998);
INSERT INTO player (playerId) VALUES (10999);

INSERT INTO game (gameId, northId, eastId, southId, westId) VALUES (8889, 10996, 10997, 10998, 10999);

INSERT INTO round (roundId, gameId, roundNumber, dealerDirection) VALUES (11111, 8889, 1, 0);