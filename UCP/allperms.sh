#!/bin/bash

for param in $*; do
    echo
    if [ -f $param ]; then

        if [ -r $param ]; then
            echo $param is readble
        else
            echo $param is not readble
        fi

        if [ -w $param ]; then
            echo $param is writable
        else
            echo $param is not writable
        fi

        if [ -x $param ]; then
            echo $param is exectutable
        else
            echo $param is not exectutable
        fi

    else
        echo $param does not exist
    fi
done
