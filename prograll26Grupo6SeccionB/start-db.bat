@echo off
echo Iniciando contenedor de Base de Datos SQL Server...
wsl docker compose up -d
echo.
echo Contenedor Prueba2 activo en puerto 1483!
pause