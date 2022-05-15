CREATE DATABASE projet_gl;
use projet_gl;

CREATE TABLE Client
(
    idClient INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    telephone VARCHAR(15) NOT NULL,
    email VARCHAR(255) NOT NULL,
    motdepasse VARCHAR(255) NOT NULL,
    numeroCarte VARCHAR(255) NOT NULL,
    admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE Vehicule
(
    idVehicule INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    marque VARCHAR(100) NOT NULL,
    immatriculation VARCHAR(15) NOT NULL
);

CREATE TABLE ClientPossedeVehicule
(
    idClient INT NOT NULL AUTO_INCREMENT,
    idVehicule INT NOT NULL,
    dateAjoutVehicule DATE NOT NULL,
    possedeTemporairement BOOLEAN NOT NULL,
    dureePossede INT,
    primary key (idClient, idVehicule),
    CONSTRAINT fk_idClientVehicule FOREIGN KEY (idClient) REFERENCES Client(idClient),
    CONSTRAINT fk_idVehiculeClient FOREIGN KEY (idVehicule) REFERENCES Vehicule(idVehicule)
);

CREATE TABLE Borne
(
    idBorne INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    etat ENUM('disponible','indisponible','occupée','reservée') NOT NULL
);

CREATE TABLE Reservation
(
    idReservation INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dateReservation DATE NOT NULL,
    dateDebut TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    dateFin TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    duree INT NOT NULL,
    idClient INT NOT NULL,
    idBorne INT NOT NULL,
    CONSTRAINT fk_idClientReservation FOREIGN KEY (idClient) REFERENCES Client(idClient),
    CONSTRAINT fk_idBorneReservation FOREIGN KEY (idBorne) REFERENCES Borne(idBorne)
);


CREATE TABLE FRAIS
(
    fraisReservation FLOAT,
    fraisHorsReservation FLOAT,
    fraisNonPresentation FLOAT
);

CREATE TABLE Contrat
(
    idContrat INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    dateDebutContrat DATE NOT NULL,
    dateFinContrat DATE NOT NULL,
    idClient INT NOT NULL,
    idBorne INT NOT NULL,
    CONSTRAINT fk_idClientReservation2 FOREIGN KEY (idClient) REFERENCES Client(idClient),
    CONSTRAINT fk_idBorneReservation2 FOREIGN KEY (idBorne) REFERENCES Borne(idBorne)
);

INSERT INTO client VALUES (1,"Administrateur","Administrateur","Administration","0000000000","admin@admin.fr","admin","0000-0000-0000-0000", TRUE);