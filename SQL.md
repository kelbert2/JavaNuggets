https://www.sqlservertutorial.net/sql-server-basics/

# Data Definition Language
## Create Database
## Alter Database
## Create Table
## Alter Table
## Drop Table

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

### ORDER BY
Sorts the selection by a certain column. By default, the result is sorted by the first column selected, then the nextm and so on.

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
## Insert Into
## Update 
## Delete

# Data Control Language
## Grant
## Revoke

# Transaction Control Language
## Begin
## Commit
## Rollback


# Data Types

# Expressions

## Create Index
## Drop Index