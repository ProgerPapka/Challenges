call conf.bat

"%mongo_dir%\mongoimport.exe" --port 27001 --db test --collection publishers publisher.json

pause