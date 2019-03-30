/*Create database GestionFormation;
use GestionFormation

Create table Membre
(
	IdMembre varchar(10),
	nom varchar(25),
	postnom varchar(30),
	prenom varchar(30),
	sexe varchar(1),
	constraint pk_memb primary key(IdMembre)
);

Create table Formateur
(
	IdFormateur varchar(10),
	constraint pk_form primary key(IdFormateur),
	constraint fk_memb_form foreign key(IdFormateur)references Membre(IdMembre)on delete cascade on update cascade
);


Create table Module
(
	IdModule varchar(10),
	Design varchar(45),
	nbrePage int,
	constraint pk_Mod primary key(IdModule)
);

Create table Universite
(
	nomUniv varchar(40),
	constraint pk_univers primary key(nomUniv)
);

Create table Formation
(
	IdMembre varchar(10),
	IdFormateur varchar(10),
	IdModule varchar(10),
	Heure timestamp,
	DateDebut date,
	DateFin date,
	universite varchar(40),
	constraint pk_Format primary key (IdMembre,IdFormateur,IdModule,Heure,DateDebut,DateFin),
	constraint fk_formation_Memb foreign key(IdMembre)references Membre(IdMembre)on delete cascade on update cascade,
	constraint fk_formateur_formation foreign key(IdFormateur) references Membre(IdMembre)on delete cascade on update cascade,
	constraint fk_formation_module foreign key(IdModule) references Module(IdModule)on delete cascade on update cascade,
	constraint fk_form_univ foreign key(universite)references Universite(nomUniv)on delete cascade on update cascade
);




Create table Paiement
(
	NumBord varchar(15),
	IdMembre varchar(10),
	IdModule varchar(10),
	DateP date,
	Montant float,
	Monnaie varchar(5),
	Taux float,
	constraint pk_Paiement primary key (IdMembre,IdModule),
	constraint fk_Paie_Memb foreign key(IdMembre)references Membre(IdMembre)on delete cascade on update cascade,
	constraint fk_Paie_Mod foreign key(IdModule) references Module(IdModule)on delete cascade on update cascade
);

Create table Evaluation
(
	IdMembre varchar(10),
	IdModule varchar(10),
	IdEvaluateur varchar(10),
	DateEv date,
	Cote float,
	constraint pk_Eval primary key(IdMembre,IdModule,IdEvaluateur),
	constraint fk_Eval_Memb foreign key(IdMembre) references Membre(IdMembre) on delete cascade on update cascade,
	constraint fk_Eval_Mod foreign key(IdModule)references Module(IdModule)on delete cascade on update cascade,
	constraint fk_Evaluation_Evaluateur foreign key (IdEvaluateur) references Formateur(IdFormateur)on delete cascade on update cascade
);


Create table Enseigner
(
	IdModule varchar(10),
	IdFormateur varchar(10),
	constraint pk_ens primary key(IdModule,IdFormateur),
	constraint fk_Mod_Ens foreign key(IdModule)references Module(IdModule) on delete cascade on update cascade,
	constraint fk_Form_Ens foreign key(IdFormateur)references Formateur(IdFormateur) on delete cascade on update cascade
);


select *from membre
insert into Membre values('SD0012','KABANZA','JUSTIN','Justin','M');
insert into Membre values('SD0013','KUDRA','BILALI','Junior','M');
insert into Membre values('SD0014','MILINGANYO','WASSO','Bienfait','M');
insert into Membre values('SD0015','EZECKIEL','EZE','EZECKIEL','M');

update Membre set postnom='PALUKU' Where idmembre='SD0015'

select *from module
insert into Module values('M001','Initiation à la programmation','25');
insert into Module values('M002','Programmation Evénementielle','25');

select *from formation
insert into formation values ('SD0013','SD0012','M001','11/02/2019','20/02/2019');
insert into formation values ('SD0014','SD0012','M001','11/02/2019','20/02/2019');
insert into formation values ('SD0013','SD0012','M002','21/02/2019','28/02/2019');
insert into formation values ('SD0015','SD0012','M001','11/02/2019','20/02/2019');

Select (select concat( nom, ' ',postnom,' ',prenom)as NomMembre from membre where idmembre=Formation.idmembre) as NomMembre,
(select concat( nom, ' ',postnom,' ',prenom) from membre where membre.idmembre=formation.idformateur) as NomFormateur,
design,datedebut,datefin from Membre inner join Formation on Membre.IdMembre=Formation.IdMembre inner join Module on Formation.idModule
=Module.idModule

Select  concat(nom,' ',postnom) as noms from membre
*/

/*
ALTER TABLE paiement RENAME TO inscription;

alter table membre alter column sexe type varchar(15);
alter table inscription add disponibilite varchar(15),add etat varchar(15);
DROP TABLE formation;
DROP TABLE universite;

CREATE TABLE universite
(
	idUniv varchar(20),
	nom varchar(250) not null,
	sigle varchar(15) not null,
	adresse varchar(150),
	tel varchar(50),
	email varchar(50),
	siteWeb varchar(50),
	dg varchar(10),
	academique varchar(10),
	ab varchar(10),
	CONSTRAINT pk_univ PRIMARY KEY(idUniv),
	CONSTRAINT fk_dg FOREIGN KEY (dg) REFERENCES membre(idmembre),
	CONSTRAINT fk_academique FOREIGN KEY (academique) REFERENCES membre(idmembre),
	CONSTRAINT fk_ab FOREIGN KEY (ab) REFERENCES membre(idmembre)
)

Create table Formation
(
	idFormation varchar(10),
        idFormateur varchar(10),
	idModule varchar(10),
	heureDebut timestamp,
        heureFin timestamp,
	dateDebut date,
	dateFin date,
	idUniv varchar(20),
        constraint pk_Format primary key (idFormation),
	constraint uk_Format unique (idFormateur,idModule,heureDebut,dateDebut,dateFin),	
	constraint fk_formateur_formation foreign key(idFormateur) references formateur(idFormateur)on delete cascade on update cascade,
	constraint fk_formation_module foreign key(idModule) references Module(idModule)on delete cascade on update cascade,
	constraint fk_form_univ foreign key(idUniv)references Universite(idUniv)on delete cascade on update cascade
);

alter table Formation alter column heureDebut type time;
alter table Formation alter column heureFin type time;
*/

CREATE TABLE affectationmbremodule
(
  idmembre varchar(10) NOT NULL,
  idformation varchar(10) NOT NULL,
  CONSTRAINT pk_affect_mbre_mdle PRIMARY KEY (idmembre, idformation),
  CONSTRAINT fk_affectation_formation FOREIGN KEY (idformation) REFERENCES formation (idformation) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_affection_mbre FOREIGN KEY (idmembre) REFERENCES membre (idmembre) ON UPDATE CASCADE ON DELETE CASCADE
);

create table Utiilisateur
(
  Idutilisateur varchar (10),
  Motdepasse varchar (10),
  CONSTRAINT pk_utili_membr PRIMARY KEY (Idutilisateur),
  CONSTRAINT fk_membre_utilisateur FOREIGN KEY (Idutilisateur) REFERENCES membre(idmembre)
  );

alter table universite
ADD percepteur character varying(10);

create table administrateur
(
Idadmin character varying(10),
CONSTRAINT pk_admin_membr PRIMARY KEY (idadmin),
CONSTRAINT fk_membre_admin FOREIGN KEY (idadmin) REFERENCES membre(idmembre) ON DELETE CASCADE ON UPDATE CASCADE
);

alter table inscription
ADD percepteur character varying(10);

ALTER TABLE Utiilisateur RENAME TO utilisateur;

ALTER TABLE utilisateur ALTER COLUMN motdepasse type varchar (20);

CREATE TABLE cleRepartition
(
	role varchar(25),
	pourcentage float,
	CONSTRAINT pk_cle_repart PRIMARY KEY(role)
);

INSERT INTO cleRepartition VALUES
('Percepteur',4),
('Formateur',20),
('Evaluateur 1',2),
('Evaluateur 2',2),
('DG',3),
('Academique',2),
('AB',2),
('CP',2),
('Section',2),
('Universite',20),
('Centre',20),
('Validation des modules',5),
('auteur',10),
('Formateur echelon 1',2),
('Formateur echelon 2',2),
('Formateur echelon 3',2)

CREATE TABLE journalisation
(
	dateop timestamp,
	idop varchar(50),
	opJourn varchar(50),
	idMembre varchar(10),
	roleMbre varchar(25),
	montant float,
	monnaie varchar(6),
	mouvement varchar(15),
	libelle varchar(250),
	CONSTRAINT pk_jounalisation PRIMARY KEY(idop,opJourn,idMembre,roleMbre),
	CONSTRAINT fk_journal_membre FOREIGN KEY(idMembre) REFERENCES membre(idMembre) ON UPDATE CASCADE ON DELETE CASCADE
);

/*Fonction de journalisation de l'inscription*/
/*===========================================*/
CREATE OR REPLACE FUNCTION journalisation_inscription()
RETURNS trigger AS

$BODY$
	BEGIN
		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		concat(NEW.idmembre,NEW.idmodule),
		'Inscription',
		NEW.percepteur,
		'Percepteur',
		((select pourcentage FROM cleRepartition WHERE role='Percepteur') * NEW.montant/100),
		NEW.monnaie,
		NEW.taux,
		'Entree',
		concat('Inscription de ', (select concat( nom, ' ',postnom,' ',prenom)as NomMembre from membre where idmembre=NEW.idmembre),' au module: ',(select design from module WHERE idmodule=NEW.idmodule));
		
		RETURN NEW;
	END;
$BODY$
LANGUAGE 'plpgsql';

/*Declencheur de journalisation de l'inscription*/
/*==============================================*/
CREATE TRIGGER ajout_journalisation_inscription
AFTER INSERT
ON inscription
FOR EACH ROW
EXECUTE PROCEDURE journalisation_inscription();

/*MODIFICATION DE LA TABLE Module*/
/*===============================*/
Alter table Module drop column nbrepage 

Alter table Module add column auteur varchar(10);
Alter table Module add constraint fk_auteur foreign key(auteur)references Membre(Idmembre)on delete cascade on update cascade;

/*CLOTURE DE LA FORMATION*/
/*=======================*/
CREATE TABLE clotureFormation
(
    idFormation varchar(10),
    dateConfirmation timestamp,
    confirmePar varchar(10),
    CONSTRAINT pk_cloture_form PRIMARY KEY(idFormation),
    CONSTRAINT fk_cloture_formation FOREIGN KEY(idFormation) REFERENCES formation(idFormation) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_cloture_confirmation FOREIGN KEY(confirmePar) REFERENCES membre(idMembre)
);

/*Fonction de journalisation de la formation*/
/*==========================================*/
CREATE OR REPLACE FUNCTION journalisation_formation_log()
RETURNS trigger AS

$BODY$
	BEGIN
		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		CONCAT(affectationmbremodule.idmembre,affectationmbremodule.idformation) as idop, 
		'Formation',
		module.auteur,
		'Auteur',
		((select pourcentage FROM cleRepartition WHERE role='auteur') * (select montant from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule)/100),
		(select monnaie from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		(select taux from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		'Entree',
		concat('Formation de ', membre.nom, ' ',membre.postnom,' ',membre.prenom,' au module: ',module.design)
		from affectationmbremodule
		inner join formation on affectationmbremodule.idFormation=formation.idFormation
		inner join module on formation.idModule=module.idModule
		inner join universite on formation.idUniv=universite.idUniv
		inner join membre on affectationmbremodule.idMembre=membre.idMembre
		where formation.idFormation=NEW.idFormation;

		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		CONCAT(affectationmbremodule.idmembre,affectationmbremodule.idformation) as idop, 
		'Formation',
		formation.idFormateur,
		'Formateur',
		((select pourcentage FROM cleRepartition WHERE role='Formateur') * (select montant from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule)/100),
		(select monnaie from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		(select taux from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		'Entree',
		concat('Formation de ', membre.nom, ' ',membre.postnom,' ',membre.prenom,' au module: ',module.design)
		from affectationmbremodule
		inner join formation on affectationmbremodule.idFormation=formation.idFormation
		inner join module on formation.idModule=module.idModule
		inner join universite on formation.idUniv=universite.idUniv
		inner join membre on affectationmbremodule.idMembre=membre.idMembre
		where formation.idFormation=NEW.idFormation;

		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		CONCAT(affectationmbremodule.idmembre,affectationmbremodule.idformation) as idop, 
		'Formation',
		universite.dg,
		'DG',
		((select pourcentage FROM cleRepartition WHERE role='DG') * (select montant from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule)/100),
		(select monnaie from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		(select taux from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		'Entree',
		concat('Formation de ', membre.nom, ' ',membre.postnom,' ',membre.prenom,' au module: ',module.design)
		from affectationmbremodule
		inner join formation on affectationmbremodule.idFormation=formation.idFormation
		inner join module on formation.idModule=module.idModule
		inner join universite on formation.idUniv=universite.idUniv
		inner join membre on affectationmbremodule.idMembre=membre.idMembre
		where formation.idFormation=NEW.idFormation;
		
		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		CONCAT(affectationmbremodule.idmembre,affectationmbremodule.idformation) as idop, 
		'Formation',
		universite.academique,
		'Academique',
		((select pourcentage FROM cleRepartition WHERE role='Academique') * (select montant from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule)/100),
		(select monnaie from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		(select taux from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		'Entree',
		concat('Formation de ', membre.nom, ' ',membre.postnom,' ',membre.prenom,' au module: ',module.design)
		from affectationmbremodule
		inner join formation on affectationmbremodule.idFormation=formation.idFormation
		inner join module on formation.idModule=module.idModule
		inner join universite on formation.idUniv=universite.idUniv
		inner join membre on affectationmbremodule.idMembre=membre.idMembre
		where formation.idFormation=NEW.idFormation;

		INSERT INTO journalisation
		(
		dateop,
		idop,
		opJourn,
		idMembre,
		roleMbre,
		montant,
		monnaie,
		taux,
		mouvement,
		libelle
		) 
		SELECT now(),
		CONCAT(affectationmbremodule.idmembre,affectationmbremodule.idformation) as idop, 
		'Formation',
		universite.ab,
		'AB',
		((select pourcentage FROM cleRepartition WHERE role='AB') * (select montant from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule)/100),
		(select monnaie from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		(select taux from inscription where inscription.idMembre= affectationmbremodule.idMembre and inscription.idModule=formation.idModule),
		'Entree',
		concat('Formation de ', membre.nom, ' ',membre.postnom,' ',membre.prenom,' au module: ',module.design)
		from affectationmbremodule
		inner join formation on affectationmbremodule.idFormation=formation.idFormation
		inner join module on formation.idModule=module.idModule
		inner join universite on formation.idUniv=universite.idUniv
		inner join membre on affectationmbremodule.idMembre=membre.idMembre
		where formation.idFormation=NEW.idFormation;

				
		RETURN NEW;
	END;
$BODY$
LANGUAGE 'plpgsql';


/*Declencheur de journalisation de la formation*/
/*=============================================*/
CREATE TRIGGER journalisation_formation
AFTER INSERT
ON clotureFormation
FOR EACH ROW
EXECUTE PROCEDURE journalisation_formation_log();


/*Requette pour alimenter le graphique*/
/*====================================*/
select (sum(case when monnaie='FC' then montant/taux else montant end))::int as mont, roleMbre from journalisation where idmembre='1' group by roleMbre

select (sum(case when monnaie='FC' then montant/taux else montant end))::int as mont from journalisation where idmembre='1'
