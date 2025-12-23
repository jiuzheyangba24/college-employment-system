@echo off
chcp 65001 >nul
cd /d d:\college\src\main\resources\db
"C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p123456 --default-character-set=utf8mb4 -e "source schema.sql"
"C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p123456 --default-character-set=utf8mb4 college -e "source data.sql"
echo Database initialized successfully!
