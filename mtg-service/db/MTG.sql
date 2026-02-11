CREATE TABLE `Player` (
  `PlayerID` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `PlayerName` varchar(64) NOT NULL,
  `Wins` int NOT NULL DEFAULT 0,
  `Losses` int NOT NULL DEFAULT 0,
  `Ties` int NOT NULL DEFAULT 0,
  `CasualWins` int NOT NULL DEFAULT 0,
  `CasualLosses` int NOT NULL DEFAULT 0
);

CREATE TABLE `Deck` (
  `DeckName` varchar(64) PRIMARY KEY NOT NULL,
  `Wins` int NOT NULL DEFAULT 0,
  `Losses` int NOT NULL DEFAULT 0,
  `Ties` int NOT NULL DEFAULT 0,
  `CasualWins` int NOT NULL DEFAULT 0,
  `CasualLosses` int NOT NULL DEFAULT 0
);

CREATE TABLE `MatchHistory` (
  `MatchID` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `PlayerID` int NOT NULL,
  `DeckName` varchar(64) NOT NULL,
  `WinnerName` varchar(64) NOT NULL,
  `Format` varchar(8) NOT NULL,
  `PlayerWin` boolean NOT NULL,
  `OpponentOne` varchar(64) NOT NULL,
  `OpponentOneDeck` varchar(64) NOT NULL,
  `OpponentTwo` varchar(64) NOT NULL,
  `OpponentTwoDeck` varchar(64) NOT NULL,
  `OpponentThree` varchar(64) NOT NULL,
  `OpponentThreeDeck` varchar(64) NOT NULL,
  `Result` varchar(8) NOT NULL,
  `PlayedAt` datetime NOT NULL,
  FOREIGN KEY (`PlayerID`) REFERENCES `Player` (`PlayerID`),
  FOREIGN KEY (`DeckName`) REFERENCES `Deck` (`DeckName`)
);

-- Sample seed data
INSERT INTO Player (PlayerID, PlayerName, Wins, Losses, Ties, CasualWins, CasualLosses) VALUES
  (1, 'Ethan', 0, 0, 0, 0, 0),
  (2, 'Micah', 0, 0, 0, 0, 0),
  (3, 'Nathan', 0, 0, 0, 0, 0),
  (4, 'Josh', 0, 0, 0, 0, 0),
  (5, 'Allen', 0, 0, 0, 0, 0);

INSERT INTO Deck (DeckName, Wins, Losses, Ties, CasualWins, CasualLosses) VALUES
  ('Etali',       0, 0, 0, 0, 0),
  ('Tymna_Kraum', 0, 0, 0, 0, 0),
  ('Inalla',      0, 0, 0, 0, 0),
  ('RogSi',       0, 0, 0, 0, 0),
  ('Kinnan',      0, 0, 0, 0, 0);

INSERT INTO MatchHistory (MatchID, PlayerID, DeckName, WinnerName, Format, PlayerWin, OpponentOne, OpponentOneDeck, OpponentTwo, OpponentTwoDeck, OpponentThree, OpponentThreeDeck, Result, PlayedAt) VALUES
  (1, 1, 'Etali', 'Ethan', 'CEDH', 1, 'Micah', 'Tymna_Kraum', 'Nathan', 'Inalla', 'Josh', 'RogSi', 'WIN',  '2026-02-01 18:30:00'),
  (2, 2, 'Tymna_Kraum', 'Ethan', 'CEDH', 0, 'Ethan', 'Etali', 'Josh', 'RogSi', 'Allen', 'Kinnan', 'TIE', '2026-02-03 19:00:00'),
  (3, 3, 'Inalla', 'Nathan', 'CASUAL', 1, 'Micah', 'Tymna_Kraum', 'Ethan', 'Etali', 'Allen', 'Kinnan', 'WIN', '2026-02-05 20:15:00');




