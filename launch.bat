@echo off
setlocal enabledelayedexpansion

:: ==========================================
:: AUTO-ELEVATION (ADMIN) pour lancement du service PostGreSQL
:: ==========================================
>nul 2>&1 "%SYSTEMROOT%\system32\cacls.exe" "%SYSTEMROOT%\system32\config\system"
if '%errorlevel%' NEQ '0' (
    echo [INFO] Demande de droits Administrateur...
    goto UACPrompt
) else ( goto gotAdmin )

:UACPrompt
    echo Set UAC = CreateObject^("Shell.Application"^) > "%temp%\getadmin.vbs"
    echo UAC.ShellExecute "%~s0", "", "", "runas", 1 >> "%temp%\getadmin.vbs"
    "%temp%\getadmin.vbs"
    exit /B

:gotAdmin
    if exist "%temp%\getadmin.vbs" ( del "%temp%\getadmin.vbs" )
    pushd "%CD%"
    CD /D "%~dp0"

:: ==========================================
:: CONFIGURATION FORCÉE DE JAVA 25
:: ==========================================
set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.2"
set "PATH=%JAVA_HOME%\bin;%PATH%"

:: ==========================================
:: CONFIGURATION POSTGRESQL
:: ==========================================
echo.
echo ===================================================
echo Configuration de PostgreSQL...
echo ===================================================
echo.

set DBNAME=univ_time

echo [INFO] Recherche de PostgreSQL...
set "PGBIN="
:: On verifie jusqu'a la version 18
if exist "C:\Program Files\PostgreSQL\18\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\18\bin"
if exist "C:\Program Files\PostgreSQL\17\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\17\bin"
if exist "C:\Program Files\PostgreSQL\16\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\16\bin"
if exist "C:\Program Files\PostgreSQL\15\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\15\bin"
if exist "C:\Program Files\PostgreSQL\14\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\14\bin"

if not defined PGBIN (
    if exist "C:\Program Files\PostgreSQL\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\bin"
)

if not defined PGBIN (
    echo [ERREUR] PostgreSQL introuvable. Lien de telechargement : https://www.enterprisedb.com/downloads/postgres-postgresql-downloads
    pause
    exit /b
)
echo [OK] Trouve : "%PGBIN%"

:: ==========================================
:: VERIFICATION ET DEMARRAGE DU SERVICE
:: ==========================================
echo [INFO] Verification du service PostgreSQL...

netstat -an | find ":5432" | find "LISTENING" >nul
if %errorlevel% neq 0 (
    echo [ATTENTION] Le port 5432 n'est pas actif.
    echo [INFO] Recherche automatique du nom du service...

    :: METHODE INTELLIGENTE : On demande a Windows le nom du service PostgreSQL
    set "PG_SERVICE="
    for /f "tokens=2 delims=:" %%A in ('sc query state^= all ^| findstr /i "SERVICE_NAME: postgresql"') do (
        set "PG_SERVICE=%%A"
        :: On nettoie l'espace au debut
        set "PG_SERVICE=!PG_SERVICE: =!"
    )

    if defined PG_SERVICE (
        echo [INFO] Service trouve : !PG_SERVICE!
        echo [INFO] Tentative de demarrage...
        net start "!PG_SERVICE!"

        timeout /t 5 >nul
        netstat -an | find ":5432" | find "LISTENING" >nul
        if !errorlevel! neq 0 (
            echo [ERREUR] Le service ne repond pas.
            goto MANUAL_CHECK
        ) else (
            echo [OK] Service demarre !
        )
    ) else (
        echo [ERREUR] Impossible de trouver le nom du service Windows pour PostgreSQL.
        goto MANUAL_CHECK
    )
) else (
    echo [OK] Le service est deja en ligne.
)
goto AUTH_CHECK

:MANUAL_CHECK
echo.
echo [AIDE] Ouvrez le menu demarrer, tapez "Services", ouvrez l'application.
echo Cherchez "PostgreSQL...", faites Clic Droit -> Demarrer.
pause
exit /b

:: ==========================================
:: BOUCLE D'AUTHENTIFICATION (USER + MDP)
:: ==========================================
:AUTH_CHECK
echo.
if exist ".dbpassword" (
    set "VALID_FORMAT=0"
    set "PGUSER="
    set "PGPASSWORD="

    :: Lecture et verification du format
    for /f "tokens=1* delims=:" %%A in (.dbpassword) do (
        if not "%%A"=="" if not "%%B"=="" (
            set "PGUSER=%%A"
            set "PGPASSWORD=%%B"
            set "VALID_FORMAT=1"
        )
    )

    if "!VALID_FORMAT!"=="0" (
        echo [ATTENTION] Fichier .dbpassword vide ou mal formate. Suppression...
        del ".dbpassword"
        goto ASK_CREDENTIALS
    ) else (
        echo [INFO] Identifiants recuperes automatiquement.
    )
) else (
    goto ASK_CREDENTIALS
)

:: Test de connexion
"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -c "SELECT 1" >nul 2>&1

if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Identifiants incorrects ou connexion impossible.
    echo Le fichier sauvegarde va etre supprime.
    if exist ".dbpassword" del ".dbpassword"
    goto ASK_CREDENTIALS
) else (
    goto DB_INIT
)

:ASK_CREDENTIALS
echo.
set /p "PGUSER=[QUESTION] Entrez le nom d'utilisateur (ex: postgres): "
set /p "PGPASSWORD=[QUESTION] Entrez le mot de passe (ex: root): "

:: Test immediat des identifiants saisis
"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -c "SELECT 1" >nul 2>&1

if %errorlevel% neq 0 (
    echo [ERREUR] Echec de connexion. Verifiez le username et le password.
    goto ASK_CREDENTIALS
)

:: Si OK, on sauvegarde au format user:pass
if not exist ".dbpassword" (
    echo !PGUSER!:!PGPASSWORD!>.dbpassword
    echo [INFO] Identifiants sauvegardes dans '.dbpassword'.
)

:: ==========================================
:: INITIALISATION BDD
:: ==========================================
:DB_INIT
echo [INFO] Verification de la base '%DBNAME%'...
"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='%DBNAME%'" | findstr "1" >nul

if %errorlevel% equ 0 (
    echo [OK] La base '%DBNAME%' existe deja.
) else (
    echo [INFO] Creation de la base '%DBNAME%'...
    "%PGBIN%\createdb.exe" -U !PGUSER! -E UTF8 %DBNAME%
)

:: ==========================================
:: EXECUTION SCRIPT SQL
:: ==========================================
echo.
echo ===================================================
echo Mise a jour du schema (Tables/Types)...
echo ===================================================
echo.

set "SQL_FILE=src\main\resources\db\schema.sql"

if exist "%SQL_FILE%" (
    echo [INFO] Execution de %SQL_FILE%...

    :: On execute le fichier avec psql
    :: PGPASSWORD doit etre set en variable d'environnement pour psql
    set PGPASSWORD=!PGPASSWORD!

    "%PGBIN%\psql.exe" -q -U !PGUSER! -d %DBNAME% -f "%SQL_FILE%"

    if !errorlevel! neq 0 (
        echo [ERREUR] Erreur lors de l'execution du script SQL.
        pause
        exit /b
    ) else (
        echo [OK] Schema mis a jour.
    )
) else (
    echo [ATTENTION] Fichier SQL introuvable ici : %SQL_FILE%
)

:: ==========================================
:: CONSTRUCTION ET LANCEMENT (JAVA 25 COMPATIBLE)
:: ==========================================
echo.
echo ===================================================
echo Construction du projet...
echo ===================================================
echo.

set MAVEN_CMD=mvn
if exist "mvnw.cmd" set MAVEN_CMD=mvnw.cmd

:: On compile sans lancer pour eviter le bug Java 25/23
call %MAVEN_CMD% clean package -DskipTests

if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Compilation echouee. Lien Java 25.0.2 : https://jdk.java.net/25/
    pause
    exit /b
)

echo.
echo ===================================================
echo Lancement manuel avec Java 25...
echo ===================================================
echo.

cd target
for /f "delims=" %%i in ('dir /b *.jar ^| findstr /v "original-"') do set JAR_NAME=%%i

if not defined JAR_NAME (
    echo [ERREUR] Jar introuvable dans target/
    pause
    exit /b
)

echo [INFO] Lancement de : %JAR_NAME% avec injection du mot de passe DB...
"%JAVA_HOME%\bin\java.exe" -jar "%JAR_NAME%" --spring.datasource.password=!PGPASSWORD! --spring.datasource.username=!PGUSER!

if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Le serveur s'est arrete avec une erreur.
    pause
)