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
