## What employee worked on a order
*  This query finds which employee worked on a specific order. It joins the Orders table with the Worker table using the EmployeeID, and returns the order ID along with the employee’s ID, first and last name, role, and email.
```
-- Get the employee details for a specific order
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
```
## All orders sorted by their current status
* This query lists all customer orders organized by their status. It joins the Orders table with the Customer table (to show the customer’s first and last name) and the OrderStatusType table
  to display the status description. The results include the order ID, order date, customer name, and status, sorted by the status description.
```
-- List all orders sorted by status
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
```

## Searching Card by its Collector Number
* This query is for finding a specific card by using it's collector number. Allowing to see all verisons of card that have the same collector number even if different sets.
```
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
```

## Getting All ListName From Customer
* This Query is getting all the names of all lists that are created by a specific customer. Allowing to see all Lists that customer has.
```
SELECT
  c.FirstName,
  c.LastName,
  l.listName,
FROM
  Customer c
JOIN
  List l ON c.CustomerEmail = l.CustomerEmail
WHERE
  c.CustomerEmail = 'washington.@gmail.com'
ORDER BY
  l.ListName ASC;
```
## Finding Card Quantity In Stock
* This query is looking up a specific card in the card table and showing what set it is from and the stock there is currently. The Query has 'sol ring', so it will go through the database and find all card names with sol ring and bring back the stock for each name it finds. 
```
SELECT
  CardName,
  SetName,
  Stock
FROM
  Card
WHERE
  CardName = 'Sol Ring';
```
## Customers With No Orders
* This query finds all customers who have never placed an order. It uses a LEFT JOIN between customers and orders, filtering for rows where the order_id is NULL. This helps identify inactive customers or customers who signed up but haven’t purchased yet.
```
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
```
## Customer Order History
* This query lists all orders placed by a specific customer, including order details, current status, and the total cost of each order.
```
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
```
