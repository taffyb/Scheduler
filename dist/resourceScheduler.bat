@echo off
echo Executing ResourceScheduler utilising 3 resources.
java -jar resourceScheduler.jar  r:3 

echo.
echo. 
echo Executing ResourceScheduler  utilising 4 resources.
java -jar resourceScheduler.jar  r:4 

echo. 
echo. 
echo Executing AlternateScheduler  utilising 2 resources.
java -jar resourceScheduler.jar  r:2 s:AlternateScheduler

echo. 
echo. 
echo Executing AlternateScheduler  utilising 2 resources terminating after 50000 ms.
java -jar resourceScheduler.jar  r:2 s:AlternateScheduler t:50000 