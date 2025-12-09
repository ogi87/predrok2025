/*
SQLyog Community v13.3.1 (64 bit)
MySQL - 10.4.32-MariaDB : Database - predmeti_nastavnici
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`predmeti_nastavnici` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `predmeti_nastavnici`;

/*Table structure for table `angazovanje` */

DROP TABLE IF EXISTS `angazovanje`;

CREATE TABLE `angazovanje` (
  `nastavnikId` bigint(20) NOT NULL,
  `predmetId` bigint(20) NOT NULL,
  `oblikNastaveId` bigint(20) NOT NULL,
  PRIMARY KEY (`nastavnikId`,`predmetId`,`oblikNastaveId`),
  KEY `fk_predmet` (`predmetId`),
  KEY `fk_oblik` (`oblikNastaveId`),
  CONSTRAINT `fk_nastavnik` FOREIGN KEY (`nastavnikId`) REFERENCES `nastavnik` (`id`),
  CONSTRAINT `fk_oblik` FOREIGN KEY (`oblikNastaveId`) REFERENCES `oblik_nastave` (`id`),
  CONSTRAINT `fk_predmet` FOREIGN KEY (`predmetId`) REFERENCES `predmet` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `angazovanje` */

insert  into `angazovanje`(`nastavnikId`,`predmetId`,`oblikNastaveId`) values 
(1,101,1),
(1,101,2),
(1,102,2),
(1,102,3),
(1,103,2),
(1,106,3);

/*Table structure for table `nastavnik` */

DROP TABLE IF EXISTS `nastavnik`;

CREATE TABLE `nastavnik` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ime` varchar(50) NOT NULL,
  `prezime` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `sifra` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `nastavnik` */

insert  into `nastavnik`(`id`,`ime`,`prezime`,`email`,`sifra`) values 
(1,'Marko','Markovic','marko@fon.bg.ac.rs','sifra123');

/*Table structure for table `oblik_nastave` */

DROP TABLE IF EXISTS `oblik_nastave`;

CREATE TABLE `oblik_nastave` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `oblik_nastave` */

insert  into `oblik_nastave`(`id`,`naziv`) values 
(1,'Predavanja'),
(2,'Vežbe'),
(3,'Laboratorijske vežbe');

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `naziv` varchar(100) NOT NULL,
  `espb` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `predmet` */

insert  into `predmet`(`id`,`naziv`,`espb`) values 
(101,'Softversko inženjerstvo',6),
(102,'Baze podataka',7),
(103,'Programiranje1',5),
(104,'Matematika 2',6),
(105,'RMT',4),
(106,'OI1',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
