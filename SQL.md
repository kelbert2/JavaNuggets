https://www.sqlservertutorial.net/sql-server-basics/

# Data Definition Language
## Create Database
## Alter Database
## Create Table
## Alter Table
## Drop Table

# Order of Operations
1. `FROM` - choose and `JOIN` tables to get base data.
2. `WHERE` - filter base data based on a condition (aggregate data is unknown).
3. `GROUP BY` - aggregate base data into distinct values grouped by some column.
4. `HAVING` - filter aggregated data.
5. `SELECT` - return specific columns of final data.
6. `ORDER BY` - sort final data.
7. `LIMIT` - limit returned data to a row count.
# Data Manipulation Language
## Select
```SQL
/* Select All */
SELECT * FROM table;

/* Select Specific */
SELECT column1, column2 | expression AS column2_alias
FROM table_name

/* Select Only Different Combinations of the SELECTed Columns */
SELECT DISTINCT column1, column2
FROM table_name
/* Returns no duplicates, so returns at most one NULL. */

/* Select Composite */
SELECT first_name + ' ' + surname AS 'Full Name'

/* Basic */
SELECT
    city,
    COUNT (*) AS count
FROM
    sales
WHERE
    state = 'CA'
GROUP BY
    city
HAVING
    COUNT (*) > 10
ORDER BY
    city;
```
#### Subqueries
Nested inside `SELECT`, `INSERT`, `UPDATE`, or `DELETE`. Can be used after `FROM`, `WHERE`, `NOT) IN`, `ANY`, `ALL`, and `(NOT) EXISTS` and in place of epxressions, if it returns a single value. 

Subqueries are executed first.

Enclosed in (parentheses). 

There can be up to 32 levels of nesting.
```SQL
SELECT order_id, customer_id
FROM sales.orders
WHERE customer_id IN (
    SELECT customer_id
    FROM sales.customers
    WHERE city = 'New York'
)
ORDER BY order_id DESC;
```
After `IN`, returns a set of 0 or more values so the outer query can make use of them. It substitutes values.

In place of an expression:
```SQL
SELECT order_id, order_date, (
    SELECT MAX (list_price)
    FROM sales.order_items i
    WHERE i.order_id = o.order_id
) AS max_list_price
FROM sales.orders o
ORDER BY order_date DESC;
```

With `ANY` operator: returns `TRUE` if one of a comparison pair `(scalar_expression, vi)` evaluates to `TRUE`.

`scalar_expression comparison_operator ANY (subquery)`

With `ANY`:
```SQL
SELECT product_name, list_price
FROM production.products
WHERE list_price >= ANY (
    SELECT AVG(list_price)
    FROM production.products
    GROUP BY brand_id
);
```
Find maximum list price for each brand and return the products greater than or equal to any brand's maximum list price.

With `ALL`:
```SQL
SELECT product_name, list_price
FROM production.products
WHERE list_price >= ALL (
    SELECT AVG(list_price)
    FROM production.products
    GROUP BY brand_id
);
```
Finds products greater than or equal to the average list price returned by the subquery.

With `EXISTS`:
```SQL
SELECT customer_id, first_name, last_name, city
FROM sales.customers c
WHERE EXISTS (
    SELECT customer_id
    FROM sales.orders o
    WHERE o.customer_id = c.customer_id
    AND YEAR(order_date) = 2017
)
ORDER BY first_name, last_name;
```
Return the names of customers that have placed an order in the year 2017.

With `FROM`:
```SQL
SELECT AVG(order_count) AS average_order_count
FROM (
    SELECT staff_id, COUNT(order_id) AS order_count
    FROM sales.orders
    GROUP BY staff_id
) t;
```
Return the average number of orders made by a staff member.

##### Correlated Subqueries / Repeating Subquery
Rely on values of the outer query. Cannot be executed independently. Executed repeatedly, for each row evaluated by the outer query.

With `WHERE`:
```SQL
SELECT product_name, list_price, category_id
FROM production.products p1
WHERE list_price IN (
    SELECT MAX(p2.list_price)
    FROM production.products p2
    WHERE p2.category_id = p1.category_id
    GROUP BY p2.category_id
)
ORDER BY category_id, product_name;
```
Finds products whose list price is the highest list price of products within that category.
### WHERE
Filtering by a specified condition.
```SQL
SELECT column1, column2
FROM table_name
WHERE condition; 
```

Conditions include `column1='Value'`, >, <, >=, <= , <> (not equal), and
*` BETWEEN value AND value` - range
* `LIKE 's%'` - pattern where starts with 's'
* * '%Cruiser%' is some string + Cruiser + some string
* `column | expression [NOT] IN ('Paris','London')` - multiple possible values
* `IS NULL` - used since NULL <> NULL

You can use multiple conditions by joining them with
* AND
* OR
* NOT
These can be combined with ()s.

#### Wildcards
* % - any string of 0 or more characters
* _ - any single character
* [list of characters] - any single character within the specified list, which is just a string of characters without separators
* [character-character] - any single character within the range
* [^list or range] - any single character not within the list or range
You can use an escape character to treat wildcard characters as regular characters.
```SQL
column | expression NOT LIKE pattern [ESCAPE escape_character]
```

Other operators:
#### EXISTS
Checks if a subquery returns any row. Will be true even if returns a `NULL` value.

```SQL
SELECT *
FROM sales.orders
WHERE customer_id IN (
        SELECT customer_id
        FROM sales.customers
        WHERE city = 'San Jose'
    )
ORDER BY customer_id, order_date;

-- Same as

SELECT *
FROM sales.orders o
WHERE EXISTS (
        SELECT customer_id
        FROM sales.customers c
        WHERE o.customer_id = c.customer_id
        AND city = 'San Jose'
    )
ORDER BY o.customer_id, order_date;
```
Returns orders of customers from San Jose.

#### ANY
Compares a scalar value with a each value in the set of values returned by a subquery `SELECT`. If any are true, the `ANY` operator returns true.

```SQL
SELECT product_name, list_price
FROM production.products
WHERE product_id = ANY(
    SELECT product_id
    FROM sales.order_items
    WHERE quantity >= 2
)
ORDER BY product_name;
```
Returns products sold with more than two units in a sales order.

#### ALL
Compares a scalar value with each value in a set returned by a subquery `SELECT`. If all are true, the `ALL` operator returns true.

For:
```SQL
SELECT AVG(list_price) average_list_price
FROM production.products
GROUP BY brand_id
ORDER BY average_list_price;
```
Returns list of average prices per brand.

With the `>` operator, the scalar expression needs to be greater than the largest value returned by the subquery (meaning it is larger than every value returned by the subquery).
```SQL
SELECT product_name, list_price
FROM production.products
WHERE list_price > ALL (
    SELECT AVG(list_price) average_list_price
    FROM production.products
    GROUP BY brand_id
)
ORDER BY list_price;
```
Returns all products with prices greater than the average list price of products of all brands.

### Grouping
#### GROUP BY
Groups rows together if they have the same value in the specified column. Removes duplicates - returns only unique combinations, unlike `ORDER BY`.

Often use aggregate functions to return a unique value per group.

Generally, columns appearing in SELECT are dependent upon groups in GROUP BY - if a column only contains one value for any given combination of values in the columns appearing in GROUP BY, can reference the column in SELECT even if it doesn't appear in an aggregate expression (that would be calculated over the entire group).
```SQL
SELECT select_list
FROM table_name
GROUP BY column_name1, column_name2

-- Same as
SELECT DISTINCT select_list
FROM table_name
ORDER BY column_name1, column_name2
```
Aggregate functions calculated per grouping:
* COUNT() - rows per group
* SUM()
* AVG()
* MIN()
* MAX()
* STRING_AGG(column_name, separator_string) - `string_agg(title, ', ') ... GROUP BY genre` strings together multiple values that would otherwise be grouped together and lost 
```SQL
SELECT customer_id, YEAR(order_date) order_year, COUNT(order_id) order_placed
FROM sales.orders
WHERE customer_id IN (1,2)
GROUP BY customer_id, YEAR(order_date)
ORDER BY customer_id
```
#### HAVING
Often used with `GROUP BY` to filter groups based on conditions. Filters aggregate results. Without `GROUP BY`, considers the whole relation as one group.

Having is processed after `GROUP BY` so cannot refer to to any aggregate functions specified in the select list using column aliases - you have to explictly call the aggregate function `HAVING aggregate_func(column_name) > value`
```SQL
SELECT select_list
FROM table_name
GROUP BY group_list
HAVING conditions
```

```SQL
SELECT a, count(*) as c
FROM table_name
GROUP BY a
HAVING count(*) > 1;

-- Same as
SELECT * 
FROM (
    SELECT a, count(*) as c
    FROM table_name
    GROUP BY a
)
WHERE c > 1;
-- WHERE filters rows, so just need to pre-group
```

#### GROUPING SETS
Grouping sets are groups of columns that can be used in `SELECT`, `GROUP BY`, and `ORDER BY` to represent a list of column names.

Typically, a single query with an aggregate function defines a single grouping set, as defined in `GROUP BY`. If nothing is in the `GROUP BY` clause, the query is run over an empty grouping set, (), or everything.

To get results with multiple aggregations of different grouping sets, can do a UNION ALL operator (will likely need some dummy column that's just `SELECT column_name, NULL, agg_func(column_name3)` to get the aggregate function with `GROUP BY column_name`).

Useful for summarizing data as subtotals.

```SQL
SELECT column_name, column_name2, agg_func(column_name3)
FROM table_name
GROUP BY column_name, column_name2 -- agg function on a per col_name, col_name2 basis

UNION ALL

SELECT column_name, NULL, agg_func(column_name3)
FROM table_name
GROUP BY column_name -- agg function over all for column_name

UNION ALL

SELECT NULL, column_name2, agg_func(column_name3)
FROM table_name
GROUP BY column_name2 -- agg function over all for column_name2

UNION ALL

SELECT NULL, NULL, agg_func(column_name3)
FROM table_name -- agg function over all data

ORDER BY column_name, column_name2;
```
This can be accomplished with the far more efficient:
```SQL
SELECT columm_name, column_name2, agg_func(column_name3)
FROM table_name
GROUP BY
    GROUPING SETS (
        (column_name, column_name2),
        (column_name),
        (column_name2),
        ()
    );
```

As an example:
```SQL
SELECT
	YEAR(OrderDate) AS OrderYear,
	MONTH(OrderDate) AS OrderMonth,
	SUM(SubTotal) AS Incomes
FROM Sales.SalesOrderHeader
GROUP BY
	GROUPING SETS
	(
		YEAR(OrderDate), --1st grouping set
		(YEAR(OrderDate),MONTH(OrderDate)), --2nd grouping set
        () -- SUM of whole table
	);

-- Same as
 	
SELECT
	YEAR(OrderDate) AS OrderYear,
	NULL AS OrderMonth, --Dummy Column
	SUM(SubTotal) AS Incomes
FROM Sales.SalesOrderHeader
GROUP BY YEAR(OrderDate)
UNION ALL
SELECT
	YEAR(OrderDate) AS OrderYear,
	MONTH(OrderDate) AS OrderMonth,
	SUM(SubTotal) AS Incomes
FROM Sales.SalesOrderHeader
GROUP BY YEAR(OrderDate), MONTH(OrderDate)
ORDER BY OrderYear, OrderMonth;
```
Returns company income per year and month, per month, and over all time, if include () in the GROUPING SET. Returns something like:
```SQL
2006 1 120 -- Sum over January, 2006
2006 2 2290
2006 3 2321
2006 4 2342
2006 5 1231
2006 6 12321
2006 7 1231
2006 8 2343
2006 9 23432
2006 10 324
2006 11 3242
2006 12 2734
2006 NULL 32094 -- Sum over 2006
2007 ...
2008 NULL 34223
NULL NULL 34980238409 -- Sum over all time
```
For a bunch of years, with the `NULL` values being subtotals as `NULL` is acting as a placeholder. If grouped columns are `NULL`able, `GROUPING` or `GROUPING_ID` can identify if the `NULL` came from the `GROUPING SETS` operator or as part of the data.

##### GROUPING
With:
```SQL
SELECT 
    GROUPING(column_name) grouping_1
    GROUPING(column_name2) grouping_2,
    column_name,
    column_name2,
    agg_func(column_name3)
... with grouping sets
```
Creates new columns. Grouping_1 shows 1 if column_3 is aggregated by column_name, 0 if it is not.

##### CUBE
Generates multiple `GROUPING SET`s. All combinations of some input. For n inputs, generates 2^{n} grouping sets.

```SQL
GROUP BY
    CUBE (d1, d2, d3);
 -- Same as
 GROUP BY
    GROUPING SETS (
        (d1,d2,d3), 
        (d1,d2),
        (d1,d3),
        (d2,d3),
        (d1),
        (d2),
        (d3), 
        ()
    );
```

Can do partials with
```SQL
GROUP BY
    d1,
    CUBE(d2, d3);
```

##### ROLLUP
Assumes a hierarchy among dimension columns (input) and, instead of generating all possible combinations as `CUBE` does, creates a subset.
```SQL
-- CUBE (d1,d2,d3)
(d1, d2, d3)
(d1, d2)
(d2, d3)
(d1, d3)
(d1)
(d2)
(d3)
()
-- ROLLUP(d1,d2,d3)
(d1, d2, d3)
(d1, d2)
(d1)
()
```
Useful if you're calculating something likes sales per year, per quarter, per month.

### ORDER BY
Sorts the selection by a certain column. By default, the result is sorted by the first column selected, then the next and so on.

NULL is the lowest value.

Order By knows column alias at the time of sorting.
```SQL
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC; 
```
Columns in ORDER BY must appear in either the SELECT list or defined in the table specified in the FROM clause.

You can also order by
* `LEN(string)` - number of characters.

#### OFFSET FETCH
Offset is the number of rows to skip before starting to return from the query. Can be a constant, variable, or parameter >= 0.

Optional Fetch is the numbver of rows to reutnr after the offset. Can be a constant, variable, or scaler >= 1.
```SQL
ORDER BY column_list [ASC |DESC]
OFFSET offset_row_count {ROW | ROWS}
FETCH {FIRST | NEXT} fetch_row_count {ROW | ROWS} ONLY
```
Useful for implementing paging.

Top 10 to 20 most expensive products:
```SQL
SELECT
    product_name,
    list_price
FROM
    production.products
ORDER BY
    list_price DESC,
    product_name 
OFFSET 10 ROWS 
FETCH NEXT 10 ROWS ONLY;
```

#### SELECT TOP
Limit the number of rows or a percentage returned in a query result set.
```SQL
SELECT TOP (expression) [PERCENT]
    [WITH TIES]
FROM 
    table_name
ORDER BY 
    column_name;
```

WITH TIES allows you to return more rows with values that match the last row in the limited result set.

Top ten percent most expensive products, where if there are multiple that are at least the minimum top 10% value, they are also returned:
```SQL
SELECT TOP 3 WITH TIES
    product_name, 
    list_price
FROM
    production.products
ORDER BY 
    list_price DESC;
``` 

## Joins
### Inner Join
Returns rows that have corresponding rows in both tables.

### Left (Outer) Join
Selects all data from the left table and adds matching data from the right table or, if none is found, NULL.

To get the rows that available only in the left table but not in the right table
```SQL
SELECT  
    c.id candidate_id,
    c.fullname candidate_name,
    e.id employee_id,
    e.fullname employee_name
FROM 
    hr.candidates c
    LEFT JOIN hr.employees e 
        ON e.fullname = c.fullname
WHERE 
    e.id IS NULL;
```
### Right Join
Is that but selecting data from the right table with matching rows on the left.

### Full Join
Reutns all rows from both left and right, with matching rows from both when available.

To get just rows that exist in one table or the other:
```SQL
SELECT  
    c.id candidate_id,
    c.fullname candidate_name,
    e.id employee_id,
    e.fullname employee_name
FROM 
    hr.candidates c
    FULL JOIN hr.employees e 
        ON e.fullname = c.fullname
WHERE
    c.id IS NULL OR
    e.id IS NULL;
```

### Cross Join
Joins every row from the first table with every row from the second (Cartesian product). It returns combinations of everything. 
### Self Join
References the same table, so need aliases to give different names.

```SQL
SELECT
    e.first_name + ' ' + e.last_name employee,
    m.first_name + ' ' + m.last_name manager
FROM
    sales.staffs e
INNER JOIN sales.staffs m ON m.staff_id = e.manager_id
ORDER BY
    manager;
```

# Combining Queries/ Set Operators
Combine multiple result sets into one.
## UNION
Includes all of the rows in both `SELTE` statements. Needs the number and order of the columns to be the same, with corresponding column datatypes the same or compatible.

By default, removes duplicate rows.
### UNION ALL
Retains duplicate rows.

Joins combine columns (add columns to the same rows), Unions combine rows (add rows on rows).

```SQL
SELECT first_name, last_name, staff_id AS id
FROM sales.staffs
UNION ALL
SELECT first_name, last_name, customer_id AS id
FROM sales.customers.
ORDER BY first_name, last_name, id
```
Returns the names of all staff members and all customers, including duplicates, as sorted by first and last name. 

The `ORDER BY` applies to the entire union. Can only be ordered by column names that are in every component result set (so not staff_id or customer_id as these are not common to the queries, but because rename to the same name, "id", can now order by id). You can also order by #, where # is some column number. This doesn't require both column #s to have the same name in both queries.

You may need to add a dummy column to merge two queries. In `SELECT NULL AS column_name`.

## INTERSECT
Returns distinct rows that are output by both queries.

```SQL
SELECT city
FROM sales.customers
INTERSECT
SELECT city
FROM sales.stores
ORDER BY city;
```
Returns cities that are common between customers and stores.

## EXCEPT
Subtracts a result set from another result set of another query. Returns distinct rows from the first query that are not output by the second query.

```SQL
SELECT product_id
FROM production.products
EXCEPT
SELECT product_id
FROM sales.order_items
ORDER BY product_id
```
Finds products that have no sales.

# Misc.
## Common Table Expressions (CTE) WITH
Allows you to define a temporary named result set that is temporarily available in teh execution scope of statements including `SELECT`, `INSERT`, `UPDATE`, `DELETE`, and `MERGE`.

```SQL
WITH expression_name[(column_name [, ...])]
AS (CTE_definition)
SQL_statement;
```
Use the expression name that will be used later in a query. The number of columns in the list after the expression name must be the same number of columns defined in the CTE_definition. The `SELECT` statement result set populates the common table expression CTE.

Useful as an alternative to subqueries.

```SQL
WITH cte_sales_amounts (staff, sales, year) AS (
    SELECT first_name + ' ' + last_name,
        SUM(quantity * list_price * (1 - discount)),
        YEAR(order_date)
    FROM sales.orders o
        INNER JOIN sales.order_items i ON i.order_id = o.order_id
        INNER JOIN sales.staffs s ON s.staff_id = o.staff_id
    GROUP BY
        first_name + ' ' + last_name, year(order_date)
)

-- Use

SELECT staff, sales
FROM cte_sales_amounts
WHERE year = 2018;
```

### Recursive CTE
Recursive CTEs reference themselves, repeatedly executing. Good for components that are made up of components and hierarchical data that points to other data.
```SQL
WITH expression_name (comma_separated,column_list)
AS (
    -- Anchor member, base result
    initial query
    UNION ALL
    -- Recursive member that references expression_name
    -- Needs a termination condition
)
```

Return weekdays:
```SQL
WITH cte_numbers(n, weekday) 
AS (
    SELECT 0, DATENAME(DW, 0)
    UNION ALL
    SELECT n + 1, DATENAME(DW, n + 1)
    FROM cte_numbers
    WHERE n < 6
)

-- Use

SELECT weekday
FROM cte_numbers;
```

To get who employees report by starting from teh top and iterating through subordinates:
```SQL
with cte_org AS (
    SELECT staff_id, first_name, manager_id
    FROM sales.staffs
    WHERE manager_id IS NULL
    UNION ALL
    SELECT e.staff_id, e.first_name, e.manager_id
    FROM sales.staffs e
        INNER JOIN cte_org o ON o.staff_id = e.manager_id
)

-- Use

SELECT * FROM cte_org;
```

## Pivot - Convert Rows to Columns
Rotates a table. Turns unique values in one column into multiple columns and aggregates any remaining column values.
```SQL
-- Create temporary result with a derived table t or common table expression (CTE)
SELECT * FROM (
    -- Select base dataset
    SELECT category_name, product_id, model_year
    FROM prodcution.products p
        INNER JOIN production.categories c
            ON c.category_id = p.category_id
) t
-- Apply pivot operator
PIVOT(
    COUNT(product_id)
    FOR category_name IN (
        [Comfort Bicylces],
        [Electric Bikes],
        [Mountain Bikes]
    )
) AS pivot_table
```

To avoid having to type each category name in parentheses after `IN`, can use `QUOTENAME()`, which will wrap input in square brackets, to generate the list.

```SQL
DECLARE @columns NVARCHAR(MAX) = '';
SELECT @columns += QUOTENAME(category_name) + ','
FROM production.categories
ORDER BY category_name

SET @columns = LEFT(@columns, LEN(@columns) - 1);
PRINT @columns;
```
`LEFT()` removes the last comma from the `@columns` string. 

To create a dynamic pivot table that updates when production.categories gets a new category name. Instead of passing a fixed list of category names, pass a list.

```SQL
DECLARE 
    @columns NVARCHAR(MAX) = '', 
    @sql     NVARCHAR(MAX) = '';

-- select the category names
SELECT 
    @columns+=QUOTENAME(category_name) + ','
FROM 
    production.categories
ORDER BY 
    category_name;

-- remove the last comma
SET @columns = LEFT(@columns, LEN(@columns) - 1);

-- construct dynamic SQL
SET @sql ='
SELECT * FROM   
(
    SELECT 
        category_name, 
        model_year,
        product_id 
    FROM 
        production.products p
        INNER JOIN production.categories c 
            ON c.category_id = p.category_id
) t 
PIVOT(
    COUNT(product_id) 
    FOR category_name IN ('+ @columns +')
) AS pivot_table;';

-- execute the dynamic SQL
EXECUTE sp_executesql @sql;
```
## Create SEQUENCE
Lists of numbers where order matters.

```SQL
CREATE SEQUENCE [schema_name.] sequence_name  
    [ AS integer_type ]  -- TINYINT, SMALLINT, INT, BIGINT, or DECIMAL and NUMERIC with a scale of 0, default BIGINT.
    [ START WITH start_value ]   -- first value, between (min_value, max_value), default min_value if ASC, max_value if DESC.
    [ INCREMENT BY increment_value ]  -- nonzero, if negative, DESCending. Used when call NEXT VALUE FOR function.
    [ { MINVALUE [ min_value ] } | { NO MINVALUE } ]  -- lower bound, default to minimum of data type - 0 for TINYINT, negative value for others.
    [ { MAXVALUE [ max_value ] } | { NO MAXVALUE } ]  -- upper bound, defaults to maximum of the data type of the sequence object.
    [ CYCLE | { NO CYCLE } ]  -- restart from the min_value for ASC or max_value for DESC or throw an exception when min_value or max_value is exceeded, default NO CYCLE.
    [ { CACHE [ cache_size ] } | { NO CACHE } ];  -- number of values to cache to improve performance, default NO CACHE.
```
Example:
```SQL
CREATE SEQUENCE item_counter
    AS INT
    START WITH 10
    INCREMENT BY 10;

-- Use

SELECT NEXT VALUE FOR item_counter;

INSERT INTO procurement.purchase_orders
    (order_id,
    vendor_id,
    order_date)
VALUES
    (NEXT VALUE FOR item_counter,1,'2019-04-30');

CREATE TABLE procurement.goods_receipts
(
    receipt_id   INT	PRIMARY KEY 
        DEFAULT (NEXT VALUE FOR item_counter), 
    order_id     INT NOT NULL, 
    full_receipt BIT NOT NULL,
    receipt_date DATE NOT NULL,
    note NVARCHAR(100),
); -- can use same sequence in multiple tables.

SELECT 
    * 
FROM 
    sys.sequences; -- view detailed information of sequences.
```

# Modifying Data
## INSERT INTO
Adding more rows. Often specifytable by schema_name.table_name
```SQL
INSERT INTO table_name (comma_separated,column_list)
VALUES (comma_separated,value_list)
```
Each column in the column list must have a corresponding value in the value list.

If you don't specify a column in the column list, but the column is in the existing table, the new rows will be filled with:
* The next incremental value if the column is an Identity Column.
* The default value, if the column has one.
* Current timestanp if the data type is a timestamp data type.
* `NULL` if the column is `NULL`able.
* Calculated value if the column is computed.

If the insert is successful, get the message `(# rows affected)`, where # is the number of rows successfully inserted.

To instead return the inserted value of a column (even one that's not in the column list!) or columns, use the `OUTPUT` clause:
```SQL
INSERT INTO sales.promotions (
    promotion_name,
    discount,
    start_date,
    expired_date
) OUTPUT inserted.promotion_id, inserted.promotion_name
VALUES (
    '2018 Fall Promotion',
    0.15,
    '20181001',
    '20181001'
);
```
To insert an explict value for an identity function, need to first execute:
```SQL
SET IDENTITY_INSERT table_name ON;
```
Can set back to off.

### INSERT Multiple Rows
Have multiple lists of values, up to 1000.
```SQL
INSERT INTO table_name (comma_separated,column_list)
VALUES
    (comma_separated,value_list1),
    (comma_separated,value_list2),
    ...
    (comma_separated,value_listn)
```

### INSERT INTO SELECT
Insert data from other tables into a table using any valid `SELECT` statement that retrieves data corresponding to the columns in the column_list.

`TOP #` where # is some number of rows to insert into table_name. `PERCENT` inserts a percentage of rows instead. Best practice to use `TOP` with `ORDER BY`.

```SQL
INSERT [TOP (expression) [PERCENT]]
INTO table_name (comma_separated,column_list)
SELECT query;
```

With `TOP` with optional `PERCENT`:
```SQL
INSERT TOP (10) [PERCENT]
INTO sales.addresses (street, city, state, zip_code)
SELECT street, city, state, zip_code
FROM sales.customers
ORDER BY first_name, last_name;
```

## UPDATE 
Modifies existing data using a list of columns c1, c2, ..., cn and values v1, v2, ..., vn to be updated. A `WHERE` condition can specify which tows are selected to be updated, else all rows are updated.
```SQL
UPDATE table_name
SET c1 = v1, c2 = v2, ... cn = vn
[WHERE condition];
```
Modifying data by increasing its previous value:
```SQL
UPDATE sales.taxes
SET max_local_tax_rate += 0.02, avg_local_tax_rate += 0.01
WHERE max_local_tax_rate = 0.01;
```

### UPDATE JOIN
Perform a cross-table update. Specify new values for each column of the table to update (in the `UPDATE` and `FROM` clauses) joined with some other table, with an optional `WHERE` condition specifying which rows are updated.

```SQL
UPDATE t1
SET t1.c1 = t2.c2,
    t2.c2 = expression,
    ...
FROM t1 [INNER | LEFT] JOIN t2 on join_predicate
[WHERE where_predicate];
```
To calculate sales commission for all staff, including those that don't have any target yet - give them 10% and use a `LEFT JOIN`:
```SQL
UPDATE sales.commissions
SET sales.commissions.commission = c.base_amount * COALESCE(t.percentage,0.1)
FROM sales.commissions c
    LEFT JOIN sales.targets t ON c.target_id = t.target_id
```

## DELETE
Removes one or more rows. If there is no `WHERE` clause, all rows are deleted.

```SQL
DELETE [TOP (expression) [PERCENT]]
FROM table_name
[WHERE search_condition];
```

Delete all that meet a condition:
```SQL
DELETE
FROM production.product_history
WHERE model_year = 2017;
```

## MERGE
To update values in a target table using values matched from a source table. May need to insert rows that are not in the target table, delete rows from the target table that the source table doesn't have, or update rows in the target table with values that don't match those in the matching rows in the source table.
```SQL
MERGE target_table USING source_table
ON merge_condition
WHEN MATCH
    THEN update_statement -- update target values using those from source
WHEN NOT MATCHED
    THEN insert_statement -- insert values into target from source
WHEN NOT MATCHED BY SOURCE
    THEN DELETE; -- delete values from target not in source
```
Example updating values and synchronizing two tables:
```SQL
MERGE sales.category t USING sales.category_staging s
ON (s.category_id = t.category_id)
WHEN MATCHED
    THEN UPDATE SET
        t.category_name = s.category_name
        t.amount = s.amount
WHEN NOT MATCHED
    THEN INSERT (category_id, category_name, amount)
        VALUES (s.category_id, s.category_name, s.amount)
WHEN NOT MATCHED BY SOURCE
    THEN DELETE;
```

# Data Definition
## Databases
Store data according to a schema.

### CREATE Database
Databases need unique names, typically up to 128 characters.
```SQL 
CREATE DATABASE database_name;
```

### DROP Database(s)
Attempting to drop a nonexisting database without specifying `IF EXISTS` may issue an error. Databases that are currently being used cannot be dropped.
```SQL
DROP DATABASE [ IF EXISTS ]
    database_name[, database_name2,...];
```

## Schema
Collection of database objects including tables, views, triggers, stored procedures, indexes, etc. Schemas are associated with usernames of their owners who also own the related database objects.

Databases may have multiple schemas.

### CREATE Schema
```SQL
CREATE SCHEMA schema_name
    [AUTHORIZATION owner_name]
```

To create a new table in the schema:
```SQL
CREATE TABLE schema_name.table_name(
    comma_separated,column_list
);
```
### ALTER Schema
Allow the transfer of securables (resource to which the Database Engine authorization system controls access, such as tables) from a schema to another in the database.

```SQL
ALTER SCHEMA target_schema_name
    TRANSFER [entity_type ::] securable_name;
```
Move securable_name into target_schema_name. entity_type can be Object, Type, ro XML Schema Collection, default Object.

Won't change the schema name of securables like stored procesdures, functions, views, or triggers. Instead, drop and re-create them in the new schema.
```SQL
-- Stored procedure for schema dbo
CREATE PROC usp_get_office_by_id(
    @id INT
) AS
BEGIN
    SELECT 
        * 
    FROM 
        dbo.offices
    WHERE 
        office_id = @id;
END;

-- Transfer to sales schema
ALTER SCHEMA sales TRANSFER OBJECT::dbo.offices;  

-- Manually modify to reflect new schema, else will get an error invalid object name
ALTER PROC usp_get_office_by_id(
    @id INT
) AS
BEGIN
    SELECT 
        * 
    FROM 
        sales.offices
    WHERE 
        office_id = @id;
END;
```

### DROP Schema
If the schema contains any objects, cannot drop. need to delete all of its objects before it can be removed. Attempting to drop a nonexisting schema will also result in an error, unless you use the `IF EXISTS` option.

```SQL
DROP SCHEMA [IF EXISTS] schema_name;
```

## Tables
Tables store data. They have names unique to the database and schema. They have at least one column with an associated data type.
### CREATE Table
If the database_name is not given, defaults to the current one.

With one column as the primary key:
```SQL
DROP TABLE IF EXISTS schema_name.table_name;

CREATE TABLE [database_name.][schema_name.]table_name (
    column_name DATA_TYPE KEY IDENTITY (1,1), -- start generating ints from 1 and increasing by one for each new row
    column_name2 DATA_TYPE DEFAULT value,
    column_name3 DATA_TYPE NOT NULL,
    column_name4 DATA_TYPE NOT NULL DEFAULT value,
    column_name5 DATA_TYPE,
    column_name6 DATA_TYPE UNIQUE,
    FOREIGN KEY (id) REFERENCES schema_name.table_name2(id), -- ensures that values in teh id column of the table_name table is available in the id column of the table_name2 table
    ...,
    table_constraints
);
```
Identity columns have their values automatically populated by the server when a new row is added to the table.

Constraints such as NOT NULL and UNIQUE are stated after the datatype. Other constraints such as Foreign key, primary key, unique, and check can also be specified.

Primary key as more than one column:

### Constraints
#### PRIMARY KEY
Column or group that uniquely identifies each row in a table. Each table can only have on primary key. All columns that participate in the primary key must be `NOT NULL` - Will automatically set this constraint if not specified. Automatically creates a unique clustered index (or non if specified) when a primary key is created.

```SQL
-- One column:
CREATE TABLE table_name (
    pk_column data_type PRIMARY KEY, -- value must be unique
    ...
);
-- Multiple columns:
CREATE TABLE table_name (
    pk_column_1 data_type,
    pk_column_2 data type,
    ...
    PRIMARY KEY (pk_column_1, pk_column_2) -- combination must be unique
);

-- Adding if not originally assigned:
ALTER TABLE sales.events 
ADD PRIMARY KEY(event_id);
```
Can use `IDENTITY` to generate a unique integer value.

#### IDENTITY
Seed is the value of the first row loaded into the table. Increment by the incremental value each time we add a new row.

Identity values are not reused even if an insert is failed or rolled back
```SQL
IDENTITY[(see,increment)]

-- Use in table constraints
column_name DATA_TYPE KEY IDENTITY (1,1)
```

#### FOREIGN KEY
Enforces link between data in tables. Column or group that uniques identifies a row of another table (or the same table if self-referencing).

```SQL
CONSTRAINT fk_constraint_name 
FOREIGN KEY (column_1, column2,...)
REFERENCES parent_table_name(column1,column2,..)
```
constraint_name is optional and will be automatically generated if not supplied.
```SQL
-- Parent table which foreign key references:
CREATE TABLE procurement.vendor_groups (
    group_id INT IDENTITY PRIMARY KEY,
    group_name VARCHAR (100) NOT NULL
);

-- Child table to which the foreign key constraint is applied:
CREATE TABLE procurement.vendors (
        vendor_id INT IDENTITY PRIMARY KEY,
        vendor_name VARCHAR(100) NOT NULL,
        group_id INT NOT NULL,
        CONSTRAINT fk_group FOREIGN KEY (group_id) 
        REFERENCES procurement.vendor_groups(group_id)
);
-- Every time we insert a new vendor, must use a vendor group that exists in the parent table or we'll get an error.
```

Ensure referential integrity - can only insert a row into the child if there is a corresponding row in the parent. Can define referential actions for when the row in the parent table is updated or deleted:
```SQL
FOREIGN KEY (foreign_key_columns)
    REFERENCES parent_table(parent_key_columns)
    ON UPDATE action 
    ON DELETE action;

-- when UPDATE action is:
NO ACTION -- raise an error and roll back update on the parent table row
CASCADE -- update corresponding rows in child table
SET NULL -- set rows in child table to NULL - foreign key column must be NULLable
SET DEFAULT -- set rows in child table to be default values - foreign key column must have default values else (if NULLable) default is NULL

-- when DELETE action is (default NO ACTION):
NO ACTION -- raise an error and roll back delete on the parent table row
CASCADE -- delete corresponding row in child table
SET NULL -- set rows in child table to be NULL - foreign key column must be NULLable
SET DEFAULT -- set rows in child table to be default values - foreign key column must have default values else (if NULLable) default is NULL
```
#### NOT NULL
Can't be null. Always written as column, not table, constraints.
```SQL
CREATE TABLE hr.persons(
    person_id INT IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(20)
);

-- Add constraint to a column:
UPDATE table_name
SET column_name = <value>
WHERE column_name IS NULL;
-- update so no existing NULL values, which would cause an error and a failure to update
ALTER TABLE table_name
ALTER COLUMN column_name data_type NOT NULL;

-- Remove constraint:
ALTER TABLE table_name
ALTER COLUMN column_name data_type NULL;
```
#### UNIQUE
Ensures that data stored in a column or group is unique among rows in a table. Creates a `UNIQUE` index behind the scenes. Will error if try to insert a duplicate row. Unlike `PRIMARY KEY`, allows `NULL`, which is treated like a regular value (can only have one `NULL` per column).

```SQL
-- With name:
CREATE TABLE hr.persons (
    person_id INT IDENTITY PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    CONSTRAINT unique_email UNIQUE(email)
);

-- With a group:
CREATE TABLE table_name (
    key_column data_type PRIMARY KEY,
    column1 data_type,
    column2 data_type,
    column3 data_type,
    ...,
    UNIQUE (column1,column2)
);

-- Add constraint:
ALTER TABLE table_name
ADD CONSTRAINT constraint_name 
UNIQUE(column1, column2,...);
-- Will error and fail to add constraint if existing data is not unique.

-- Remove constraint:
ALTER TABLE table_name
DROP CONSTRAINT constraint_name;
```
#### CHECK
Value in column must satisfy a Boolean expression, else error. `NULL` evaluates to `UNKNOWN` and can bypass constraints if no additional `NOT NULL` constraint.

```SQL
CREATE TABLE test.products(
    product_id INT IDENTITY PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    unit_price DEC(10,2) CHECK(unit_price > 0),
    discounted_price DEC(10,2) CHECK(discounted_price > 0),
    CHECK(discounted_price < unit_price) -- can refer to other columns
);

-- Can assign a name to the constraint, which will help classify error messages and ease modification. If none specified, name is automatically generated: 
CREATE TABLE test.products(
    product_id INT IDENTITY PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    unit_price DEC(10,2) CONSTRAINT positive_price CHECK(unit_price > 0),
    discounted_price DEC(10,2) CHECK(discounted_price > 0),
    CHECK(discounted_price < unit_price)
);

-- Add named constraint:
ALTER TABLE test.products
ADD CONSTRAINT positive_price CHECK(unit_price > 0);

-- Remove by name:
ALTER TABLE table_name
DROP CONSTRAINT constraint_name;

-- Disable for insert or update:
ALTER TABLE table_name
NOCHECK CONSTRAINT constraint_name;

ALTER TABLE test.products
NO CHECK CONSTRAINT valid_price;

```
Can write column constraints as table constraints:
```SQL
CREATE TABLE test.products(
    product_id INT IDENTITY PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    unit_price DEC(10,2),
    discounted_price DEC(10,2),
    CHECK(unit_price > 0),
    CHECK(discounted_price > 0),
    CONSTRAINT valid_prices CHECK(discounted_price > unit_price)
);
-- or
CREATE TABLE test.products(
    product_id INT IDENTITY PRIMARY KEY,
    product_name VARCHAR(255) NOT NULL,
    unit_price DEC(10,2),
    discounted_price DEC(10,2),
    CHECK(unit_price > 0),
    CHECK(discounted_price > 0 AND discounted_price > unit_price)
);
```
### DROP Table(s)
Deletes all data, triggers, constraints, and permissions for that table. Doesn't explictly drop views and stored procedures that reference the dropped table, even though they are dependent upon it. You need to clean up things that reference the table yourself.

If database_name is not supplied, defaults to the currently connected database.
```SQL
DROP TABLE [IF EXISTS]  [database_name.][schema_name.]table_name[, [schema_name.]table_name_2, …, [schema_name.]table_name_n];
```

If some column in the table is referenced in a foreign key constraint of another table, cannot drop the table. The foreign key constraint or referencing table must be dropped first, even if only in order in the drop table list.

### Truncate Table
Delete all rows from a table. Executes far faster than `DELETE` without a `WHERE` clause and uses fewer system and transaction log resources.
```SQL
TRUNCATE TABLE [database_name.][schema_name.]table_name;
```
Differences with `DELETE`:
* `DELETE` removes rows one at a time and inserts an entry in the transaction log for each removed row. `TRUNCATE TABLE` deallocates the data pages and inserts only the page deallocations in the transaction logs. 
*`DELETE` locks each row while `TRUNCATE TABLE` locks the table and pages.
* If the table has an identity column, the counter is reset to the seed value with `TRUNCATE TABLE` but not with `DELETE`.

### Rename Table
SQL doesn't offer a command for this, but specific implementations likely will.

### SELECT INTO Table
Creates a new table and inserts rows from the query. Does not copy constraints.

```SQL
SELECT 
    select_list
INTO 
    destination
FROM 
    source
[WHERE condition]
```

### ALTER Table ADD Column(s)

```SQL
ALTER TABLE table_name
ADD column_name data_type column_constraint[, column_name2 data_type2 column_constraint2, ...];

-- Use

ALTER TABLE sales.quotations 
    ADD amount DECIMAL (10, 2) NOT NULL,
        customer_name VARCHAR (50) NOT NULL;
```

### ALTER Table DROP Column
Modify data type, change size, add a `NOT NULL` constraint. 

If the new data type is not compatible with the old one, you will get a conversion error if there is incompatible data in the column.

If the existing data cannot convert data based on the new size, the conversion fails and an error message is issued.

All values must be non-null to add a `NOT NULL` constraint.
```SQL
-- Change data type
ALTER TABLE table_name 
ALTER COLUMN column_name new_data_type(size);

-- Change size use
CREATE TABLE t2 (c VARCHAR(10));
ALTER TABLE t2 ALTER COLUMN c VARCHAR (50);

-- Add NOT NULL constraint
ALTER TABLE t2 ALTER COLUMN c VARCHAR (50) NOT NULL;
```
### ALTER Table DROP Constraint
```SQL
CREATE TABLE table_name(
    column_name,
    column_name2 CONSTRAINT constraint_name CHECK(column_name >= 0) -- or some other expression
);

ALTER TABLE table_name
DROP CONSTRAINT constraint_name;
```

### ALTER Table DROP Column(s)
If the column you want to delete has a `CHECK` constraint, you must delete the constraint before hte column can be removed. Columns that have `PRIMARY KEY` or `FOREIGN KEY` constraints cannot be removed.
```SQL
ALTER TABLE table_name
DROP COLUMN column_name[, column_name2, ...];
```

### Computed Columns
Column with values derived from values of other columns in the same table.

```SQL
CREATE TABLE table_name(
    ...,
    column_name AS expression [PERSISTED],
    ...
);

ALTER TABLE table_name
ADD column_name AS expression [PERSISTED];
```

Can be persisted, meaning the SQL Server will physically store the data of the computed column on disk, which will need to be recalculated when the data is changed, but can be be retrieved quicker as it won't require calculation.

Functions must be deterministic if they are to be persisted - they must always return the same result. Something like `GETDATE()` is not deterministic. 

```SQL
CREATE TABLE persons(
    person_id  INT PRIMARY KEY IDENTITY, 
    first_name NVARCHAR(100) NOT NULL, 
    last_name  NVARCHAR(100) NOT NULL, 
    dob        DATE
);

-- Persisted (stored) column
ALTER TABLE persons
ADD full_name AS (first_name + ' ' + last_name) PERSISTED;

-- With non-deterministic function, cannot be persisted
ALTER TABLE persons
ADD age_in_years 
    AS (CONVERT(INT,CONVERT(CHAR(8),GETDATE(),112))-CONVERT(CHAR(8),dob,112))/10000;
```

### Temporary Tables
Useful for storing immediate result sets that are accessed multiple times.

```SQL
SELECT 
    select_list
INTO 
    #temporary_table -- often prefix with a hash symbol, or ## if should be able to be accessed from different connections
FROM 
    table_name
....

CREATE TABLE #products (
    product_name VARCHAR(MAX),
    list_price DEC(10,2)
);
```
Can insert data and query data against it just as you would a regular table, but only for the current session. The temporary table will not exist in other connections unless it is global (prefixed with `##`).

When the connection that created the tabvle is closed, the table is dropped. For global tempoary tables, they are dropped when the connection that created them is closed and any queries against the table from other connections are complete.

### Synonym/ Alias
Alternative name for database objects, like tables, views, stored procedures, user-defined functions, and sequences. The object for which the synonym is being created need not exist at the time of the synonym's creation.

Useful if you refer to an object from another database often. By using a synonym in objects that reference, say, a table, you then only need to modify the synonym to use the table's new name.

```SQL
CREATE SYNONYM [schema_name_1.]synonym_name 
FOR [server_name.][database_name.][schema_name_2.]object_name;

CREATE SYNONYM orders FOR sales.orders;

DROP SYNONYM [ IF EXISTS ] [schema_name_1.]synonym_name  
```
The synonym can be used anywhere where you'd used the target object.

# Data Control Language
## Grant
## Revoke

# Transaction Control Language
## Begin
## Commit
## Rollback

# Data Types
## Exact Numerics
If threshold of INT is exceeded, converts to DECIMAL.

bigint	−2^63 (−9,223,372, 036,854,775,808)	2^63−1 (−9,223,372, 036,854,775,807)	8 bytes
int	−2^31 (−2,147, 483,648)	2^31−1 (−2,147, 483,647)	4 bytes
smallint	−2^15 (−32,767)	2^15 (−32,768)	2 bytes
tinyint	0	255	1 byte
bit	0	1	1 byte/8bit column
decimal	−10^38+1	10^381−1	5 to 17 bytes
numeric	−10^38+1	10^381−1	5 to 17 bytes
money	−922,337, 203, 685,477.5808	+922,337, 203, 685,477.5807	8 bytes
smallmoney	−214,478.3648	+214,478.3647	4 bytes
### BIT
1 byte of memory.

0, 1, or `NULL`. ('True') and any nonzero value convert to 1 and ('False') converts to 0.
### INT
4 bytes of memory.

−2^31 (−2,147, 483,648) - 2^31−1 (−2,147, 483,647).
### BIGINT
8 bytes of memory.

−2^63 (−9,223,372, 036,854,775,808) - 2^63−1 (−9,223,372, 036,854,775,807).
### SMALLINT
2 bytes of memory.

−2^15 (−32,767) - 2^15 (−32,768).
### TINYINT
1 byte of memory.

0 - 255.
### DECIMAL(p,s)/ NUMERIC(p,s)
5 - 17 bytes of memory.

−10^38+1 - 	10^381−1.

Fixed precision p and scale s. Precision is the maximum total number of decimal digits that will be stored - both to the left and right of the decimal point, from 1 to 38 (default 38).

Scale s is the number of decimal digits stored to the right of the decimal point, from 0 to p (default 0).

Precision	Storage bytes
1 – 9	5
10-19	9
20-28	13
29-38	17
### MONEY
8 bytes of memory.

−922,337, 203, 685,477.5808 - +922,337, 203, 685,477.5807.
### SMALLMONEY
4 bytes of memory.

−214,478.3648 - +214,478.3647.

## Approximate Numerics
Floating point numeric data.
### FLOAT(n)
Memory depends on value of n.

−1.79E+308 - 1.79E+308.

7 digits of precision.
### REAL
4 bytes of memory.

−3.40E+38 - 3.40E+38.

15 digits of precision.

## Binary Strings
Data Type 	Lower limit	Upper limit 	Memory
binary	0 bytes	8000 bytes	n bytes
varbinary	0 bytes	8000 bytes	The actual length of data entered + 2 bytes
image	0 bytes	2,147,483,647 bytes
### BINARY
Fixed-length data.
### VARBINARY
Variable-length data.
### Deprecating: IMAGE

## Character Strings
Non-unicode data.

Data Type 	Lower limit	Upper limit 	Memory
char	0 chars	8000 chars	n bytes
varchar	0 chars	8000 chars	n bytes + 2 bytes
varchar (max)	0 chars	2^31 chars	n bytes + 2 bytes
text	0 chars	2,147,483,647 chars	n bytes + 4 bytes
### CHAR(n)
Fixed-length data.

String length n from 1 to 8,000 (default 1). Will add trailing spaces if too small when storing but will remove these spaces before returning the value. Error if inserting a string with an excessive length.

('Char')

`LEN(val)` is the number of characters, excluding trailing spaces and `DATALENGTH(val)` is the number of bytes.
### VARCHAR(n)
Variable-length data.

String length n fro m1 to 8,000 (default 1).

`VARCHAR(max)` maximizes storage wize, which is 2^31-1 bytes, or 2 GB.

Character Varying. use when data length or column length is variable and actual data is always way less than capacity.
### Deprecating: TEXT

## Unicode Character Strings
Store unicode characters in teh form UNICODE UCS-2 characters.

Data Type 	Lower limit	Upper limit 	Memory
nchar	0 chars	4000 chars	2 times n bytes
nvarchar	0 chars	4000 chars	2 times n bytes + 2 bytes
ntext	0 chars	1,073,741,823 char	2 times the string length
### NCHAR(n)
Fixed-length data.

String length n from 1 to 4,000 (default 1).

National Character
### NVARCHAR(n)
Variable-length data.

String length n from 1 to 4,000 (default 1).

`NVARCHAR(max)` stores in 2^31-1 bytes, or 2 GB.

National Character Varying.
### Deprecating: NTEXT

## Date & Time
Recommended to use: time, date, datetime2, and datetimeoffset to align with SQL standard and portability. tiem, datetime2, and datetime have more seconds precision and datetimeoffset supports timezones.

Data Type 	Storage size 	Accuracy	Lower Range	Upper Range
datetime	8 bytes	Rounded to increments of .000, .003, .007	1753-01-01	9999-12-31
smalldatetime	4 bytes, fixed	1 minute	1900-01-01	2079-06-06
date	3 bytes, fixed	1 day	0001-01-01	9999-12-31
time	5 bytes	100 nanoseconds	00:00:00.0000000	23:59:59.9999999
datetimeoffset	10 bytes	100 nanoseconds	0001-01-01	9999-12-31
datetime2	6 bytes	100 nanoseconds	0001-01-01	9999-12-31
### DATE
3 bytes of memory, fixed.

0001-01-01 - 9999-12-31.

Accurate up to 1 day.

String literal: `YYYY-DD-MM`.

Month MM ranges from 01 to 12.

### TIME[ (fractional second scale) ]
5 bytes of memory with default of 100ms fractional second precision.

00:00:00.0000000 - 23:59:59.9999999.

Accurate up to 100 nanoseconds.

Fractional second scale specifies number vof digits for the fractional part of seconds, ranging from 0 to 7 (default 7).

String literal: `hh:mm:ss[.nnnnnnn]`. Has no timezone awareness.

Hour hh ranges from 0 to 23. Fractional seconds can be 0-7 digits, rangings from 0 to 9,999,999.

### DATETIME2(fractional seconds precision)
6 bytes of memory for precision less than 3, 7 bytes for precision between 3 and 4, 8 bytes otherwise.

0001-01-01 - 9999-12-31.

00:00:00 - 23:59:59.9999999.

Accurate up to 100 nanoseconds.

Optional fractional seconds precision, ranging from 0 to 7.

String literal: `YYYY-MM-DD hh:mm:ss[.fractional seconds]`

Month ranges from 01 to 12. hh is an hour from 00 to 23.

Fractional seconds is  0-7 digit number ranging from 0 - 9,999,999.

```SQL
CREATE TABLE production.product_colors (
    color_id INT PRIMARY KEY IDENTITY,
    color_name VARCHAR (50) NOT NULL,
    created_at DATETIME2
);

ALTER TABLE production.product_colors 
ADD CONSTRAINT df_current_time 
DEFAULT CURRENT_TIMESTAMP FOR created_at;
```

### DATETIME
8 bytes of memory.

1753-01-01 - 9999-12-31.

Rounded to increments of .000, .003, .007.

### SMALLDATETIME
4 bytes of memory, fixed.

1900-01-01 - 2079-06-06.

Accuracy up to 1 minute.
### DATETIMEOFFSET[ (fractional seconds precision) ]
10 bytes of memory.

0001-01-01 - 9999-12-31.

00:00:00 - 23:59:59.9999999.

Accurate up to 100 nanoseconds.

Single point in datetime allong with an offset of how much that datetime differs from UTC.

String literal: `YYYY-MM-DDThh:mm:ss[.nnnnnnn][{+|-}hh:mm]`. Example: `2020-12-12 11:30:30.12345` or ISO: `2020-12-12 19:30:30.12345Z` which is `YYYY-MM-DDThh:mm:ss[.nnnnnnn]Z`.

Timezone offset from UTC [+|-]hh:mm.

Hours hh from 00 to 14. 

```SQL
-- Use

CREATE TABLE messages(
    id         INT PRIMARY KEY IDENTITY, 
    message    VARCHAR(255) NOT NULL, 
    created_at DATETIMEOFFSET NOT NULL
);

INSERT INTO messages(message,created_at)
VALUES('DATETIMEOFFSET demo',
        CAST('2019-02-28 01:45:00.0000000 -08:00' AS DATETIMEOFFSET));

SELECT 
    id, 
    message, 
	created_at 
        AS 'Pacific Standard Time'
    created_at AT TIME ZONE 'SE Asia Standard Time' 
        AS 'SE Asia Standard Time',
FROM 
    messages;
```

## Others
Data Type 	Description
cursor	for variables or stored procedure OUTPUT parameter that contains a reference to a cursor
rowversion	expose automatically generated, unique binary numbers within a database.
hierarchyid	represent a tree position in a tree hierarchy
uniqueidentifier	16-byte GUID
sql_variant	store values of other data types
XML	store XML data in a column, or a variable of XML type
Spatial Geometry type	represent data in a flat coordinate system.
Spatial Geography type	store ellipsoidal (round-earth) data, such as GPS latitude and longitude coordinates.
table	store a result set temporarily for processing at a later time

### GUID/ UNIQUEIDENTIFIER
16 bytes of memory.

Globally Unique Identifier. Guaranteed to be unique across tables, databases, and even servers.

Binary data type
```SQL
SELECT 
    NEWID() AS GUID; -- ex: 3297F0F2-35D3-4231-919D-1CFCF4035975
```

Can use as primary key instead of incrementing integers, making it easier to moerge data from different servers. Don't expose any information about insertion order. Does increase storage space and makes troubleshooting a little more difficult.

### CURSOR
### HIERARCHYID
### SQL_VARIANT
### Spacial Geometry Types
### Spacial Geography Types
### TABLE
### ROWVERSION
### XML


# Expressions
Can be used in any clause that accepts an expression, like `SELECT`, `WHERE`, `GROUP BY`, and `HAVING`.
```SQL
CASE input   
    WHEN e1 THEN r1 -- boolean expression e1 and result r1
    WHEN e2 THEN r2
    ...
    WHEN en THEN rn
    [ ELSE re ]   -- returns NULL if no ELSE statement supplied
END  

-- Use

SELECT    
    CASE order_status
        WHEN 1 THEN 'Pending'
        WHEN 2 THEN 'Processing'
        WHEN 3 THEN 'Rejected'
        WHEN 4 THEN 'Completed'
    END AS order_status, 
    COUNT(order_id) order_count
FROM    
    sales.orders
WHERE 
    YEAR(order_date) = 2018
GROUP BY 
    order_status;

SELECT    
    SUM(CASE
            WHEN order_status = 1
            THEN 1
            ELSE 0
        END) AS 'Pending', 
    SUM(CASE
            WHEN order_status = 2
            THEN 1
            ELSE 0
        END) AS 'Processing', 
    SUM(CASE
            WHEN order_status = 3
            THEN 1
            ELSE 0
        END) AS 'Rejected', 
    SUM(CASE
            WHEN order_status = 4
            THEN 1
            ELSE 0
        END) AS 'Completed', 
    COUNT(*) AS Total
FROM    
    sales.orders
WHERE 
    YEAR(order_date) = 2018;

SELECT    
    o.order_id, 
    SUM(quantity * list_price) order_value,
    CASE
        WHEN SUM(quantity * list_price) <= 500 
            THEN 'Very Low'
        WHEN SUM(quantity * list_price) > 500 AND 
            SUM(quantity * list_price) <= 1000 
            THEN 'Low'
        WHEN SUM(quantity * list_price) > 1000 AND 
            SUM(quantity * list_price) <= 5000 
            THEN 'Medium'
        WHEN SUM(quantity * list_price) > 5000 AND 
            SUM(quantity * list_price) <= 10000 
            THEN 'High'
        WHEN SUM(quantity * list_price) > 10000 
            THEN 'Very High'
    END order_priority
FROM    
    sales.orders o
INNER JOIN sales.order_items i ON i.order_id = o.order_id
WHERE 
    YEAR(order_date) = 2018
GROUP BY 
    o.order_id;
```
## CASE
if..else logic. Evaluates a list of conditions and returns one of multiple specified results.
## COALESCE
Evaluates a list of arguments and returns the first non-`NULL` value. Useful if you know some inputs will be `NULL`, but you want a fallback non-`NULL` value. If all expressions evaluate to `NULL`, the function will return `NULL`.

Can use in any clause that accepts an expression, like `SELECT`, `WHERE`, `GROUP BY`, and `HAVING`.

```SQL
COALESCE(phone_number, 'N/A')
```
Really just syntactic sugar of `CASE`. 
```SQL
COALESCE(e1,e2,e3)

-- Same as

CASE
    WHEN e1 IS NOT NULL THEN e1
    WHEN e2 IS NOT NULL THEN e2
    ELSE e3
END
```

## NULLIF
Returns `NULL` if first expression equals the second one, else returns the first expression. The expressions must evaluate to scalar values.

Useful if want to convert something like an empty string to `NULL`.

```SQL
NULLIF(a,b)

-- Same as

CASE 
    WHEN a=b THEN NULL 
    ELSE a 
END
```
## CONCAT
Concatenates strings. Can also use + between strings.

## Create Index
## Drop Index

# Good-to-Know
## Find Duplicates
## Remove Duplicates