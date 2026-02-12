@echo off
setlocal enabledelayedexpansion

:: ==========================================
:: AUTO-ELEVATION (ADMIN)
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
:: CONFIGURATION JAVA
:: ==========================================
set "JAVA_HOME=C:\Program Files\Java\jdk-25.0.2"
set "PATH=%JAVA_HOME%\bin;%PATH%"

:: ==========================================
:: GESTION CONFIGURATION (.launcherConfig)
:: ==========================================
set "CONFIG_FILE=.launcherConfig"
set "NEED_CREATE=0"

:: Valeurs par defaut
set "DEBUG_MODE=0"
set "DEBUG_DB=0"
set "BUILD=1"
set "RESET_DB=0"

:: 1. Verification de l'existence et lecture
if not exist "%CONFIG_FILE%" (
    set "NEED_CREATE=1"
) else (
    set "FOUND_DEBUG="
    set "FOUND_DB="
    set "FOUND_BUILD="
    set "FOUND_RESET="

    for /f "tokens=1* delims==" %%A in (%CONFIG_FILE%) do (
        if "%%A"=="debug" (
            set "DEBUG_MODE=%%B"
            set "FOUND_DEBUG=1"
        )
        if "%%A"=="debug-db" (
            set "DEBUG_DB=%%B"
            set "FOUND_DB=1"
        )
        if "%%A"=="build" (
            set "BUILD=%%B"
            set "FOUND_BUILD=1"
        )
        if "%%A"=="reset-db" (
            set "RESET_DB=%%B"
            set "FOUND_RESET=1"
        )
    )

    :: Si une variable manque, on regenere le fichier
    if not defined FOUND_DEBUG set "NEED_CREATE=1"
    if not defined FOUND_DB set "NEED_CREATE=1"
    if not defined FOUND_BUILD set "NEED_CREATE=1"
    if not defined FOUND_RESET set "NEED_CREATE=1"
)

:: 2. Creation ou Reparation du fichier
if "!NEED_CREATE!"=="1" (
    echo [INFO] Generation du fichier configuration par defaut...
    (
        echo debug=0
        echo debug-db=0
        echo build=1
        echo reset-db=0
    ) > "%CONFIG_FILE%"

    set "DEBUG_MODE=0"
    set "DEBUG_DB=0"
    set "BUILD=1"
    set "RESET_DB=0"
)

:: 3. Logique RESET-DB (Auto-Update SECURISE)
set "DO_RESET=0"
set "UPDATE_CONFIG=0"

if "!RESET_DB!"=="1" (
    echo [INFO] Commande RESET-DB detectee.
    echo [INFO] -^> La base sera supprimee.
    echo [INFO] -^> Mise a jour de .launcherConfig pour remettre reset-db a 0.

    set "DO_RESET=1"
    set "RESET_DB=0"
    set "UPDATE_CONFIG=1"
)

:: Reecriture du fichier (Hors du bloc IF pour eviter le crash des parentheses)
if "!UPDATE_CONFIG!"=="1" (
    (
        echo debug=!DEBUG_MODE!
        echo debug-db=!DEBUG_DB!
        echo build=!BUILD!
        echo reset-db=0
    ) > "%CONFIG_FILE%"
)

:: Verification des autres conditions de reset (Debug total)
if "!DEBUG_MODE!"=="1" (
    if "!DEBUG_DB!"=="1" (
        set "DO_RESET=1"
    )
)

:: 4. Affichage Infos
if "!DEBUG_MODE!"=="1" (
    echo.
    echo ===================================================
    echo [MODE DEBUG ACTIVE]
    echo ===================================================
    echo.
    echo [INFO] Debug general : ON

    if "!DEBUG_DB!"=="1" (
        echo [INFO] Debug DB      : ON ^(Reset + Data^)
    ) else (
        echo [INFO] Debug DB      : OFF
    )
)

if "!DO_RESET!"=="1" (
    echo [ATTENTION] MODE RESET ACTIF : La base de donnees sera ecrasee.
)

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
if exist "C:\Program Files\PostgreSQL\18\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\18\bin"
if exist "C:\Program Files\PostgreSQL\17\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\17\bin"
if exist "C:\Program Files\PostgreSQL\16\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\16\bin"
if exist "C:\Program Files\PostgreSQL\15\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\15\bin"
if exist "C:\Program Files\PostgreSQL\14\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\14\bin"

if not defined PGBIN (
    if exist "C:\Program Files\PostgreSQL\bin\psql.exe" set "PGBIN=C:\Program Files\PostgreSQL\bin"
)

if not defined PGBIN (
    echo [ERREUR] PostgreSQL introuvable.
    pause
    exit /b
)
echo [OK] Trouve : "%PGBIN%"

:: ==========================================
:: SERVICE POSTGRESQL
:: ==========================================
echo [INFO] Verification du service PostgreSQL...

netstat -an | find ":5432" | find "LISTENING" >nul
if %errorlevel% neq 0 (
    echo [ATTENTION] Le port 5432 n'est pas actif.
    echo [INFO] Recherche du service...
    set "PG_SERVICE="
    for /f "tokens=2 delims=:" %%A in ('sc query state^= all ^| findstr /i "SERVICE_NAME: postgresql"') do (
        set "PG_SERVICE=%%A"
        set "PG_SERVICE=!PG_SERVICE: =!"
    )

    if defined PG_SERVICE (
        echo [INFO] Service trouve : !PG_SERVICE!
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
        goto MANUAL_CHECK
    )
) else (
    echo [OK] Service en ligne.
)
goto AUTH_CHECK

:MANUAL_CHECK
echo.
echo [AIDE] Lancez 'services.msc' et demarrez PostgreSQL.
pause
exit /b

:: ==========================================
:: AUTHENTIFICATION
:: ==========================================
:AUTH_CHECK
echo.
if exist ".dbpassword" (
    set "VALID_FORMAT=0"
    set "PGUSER="
    set "PGPASSWORD="
    for /f "tokens=1* delims=:" %%A in (.dbpassword) do (
        if not "%%A"=="" if not "%%B"=="" (
            set "PGUSER=%%A"
            set "PGPASSWORD=%%B"
            set "VALID_FORMAT=1"
        )
    )
    if "!VALID_FORMAT!"=="0" (
        echo [ATTENTION] Fichier .dbpassword invalide.
        del ".dbpassword"
        goto ASK_CREDENTIALS
    ) else (
        echo [INFO] Identifiants recuperes.
    )
) else (
    goto ASK_CREDENTIALS
)

"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -c "SELECT 1" >nul 2>&1
if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Identifiants incorrects.
    if exist ".dbpassword" del ".dbpassword"
    goto ASK_CREDENTIALS
) else (
    goto DB_INIT
)

:ASK_CREDENTIALS
echo.
set /p "PGUSER=[QUESTION] Utilisateur (ex: postgres): "
set /p "PGPASSWORD=[QUESTION] Mot de passe: "
"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -c "SELECT 1" >nul 2>&1
if %errorlevel% neq 0 (
    echo [ERREUR] Echec connexion.
    goto ASK_CREDENTIALS
)
if not exist ".dbpassword" (
    echo !PGUSER!:!PGPASSWORD!>.dbpassword
    echo [INFO] Identifiants sauvegardes.
)

:: ==========================================
:: INITIALISATION BDD
:: ==========================================
:DB_INIT
echo [INFO] Verification de la base '%DBNAME%'...

:: --- LOGIQUE DE SUPPRESSION (RESET) ---
:: Active si DO_RESET=1 (soit via debug-db, soit via reset-db)
if "!DO_RESET!"=="1" (
    echo [RESET] Nettoyage des connexions actives sur %DBNAME%...

    :: 1. On tue brutalement toutes les sessions connectees a cette base
    set PGPASSWORD=!PGPASSWORD!
    "%PGBIN%\psql.exe" -U !PGUSER! -d postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '%DBNAME%' AND pid <> pg_backend_pid();" >nul 2>&1

    echo [RESET] Suppression de la base existante...
    :: 2. On supprime
    "%PGBIN%\dropdb.exe" -U !PGUSER! --if-exists -f %DBNAME%
)

"%PGBIN%\psql.exe" -U !PGUSER! -d postgres -tAc "SELECT 1 FROM pg_database WHERE datname='%DBNAME%'" | findstr "1" >nul

if %errorlevel% equ 0 (
    echo [OK] La base '%DBNAME%' existe deja.
) else (
    echo [INFO] Creation de la base '%DBNAME%'...
    "%PGBIN%\createdb.exe" -U !PGUSER! -E UTF8 %DBNAME%
)

:: ==========================================
:: SCRIPT SQL : SCHEMA
:: ==========================================
echo.
echo ===================================================
echo Mise a jour du schema...
echo ===================================================
echo.

set "SQL_FILE=src\main\resources\db\schema.sql"
if exist "%SQL_FILE%" (
    set PGPASSWORD=!PGPASSWORD!
    "%PGBIN%\psql.exe" -q -U !PGUSER! -d %DBNAME% -f "%SQL_FILE%"
    if !errorlevel! neq 0 (
        echo [ERREUR] Erreur script SQL schema.
        pause
        exit /b
    ) else (
        echo [OK] Schema mis a jour.
    )
) else (
    echo [ATTENTION] Fichier schema.sql introuvable.
)

:: ==========================================
:: SCRIPT SQL : DATA (DEBUG ONLY)
:: ==========================================
:: L'injection de donnees de test ne se fait QUE si le mode DEBUG-DB est actif
:: Si on a fait un "reset-db" pour la prod (sans debug), on n'injecte PAS les donnees.
if "!DEBUG_MODE!"=="1" (
    if "!DEBUG_DB!"=="1" (
        echo.
        echo ===================================================
        echo [DEBUG-DB] Injection des donnees de test...
        echo ===================================================

        :: On definit le dossier cible
        set "DB_FOLDER=src\main\resources\db"

        if exist "!DB_FOLDER!\data.sql" (
            echo [INFO] Fichier trouve.

            :: 1. On se déplace dans le dossier
            pushd "!DB_FOLDER!"

            echo [INFO] Injection en cours...
            set PGCLIENTENCODING=UTF8

            "%PGBIN%\psql.exe" -q -U !PGUSER! -d %DBNAME% -f "data.sql"

            if !errorlevel! neq 0 (
                echo [ERREUR] Erreur lors de l'injection des donnees.
            ) else (
                echo [OK] Donnees injectees avec succes.
            )
            popd
        ) else (
            echo [ATTENTION] Fichier data.sql introuvable dans !DB_FOLDER!
        )
    )
)

:: ==========================================
:: BUILD ET LANCEMENT
:: ==========================================
echo.
echo ===================================================
echo Construction du projet...
echo ===================================================
echo.

set MAVEN_CMD=mvn
if exist "mvnw.cmd" set MAVEN_CMD=mvnw.cmd

set "DO_BUILD=1"

:: Si build est a 0, on essaye de sauter le build
if "!BUILD!"=="0" (
    :: Verification si un jar existe deja
    if exist "target\*.jar" (
        echo [INFO] build=0 et JAR trouve. Le build est ignore.
        set "DO_BUILD=0"
    ) else (
        echo [ATTENTION] build=0 mais aucun JAR trouve. Build force.
    )
)

if "!DO_BUILD!"=="1" (
    call %MAVEN_CMD% clean package -DskipTests

    if !errorlevel! neq 0 (
        echo.
        echo [ERREUR] Compilation echouee.
        pause
        exit /b
    )
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

echo [INFO] Lancement de : %JAR_NAME%...
"%JAVA_HOME%\bin\java.exe" -jar "%JAR_NAME%" --spring.datasource.password=!PGPASSWORD! --spring.datasource.username=!PGUSER! --app.debug-db=!DEBUG_DB!

if %errorlevel% neq 0 (
    echo.
    echo [ERREUR] Le serveur s'est arrete avec une erreur.
    pause
)