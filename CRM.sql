CREATE TABLE adresse(
    id INT PRIMARY KEY AUTO_INCREMENT,
    rue VARCHAR(255) NOT NULL,
    ville VARCHAR(255) NOT NULL,
    pays VARCHAR(255) NOT NULL,
    code_postal VARCHAR(5) NOT NULL
);

CREATE TABLE produit(
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    description TEXT,
    prix DOUBLE NOT NULL CHECK(prix >= 0)
);

CREATE TABLE client(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_adresse INT NOT NULL,
    nom_societe VARCHAR(255),
    mail VARCHAR(255) NOT NULL,
    nom VARCHAR(255) NOT NULL,
    prenom VARCHAR(255) NOT NULL,
    telephone VARCHAR(10) NOT NULL,
    etat INT,
    genre INT,
    CONSTRAINT fk_client_adresse FOREIGN KEY(id_adresse) REFERENCES adresse(id)
);

CREATE TABLE panier(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_client INT NOT NULL,
    CONSTRAINT fk_panier_client FOREIGN KEY(id_client) REFERENCES client(id)
);

CREATE TABLE paiement(
    id INT PRIMARY KEY AUTO_INCREMENT,
    no_carte INT NOT NULL,
    code_confidentiel INT NOT NULL,
    banque VARCHAR(255),
    id_client INT NOT NULL,
    CONSTRAINT fk_paiement_client FOREIGN KEY(id_client) REFERENCES client(id)
);

CREATE TABLE contient(
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_produit INT NOT NULL,
    id_panier INT NOT NULL,
    CONSTRAINT fk_contient_produit FOREIGN KEY(id_produit) REFERENCES produit(id),
    CONSTRAINT fk_contient_panier FOREIGN KEY(id_panier) REFERENCES panier(id)
);


// INSERTION

INSERT INTO adresse(rue,ville,pays,code_postal) VALUES (3 rue des Franciscains, Nantes, France, 44300),
('10 rue des mouettes', 'Gouesnou', 'France', '29850'),
('25 rue de Rennes', 'Paris', 'France', '75020'),
('rue des Oiseaux', 'Rennes', 'France', '35000'),
('avenue de la République', 'Lyon', 'France', '69000'),
('boulevard de la Liberté', 'Caen', 'France', '14000'),
('la marcelle', 'rennes','France','35500'),
('la faillette', 'new york','USA','44620'),
('conemuse', 'rennes', 'France', '35500'),
('8 rue de mellac', 'QUIMPERLE', 'FRANCE', '29300'),
('34 rue fontquentin', 'ROANNE', 'FRANCE', '42300'),
('16 place champlain', 'CAEN', 'FRANCE', '14000');

 

INSERT INTO client(id_adresse, nom_societe, mail, nom, prenom, telephone) VALUES (1, 'salopette', 'salopette@gmail.com', 'eric', 'gilbert', '0617354610'),
(2, 'pirouette', 'pirouette@gmail.com', 'salaun', 'morgane', '0617958610'),
(3, 'rose', 'rose@gmail.com', 'quere', 'rose', '0615359618'),
(4, 'Crédit Agricole', 'paolo@gmail.com', 'Roberto', 'Paolo', '0102030405'),
(5, 'Arkéa', 'jean@yahoo.fr', 'Dupont', 'Jean', '0608726523'),
(6, 'Société Générale', 'charlotte@hotmail.fr', 'Robert', 'Charlotte', '0723554489'),
(7, 'vetement et mode', 'lemarcelle@gmail.com', 'frette','nicolas','0665321545'),
(8,'gallerie la faillette','lux@gmail.com','de beau part', 'christelle','0682464652'),
(9,'music','zigzag@hotmail.com', 'soldomi', 'sigmud','0523252434'),
(10, 'Infotel', 'repojsdfp@gmail.com', 'Tartampion','Franklin', '0123456789'),
(11, 'Grenat', 'repop@gmail.com', 'Tartampion','Franklin', '4512568978'),
INSERT INTO client (id_adresse,mail,nom,prenom,telephone) VALUES (12, 'dfp@gmail.com', 'Mère','Michèle', '9887655432');

 

INSERT INTO produit (nom, prix) VALUES ('lunettes', 250),
('boitier', 10),
('lentilles', 90),
('planche de surf', 750.8),
('paddle', 530.5),
('kayak', 420.1),
('t-shirt', 1),
('caba', 10000),
('violon', 5750),
('Parapluie', 950),
('Chaussettes', 10),
('Pile', 1.50);

INSERT INTO panier (id_client) VALUES (1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12);

INSERT INTO contient (id_produit,id_panier) VALUES (1,2),
(1,1),
(3,4),
(4,4),
(5,6),
(5,8),
(10,7),
(8,12),
(7,11),
(10,7),
(12,5),
(2,5),



//EXO

//1
SELECT * 
FROM produit 
WHERE produit.nom 
LIKE  'c%';

//2
SELECT * 
FROM produit 
WHERE produit.nom 
LIKE  '%c%';

//3
SELECT * 
FROM client 
LEFT JOIN adresse 
ON client.id_adresse = adresse.id 
WHERE adresse.ville = 'Nantes';

//4
SELECT client.prenom, client.nom, panier.id
FROM client
INNER JOIN panier ON panier.id_client = client.id
WHERE client.id = 3;

//5
SELECT id_panier, COUNT(id_produit)
FROM contient
GROUP BY id_panier;

//6
SELECT client.nom, client.prenom, id_panier, SUM(produit.prix) 
FROM contient 
INNER JOIN produit ON contient.id_produit = produit.id 
INNER JOIN panier ON contient.id_panier = panier.id
INNER JOIN client ON  panier.id_client = client.id
GROUP BY id_panier;

//7
SELECT contient.id_panier 
FROM contient 
WHERE contient.id_produit = 1;

//8
SELECT DISTINCT client.prenom, client.nom 
FROM client 
INNER JOIN panier 
ON panier.id_client = client.id;