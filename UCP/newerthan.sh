#!/bin/bash

echo enter two file names:
read file1 file2

if [ -f $file1 ] && [ $file1 -nt $file2 ]; then
echo $file1 is newer

elif [ -f $file2 ] && [ $file2 -nt $file1 ]; then
echo $file2 is newer

else
echo $file1 and $file2 do not exist

fi

