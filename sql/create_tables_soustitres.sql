
CREATE SEQUENCE soustitres.statut_id_statut_seq;

CREATE TABLE soustitres.statut (
                id_statut INTEGER NOT NULL DEFAULT nextval('soustitres.statut_id_statut_seq'),
                statut VARCHAR(50) NOT NULL,
                CONSTRAINT statut_pk PRIMARY KEY (id_statut)
);


ALTER SEQUENCE soustitres.statut_id_statut_seq OWNED BY soustitres.statut.id_statut;

CREATE SEQUENCE soustitres.fichier_idfichier_seq;

CREATE TABLE soustitres.Fichier (
                id_fichier INTEGER NOT NULL DEFAULT nextval('soustitres.fichier_idfichier_seq'),
                nom VARCHAR(50) NOT NULL,
                description VARCHAR(150) NOT NULL,
                CONSTRAINT fichier_pk PRIMARY KEY (id_fichier)
);


ALTER SEQUENCE soustitres.fichier_idfichier_seq OWNED BY soustitres.Fichier.id_fichier;

CREATE SEQUENCE soustitres.traduction_id_traduction_seq;

CREATE TABLE soustitres.Traduction (
                id_traduction INTEGER NOT NULL DEFAULT nextval('soustitres.traduction_id_traduction_seq'),
                source INTEGER NOT NULL,
                cible INTEGER NOT NULL,
                id_statut INTEGER NOT NULL,
                CONSTRAINT traduction_pk PRIMARY KEY (id_traduction)
);


ALTER SEQUENCE soustitres.traduction_id_traduction_seq OWNED BY soustitres.Traduction.id_traduction;

CREATE SEQUENCE soustitres.lignefichier_idligne_seq;

CREATE TABLE soustitres.LigneFichier (
                id_ligne INTEGER NOT NULL DEFAULT nextval('soustitres.lignefichier_idligne_seq'),
                id_fichier INTEGER NOT NULL,
                numero_ligne VARCHAR NOT NULL,
                time TIME NOT NULL,
                texte VARCHAR(250) NOT NULL,
                CONSTRAINT lignefichier_pk PRIMARY KEY (id_ligne)
);


ALTER SEQUENCE soustitres.lignefichier_idligne_seq OWNED BY soustitres.LigneFichier.id_ligne;

ALTER TABLE soustitres.Traduction ADD CONSTRAINT statut_traduction_fk
FOREIGN KEY (id_statut)
REFERENCES soustitres.statut (id_statut)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE soustitres.LigneFichier ADD CONSTRAINT fichier_lignefichier_fk
FOREIGN KEY (id_fichier)
REFERENCES soustitres.Fichier (id_fichier)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE soustitres.Traduction ADD CONSTRAINT fichier_traduction_fk
FOREIGN KEY (source)
REFERENCES soustitres.Fichier (id_fichier)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE soustitres.Traduction ADD CONSTRAINT fichier_traduction_fk1
FOREIGN KEY (cible)
REFERENCES soustitres.Fichier (id_fichier)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
