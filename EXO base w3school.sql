//EXO base w3school

// 1 Récupérer le nombre de customers par ville.
SELECT City, COUNT(CustomerID)
FROM [Customers]
GROUP BY City
ORDER BY COUNT(CUSTOMERID) DESC

//2 Récupérer le nom de chaque produit présent dans une Order
SELECT Orders.OrderID, Products.ProductName 
FROM Products,Orders,OrderDetails
WHERE OrderDetails.OrderID = Orders.OrderID
AND OrderDetails.ProductID = Products.ProductID

//3 Récupérer tous les produits de la catégorie "Beverages"
SELECT CategoryName, Products.ProductName 
FROM Categories,Products
WHERE Categories.CategoryID = Products.CategoryID
AND Categories.CategoryName = 'Beverages'

//4 Compter le nombre de clients ayant une tarte au sucre dans leurs commandes
SELECT COUNT(Customers.CustomerName) AS Client_avec_tarte_au_sucre
FROM Customers, Orders, OrderDetails 
WHERE Customers.CustomerID = Orders.CustomerID
AND Orders.OrderID = OrderDetails.OrderID
AND OrderDetails.ProductID = 62


//5 Compter le nombre de Sirop d'érable présent dans des commandes
SELECT SUM(OrderDetails.Quantity) AS Nbr_de_sirop_d_érable_dans_les_commandes
FROM OrderDetails, Products
WHERE OrderDetails.ProductID = Products.ProductID
AND Products.ProductID = 61

