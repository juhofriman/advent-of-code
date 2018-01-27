#! /bin/bash

docker run --name aoc-postgres -it -e POSTGRES_PASSWORD=postgres -d postgres
