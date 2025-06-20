-- Création de la base de données
CREATE DATABASE garage;

-- Utilisation de la base de données
USE garage;

-- Création de la table clients
CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telephone VARCHAR(20)
);

-- Création de la table mecanos
CREATE TABLE mecanos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telephone VARCHAR(20),
    specialite VARCHAR(100)
);
-- Insertion de clients
INSERT INTO clients (nom, prenom, email, password, telephone) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0612345678'),
('Martin', 'Sophie', 'sophie.martin@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0698765432'),
('Bernard', 'Pierre', 'pierre.bernard@email.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0687654321');

-- Insertion de mécanos
INSERT INTO mecanos (nom, prenom, email, password, telephone, specialite) VALUES
('Leroy', 'Thomas', 'thomas.leroy@garage.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0678912345', 'Moteurs'),
('Petit', 'Laura', 'laura.petit@garage.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0656789123', 'Carrosserie'),
('Moreau', 'Alexandre', 'alexandre.moreau@garage.com', '$2y$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', '0645678912', 'Électronique');
CREATE TABLE vehicule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(50) NOT NULL,
    modele VARCHAR(50) NOT NULL,
    annee INT NOT NULL,
    immatriculation VARCHAR(20) UNIQUE NOT NULL,
    type_vehicule ENUM('VOITURE', 'MOTO', 'CAMION', 'UTILITAIRE') NOT NULL,
    carburant ENUM('ESSENCE', 'DIESEL', 'HYBRIDE', 'ELECTRIQUE', 'GPL') NOT NULL,
    kilometrage INT,
    date_dernier_entretien DATE,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);
-- Véhicule pour le client Jean Dupont (id=1)
INSERT INTO vehicule (marque, modele, annee, immatriculation, type_vehicule, carburant, kilometrage, date_dernier_entretien, client_id)
VALUES ('Renault', 'Clio', 2018, 'AB-123-CD', 'VOITURE', 'DIESEL', 45000, '2023-06-15', 1);

-- Véhicule pour le client Sophie Martin (id=2)
INSERT INTO vehicule (marque, modele, annee, immatriculation, type_vehicule, carburant, kilometrage, date_dernier_entretien, client_id)
VALUES ('Peugeot', '208', 2020, 'EF-456-GH', 'VOITURE', 'ESSENCE', 22000, '2023-09-10', 2);

-- Véhicule utilitaire
INSERT INTO vehicule (marque, modele, annee, immatriculation, type_vehicule, carburant, kilometrage, date_dernier_entretien, client_id)
VALUES ('Ford', 'Transit', 2019, 'IJ-789-KL', 'UTILITAIRE', 'DIESEL', 85000, '2023-11-05', 3);
-- Assurez-vous que la base existe
CREATE DATABASE IF NOT EXISTS garage;
USE garage;

-- Table clients (existante)
CREATE TABLE IF NOT EXISTS clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telephone VARCHAR(20)
);

-- Table mecanos (existante)
CREATE TABLE IF NOT EXISTS mecanos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    telephone VARCHAR(20),
    specialite VARCHAR(100)
);

-- Table vehicule (existante)
CREATE TABLE IF NOT EXISTS vehicule (
    id INT AUTO_INCREMENT PRIMARY KEY,
    marque VARCHAR(50) NOT NULL,
    modele VARCHAR(50) NOT NULL,
    annee INT NOT NULL,
    immatriculation VARCHAR(20) UNIQUE NOT NULL,
    client_id INT NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients(id) ON DELETE CASCADE
);

-- Table panne (existante)
CREATE TABLE IF NOT EXISTS panne (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prix DECIMAL(10,2) NOT NULL
);

-- Données pour pannes (existantes)
INSERT IGNORE INTO panne (nom, prix) VALUES
    ('Changement batterie', 150.00),
    ('Remplacement alternateur', 350.00),
    ('Réparation freinage', 180.00),
    ('Vidange moteur', 80.00),
    ('Changement courroie de distribution', 400.00),
    ('Réparation boîte de vitesses', 750.00),
    ('Diagnostic électronique', 50.00),
    ('Changement bougies d''allumage', 60.00),
    ('Remplacement injecteurs', 300.00),
    ('Réglage parallélisme', 90.00),
    ('Changement amortisseurs', 450.00),
    ('Réparation climatisation', 220.00),
    ('Peinture carrosserie', 600.00),
    ('Réparation échappement', 130.00),
    ('Changement filtres à air', 45.00),
    ('Remplacement pompe à essence', 275.00),
    ('Changement phare LED', 120.00),
    ('Réparation serrure porte', 100.00),
    ('Remplacement pare-brise', 450.00),
    ('Entretien circuit de refroidissement', 200.00);

-- Nouvelles tables
CREATE TABLE IF NOT EXISTS rendezvous (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_heure DATETIME NOT NULL,
    statut ENUM('planifie', 'en_cours', 'termine', 'annule') NOT NULL DEFAULT 'planifie',
    vehicule_id INT NOT NULL,
    panne_id INT,
    FOREIGN KEY (vehicule_id) REFERENCES vehicule(id) ON DELETE CASCADE,
    FOREIGN KEY (panne_id) REFERENCES panne(id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS reparation (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_debut DATETIME NOT NULL,
    date_fin DATETIME,
    statut ENUM('en_attente', 'en_cours', 'termine') NOT NULL DEFAULT 'en_attente',
    description TEXT,
    cout DECIMAL(10,2),
    rendezvous_id INT,
    mecano_id INT NOT NULL,
    FOREIGN KEY (rendezvous_id) REFERENCES rendezvous(id) ON DELETE SET NULL,
    FOREIGN KEY (mecano_id) REFERENCES mecanos(id) ON DELETE CASCADE
);
Voici plus haut la requête de création de la base de données, ça fonctionne de la manière suivante :
Il faut créer cette base de données dans mysql workbench 
~ Par la suite, aller dans la classe DateService pour configurer votre nom d'utilisateur et votre mot de passe dans mysql workbench.
~ Ensuite, il faudra créer trois package: models, utils et service puis insérer chaque classe à l'intérieur selon la spécification de package tout en haut de la classe. 
~ c'est MainApp qu'il faudra exécuter 
Nous rappelons que nous avons utiliser intellij avec javaFX pour développer cette application.
