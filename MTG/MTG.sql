CREATE TABLE `Customer` (
  `CustomerEmail` varchar(128) PRIMARY KEY NOT NULL,
  `FirstName` varchar(35) NOT NULL,
  `LastName` varchar(35) NOT NULL,
  `PhoneNumber` char(10) NOT NULL
);

CREATE TABLE `Worker` (
  `EmployeeID` int PRIMARY KEY NOT NULL,
  `FirstName` varchar(35) NOT NULL,
  `LastName` varchar(35) NOT NULL,
  `Role` char(3) NOT NULL,
  `Email` varchar(128) NOT NULL
);

CREATE TABLE `OrderStatusType` (
  `StatusTypeID` tinyint PRIMARY KEY NOT NULL,
  `StatusDescription` varchar(255) NOT NULL
);

CREATE TABLE `Orders` (
  `OrderID` int PRIMARY KEY NOT NULL,
  `OrderDate` datetime NOT NULL,
  `OrderStatusTypeID` tinyint NOT NULL,
  `CustomerEmail` varchar(128) NOT NULL,
  `EmployeeID` int NOT NULL,
  FOREIGN KEY (`CustomerEmail`) REFERENCES `Customer` (`CustomerEmail`),
  FOREIGN KEY (`EmployeeID`) REFERENCES `Worker` (`EmployeeID`),
  FOREIGN KEY (`OrderStatusTypeID`) REFERENCES `OrderStatusType` (`StatusTypeID`)
);

CREATE TABLE `Card` (
  `CardNumber` smallint(4) NOT NULL,
  `SetName` char(3) NOT NULL,
  `CardCondition` varchar(14) NOT NULL,
  `CardName` varchar(128) NOT NULL,
  `CardDescription` text NOT NULL,
  `ManaValue` varchar(10) NOT NULL,
  `CardType` varchar(64) NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  `Stock` int NOT NULL,
  PRIMARY KEY (`CardNumber`, `SetName`)
);

CREATE TABLE `OrderItem` (
  `OrderID` int NOT NULL,
  `CardNumber` smallint NOT NULL,
  `SetName` char(3) NOT NULL,
  `Quantity` smallint NOT NULL,
  `Price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`OrderID`, `CardNumber`, `SetName`),
  FOREIGN KEY (`OrderID`) REFERENCES `Orders` (`OrderID`),
  FOREIGN KEY (`CardNumber`, `SetName`) REFERENCES `Card` (`CardNumber`, `SetName`)
);

CREATE TABLE `List` (
  `ListID` int PRIMARY KEY NOT NULL,
  `CustomerEmail` varchar(128) NOT NULL,
  `ListName` varchar(32) NOT NULL,
  FOREIGN KEY (`CustomerEmail`) REFERENCES `Customer` (`CustomerEmail`)
);

CREATE TABLE `ListItem` (
  `ListID` int NOT NULL,
  `CardNumber` smallint NOT NULL,
  `SetName` char(3) NOT NULL,
  `QuantityWanted` smallint NOT NULL,
  PRIMARY KEY (`ListID`, `CardNumber`, `SetName`),
  FOREIGN KEY (`ListID`) REFERENCES `List` (`ListID`),
  FOREIGN KEY (`CardNumber`, `SetName`) REFERENCES `Card` (`CardNumber`, `SetName`)
);


-- ===============================================
-- MTG sample seed data for testing GET endpoints
-- ===============================================

-- 1) Status types
INSERT INTO OrderStatusType (StatusTypeID, StatusDescription) VALUES
  (1, 'Pending'),
  (2, 'Paid'),
  (3, 'Fulfilled'),
  (4, 'Canceled');

-- 2) Customers
INSERT INTO Customer (CustomerEmail, FirstName, LastName, PhoneNumber) VALUES
  ('sara@mtgshop.com', 'Sara', 'Nguyen', '5095551111'),
  ('mike@mtgshop.com', 'Mike', 'Lopez', '5095552222'),
  ('josh@mtgshop.com', 'Josh', 'Kim',   '5095553333');

-- 3) Workers
INSERT INTO Worker (EmployeeID, FirstName, LastName, Role, Email) VALUES
  (101, 'Ava',  'Reed',  'ADM', 'ava.reed@mtgshop.com'),
  (102, 'Noah', 'Stone', 'SAL', 'noah.stone@mtgshop.com'),
  (103, 'Lia',  'Park',  'MGR', 'lia.park@mtgshop.com');

-- 4) Cards (composite PK: CardNumber, SetName)
INSERT INTO Card
  (CardNumber, SetName, CardCondition, CardName, CardDescription, ManaValue, CardType, Price, Stock)
VALUES
  (269, 'ALP', 'NM', 'Sol Ring',        'Adds two colorless mana.', '1', 'Artifact',  2.50,  25),
  (1,   'LEB', 'NM', 'Black Lotus',     'The Power Nine icon.',     '0', 'Artifact', 150000.00, 1),
  (4,   'MPS', 'LP', 'Lightning Bolt',  'Deal 3 damage to any target.', '1', 'Instant', 2.00, 120),
  (2,   'DMU', 'NM', 'Sheoldred',       'Powerful creature.',       '4', 'Creature', 65.00,  8),
  (10,  'ELD', 'MP', 'Fabled Passage',  'Fetch a basic land.',      '0', 'Land',     12.00, 30),
  (7,   'IKO', 'NM', 'Lurrus',          'Companion creature.',      '3', 'Creature',  5.00, 14);

-- 5) Lists (customer wishlists)
INSERT INTO List (ListID, CustomerEmail, ListName) VALUES
  (1, 'sara@mtgshop.com', 'Commander Wants'),
  (2, 'mike@mtgshop.com', 'Modern Staples');

-- 6) Orders
INSERT INTO Orders (OrderID, OrderDate, OrderStatusTypeID, CustomerEmail, EmployeeID) VALUES
  (5001, '2025-10-01 10:00:00', 1, 'sara@mtgshop.com', 102), -- Pending
  (5002, '2025-10-02 12:30:00', 2, 'mike@mtgshop.com', 103), -- Paid
  (5003, '2025-10-03 15:45:00', 3, 'josh@mtgshop.com', 101); -- Fulfilled

-- 7) Order items (composite PK: OrderID, CardNumber, SetName)
INSERT INTO OrderItem (OrderID, CardNumber, SetName, Quantity, Price) VALUES
  (5001, 10,  'ELD', 2, 12.00),  -- Fabled Passage x2
  (5001, 4,   'MPS', 1,  2.00),  -- Lightning Bolt x1
  (5002, 269, 'ALP', 1,  2.50),  -- Sol Ring x1
  (5003, 2,   'DMU', 1, 65.00);  -- Sheoldred x1

-- 8) List items (composite PK: ListID, CardNumber, SetName)
INSERT INTO ListItem (ListID, CardNumber, SetName, QuantityWanted) VALUES
  (1, 269, 'ALP', 1),  -- Sara wants Sol Ring
  (1, 10,  'ELD', 2),  -- Sara wants two Fabled Passage
  (2, 4,   'MPS', 4);  -- Mike wants four Lightning Bolt
