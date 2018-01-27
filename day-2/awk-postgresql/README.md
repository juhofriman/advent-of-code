# DAY-2

## Running

You'll need Postgresql.

Docker spells included.

```
./start-container.sh
./psql-to.container.sh

# Password is postgres
```

```
./make-solving-sqls.sh input.txt

## and paste output to psql console

postgres=# WITH calculations AS (SELECT row, min(value), max(value), max(value) - min(value)  AS rowdiff FROM sheet GROUP BY row) SELECT sum(rowdiff) AS checksum_1 FROM calculations;
 checksum_1
------------
!!!!!!!!!!!!
(1 row)

postgres=# WITH calculations AS (SELECT a.row, a.value, b.value, a.value /  b.value AS div FROM sheet AS a LEFT JOIN sheet AS b ON a.row = b.row WHERE a.value <> b.value AND mod(a.value, b.value) = 0) SELECT sum(div) AS checksum_1 FROM calculations;
 checksum_1
------------
!!!!!!!!!!!!
(1 row)
``
