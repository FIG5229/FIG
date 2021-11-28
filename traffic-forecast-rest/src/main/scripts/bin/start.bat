@echo off
::设置变量
SET PWDPATH=%cd%
SET CLSPATH=..
cd %CLSPATH%
set SERVER_NAME=${pkg.mainClass}

set JAVA_OPTS=-server -Xms1024m -Xmx2048m -Xss256k -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m
set CLASS_PATH=classes;lib/*
set JAVA_CMD=java %JAVA_OPTS% -cp %CLASS_PATH% %SERVER_NAME%
%JAVA_CMD%