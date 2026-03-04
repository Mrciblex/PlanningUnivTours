#!/usr/bin/env bash

# Se placer dans le dossier du script
cd "$(dirname "$0")" || exit 1

# ==========================================
# CONFIGURATION JAVA (Equivalent Mac)
# ==========================================
# Tente de trouver Java 25 via l'utilitaire natif macOS, sinon garde la version par défaut
if /usr/libexec/java_home -v 25 &> /dev/null; then
    export JAVA_HOME=$(/usr/libexec/java_home -v 25)
    export PATH="$JAVA_HOME/bin:$PATH"
fi

# ==========================================
# CONFIGURATION
# ==========================================
CONFIG_FILE=".launcherConfig"
NEED_CREATE=0

# Valeurs par defaut
DEBUG_MODE=0
DEBUG_DB=0
BUILD=1
RESET_DB=0

# 1. Verification de l'existence et lecture
if [ ! -f "$CONFIG_FILE" ]; then
    NEED_CREATE=1
else
    # || [ -n "$key" ] permet de lire la derniere ligne meme sans saut de ligne final
    while IFS='=' read -r key value || [ -n "$key" ]; do
        # Nettoyage des retours chariots Windows (\r) potentiels
        key=$(echo "$key" | tr -d '\r')
        value=$(echo "$value" | tr -d '\r')

        case "$key" in
            debug) DEBUG_MODE="$value" ;;
            debug-db) DEBUG_DB="$value" ;;
            build) BUILD="$value" ;;
            reset-db) RESET_DB="$value" ;;
        esac
    done < "$CONFIG_FILE"

    # Vérification basique si une variable manque
    if [ -z "$DEBUG_MODE" ] || [ -z "$DEBUG_DB" ] || [ -z "$BUILD" ] || [ -z "$RESET_DB" ]; then
        NEED_CREATE=1
    fi
fi

# 2. Creation ou Reparation du fichier
if [ "$NEED_CREATE" -eq 1 ]; then
    echo "[INFO] Generation du fichier configuration par defaut..."
    cat > "$CONFIG_FILE" << EOF
debug=0
debug-db=0
build=1
reset-db=0
EOF
    DEBUG_MODE=0
    DEBUG_DB=0
    BUILD=1
    RESET_DB=0
fi

# 3. Logique RESET-DB (Auto-Update SECURISE)
DO_RESET=0
UPDATE_CONFIG=0

if [ "$RESET_DB" -eq 1 ]; then
    echo "[INFO] Commande RESET-DB detectee."
    echo "[INFO] -> La base sera supprimee."
    echo "[INFO] -> Mise a jour de .launcherConfig pour remettre reset-db a 0."
    DO_RESET=1
    RESET_DB=0
    UPDATE_CONFIG=1
fi

if [ "$UPDATE_CONFIG" -eq 1 ]; then
    cat > "$CONFIG_FILE" << EOF
debug=$DEBUG_MODE
debug-db=$DEBUG_DB
build=$BUILD
reset-db=0
EOF
fi

# Verification des autres conditions de reset (Debug total)
if [ "$DEBUG_MODE" -eq 1 ] && [ "$DEBUG_DB" -eq 1 ]; then
    DO_RESET=1
fi

# 4. Affichage Infos
if [ "$DEBUG_MODE" -eq 1 ]; then
    echo ""
    echo "==================================================="
    echo "[MODE DEBUG ACTIVE]"
    echo "==================================================="
    echo ""
    echo "[INFO] Debug general : ON"
    if [ "$DEBUG_DB" -eq 1 ]; then
        echo "[INFO] Debug DB      : ON (Reset + Data)"
    else
        echo "[INFO] Debug DB      : OFF"
    fi
fi

if [ "$DO_RESET" -eq 1 ]; then
    echo "[ATTENTION] MODE RESET ACTIF : La base de donnees sera ecrasee."
fi

# ==========================================
# CONFIGURATION POSTGRESQL
# ==========================================
echo ""
echo "==================================================="
echo "Configuration de PostgreSQL..."
echo "==================================================="
echo ""

DBNAME="univ_time"

echo "[INFO] Recherche de PostgreSQL..."
if ! command -v psql &> /dev/null; then
    echo "[ERREUR] PostgreSQL (psql) introuvable."
    echo "[AIDE] Installez-le via le terminal avec : brew install postgresql"
    echo ""
    read -n 1 -s -r -p "Appuyez sur une touche pour quitter..."
    echo ""
    exit 1
fi
echo "[OK] psql trouve."

# ==========================================
# SERVICE POSTGRESQL
# ==========================================
echo "[INFO] Verification du service PostgreSQL..."

MANUAL_CHECK=0

if ! nc -z localhost 5432; then
    echo "[ATTENTION] Le port 5432 n'est pas actif."
    echo "[INFO] Tentative de demarrage automatique..."

    # On vérifie si Homebrew est installé pour lancer le service
    if command -v brew &> /dev/null; then
        # On cherche le nom exact du service postgresql
        PG_SERVICE=$(brew services list | grep -i postgresql | awk '{print $1}' | head -n 1)

        if [ -n "$PG_SERVICE" ]; then
            echo "[INFO] Service Homebrew trouve : $PG_SERVICE"
            brew services start "$PG_SERVICE"

            # Pause pour laisser le temps au moteur de démarrer
            sleep 5

            # Revérification
            if ! nc -z localhost 5432; then
                echo "[ERREUR] Le service ne repond pas."
                MANUAL_CHECK=1
            else
                echo "[OK] Service demarre !"
            fi
        else
            MANUAL_CHECK=1
        fi
    else
        MANUAL_CHECK=1
    fi

    # Fallback si l'automatisation a échoué (Comportement strict du .bat : on quitte)
    if [ "$MANUAL_CHECK" -eq 1 ]; then
        echo ""
        echo "[AIDE] Lancez PostgreSQL manuellement (ex: Postgres.app ou brew services start postgresql)."
        read -n 1 -s -r -p "Appuyez sur une touche pour quitter..."
        echo ""
        exit 1
    fi
else
    echo "[OK] Service en ligne."
fi

# ==========================================
# AUTHENTIFICATION
# ==========================================
echo ""
ASK_CREDENTIALS=1

if [ -f ".dbpassword" ]; then
    IFS=':' read -r PGUSER_FILE PGPASSWORD_FILE < ".dbpassword"

    # Nettoyage des retours chariots Windows (\r)
    PGUSER_FILE=$(echo "$PGUSER_FILE" | tr -d '\r')
    PGPASSWORD_FILE=$(echo "$PGPASSWORD_FILE" | tr -d '\r')

    if [ -n "$PGUSER_FILE" ] && [ -n "$PGPASSWORD_FILE" ]; then
        PGUSER="$PGUSER_FILE"
        export PGPASSWORD="$PGPASSWORD_FILE"

        if psql -U "$PGUSER" -d postgres -c "SELECT 1" &> /dev/null; then
            echo "[INFO] Identifiants recuperes."
            ASK_CREDENTIALS=0
        else
            echo "[ERREUR] Identifiants incorrects."
            rm -f ".dbpassword"
        fi
    else
        echo "[ATTENTION] Fichier .dbpassword invalide."
        rm -f ".dbpassword"
    fi
fi

while [ "$ASK_CREDENTIALS" -eq 1 ]; do
    echo ""
    read -p "[QUESTION] Utilisateur (ex: postgres): " PGUSER
    read -s -p "[QUESTION] Mot de passe: " PGPASSWORD
    echo ""
    export PGPASSWORD

    if psql -U "$PGUSER" -d postgres -c "SELECT 1" &> /dev/null; then
        echo "$PGUSER:$PGPASSWORD" > .dbpassword
        echo "[INFO] Identifiants sauvegardes."
        ASK_CREDENTIALS=0
    else
        echo "[ERREUR] Echec connexion."
    fi
done

# ==========================================
# INITIALISATION BDD
# ==========================================
echo "[INFO] Verification de la base '$DBNAME'..."

if [ "$DO_RESET" -eq 1 ]; then
    echo "[RESET] Nettoyage des connexions actives sur $DBNAME..."
    psql -U "$PGUSER" -d postgres -c "SELECT pg_terminate_backend(pid) FROM pg_stat_activity WHERE datname = '$DBNAME' AND pid <> pg_backend_pid();" &> /dev/null

    echo "[RESET] Suppression de la base existante..."
    dropdb -U "$PGUSER" --if-exists "$DBNAME"
fi

if psql -U "$PGUSER" -lqt | cut -d \| -f 1 | grep -qw "$DBNAME"; then
    echo "[OK] La base '$DBNAME' existe deja."
else
    echo "[INFO] Creation de la base '$DBNAME'..."
    createdb -U "$PGUSER" -E UTF8 "$DBNAME"
fi

# ==========================================
# SCRIPT SQL : SCHEMA
# ==========================================
echo ""
echo "==================================================="
echo "Mise a jour du schema..."
echo "==================================================="
echo ""

SQL_FILE="src/main/resources/db/schema.sql"
if [ -f "$SQL_FILE" ]; then
    if psql -q -U "$PGUSER" -d "$DBNAME" -f "$SQL_FILE"; then
        echo "[OK] Schema mis a jour."
    else
        echo "[ERREUR] Erreur script SQL schema."
        read -n 1 -s -r -p "Appuyez sur une touche pour quitter..."
        echo ""
        exit 1
    fi
else
    echo "[ATTENTION] Fichier schema.sql introuvable."
fi

# ==========================================
# SCRIPT SQL : DATA (DEBUG ONLY)
# ==========================================
if [ "$DEBUG_MODE" -eq 1 ] && [ "$DEBUG_DB" -eq 1 ]; then
    echo ""
    echo "==================================================="
    echo "[DEBUG-DB] Injection des donnees de test..."
    echo "==================================================="

    DATA_FILE="src/main/resources/db/data.sql"
    if [ -f "$DATA_FILE" ]; then
        echo "[INFO] Fichier trouve."
        echo "[INFO] Injection en cours..."
        export PGCLIENTENCODING=UTF8

        # Le script bat utilisait pushd/popd pour se placer dans le dossier,
        # on peut simplement l'appeler depuis la racine ici, c'est équivalent.
        if psql -q -U "$PGUSER" -d "$DBNAME" -f "$DATA_FILE"; then
            echo "[OK] Donnees injectees avec succes."
        else
            echo "[ERREUR] Erreur lors de l'injection des donnees."
        fi
    else
        echo "[ATTENTION] Fichier data.sql introuvable."
    fi
fi

# ==========================================
# BUILD ET LANCEMENT (AVEC HOT RELOAD)
# ==========================================
echo ""
echo "==================================================="
echo "Lancement du projet (Mode DevTools)..."
echo "==================================================="
echo ""

# Choix dynamique de l'executable Maven (wrapper prioritaire)
MAVEN_CMD="mvn"
if [ -x "./mvnw" ]; then
    MAVEN_CMD="./mvnw"
fi

# Compilation (remplace la creation du JAR)
if [ "$BUILD" -eq 1 ]; then
    echo "[INFO] Compilation demandee (build=1)..."
    "$MAVEN_CMD" clean compile

    if [ $? -ne 0 ]; then
        echo ""
        echo "[ERREUR] Compilation echouee."
        read -n 1 -s -r -p "Appuyez sur une touche pour fermer le terminal et lire les logs ci-dessus..."
        echo ""
        exit 1
    fi
else
    echo "[INFO] Demarrage rapide sans recompilation initiale (build=0)..."
fi

echo ""
echo "[INFO] Lancement du serveur avec Spring Boot Run..."
echo "[INFO] Modifiez un fichier HTML ou Java dans votre IDE pour actualiser la page !"
echo ""

# Lancement via le plugin Spring Boot avec transmission des identifiants
"$MAVEN_CMD" spring-boot:run -Dspring-boot.run.arguments="--spring.datasource.password=${PGPASSWORD} --spring.datasource.username=${PGUSER} --app.debug-db=${DEBUG_DB}"

# On verifie s'il y a eu une erreur reconnue par Maven ou un crash
if [ $? -ne 0 ]; then
    echo ""
    echo "==================================================="
    echo "[ERREUR CRITIQUE] Le serveur a plante ou s'est arrete avec une erreur !"
    echo "==================================================="
else
    echo ""
    echo "==================================================="
    echo "[INFO] Le serveur s'est arrete."
    echo "==================================================="
fi

# Pause inconditionnelle pour empêcher la fermeture de la fenêtre
echo ""
read -n 1 -s -r -p "Appuyez sur une touche pour fermer le terminal et lire les logs ci-dessus..."
echo ""