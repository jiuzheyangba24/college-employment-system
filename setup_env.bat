@echo off
REM 高校就业信息管理系统 - 环境变量设置脚本
REM 使用方法：双击运行此脚本，然后重新打开命令行窗口

echo 正在设置环境变量...

REM 设置 JAVA_HOME
setx JAVA_HOME "D:\dev\jdk-17.0.9"

REM 设置 MAVEN_HOME
setx MAVEN_HOME "D:\dev\apache-maven-3.9.6"

REM 添加到 PATH
setx PATH "%PATH%;D:\dev\jdk-17.0.9\bin;D:\dev\apache-maven-3.9.6\bin"

echo.
echo ========================================
echo 环境变量设置完成！
echo JAVA_HOME = D:\dev\jdk-17.0.9
echo MAVEN_HOME = D:\dev\apache-maven-3.9.6
echo ========================================
echo.
echo 请关闭此窗口，然后重新打开命令行窗口使设置生效。
echo.
pause
