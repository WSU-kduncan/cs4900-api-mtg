-- What employee worked on a order
-- This query finds which employee worked on a specific order.
SELECT 
    o.OrderID,             
    w.EmployeeID,         
    w.FirstName,            
    w.LastName,           
    w.Role,               
    w.Email                 
FROM Orders o
JOIN Worker w 
    ON o.EmployeeID = w.EmployeeID  
WHERE o.OrderID = 123;

-- All orders sorted by their current status
-- This query lists all customer orders organized by their status.
SELECT 
    o.OrderID,                        
    o.OrderDate,                      
    c.FirstName AS CustomerFirstName, 
    c.LastName AS CustomerLastName,  
    ost.StatusDescription             
FROM Orders o
JOIN Customer c 
    ON o.CustomerEmail = c.CustomerEmail         
JOIN OrderStatusType ost 
    ON o.OrderStatusTypeID = ost.StatusTypeID     
ORDER BY ost.StatusDescription;

-- Searching Card by its Collector Number
-- This query is for finding a specific card by using its collector number.
SELECT
    CardName,
    SetName,
    CardCondition,
    CardType,
    Price,
    Stock
FROM
    Card
WHERE
    CardNumber = 261;

-- Getting All ListName From Customer
-- This query gets all the names of lists created by a specific customer.
SELECT
  c.FirstName,
  c.LastName,
  l.ListName
FROM
  Customer c
JOIN
  List l ON c.CustomerEmail = l.CustomerEmail
WHERE
  c.CustomerEmail = 'washington.@gmail.com'
ORDER BY
  l.ListName ASC;

-- Finding Card Quantity In Stock
-- This query looks up a specific card in the card table and shows current stock.
SELECT
  CardName,
  SetName,
  Stock
FROM
  Card
WHERE
  CardName = 'Sol Ring';

-- Customers With No Orders
-- This query finds all customers who have never placed an order.
SELECT 
    c.customer_id, 
    c.first_name, 
    c.last_name, 
    c.email
FROM customers c
LEFT JOIN orders o 
    ON o.customer_id = c.customer_id
WHERE o.order_id IS NULL
ORDER BY c.last_name, c.first_name;

-- Customer Order History
-- This query lists all orders placed by a specific customer, including totals.
SELECT 
    o.OrderID,
    o.OrderDate,
    c.FirstName AS CustomerFirstName,
    c.LastName  AS CustomerLastName,
    ost.StatusDescription,
    SUM(oi.Quantity * oi.Price) AS OrderTotal
FROM Orders o
JOIN Customer c 
    ON o.CustomerEmail = c.CustomerEmail
JOIN OrderStatusType ost 
    ON o.OrderStatusTypeID = ost.StatusTypeID
LEFT JOIN OrderItem oi 
    ON oi.OrderID = o.OrderID
WHERE c.CustomerEmail = 'customer@example.com'
GROUP BY o.OrderID, o.OrderDate, c.FirstName, c.LastName, ost.StatusDescription
ORDER BY o.OrderDate DESC;
