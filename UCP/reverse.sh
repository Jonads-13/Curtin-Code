#!/bin/bash

o=

for string in $*; do
    o="$string $o"
done

echo $o
