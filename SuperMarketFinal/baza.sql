-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Хост: 127.0.0.1
-- Время создания: Янв 16 2017 г., 15:29
-- Версия сервера: 5.5.25
-- Версия PHP: 5.5.9

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- База данных: `baza_gui`
--

-- --------------------------------------------------------

--
-- Структура таблицы `invoice`
--

CREATE TABLE IF NOT EXISTS `invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `number` varchar(20) NOT NULL,
  `id_client` int(11) NOT NULL,
  `id_store` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_client` (`id_client`),
  KEY `id_store` (`id_store`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `invoice`
--

INSERT INTO `invoice` (`id`, `date`, `number`, `id_client`, `id_store`) VALUES
(1, '2017-01-01', 'AA-124/16', 1, 1),
(3, '2017-01-02', '112', 4, 2),
(4, '2017-01-10', '300', 4, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `invoice_table`
--

CREATE TABLE IF NOT EXISTS `invoice_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_invoice` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `price` float NOT NULL,
  `summa` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_product` (`id_product`),
  KEY `id_invoice` (`id_invoice`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=12 ;

--
-- Дамп данных таблицы `invoice_table`
--

INSERT INTO `invoice_table` (`id`, `id_invoice`, `id_product`, `quantity`, `price`, `summa`) VALUES
(5, 1, 2, 11, 18.55, 204.05),
(6, 1, 1, 12.5, 11, 137.5),
(7, 3, 1, 14.5, 10.38, 150.51),
(9, 3, 2, 14, 18.55, 259.7),
(10, 4, 1, 12, 10.38, 124.56),
(11, 4, 1, 2, 10.38, 20.76);

-- --------------------------------------------------------

--
-- Структура таблицы `klient`
--

CREATE TABLE IF NOT EXISTS `klient` (
  `idKlient` int(11) NOT NULL AUTO_INCREMENT,
  `iloscPunktow` int(11) DEFAULT NULL,
  `imie` varchar(20) DEFAULT NULL,
  `nazwisko` varchar(20) DEFAULT NULL,
  `kodPocztowy` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idKlient`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `klient`
--

INSERT INTO `klient` (`idKlient`, `iloscPunktow`, `imie`, `nazwisko`, `kodPocztowy`) VALUES
(1, 122, 'Ivan', 'Salo', '11124'),
(4, 11, 'Lady', 'Gaga', '100000');

-- --------------------------------------------------------

--
-- Структура таблицы `konta`
--

CREATE TABLE IF NOT EXISTS `konta` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `haslo` varchar(100) DEFAULT NULL,
  `uprawnienia` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `konta`
--

INSERT INTO `konta` (`id`, `login`, `haslo`, `uprawnienia`) VALUES
(1, 'admin', '123', 'Admin'),
(2, 'buba', '123', 'Sale'),
(3, 'vasya', '123', 'Manager'),
(4, 'vova', '123', 'Stock');

-- --------------------------------------------------------

--
-- Структура таблицы `magazynier`
--

CREATE TABLE IF NOT EXISTS `magazynier` (
  `idMagazynier` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idMagazynier`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `magazynier`
--

INSERT INTO `magazynier` (`idMagazynier`, `imie`, `nazwisko`) VALUES
(1, 'Tristan', 'Bull'),
(2, 'Adam', 'Waltz');

-- --------------------------------------------------------

--
-- Структура таблицы `pracownicy`
--

CREATE TABLE IF NOT EXISTS `pracownicy` (
  `id_pracownika` int(11) NOT NULL AUTO_INCREMENT,
  `id_konta` int(11) NOT NULL,
  `imie` varchar(30) NOT NULL,
  `nazwisko` varchar(30) NOT NULL,
  `PESEL` char(11) NOT NULL,
  `stanowisko` varchar(50) NOT NULL,
  `salary` float NOT NULL,
  `premia` float NOT NULL DEFAULT '0',
  `data_zatrudnienia` date NOT NULL,
  `data_zwolnienia` date DEFAULT NULL,
  `adres` varchar(120) NOT NULL,
  PRIMARY KEY (`id_pracownika`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=6 ;

--
-- Дамп данных таблицы `pracownicy`
--

INSERT INTO `pracownicy` (`id_pracownika`, `id_konta`, `imie`, `nazwisko`, `PESEL`, `stanowisko`, `salary`, `premia`, `data_zatrudnienia`, `data_zwolnienia`, `adres`) VALUES
(1, 1, 'Vasya', 'Kikiradziuk', '11qq11', 'Admin', 1800, 15, '2014-01-01', NULL, ''),
(2, 2, 'Second', 'User', '', 'Boss', 2500, 90, '2016-08-15', NULL, ''),
(3, 0, 'Buba', 'Smith', 'fdsfsdf', 'Seller', 800, 35, '2015-01-01', NULL, ''),
(5, 0, 'Mukaka', 'Balumba', '', 'Storekeeper', 980, 10, '2016-07-12', NULL, '');

-- --------------------------------------------------------

--
-- Структура таблицы `produkty`
--

CREATE TABLE IF NOT EXISTS `produkty` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(50) DEFAULT NULL,
  `punkty` int(11) DEFAULT '0',
  `dlugosc_gwarancji` int(11) DEFAULT NULL,
  `cena_sprzedazy` float DEFAULT NULL,
  `cena_zakupu` float DEFAULT NULL,
  `unit` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `produkty`
--

INSERT INTO `produkty` (`id`, `nazwa`, `punkty`, `dlugosc_gwarancji`, `cena_sprzedazy`, `cena_zakupu`, `unit`) VALUES
(1, 'Omar', 14, 12, 12.18, 10.38, 'pc'),
(2, 'Lobster', 12, 11, 32.28, 18.55, 'kg');

-- --------------------------------------------------------

--
-- Структура таблицы `salary`
--

CREATE TABLE IF NOT EXISTS `salary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `number` varchar(20) NOT NULL,
  `period` varchar(50) NOT NULL,
  `days` int(11) NOT NULL,
  `days_work` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `salary`
--

INSERT INTO `salary` (`id`, `date`, `number`, `period`, `days`, `days_work`) VALUES
(1, '2017-01-11', '1', 'December, 2016', 31, 25),
(2, '2017-01-16', '2', 'January, 2017', 31, 24);

-- --------------------------------------------------------

--
-- Структура таблицы `salary_table`
--

CREATE TABLE IF NOT EXISTS `salary_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_salary` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `salary_user` float NOT NULL,
  `work_days` int(11) NOT NULL,
  `salary` float NOT NULL,
  `premia_percent` float NOT NULL,
  `premia_summa` float NOT NULL,
  `summa` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_salary` (`id_salary`),
  KEY `id_user` (`id_user`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Дамп данных таблицы `salary_table`
--

INSERT INTO `salary_table` (`id`, `id_salary`, `id_user`, `salary_user`, `work_days`, `salary`, `premia_percent`, `premia_summa`, `summa`) VALUES
(1, 1, 1, 1800, 31, 2232, 15, 334.8, 2566.8),
(2, 1, 2, 1000, 31, 1240, 5, 62, 1302),
(3, 1, 5, 980, 25, 980, 0, 0, 980),
(4, 1, 3, 800, 22, 704, 35, 246.4, 950.4),
(5, 2, 1, 1800, 24, 1800, 15, 270, 2070),
(6, 2, 2, 2500, 18, 1875, 75, 1406.25, 3281.25),
(7, 2, 3, 800, 24, 800, 10, 80, 880),
(8, 2, 5, 980, 24, 980, 10, 98, 1078);

-- --------------------------------------------------------

--
-- Структура таблицы `sale`
--

CREATE TABLE IF NOT EXISTS `sale` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `number` varchar(20) NOT NULL,
  `id_seller` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_seller` (`id_seller`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `sale`
--

INSERT INTO `sale` (`id`, `date`, `number`, `id_seller`) VALUES
(1, '2017-01-10', '114', 1),
(2, '2017-01-10', '300', 2);

-- --------------------------------------------------------

--
-- Структура таблицы `sale_table`
--

CREATE TABLE IF NOT EXISTS `sale_table` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_sale` int(11) NOT NULL,
  `id_store` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `quantity` float NOT NULL,
  `price` float NOT NULL,
  `summa` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_sale` (`id_sale`),
  KEY `id_store` (`id_store`),
  KEY `id_product` (`id_product`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Дамп данных таблицы `sale_table`
--

INSERT INTO `sale_table` (`id`, `id_sale`, `id_store`, `id_product`, `quantity`, `price`, `summa`) VALUES
(1, 1, 1, 1, 10, 10.38, 103.8),
(3, 1, 1, 2, 14, 18.55, 259.7),
(4, 2, 1, 1, 10, 10.38, 103.8);

-- --------------------------------------------------------

--
-- Структура таблицы `sprzedawca`
--

CREATE TABLE IF NOT EXISTS `sprzedawca` (
  `idSprzedawca` int(11) NOT NULL AUTO_INCREMENT,
  `imie` varchar(30) DEFAULT NULL,
  `nazwisko` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`idSprzedawca`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Дамп данных таблицы `sprzedawca`
--

INSERT INTO `sprzedawca` (`idSprzedawca`, `imie`, `nazwisko`) VALUES
(1, 'Johh', 'Smith III'),
(2, 'Omar', 'Tupak');

--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `invoice`
--
ALTER TABLE `invoice`
  ADD CONSTRAINT `invoice_ibfk_1` FOREIGN KEY (`id_client`) REFERENCES `klient` (`idKlient`),
  ADD CONSTRAINT `invoice_ibfk_2` FOREIGN KEY (`id_store`) REFERENCES `magazynier` (`idMagazynier`);

--
-- Ограничения внешнего ключа таблицы `invoice_table`
--
ALTER TABLE `invoice_table`
  ADD CONSTRAINT `invoice_table_ibfk_1` FOREIGN KEY (`id_product`) REFERENCES `produkty` (`id`),
  ADD CONSTRAINT `invoice_table_ibfk_2` FOREIGN KEY (`id_invoice`) REFERENCES `invoice` (`id`);

--
-- Ограничения внешнего ключа таблицы `salary_table`
--
ALTER TABLE `salary_table`
  ADD CONSTRAINT `salary_table_ibfk_1` FOREIGN KEY (`id_salary`) REFERENCES `salary` (`id`),
  ADD CONSTRAINT `salary_table_ibfk_2` FOREIGN KEY (`id_user`) REFERENCES `pracownicy` (`id_pracownika`);

--
-- Ограничения внешнего ключа таблицы `sale`
--
ALTER TABLE `sale`
  ADD CONSTRAINT `sale_ibfk_1` FOREIGN KEY (`id_seller`) REFERENCES `sprzedawca` (`idSprzedawca`);

--
-- Ограничения внешнего ключа таблицы `sale_table`
--
ALTER TABLE `sale_table`
  ADD CONSTRAINT `sale_table_ibfk_1` FOREIGN KEY (`id_sale`) REFERENCES `sale` (`id`),
  ADD CONSTRAINT `sale_table_ibfk_2` FOREIGN KEY (`id_store`) REFERENCES `magazynier` (`idMagazynier`),
  ADD CONSTRAINT `sale_table_ibfk_3` FOREIGN KEY (`id_product`) REFERENCES `produkty` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
