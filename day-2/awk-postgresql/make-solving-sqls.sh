#! /bin/bash

echo "DROP TABLE IF EXISTS sheet;"
echo "CREATE TABLE sheet ("
echo "     row INTEGER NOT NULL,"
echo "     value INTEGER NOT NULL);"
awk '{for(i=1;i<=NF;i++){print "INSERT INTO sheet VALUES(" NR ", " $i ");"}};' $1

echo "WITH calculations AS (SELECT row, min(value), max(value), max(value) - min(value)  AS rowdiff FROM sheet GROUP BY row) SELECT sum(rowdiff) AS checksum_1 FROM calculations;"
echo "WITH calculations AS (SELECT a.row, a.value, b.value, a.value /  b.value AS div FROM sheet AS a LEFT JOIN sheet AS b ON a.row = b.row WHERE a.value <> b.value AND mod(a.value, b.value) = 0) SELECT sum(div) AS checksum_1 FROM calculations;"
