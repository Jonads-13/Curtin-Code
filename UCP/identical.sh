#!/bin/bash

for file in $*; do

    for other in $*; do
        if [ $file != $other ]; then
            diff -q --brief $file $other &> null
            if [ $? -eq 0 ]; then
                echo $file and $other are the same
            fi
        fi
    done
done 
