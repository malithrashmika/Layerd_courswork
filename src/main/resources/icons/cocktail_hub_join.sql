SELECT 
    oi.order_id AS OrderID,
    oi.item_id AS ItemID,
    o.customer_id AS CustomerID,
    c.name AS CustomerName,
    i.name AS ItemName,
    oi.unit_price AS UnitPrice,
    oi.qty AS Qty,
    oi.cocktail_hub.order_item.subtotal AS Subtotal
FROM 
    cocktail_hub.order_item oi
JOIN 
    cocktail_hub.item i ON oi.item_id = i.item_id
JOIN 
    cocktail_hub.orders o ON oi.order_id = o.order_id
JOIN 
    cocktail_hub.customer c ON o.customer_id = c.customer_id
ORDER BY 
    oi.order_id, oi.item_id;
