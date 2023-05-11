#!/bin/bash

filename=$1

find . -type f -path 'files' -name $filename

if ! [ $? ]; then
    echo $filename does not exist
    echo enter another file

else
    for i in $filename; do
        echo $i does exist
    done
fi