#!/bin/bash

if [ -f $1 ]; then

    if [ -r $1 ]; then
        echo $1 is readble
    else
        echo $1 is not readble
    fi

    if [ -w $1 ]; then
        echo $1 is writable
    else
        echo $1 is not writable
    fi

    if [ -x $1 ]; then
        echo $1 is exectutable
    else
        echo $1 is not exectutable
    fi

else
    echo $1 does not exist
fi