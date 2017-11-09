call conf.bat

call mkdirs.bat

start "" /min "%mongo_dir%\mongod.exe" --dbpath "%mongo_data_dir%\db\%db_name1%" --logpath "%mongo_data_dir%\log\%db_name1%\mongo.log" --smallfiles --oplogSize 128 --port 27001 --replSet epamreplica

start "" /min "%mongo_dir%\mongod.exe" --dbpath "%mongo_data_dir%\db\%db_name2%" --logpath "%mongo_data_dir%\log\%db_name2%\mongo.log" --smallfiles --oplogSize 128 --port 27002 --replSet epamreplica

start "" /min "%mongo_dir%\mongod.exe" --dbpath "%mongo_data_dir%\db\%db_name3%" --logpath "%mongo_data_dir%\log\%db_name3%\mongo.log" --smallfiles --oplogSize 128 --port 27003 --replSet epamreplica
