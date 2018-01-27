#! /bin/bash

docker run -it --rm --link aoc-postgres:postgres postgres psql -h postgres -U postgres
