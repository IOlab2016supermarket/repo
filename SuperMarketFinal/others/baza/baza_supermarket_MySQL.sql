--CREATE DATABASE baza_supermarket;
--drop database baza_supermarket;
USE baza_supermarket;

CREATE USER 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;

CREATE USER 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;


CREATE TABLE klient 
(
	idKlient INTEGER PRIMARY KEY NOT NULL ,
	iloscPunktow INTEGER ,
	imie VARCHAR(20) ,
	nazwisko VARCHAR(20) ,
	kodPocztowy INTEGER
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

CREATE TABLE konta 
(
     login VARCHAR(20) PRIMARY KEY NOT NULL , 
     haslo VARCHAR(100) , 
     uprawnienia VARCHAR(7)
);

CREATE TABLE produkty 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     nazwa VARCHAR(50) , 
     punkty INTEGER DEFAULT 0 , 
     dlugosc_gwarancji INTEGER , 
     cena_sprzedazy FLOAT , 
     cena_zakupu FLOAT
);

CREATE TABLE magazyn 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     id_partii INTEGER NOT NULL , 
     ustawienie_regalu CHAR(7) NOT NULL,
     ilosc_produktow_w_regale INTEGER,
	 CONSTRAINT Magazyn_Ustawienie_regalu_Pattern
		CHECK (ustawienie_regalu LIKE 'URM[0-9][0-9][0-9][0-9]')
);

CREATE TABLE pracownicy 
(
     id_pracownika INTEGER PRIMARY KEY NOT NULL,
     id_konta VARCHAR(20) NOT NULL, 
     imie VARCHAR(30) NOT NULL, 
     nazwisko VARCHAR(30) NOT NULL, 
     PESEL CHAR(11) NOT NULL,
     stanowisko VARCHAR(50) NOT NULL, 
     premia FLOAT DEFAULT 0.0, 
     data_zatrudnienia DATE NOT NULL, 
     data_zwolnienia DATE, 
     adres VARCHAR(120) ,
     CONSTRAINT Pracownicy_PESEL_Pattern
		CHECK(PESEL LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	 CONSTRAINT Pracownik_konta_FK 
		FOREIGN KEY (id_konta) REFERENCES konta(login)
);

CREATE TABLE wynagrodzenia
(
     id_wyplaty INTEGER PRIMARY KEY NOT NULL,
     pracownik INTEGER NOT NULL,
     kwota FLOAT NOT NULL,
     data_wyplacenia DATE,	
     CONSTRAINT wynagrodzenia_kwota_chk CHECK (kwota > 0),
     CONSTRAINT wynagrodzenia_pracownicy_fk
        FOREIGN KEY (pracownik) REFERENCES pracownicy(id_pracownika)
);

CREATE TABLE promocja 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     data_rozpoczecia DATETIME , 
     data_zakonczenia DATETIME , 
     id_produktu INTEGER NOT NULL,
	 stara_cena FLOAT NOT NULL,
	 nowa_cena FLOAT NOT NULL,
     CONSTRAINT Promocje_data_rozp_wczesniejsza_niz_data_zak
		CHECK (data_rozpoczecia < data_zakonczenia)
);
 

CREATE TABLE reklamacje 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     id_klienta INTEGER NOT NULL , 
     id_produktu INTEGER NOT NULL , 
     data_zgloszenia DATETIME , 
     opis VARCHAR(300),
	CONSTRAINT Reklamacje_Produkty_FK 
		FOREIGN KEY (id_produktu) REFERENCES produkty(id) 
);
 
CREATE TABLE transakcje 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     id_kasjera INTEGER NOT NULL , 
     id_produktu INTEGER NOT NULL ,
     nr_paragonu INTEGER NOT NULL, 
     ilosc INTEGER , 
     data_sprzedazy DATETIME , 
     rodzaj_platnosci CHAR,
     CONSTRAINT Transakcje_Pracownik_FK 
		FOREIGN KEY (id_kasjera) REFERENCES pracownicy(id_pracownika),
	 CONSTRAINT transakcje_Produkty_FK 
		FOREIGN KEY(id_produktu) REFERENCES produkty(id) 
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
		FOREIGN KEY(idProdukt) REFERENCES produkty(id) ,
	 CONSTRAINT faktura_zam_FK 
		FOREIGN KEY(idFaktura) REFERENCES faktura(idFaktura) ,
	 CONSTRAINT klient_zam_FK 
		FOREIGN KEY(idKlient) REFERENCES klient(idKlient)
);

CREATE TABLE produktySprzedaz 
(
	idProduktySprzedaz INTEGER PRIMARY KEY NOT NULL ,
	idFaktury INTEGER ,
	CONSTRAINT produktySprzedaz_produkt_FK 
		FOREIGN KEY(idProduktySprzedaz) REFERENCES produkty(id) ,
	CONSTRAINT produktySprzedaz_faktura_FK 
		FOREIGN KEY(idFaktury) REFERENCES faktura(idFaktura)
);
