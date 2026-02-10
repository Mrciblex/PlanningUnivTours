CREATE TYPE type_cours AS ENUM (
    'CM',
    'TD',
    'TP'
);

CREATE TYPE type_besoin AS ENUM (
    'salle info',
    'salle physique',
    'salle normale'
);

CREATE TABLE Professeurs
(
    idProf SERIAL,
    nomProf VARCHAR(150),
    prenomProf VARCHAR(150),
    intervenantExterieur BOOLEAN,
    CONSTRAINT chk_nom_prof CHECK (nomProf IS NOT NULL),
    CONSTRAINT chk_prenom_prof CHECK (prenomProf IS NOT NULL),
    CONSTRAINT chk_intervenantExterieur CHECK (intervenantExterieur IS NOT NULL),
    CONSTRAINT pk_prof PRIMARY KEY (idProf)
);

CREATE TABLE Salles
(
    idSalle SERIAL,
    nbPlace INT,
    salleMachine BOOLEAN,
    nbPC INT,
    CONSTRAINT chk_nb_place CHECK (nbPlace IS NOT NULL),
    CONSTRAINT chk_salle_machine CHECK (salleMachine IS NOT NULL),
    CONSTRAINT pk_salle PRIMARY KEY (idSalle)
);

CREATE TABLE Modules
(
    idModule SERIAL,
    nomModule VARCHAR(150),
    CONSTRAINT chk_nom_module CHECK (nomModule IS NOT NULL),
    CONSTRAINT pk_module PRIMARY KEY (idModule)
);

CREATE TABLE Composantes
(
    idComposante SERIAL,
    nomComposante VARCHAR(150),
    volumeHoraireTotal INT,
    volumeHoraireCM INT,
    volumeHoraireTD INT,
    volumeHoraireTP INT,
    blocHoraireCM INT,
    blocHoraireTD INT,
    blocHoraireTP INT,
    idModule INT,
    CONSTRAINT chk_nom_composante CHECK (nomComposante IS NOT NULL),
    CONSTRAINT pk_composante PRIMARY KEY (idComposante),
    CONSTRAINT fk_module FOREIGN KEY (idModule) REFERENCES Modules (idModule)
);

CREATE TABLE Promos
(
    idPromo SERIAL,
    nomPromo VARCHAR(150),
    anneePromo INT,
    nbEtuPromo INT,
    CONSTRAINT chk_nom_promo CHECK (nomPromo IS NOT NULL),
    CONSTRAINT chk_annee_promo CHECK (anneePromo IS NOT NULL),
    CONSTRAINT chk_nb_etu_promo CHECK (nbEtuPromo IS NOT NULL),
    CONSTRAINT pk_promo PRIMARY KEY (idPromo)
);


CREATE TABLE Groupes
(
    idGroupe SERIAL,
    nomGroupe VARCHAR(150),
    nbEtuGroupe INT,
    idPromo INT,
    CONSTRAINT chk_nom_groupe CHECK (nomGroupe IS NOT NULL),
    CONSTRAINT chk_nb_etu_groupe CHECK (nbEtuGroupe IS NOT NULL),
    CONSTRAINT pk_groupe PRIMARY KEY (idGroupe),
    CONSTRAINT fk_promo FOREIGN KEY (idPromo) REFERENCES Promos (idPromo)
);

CREATE TABLE SousGroupes
(
    idSousGroupe SERIAL,
    nomSousGroupe VARCHAR(150),
    nbEtuSousGroupe INT,
    idGroupe INT,
    CONSTRAINT chk_nom_sous_groupe CHECK (nomSousGroupe IS NOT NULL),
    CONSTRAINT chk_nb_etu_sous_groupe CHECK (nbEtuSousGroupe IS NOT NULL),
    CONSTRAINT pk_sous_groupe PRIMARY KEY (idSousGroupe),
    CONSTRAINT fk_groupe FOREIGN KEY (idGroupe) REFERENCES Groupes (idGroupe)
);

CREATE TABLE Cours
(
    idCours SERIAL,
    heureDebutCours TIMESTAMP,
    heureFinCours TIMESTAMP,
    typeCours type_cours,
    idComposante INT,
    idProf INT,
    idSalle INT,
    CONSTRAINT chk_heure_debut_cours CHECK (heureDebutCours IS NOT NULL),
    CONSTRAINT chk_heure_fin_cours CHECK (heureFinCours IS NOT NULL),
    CONSTRAINT chk_horaire_cours CHECK (heureDebutCours < heureFinCours),
    CONSTRAINT chk_type_cours CHECK (typeCours IS NOT NULL),
    CONSTRAINT pk_cours PRIMARY KEY (idCours),
    CONSTRAINT fk_prof FOREIGN KEY (idProf) REFERENCES Professeurs (idProf),
    CONSTRAINT fk_salle FOREIGN KEY (idSalle) REFERENCES Salles (idSalle),
    CONSTRAINT fk_composante FOREIGN KEY (idComposante) REFERENCES Composantes (idComposante)
);

CREATE TABLE Jours
(
    idJour SERIAL,
    jourSemaine INT,
    idProf INT,
    CONSTRAINT chk_jour_semaine CHECK ((jourSemaine IS NOT NULL) AND (jourSemaine BETWEEN 1 AND 5)),
    CONSTRAINT pk_jour PRIMARY KEY (idJour),
    CONSTRAINT fk_prof FOREIGN KEY (idProf) REFERENCES Professeurs (idProf)
);

CREATE TABLE Disponibilites
(
    idDispo SERIAL,
    heureDebutDispo INT,
    heureFinDispo INT,
    idJour INT,
    CONSTRAINT chk_heure_debut_dispo CHECK (heureDebutDispo IS NOT NULL),
    CONSTRAINT chk_heure_fin_dispo CHECK (heureFinDispo IS NOT NULL),
    CONSTRAINT chk_horaire_dispo CHECK (heureDebutDispo < heureFinDispo),
    CONSTRAINT pk_dispo PRIMARY KEY (idDispo),
    CONSTRAINT fk_jour FOREIGN KEY (idJour) REFERENCES Jours (idJour)
);

CREATE TABLE BesoinSalle
(
    idSalle INT,
    idComposante INT,
    typeBesoin type_besoin,
    CONSTRAINT chk_type_besoin CHECK (typeBesoin IS NOT NULL),
    CONSTRAINT pk_salle_composante PRIMARY KEY (idSalle, idComposante),
    CONSTRAINT fk_salle FOREIGN KEY (idSalle) REFERENCES Salles (idSalle),
    CONSTRAINT fk_composante FOREIGN KEY (idComposante) REFERENCES Composantes (idComposante)
);

CREATE TABLE RepartitionSemaine
(
    idRepartitionSemaine SERIAL,
    numSemaine INT,
    qteTypeCours INT,
    CONSTRAINT chk_num_semaine CHECK (numSemaine IS NOT NULL),
    CONSTRAINT pk_prof_composante PRIMARY KEY (idRepartitionSemaine)
);

CREATE TABLE PromoEstComposee
(
    idPromo INT,
    idModule INT,
    CONSTRAINT pk_promo_module PRIMARY KEY (idPromo, idModule),
    CONSTRAINT fk_promo FOREIGN KEY (idPromo) REFERENCES Promos (idPromo),
    CONSTRAINT fk_module FOREIGN KEY (idModule) REFERENCES Modules (idModule)
);

CREATE TABLE TD
(
    idProf INT,
    idGroupe INT,
    idComposante INT,
    idRepartitionSemaine INT,
    CONSTRAINT pk_td PRIMARY KEY (idProf, idGroupe, idComposante, idRepartitionSemaine),
    CONSTRAINT fk_prof_td FOREIGN KEY (idProf) REFERENCES Professeurs (idProf),
    CONSTRAINT fk_groupe_td FOREIGN KEY (idGroupe) REFERENCES Groupes (idGroupe),
    CONSTRAINT fk_composante_td FOREIGN KEY (idComposante) REFERENCES Composantes (idComposante),
    CONSTRAINT fk_repartition_semaine_td FOREIGN KEY (idRepartitionSemaine) REFERENCES RepartitionSemaine (idRepartitionSemaine)
);

CREATE TABLE CM
(
    idProf INT,
    idPromo INT,
    idComposante INT,
    idRepartitionSemaine INT,
    CONSTRAINT pk_cm PRIMARY KEY (idProf, idPromo, idComposante, idRepartitionSemaine),
    CONSTRAINT fk_prof_cm FOREIGN KEY (idProf) REFERENCES Professeurs (idProf),
    CONSTRAINT fk_promo_cm FOREIGN KEY (idPromo) REFERENCES Promos (idPromo),
    CONSTRAINT fk_composante_cm FOREIGN KEY (idComposante) REFERENCES Composantes (idComposante),
    CONSTRAINT fk_repartition_semaine_cm FOREIGN KEY (idRepartitionSemaine) REFERENCES RepartitionSemaine (idRepartitionSemaine)
);

CREATE TABLE TP
(
    idProf INT,
    idSousGroupe INT,
    idComposante INT,
    idRepartitionSemaine INT,
    CONSTRAINT pk_tp PRIMARY KEY (idProf, idSousGroupe, idComposante, idRepartitionSemaine),
    CONSTRAINT fk_prof_tp FOREIGN KEY (idProf) REFERENCES Professeurs (idProf),
    CONSTRAINT fk_sous_groupe_tp FOREIGN KEY (idSousGroupe) REFERENCES SousGroupes (idSousGroupe),
    CONSTRAINT fk_composante_tp FOREIGN KEY (idComposante) REFERENCES Composantes (idComposante),
    CONSTRAINT fk_repartition_semaine_tp FOREIGN KEY (idRepartitionSemaine) REFERENCES RepartitionSemaine (idRepartitionSemaine)
);

CREATE TABLE ParticipeA
(
    idSousGroupe INT,
    idCours INT,
    CONSTRAINT pk_promo_module_participe_a PRIMARY KEY (idSousGroupe, idCours),
    CONSTRAINT fk_sous_groupe_participe_a FOREIGN KEY (idSousGroupe) REFERENCES SousGroupes (idSousGroupe),
    CONSTRAINT fk_course_participe_a FOREIGN KEY (idCours) REFERENCES Cours (idCours)
);