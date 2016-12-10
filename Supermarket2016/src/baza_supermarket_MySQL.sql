CREATE DATABASE baza_supermarket;
--drop database baza_supermarket;
USE baza_supermarket;

CREATE USER 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;

CREATE USER 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;


CREATE TABLE dostawca 
(
     idDostawca INTEGER PRIMARY KEY NOT NULL , 
     nazwa VARCHAR(50) ,
	 adres VARCHAR(100)
);

CREATE TABLE magazynier 
(
     idMagazynier INTEGER PRIMARY KEY NOT NULL , 
     imie VARCHAR(30) ,
	 nazwisko VARCHAR(30)
);

CREATE TABLE sprzedawca 
(
     idSprzedawca INTEGER PRIMARY KEY NOT NULL , 
     imie VARCHAR(30) ,
	 nazwisko VARCHAR(30)
);

CREATE TABLE logowanie 
(
     login VARCHAR(20) PRIMARY KEY NOT NULL , 
     haslo VARCHAR(20)
);

CREATE TABLE promocja 
(
     idPromocji INTEGER PRIMARY KEY NOT NULL , 
     odKiedy DATETIME , 
     doKiedy DATETIME , 
	 idProduktu INTEGER ,
	 staraCena FLOAT ,
	 nowaCena FLOAT
);

CREATE TABLE produkt
(
     idProdukt INTEGER PRIMARY KEY NOT NULL , 
     nazwaProduktu VARCHAR(50) , 
	 waga FLOAT ,
	 dlugoscGwarancji INTEGER ,
	 dataWaznosci DATE ,
	 cenaZakupu FLOAT ,
	 cenaSprzedazy FLOAT ,
	 cenaPromocyjna FLOAT ,
	 ilosc INTEGER ,
	 nr_regalu INTEGER ,
	 nr_polki INTEGER ,
	 nr_miejsca INTEGER ,
     punkty INTEGER DEFAULT 0 ,
	 idPromocji INTEGER ,
	 CONSTRAINT promocja_produkt_FK 
		FOREIGN KEY (idPromocji) REFERENCES promocja(idPromocji) 
);

CREATE TABLE pracownik 
(
	id INTEGER PRIMARY KEY NOT NULL ,
	imie VARCHAR(30) NOT NULL ,
	nazwisko VARCHAR(30) NOT NULL , 
	dataZatrudnienia DATETIME NOT NULL ,
	nazwaStanowiska VARCHAR(30) NOT NULL ,
	wynagrodzenie FLOAT
);

CREATE TABLE klient 
(
	idKlient INTEGER PRIMARY KEY NOT NULL ,
	iloscPunktow INTEGER ,
	imie VARCHAR(20) ,
	nazwisko VARCHAR(20) ,
	kodPocztowy INTEGER
);

CREATE TABLE reklamacja 
(
     idReklamacja INTEGER PRIMARY KEY NOT NULL , 
     idKlient INTEGER NOT NULL , 
     idProdukt INTEGER NOT NULL , 
	 CONSTRAINT produkt_reklamacja_FK 
		FOREIGN KEY (idProdukt) REFERENCES produkt(idProdukt) ,
	 CONSTRAINT klient_reklamacja_FK 
		FOREIGN KEY (idKlient) REFERENCES klient(idKlient)
);

CREATE TABLE faktura
(
	idFaktura INTEGER PRIMARY KEY NOT NULL ,
	idKlient INTEGER ,
	produkty VARCHAR(300) ,
	wartosc FLOAT ,
	iloscProduktow INTEGER ,
	idZamowienia INTEGER ,
	CONSTRAINT klient_faktura_FK 
		FOREIGN KEY(idKlient) REFERENCES klient(idKlient)
);

CREATE TABLE zamowienie 
(
     idZamowienie INTEGER PRIMARY KEY NOT NULL , 
	 idProdukt INTEGER NOT NULL ,
     idKlient INTEGER NOT NULL , 
     idSprzedawca INTEGER NOT NULL ,
	 iloscProduktow INTEGER NOT NULL ,
	 produkty VARCHAR(300) ,
	 status1 VARCHAR(20) ,
	 czasDostawy DATETIME ,
	 dostawca VARCHAR(100) ,
	 nr_zamowienia INTEGER ,
	 idFaktura INTEGER ,
	 CONSTRAINT produkty_zam_FK 
		FOREIGN KEY(idProdukt) REFERENCES produkt(idProdukt) ,
	 CONSTRAINT faktura_zam_FK 
		FOREIGN KEY(idFaktura) REFERENCES faktura(idFaktura) ,
	 CONSTRAINT klient_zam_FK 
		FOREIGN KEY(idKlient) REFERENCES klient(idKlient)
);

