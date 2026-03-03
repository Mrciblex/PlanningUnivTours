# PlanningUnivTours
Une application de gestion d'emplois du temps pour l'université de Tours.

# Réaliser par :
  - Musa
  - Evan
  - Fab
  - Mathis
  - Léonie

# Congi launcher :
debug : Si le mode debug est activé ou non

debug-db : Efface toute la base et remet les données de test par défaut

build : Si le build doit se faire ou non (automatiquement oui si le .jar non trouvé)

reset-db : Reset la base et remet le schéma par défaut sans aucune donnée. Repasse automatiquement à ZÉRO après le lancement.



# Fiche de Configuration :

Pour exécuter PlanningUnivTours en local, assurez-vous d’avoir les éléments suivants installés sur votre machine :

Java JDK 25+ (pour exécuter Spring Boot).

Git (pour cloner le dépôt).

Un navigateur web (Chrome, Firefox, Edge, etc.).

Un système d’exploitation : Windows, macOS ou Linux.

(à voir avec macOs ou Linux, car nous n'avons pu tester que sur windows)

# Étapes d’Installation et de Lancement
1. Cloner le Dépôt Git

Ouvrez un terminal ou une invite de commandes et exécutez la commande suivante pour cloner le dépôt dans un dossier local de votre choix :

git clone https://github.com/Mrciblex/PlanningUnivTours.git

2. Configurer le Serveur Local

Accédez au dossier clonné et localisez le fichier luncher.sh (pour macOs et  linux) ou lunch.bat (pour windows).
Copiez ce raccourci et collez-le dans un dossier accessible (ex : Bureau).
Double-cliquez sur univTime pour lancer le serveur local.

Ce fichier démarre automatiquement la base de données PostgreSQL et le serveur Spring Boot.


3. Connexion à la Base de Données

Identifiants par défaut :

Login : postgres

Mot de passe : root

Sinon propre à chacun

4. Accéder à l’Application

Ouvrez un navigateur web (Chrome, Firefox, Edge, etc.).
Rendez-vous à l’adresse suivante :

http://localhost:8080/univtime/

L’interface de PlanningUnivTours devrait s’afficher.
