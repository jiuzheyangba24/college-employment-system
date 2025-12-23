@echo off
cd /d d:\college
"C:\Program Files\MySQL\MySQL Server 8.4\bin\mysql.exe" -u root -p123456 college -e "source fix_user.sql"
