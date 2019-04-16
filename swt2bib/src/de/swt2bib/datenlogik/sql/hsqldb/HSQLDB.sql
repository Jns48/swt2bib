/**
 * Author:  Tim Lorse
 * Created: 17.04.2018
 * Last Change: 24.04.2018
 */
-- Löscht das angegebene Schema, wenn es leer ist
--DROP SCHEMA swt2bib IF EXISTS;
-- Löscht das angegebene Schema inklusive der Tabellen
DROP SCHEMA swt2bib IF EXISTS CASCADE;

-- Erstellt das Schema
CREATE SCHEMA swt2bib;
-- Setzt das Logging für HSQLDB
SET DATABASE EVENT LOG LEVEL 3;
SET DATABASE EVENT LOG SQL LEVEL 3;


---- Löschen(DROP)
-- Löscht ansichten (Views)

-- Löscht Trigger

-- Löscht Tabellen(Table)



---- Erstellen(Create)
-- Erstellt Tabellen(Table)
-- ohne Fremdschlüssel
CREATE TABLE swt2bib.user(
  u_ID              INTEGER         NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  u_Vorname         VARCHAR(150)    NOT NULL,
  u_Nachname        VARCHAR(150)    NOT NULL,
  u_login           VARCHAR(8)      UNIQUE NOT NULL,
  u_password        VARCHAR(192)    NOT NULL,
  u_Mitarbeiter     BOOLEAN         DEFAULT FALSE,
  CONSTRAINT u_uID_PK PRIMARY KEY (u_ID)
);

CREATE TABLE swt2bib.genre(
  g_ID              INTEGER         NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  g_name            VARCHAR(150)    UNIQUE NOT NULL,
  CONSTRAINT g_gID_PK PRIMARY KEY (g_ID)
);

CREATE TABLE swt2bib.kategoriemedien(
  km_ID             INTEGER         NOT NULL GENERATED BY DEFAULT AS IDENTITY (START WITH 1),
  km_name           VARCHAR(150)    UNIQUE,
  km_beschreibung   VARCHAR(500),
  CONSTRAINT km_kmID_PK PRIMARY KEY (km_ID)
);
-- mit Fremdschlüssel
CREATE TABLE swt2bib.tmp1(
  
);

-- Erstellt Ansichten(Views)
CREATE VIEW swt2bib.tmp2(
  
);

-- Erstellen von Trigger
--BSP:
CREATE TRIGGER dms.UPDATE_Daten_ON_DELETE
  AFTER DELETE ON dms.user
  REFERENCING 
  NEW AS newrow
  OLD AS oldrow
  FOR EACH ROW 
  BEGIN ATOMIC
  UPDATE dms.daten SET u_ID = 1 WHERE u_ID = (SELECT u_ID FROM dms.user WHERE u_Username LIKE oldrow.U_USERNAME);
END;





---- Einfügen(Insert)
INSERT INTO swt2bib.genre(g_name) VALUES ('');
INSERT INTO swt2bib.kategoriemedien(km_name, km_beschreibung) VALUES ('', '');
-- Password: !Administrator@swt2bib
-- Passwordalgorithmus: SHA-512
INSERT INTO swt2bib.user(u_Vorname, u_Nachname, u_Login, u_password, u_mitarbeiter) 
VALUES ('Admin', 'Min', 'admin001', '18b1dc1d41a312a1fd17414211d1c11af1641f61a016e13e18d1991fb15711f1d01d51151421501791c012f1311e719f10414a1e61451bc1471401b61971ba1f11311be1f41771c417a11f1651d617d1e916019a11c1ef1bf19b1dc123100125', true);