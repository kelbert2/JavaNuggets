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
SELECT column1, column2 AS column2_name
FROM table_name

/* Select Only Different Values */
SELECT DISTINCT column1, column2
FROM table_name
/* Returns no duplicates */
```
### WHERE
Filtering by a specified condition.
```SQL
SELECT column1, column2
FROM table_name
WHERE condition; 
```

Conditions include `column1='Value'`, >, <, >=, <= , <> (not equal), and
*` BETWEEN value AND value` (range)
* `LIKE 's%'` (pattern where starts with 's')
* `IN ('Paris','London')` (multiple possible values)

You can use multiple conditions by joining them with
* AND
* OR
* NOT
These can be combined with ()s.

### ORDER BY
Sorts the selection by a certain column. By default, the result is sorted by the first column selected, then the nextm and so on.

NULL is the lowest value.
```SQL
SELECT column1, column2, ...
FROM table_name
ORDER BY column1, column2, ... ASC|DESC; 
```
Columns in ORDER BY must appear in either the SELECT list or defined in the table specified in the FROM clause.

You can also order by
* `LEN(string)` - number of characters.
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

## Create Index
## Drop Index