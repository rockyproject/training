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

alter table Formation 
alter column heuredebut type time, 
alter column heurefin type time;
