@echo off
CALL mvn clean install -DDB_PASSWORD=Qazxsw@123
CALL mvn package -DDB_PASSWORD=Qazxsw@123
CALL docker build -t trongduc22062000/subleasing-be:1.0.%1 .
CALL docker push trongduc22062000/subleasing-be:1.0.%1
REM 1.0.11
