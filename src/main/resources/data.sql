INSERT INTO player VALUES (9996, default, default);
INSERT INTO player VALUES (9997, default, default);
INSERT INTO player VALUES (9998, default, default);
INSERT INTO player VALUES (9999, default, default);

INSERT INTO game (gameId, northId, eastId, southId, westId) VALUES(8888, 9996, 9997, 9998, 9999);

INSERT INTO round (roundId, gameId, roundNumber, dealerDirection) VALUES(7777, 8888, 1, 0);

INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6663, 7777, 9996, '2C, 4C, 2D, KD, 5H, 7H, TH, 3S, 4S, 6S, 9S, QS, AS');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6664, 7777, 9997, '3C, 6C, 8C, 9C, TC, 3D, 6D, 9D, QD, AH, 2S, 7S, JS');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6665, 7777, 9998, '5C, 7C, JC, KC, 4D, 5D, 7D, AD, JH, KH, 8S, TS, QH');
INSERT INTO hand (handId, roundId, playerId, cards) VALUES (6666, 7777, 9999, 'AC, QC, 8D, TD, JD, 8H, 9H, TH, QH, 6H, 3H, 5S, KS');

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
VALUES (3330, 4440, 0, )

UPDATE trick 
SET winningDirection = _ 
WHERE trickId = 4440;
