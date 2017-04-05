#!/bin/bash

rm movies.json

curl http://www.imdb.com/chart/top |
grep '^<a href="/title/' |
cut -f 3 -d/ |
xargs -n 1 -I {} curl -w '\n' http://www.omdbapi.com/?i={} >> movies.json
