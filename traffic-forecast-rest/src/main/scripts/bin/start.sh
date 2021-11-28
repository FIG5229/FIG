#!/bin/sh
Time=`date +%Y%m%d`
PWDPATH="$(pwd)"
CLSPATH="$PWDPATH/.."
cd $CLSPATH
export SERVER_NAME=${pkg.mainClass}

export JAVA_OPTS="-server -Xms1024m -Xmx2048m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -Dfile.encoding=utf-8";
PID_FILE=$PWDPATH/pid
check(){
    if [ ! -z "$PID" ];
    then
      RUNNING=`ps -F -p $PID | grep ${PID}`
      if [ ! -z "$RUNNING" ];
      then
        echo "$SERVER_NAME is already running..."
        exit 1
      fi
    fi
}

if [ -f $PID_FILE ];
then
    PID=`cat $PID_FILE`
    check
fi
CLASS_PATH=classes:lib/*
JAVA_OPTS=$JAVA_OPTS
JAVA_CMD="java $JAVA_OPTS -cp $CLASS_PATH $SERVER_NAME 2>&1 >/dev/null"

eval "$JAVA_CMD &"
echo $! > $PID_FILE
