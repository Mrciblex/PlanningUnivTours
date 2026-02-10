@echo off
setlocal

:: ================= CONFIGURATION =================
:: MOT DE PASSE
set PGPASSWORD=root

set DBNAME=univ_time
set PGUSER=postgres

:: ================= RECHERCHE AUTO DE POSTGRES =================
echo [INFO] Recherche de psql.exe...

set "PGBIN="

:: Test des versions courantes
if exist "C:\Program Files\PostgreSQL\18\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\18\bin"
if exist "C:\Program Files\PostgreSQL\17\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\17\bin"
if exist "C:\Program Files\PostgreSQL\16\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\16\bin"
if exist "C:\Program Files\PostgreSQL\15\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\15\bin"
if exist "C:\Program Files\PostgreSQL\14\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\14\bin"

:: Si toujours pas trouver on essaie le dossier sans version
if not defined PGBIN (
    if exist "C:\Program Files\PostgreSQL\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\bin"
)

if not defined PGBIN (
    echo [ERREUR] PostgreSQL est introuvable.
    echo Verifiez que le dossier C:\Program Files\PostgreSQL\XX\bin existe.
    pause
    exit /b
)

echo [OK] Trouve : "%PGBIN%"

:: ================= VERIFICATION BASE DE DONNEES =================

echo [INFO] Test de connexion a la BDD...
"%PGBIN%\psql.exe" -U %PGUSER% -d postgres -c "SELECT 1" >nul 2>&1
if %errorlevel% neq 0 goto ERROR_CONN

echo [INFO] Verification de l'existence de la base '%DBNAME%'...
"%PGBIN%\psql.exe" -U %PGUSER% -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='%DBNAME%'" | findstr "1" >nul
if %errorlevel% equ 0 goto DB_EXISTS

echo [INFO] La base n'existe pas. Creation en cours...
"%PGBIN%\createdb.exe" -U %PGUSER% -E UTF8 %DBNAME%
if %errorlevel% neq 0 goto ERROR_CREATE

echo.
echo [OK] Base de donnees '%DBNAME%' creee avec succes !
goto LAUNCH_SPRING

:: ================= GESTION DES ETATS =================

:DB_EXISTS
echo.
echo [OK] La base '%DBNAME%' existe deja.
goto LAUNCH_SPRING

:LAUNCH_SPRING
echo.
echo ===================================================
echo [INFO] Base de donnees configurer. Lancement de Spring Boot...
echo ===================================================
echo.

:: Vérifie si le wrapper Maven existe
if exist "mvnw.cmd" (
    echo [INFO] Utilisation de Maven Wrapper
    call mvnw.cmd spring-boot:run
) else (
    echo [INFO] mvnw introuvable, utilisation de Maven global...
    call mvn spring-boot:run
)

if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Le serveur s'est arrete avec une erreur.
    pause
    exit /b
)
goto END

:: ================= GESTION DES ERREURS =================

:ERROR_CONN
echo.
echo [ERREUR CRITIQUE] Impossible de se connecter a PostgreSQL.
echo Causes possibles :
echo 1. Le mot de passe dans ce fichier est faux.
echo 2. Le service PostgreSQL est arrete.
pause
goto END

:ERROR_CREATE
echo.
echo [ERREUR] La commande createdb a echoue.
pause
goto END

:END