--CREATE DATABASE baza_supermarket;
--drop database baza_supermarket;
USE baza_supermarket;

CREATE USER 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'%' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;

CREATE USER 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.';
GRANT ALL PRIVILEGES ON *.* TO 'pracownik'@'localhost' IDENTIFIED BY 'Pracownik0.' WITH GRANT OPTION;

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
	 staraCena INTEGER ,
	 nowaCena INTEGER
/*
     id_partii INTEGER NOT NULL,
     CONSTRAINT Promocje_data_rozp_wczesniejsza_niz_data_zak
		CHECK (data_rozpoczecia < data_zakonczenia)
*/
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
/*
CREATE TABLE magazyn 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     id_partii INTEGER NOT NULL , 
     ustawienie_regalu CHAR(7) NOT NULL,
     ilosc_produktow_w_regale INTEGER,
	 CONSTRAINT Magazyn_Ustawienie_regalu_Pattern
		CHECK (ustawienie_regalu LIKE 'URM[0-9][0-9][0-9][0-9]')
);

CREATE TABLE stanowiska 
(
     id INTEGER PRIMARY KEY NOT NULL , 
     nazwa VARCHAR(30) , 
     pensja FLOAT NOT NULL, 
     budzet_operacyjny FLOAT
);
*/

CREATE TABLE pracownik 
(
	id INTEGER PRIMARY KEY NOT NULL ,
	imie VARCHAR(30) NOT NULL ,
	nazwisko VARCHAR(30) NOT NULL , 
	dataZatrudnienia DATETIME NOT NULL ,
	nazwaStanowiska VARCHAR(30) NOT NULL ,
	wynagrodzenie FLOAT
/*
     PESEL CHAR(11) PRIMARY KEY NOT NULL , 
     id_stanowiska INTEGER , 
     id_konta VARCHAR(20) NOT NULL, 
     imie VARCHAR(30) NOT NULL, 
     nazwisko VARCHAR(30) NOT NULL, 
     premia FLOAT DEFAULT 0.0, 
     data_zatrudnienia DATETIME NOT NULL, 
     data_zwolnienia DATETIME , 
     miasto VARCHAR(30) , 
     ulica VARCHAR(40) , 
     nr_budynku VARCHAR(5) , 
     nr_mieszkania VARCHAR(5) , 
     kod_pocztowy VARCHAR(6) , 
     poczta VARCHAR(30),
     CONSTRAINT Pracownicy_PESEL_Pattern
		CHECK(PESEL LIKE '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	 CONSTRAINT Pracownik_Stanowisko_FK 
		FOREIGN KEY (id_stanowiska) REFERENCES stanowiska(id) ON DELETE SET NULL,
     CONSTRAINT Pracownik_konta_FK 
		FOREIGN KEY (id_konta) REFERENCES logowanie(login)
*/
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
/*
     data_zgloszenia DATETIME , 
     opis VARCHAR(300),
	CONSTRAINT Reklamacje_Produkty_FK 
		FOREIGN KEY (id_produktu) REFERENCES produkt(idProdukt) 
*/
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
/*
     nr_paragonu INTEGER NOT NULL, 
     ilosc INTEGER , 
     data_sprzedazy DATETIME , 
     rodzaj_platnosci CHAR,
     CONSTRAINT Transakcje_Pracownik_FK 
		FOREIGN KEY (id_kasjera) REFERENCES pracownik(id),
	 CONSTRAINT transakcje_Produkty_FK 
		FOREIGN KEY(id_produktu) REFERENCES produkt(idProdukt) 
*/
);

