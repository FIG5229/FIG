#!/bin/sh
kill -9 `cat $(pwd)/pid`
rm -f $(pwd)/pid
#kill `ps -ef | grep java | grep $1 | grep -v grep | awk '{print $2}'`
echo kill success