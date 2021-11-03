CREATE DATABASE  IF NOT EXISTS `housing` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `housing`;
-- MySQL dump 10.13  Distrib 8.0.20, for Win64 (x86_64)
--
-- Host: 10.0.0.8    Database: housing
-- ------------------------------------------------------
-- Server version	8.0.20

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `com_server`
--

DROP TABLE IF EXISTS `com_server`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `com_server` (
  `id_com_server` int NOT NULL AUTO_INCREMENT,
  `name_server` varchar(128) NOT NULL,
  `ip_server` varchar(25) NOT NULL,
  `port_server` varchar(10) NOT NULL,
  `id_manag_company` int DEFAULT NULL,
  `id_type_object` int DEFAULT NULL,
  `lock_flag` int DEFAULT NULL,
  PRIMARY KEY (`id_com_server`),
  KEY `fk_com_server_manag_company1_idx` (`id_manag_company`),
  KEY `fk_com_server_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_com_server_manag_company1` FOREIGN KEY (`id_manag_company`) REFERENCES `manag_company` (`id_manag_company`),
  CONSTRAINT `fk_com_server_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_server`
--

LOCK TABLES `com_server` WRITE;
/*!40000 ALTER TABLE `com_server` DISABLE KEYS */;
INSERT INTO `com_server` VALUES (22,'Сервер связи №1','213.189.220.200','7776',1,2,18);
/*!40000 ALTER TABLE `com_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counts`
--

DROP TABLE IF EXISTS `counts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counts` (
  `id_counts` int NOT NULL AUTO_INCREMENT,
  `id_uspd_dev` int NOT NULL,
  `serial_num` varchar(128) NOT NULL,
  `date_plug` date DEFAULT NULL,
  `date_expire` date DEFAULT NULL,
  `name_count` varchar(128) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id_type_object` int DEFAULT NULL,
  `lock_flag` int DEFAULT NULL,
  `type_count` int DEFAULT NULL,
  `num_ch` int DEFAULT '1',
  PRIMARY KEY (`id_counts`),
  KEY `fk_counts_uspd_dev1_idx` (`id_uspd_dev`),
  KEY `fk_counts_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_counts_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`),
  CONSTRAINT `fk_counts_uspd_dev1` FOREIGN KEY (`id_uspd_dev`) REFERENCES `uspd_dev` (`id_uspd_dev`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counts`
--

LOCK TABLES `counts` WRITE;
/*!40000 ALTER TABLE `counts` DISABLE KEYS */;
INSERT INTO `counts` VALUES (41,18,'12345-098777','2021-04-29','2021-09-30','ХВ','Екатеринбург, Щербакова 39-1',4,10,1,1),(43,18,'12345-09878','2021-04-29','2023-02-17','ГВ','Екатеринбург, Щербакова 39-1',4,8,2,2),(46,19,'12345-09879','2021-04-29','2022-04-17','ХВ','Екатеринбург, Щербакова 39-2',4,5,1,1),(47,19,'12345-09880','2021-04-29','2022-04-17','ГВ','Екатеринбург, Щербакова 39-2',4,3,2,2),(49,20,'12345-09876','2021-02-17','2021-02-17','ХВ','Екатеринбург, Щербакова 39-3',4,2,1,1),(50,20,'12345-09876','2021-02-17','2021-02-17','ГВ','Екатеринбург, Щербакова 39-3',4,3,2,2),(62,22,'104104-1','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-4',4,1,1,1),(63,22,'104104','2021-10-29','2021-10-29','ГВ','Екатеринбург, Щербакова 39-4',4,0,2,2),(64,23,'105105','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-5',4,0,1,1),(65,23,'105105-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-5',4,0,2,2),(66,24,'106106','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-6',4,0,1,1),(67,24,'106106-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-6',4,0,2,2),(68,25,'107107','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-7',4,0,1,1),(69,25,'107107-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-7',4,0,2,2),(70,26,'108108','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-8',4,0,1,1),(71,26,'108108-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-8',4,0,2,2),(72,27,'109109','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-9',4,0,1,1),(73,27,'109109-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-9',4,0,2,2),(74,28,'110110','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-10',4,0,1,1),(75,28,'110110-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-10',4,0,2,2),(78,30,'112112','2021-10-29','2024-10-29','ХВ','Екатеринбург, Щербакова 39-12',4,0,1,1),(79,30,'112112-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Щербакова 39-12',4,0,2,2),(80,31,'14А116','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-116',4,0,1,1),(81,31,'14А116-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-116',4,0,2,2),(82,32,'14А117','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-117',4,0,1,1),(83,32,'14А117-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-117',4,0,2,2),(84,33,'14А118','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-118',4,0,1,1),(85,33,'14А118-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-118',4,0,2,2),(86,34,'14А119','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-119',4,0,1,1),(87,34,'14А119-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-119',4,0,2,2),(88,35,'14А120','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-120',4,0,1,1),(89,35,'14А120-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-120',4,0,2,2),(90,36,'14А121','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-121',4,0,1,1),(91,36,'14А121-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-121',4,0,2,2),(92,37,'14А122','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-122',4,0,1,1),(93,37,'14А122-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-122',4,0,2,2),(94,38,'14А123','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-123',4,0,1,1),(95,38,'14А-123-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-123',4,0,2,2),(96,39,'14А124','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-124',4,0,1,1),(97,39,'14А124-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-124',4,0,2,2),(98,40,'14А125','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-125',4,0,1,1),(99,40,'14А125-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-125',4,0,2,2),(100,41,'14А126','2021-10-29','2025-10-29','ХВ','Екатеринбург, Вилонова 14А-126',4,0,1,1),(101,41,'14А126-1','2021-10-29','2025-10-29','ГВ','Екатеринбург, Вилонова 14А-126',4,0,2,2),(102,42,'141','2021-10-29','2024-10-29','ХВ','Екатеринбург, Вилонова 14-1',4,0,1,1),(103,42,'141-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Вилонова 14-1',4,0,2,2),(104,43,'142','2021-10-29','2024-10-29','ХВ','Екатеринбург, Вилонова 14-2',4,0,1,1),(105,43,'142-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Вилонова 14-2',4,0,2,2),(106,44,'1413','2021-10-29','2024-10-29','ХВ','Екатеринбург, Вилонова 14-13',4,0,1,1),(107,44,'1413-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Вилонова 14-13',4,0,2,2),(108,45,'1414','2021-10-29','2024-10-29','ХВ','Екатеринбург, Вилонова 14-14',4,0,1,1),(109,45,'1414-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Вилонова 14-14',4,0,2,2),(110,46,'1415','2021-10-29','2024-10-29','ХВ','Екатеринбург, Вилонова 14-15',4,1,1,1),(111,46,'1415-1','2021-10-29','2024-10-29','ГВ','Екатеринбург, Вилонова 14-15',4,0,2,2),(112,47,'10С-001','2021-11-01','2023-11-01','ХВ','Екатеринбург, Вилонова 14-1',4,1,1,1),(113,47,'','2021-11-01','2023-11-01','ГВ','Екатеринбург, Вилонова 14-1',4,1,2,2),(114,48,'10С-001','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 10-1',4,0,1,1),(115,48,'10С-001-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 10-1',4,0,0,2),(116,49,'10С-002','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 10-2',4,0,1,1),(117,49,'10С-002-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 10-2',4,0,2,2),(118,50,'10С-003','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 10-3',4,0,1,1),(119,50,'10С-003-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 10-3',4,0,2,2),(120,51,'10С-004','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 10-4',4,0,1,1),(121,51,'10С-004-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 10-4',4,0,2,2),(122,52,'10С-005','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 10-5',4,0,1,1),(123,52,'10С-005-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 10-5',4,0,2,2),(124,53,'12С-001','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 12-1',4,0,1,1),(125,53,'12С-001-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 12-1',4,0,2,2),(126,54,'12С-002','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 12-2',4,1,1,1),(127,54,'12С-002-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 12-2',4,0,2,2),(128,55,'12С-004','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 12-4',4,0,1,1),(129,55,'12С-004-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 12-4',4,0,2,2),(130,56,'12С-005','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 12-5',4,0,1,1),(131,56,'12С-005-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 12-5',4,0,2,2),(132,57,'15С-003','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 15-3',4,0,1,1),(133,57,'15С-003-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 15-3',4,0,2,2),(134,58,'15С-004','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 15-4',4,0,1,1),(135,58,'15С-004-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 15-4',4,2,2,2),(136,59,'15С-005','2021-11-01','2023-11-01','ХВ','Екатеринбург, Строителей 15-5',4,0,1,1),(137,59,'15С-005-1','2021-11-01','2023-11-01','ГВ','Екатеринбург, Строителей 15-5',4,0,2,2);
/*!40000 ALTER TABLE `counts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_counts`
--

DROP TABLE IF EXISTS `foto_counts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_counts` (
  `id_foto_counts` int NOT NULL AUTO_INCREMENT,
  `name_file` varchar(128) DEFAULT NULL,
  `image_count` mediumblob,
  `lock_flag` int DEFAULT NULL,
  `id_counts` int NOT NULL,
  PRIMARY KEY (`id_foto_counts`),
  KEY `FKeuigxu22h3e9cwquxdm04b3vb` (`id_counts`),
  CONSTRAINT `FKeuigxu22h3e9cwquxdm04b3vb` FOREIGN KEY (`id_counts`) REFERENCES `counts` (`id_counts`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `foto_counts`
--

LOCK TABLES `foto_counts` WRITE;
/*!40000 ALTER TABLE `foto_counts` DISABLE KEYS */;
/*!40000 ALTER TABLE `foto_counts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (19);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `house`
--

DROP TABLE IF EXISTS `house`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `house` (
  `id_house` int NOT NULL AUTO_INCREMENT,
  `name_house` varchar(145) COLLATE utf8_bin DEFAULT NULL COMMENT 'Характеристика дома',
  `address` varchar(145) COLLATE utf8_bin NOT NULL,
  `id_type_object` int NOT NULL DEFAULT '8',
  `id_street` int NOT NULL DEFAULT '0',
  `id_link_object` int DEFAULT '0',
  PRIMARY KEY (`id_house`),
  UNIQUE KEY `address_UNIQUE` (`address`),
  KEY `fk_street_house_idx` (`id_street`),
  KEY `fk_house_type_obj_idx` (`id_type_object`),
  CONSTRAINT `fk_house_street` FOREIGN KEY (`id_street`) REFERENCES `street` (`id_street`),
  CONSTRAINT `fk_house_type_obj` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `house`
--

LOCK TABLES `house` WRITE;
/*!40000 ALTER TABLE `house` DISABLE KEYS */;
INSERT INTO `house` VALUES (1,'Многоэтажный дом, кирпич, 14 этажей','Щербакова 39',8,1,96),(8,'Жилой дом, монолит, 8-12 этажей ','Вилонова 14А',8,4,111),(13,'Дом № 14, панель, 10 этажей','Вилонова 14',8,4,111),(14,'Дом № 10, панель, 9 этажей','Строителей 10',8,25,172),(15,'Дом № 15, панель, 9 этажей','Строителей 15',8,25,172),(16,'Дом № 20, панель, 12 этажей','Строителей 12',8,25,172);
/*!40000 ALTER TABLE `house` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_object`
--

DROP TABLE IF EXISTS `link_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_object` (
  `id_link_object` int NOT NULL AUTO_INCREMENT,
  `id_parent` int NOT NULL,
  `id_type_object` int NOT NULL,
  `id_object` int DEFAULT NULL,
  `lock_flag` int DEFAULT NULL,
  PRIMARY KEY (`id_link_object`),
  KEY `fk_link_object_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_link_object_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=209 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_object`
--

LOCK TABLES `link_object` WRITE;
/*!40000 ALTER TABLE `link_object` DISABLE KEYS */;
INSERT INTO `link_object` VALUES (1,0,1,1,1),(63,1,2,22,0),(64,63,3,18,0),(65,64,4,41,0),(69,64,4,43,0),(73,63,3,19,0),(74,63,3,20,0),(77,73,4,46,0),(78,73,4,47,0),(80,74,4,49,0),(81,74,4,50,0),(95,63,3,22,0),(96,63,3,23,0),(97,63,3,24,0),(98,63,3,25,0),(99,63,3,26,0),(100,63,3,27,0),(101,63,3,28,0),(103,63,3,30,0),(104,95,4,62,0),(105,95,4,63,0),(106,96,4,64,0),(107,96,4,65,0),(108,97,4,66,0),(109,97,4,67,0),(110,98,4,68,0),(111,98,4,69,0),(112,99,4,70,0),(113,99,4,71,0),(114,100,4,72,0),(115,100,4,73,0),(116,101,4,74,0),(117,101,4,75,0),(120,103,4,78,0),(121,103,4,79,0),(122,63,3,31,0),(123,63,3,32,0),(124,63,3,33,0),(125,63,3,34,0),(126,63,3,35,0),(127,63,3,36,0),(128,63,3,37,0),(129,63,3,38,0),(130,63,3,39,0),(131,63,3,40,0),(132,63,3,41,0),(133,122,4,80,0),(134,122,4,81,0),(135,123,4,82,0),(136,123,4,83,0),(137,124,4,84,0),(138,124,4,85,0),(139,125,4,86,0),(140,125,4,87,0),(141,126,4,88,0),(142,126,4,89,0),(143,127,4,90,0),(144,127,4,91,0),(145,128,4,92,0),(146,128,4,93,0),(147,129,4,94,0),(148,129,4,95,0),(149,130,4,96,0),(150,130,4,97,0),(151,131,4,98,0),(152,131,4,99,0),(153,132,4,100,0),(154,132,4,101,0),(155,63,3,42,0),(156,63,3,43,0),(157,63,3,44,0),(158,63,3,45,0),(159,63,3,46,0),(160,63,3,47,0),(161,155,4,102,0),(162,155,4,103,0),(163,156,4,104,0),(164,156,4,105,0),(165,157,4,106,0),(166,157,4,107,0),(167,158,4,108,0),(168,158,4,109,0),(169,159,4,110,0),(170,159,4,111,0),(171,160,4,112,0),(172,160,4,113,0),(173,63,3,48,0),(174,63,3,49,0),(175,63,3,50,0),(176,63,3,51,0),(177,63,3,52,0),(178,173,4,114,0),(179,173,4,115,0),(180,174,4,116,0),(181,174,4,117,0),(182,175,4,118,0),(183,175,4,119,0),(184,176,4,120,0),(185,176,4,121,0),(186,177,4,122,0),(187,177,4,123,0),(188,63,3,53,0),(189,63,3,54,0),(190,63,3,55,0),(191,63,3,56,0),(192,188,4,124,0),(193,188,4,125,0),(194,189,4,126,0),(195,189,4,127,0),(196,190,4,128,0),(197,190,4,129,0),(198,191,4,130,0),(199,191,4,131,0),(200,63,3,57,0),(201,63,3,58,0),(202,63,3,59,0),(203,200,4,132,0),(204,200,4,133,0),(205,201,4,134,0),(206,201,4,135,0),(207,202,4,136,0),(208,202,4,137,0);
/*!40000 ALTER TABLE `link_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `link_object_uk`
--

DROP TABLE IF EXISTS `link_object_uk`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_object_uk` (
  `id_link_object` int NOT NULL AUTO_INCREMENT,
  `id_parent` int NOT NULL,
  `id_type_object` int NOT NULL,
  `id_object` int DEFAULT NULL,
  `lock_flag` int DEFAULT NULL,
  PRIMARY KEY (`id_link_object`),
  KEY `fk_link_object_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_link_object_uk_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=249 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_object_uk`
--

LOCK TABLES `link_object_uk` WRITE;
/*!40000 ALTER TABLE `link_object_uk` DISABLE KEYS */;
INSERT INTO `link_object_uk` VALUES (95,0,1,1,0),(96,95,9,1,0),(97,96,8,1,0),(98,97,10,1,0),(103,97,10,2,0),(107,97,10,3,0),(111,95,9,4,NULL),(122,111,8,8,NULL),(130,97,10,6,NULL),(131,97,10,7,NULL),(139,122,10,9,NULL),(143,122,10,10,NULL),(144,122,10,11,NULL),(145,122,10,12,NULL),(146,122,10,13,NULL),(147,122,10,14,NULL),(148,122,10,15,NULL),(149,122,10,16,NULL),(150,122,10,17,NULL),(151,122,10,18,NULL),(152,122,10,19,NULL),(153,97,10,20,NULL),(154,97,10,21,NULL),(155,97,10,22,NULL),(156,97,10,23,NULL),(157,97,10,24,NULL),(159,97,10,26,NULL),(165,111,8,13,NULL),(166,165,10,32,NULL),(167,165,10,33,NULL),(168,165,10,34,NULL),(169,165,10,35,NULL),(170,165,10,36,NULL),(171,165,10,37,NULL),(172,95,9,25,NULL),(173,172,8,14,NULL),(174,172,8,15,NULL),(175,172,8,16,NULL),(176,173,10,38,NULL),(177,173,10,39,NULL),(178,173,10,40,NULL),(179,174,10,41,NULL),(180,174,10,42,NULL),(181,174,10,43,NULL),(182,175,10,44,NULL),(183,175,10,45,NULL),(184,175,10,46,NULL),(185,175,10,47,NULL),(186,98,11,18,NULL),(187,98,11,19,NULL),(189,103,11,21,NULL),(190,103,11,22,NULL),(192,107,11,24,NULL),(193,107,11,25,NULL),(195,130,11,27,NULL),(196,130,11,28,NULL),(198,131,11,30,NULL),(199,131,11,31,NULL),(201,153,11,33,NULL),(202,153,11,34,NULL),(204,154,11,36,NULL),(205,154,11,37,NULL),(207,155,11,39,NULL),(208,155,11,40,NULL),(210,156,11,42,NULL),(211,156,11,43,NULL),(213,157,11,45,NULL),(214,157,11,46,NULL),(216,159,11,48,NULL),(217,159,11,49,NULL),(219,139,11,51,NULL),(220,143,11,52,NULL),(221,144,11,53,NULL),(222,145,11,54,NULL),(223,146,11,55,NULL),(224,147,11,56,NULL),(225,148,11,57,NULL),(226,149,11,58,NULL),(227,150,11,59,NULL),(228,151,11,60,NULL),(229,152,11,61,NULL),(230,166,11,62,NULL),(231,167,11,63,NULL),(232,168,11,64,NULL),(233,169,11,65,NULL),(234,170,11,66,NULL),(235,171,11,67,NULL),(236,176,11,68,NULL),(237,177,11,69,NULL),(238,178,11,70,NULL),(239,179,11,71,NULL),(240,180,11,72,NULL),(241,181,11,73,NULL),(242,182,11,74,NULL),(243,183,11,75,NULL),(244,184,11,76,NULL),(245,185,11,77,NULL);
/*!40000 ALTER TABLE `link_object_uk` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manag_company`
--

DROP TABLE IF EXISTS `manag_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manag_company` (
  `id_manag_company` int NOT NULL AUTO_INCREMENT,
  `name_company` varchar(255) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address_1` varchar(255) DEFAULT NULL,
  `address_2` varchar(255) DEFAULT NULL,
  `id_type_object` int NOT NULL,
  `lock_flag` int DEFAULT NULL,
  PRIMARY KEY (`id_manag_company`),
  KEY `fk_manag_company_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_manag_company_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manag_company`
--

LOCK TABLES `manag_company` WRITE;
/*!40000 ALTER TABLE `manag_company` DISABLE KEYS */;
INSERT INTO `manag_company` VALUES (1,'УК \"Наш дом 39\"','456-56-892','Щербакова 39','2 подъезд',1,17);
/*!40000 ALTER TABLE `manag_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `person_acnt`
--

DROP TABLE IF EXISTS `person_acnt`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `person_acnt` (
  `id_person_acnt` int NOT NULL AUTO_INCREMENT,
  `num_acnt` varchar(145) COLLATE utf8_bin NOT NULL COMMENT 'Номер лицевого счета',
  `id_type_object` int NOT NULL DEFAULT '11',
  `id_room` int NOT NULL DEFAULT '0',
  `id_link_object` int DEFAULT '0',
  PRIMARY KEY (`id_person_acnt`),
  KEY `fk_personacnt_room_idx` (`id_room`),
  KEY `fk_person_acnt_type_obj_idx` (`id_type_object`),
  KEY `fk_personacnt_type_obj_idx` (`id_type_object`),
  CONSTRAINT `fk_personacnt_room` FOREIGN KEY (`id_room`) REFERENCES `room` (`id_room`),
  CONSTRAINT `fk_personacnt_type_obj` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Лицевые счета';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `person_acnt`
--

LOCK TABLES `person_acnt` WRITE;
/*!40000 ALTER TABLE `person_acnt` DISABLE KEYS */;
INSERT INTO `person_acnt` VALUES (18,'0-390001',11,1,98),(19,'1-390001',11,1,98),(21,'0-390002',11,2,103),(22,'1-390002',11,2,103),(24,'0-390003',11,3,107),(25,'1-390003',11,3,107),(27,'0-390004',11,6,130),(28,'1-390004',11,6,130),(30,'0-390005',11,7,131),(31,'1-390005',11,7,131),(33,'0-390006',11,20,153),(34,'1-390006',11,20,153),(36,'0-390007',11,21,154),(37,'1-390007',11,21,154),(39,'0-390008',11,22,155),(40,'1-390008',11,22,155),(42,'0-390009',11,23,156),(43,'1-390009',11,23,156),(45,'0-390010',11,24,157),(46,'1-390010',11,24,157),(48,'0-390012',11,26,159),(49,'1-390012',11,26,159),(51,'140116',11,9,139),(52,'140117',11,10,143),(53,'140118',11,11,144),(54,'140119',11,12,145),(55,'140120',11,13,146),(56,'140121',11,14,147),(57,'140122',11,15,148),(58,'140123',11,16,149),(59,'140124',11,17,150),(60,'140125',11,18,151),(61,'140126',11,19,152),(62,'14А001',11,32,166),(63,'14А002',11,33,167),(64,'14А013',11,34,168),(65,'14А014',11,35,169),(66,'14А015',11,36,170),(67,'14А016',11,37,171),(68,'10С001',11,38,176),(69,'10С002',11,39,177),(70,'10С003',11,40,178),(71,'15С003',11,41,179),(72,'15С004',11,42,180),(73,'15С005',11,43,181),(74,'20С001',11,44,182),(75,'20С002',11,45,183),(76,'20С004',11,46,184),(77,'20С005',11,47,185);
/*!40000 ALTER TABLE `person_acnt` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` int NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room` (
  `id_room` int NOT NULL AUTO_INCREMENT,
  `number_room` varchar(45) COLLATE utf8_bin NOT NULL DEFAULT '0',
  `name_room` varchar(145) COLLATE utf8_bin DEFAULT NULL,
  `id_house` int NOT NULL DEFAULT '0',
  `id_type_object` int NOT NULL DEFAULT '10',
  `id_uspd_dev` int NOT NULL DEFAULT '0',
  `id_link_object` int DEFAULT '0',
  `number_uspd` varchar(45) COLLATE utf8_bin DEFAULT '0',
  PRIMARY KEY (`id_room`),
  KEY `fk_room_house_idx` (`id_house`),
  KEY `fk_room_type_obj_idx` (`id_type_object`),
  KEY `fk_room_uspd_dev_idx` (`id_uspd_dev`),
  CONSTRAINT `fk_room_house` FOREIGN KEY (`id_house`) REFERENCES `house` (`id_house`),
  CONSTRAINT `fk_room_type_obj` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`),
  CONSTRAINT `fk_room_uspd_dev` FOREIGN KEY (`id_uspd_dev`) REFERENCES `uspd_dev` (`id_uspd_dev`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'1','Квартира',1,10,18,97,'101'),(2,'2','Квартира ',1,10,19,97,'102'),(3,'3','Квартира ',1,10,20,97,'103'),(6,'4','Квартира №',1,10,22,97,'104'),(7,'5','Квартира №',1,10,23,97,'105'),(9,'116','Квартира',8,10,31,122,'113'),(10,'117','Квартира № ',8,10,32,122,'114'),(11,'118','Квартира № ',8,10,33,122,'115'),(12,'119','Квартира № ',8,10,34,122,'116'),(13,'120','Квартира № ',8,10,35,122,'117'),(14,'121','Квартира № ',8,10,36,122,'118'),(15,'122','Квартира № ',8,10,37,122,'119'),(16,'123','Квартира № ',8,10,38,122,'120'),(17,'124','Квартира № ',8,10,39,122,'121'),(18,'125','Квартира № ',8,10,40,122,'122'),(19,'126','Квартира № ',8,10,41,122,'123'),(20,'6','Квартира № ',1,10,24,97,'106'),(21,'7','Квартира № ',1,10,25,97,'107'),(22,'8','Квартира ',1,10,26,97,'108'),(23,'9','Квартира № ',1,10,27,97,'109'),(24,'10','Квартира № ',1,10,28,97,'110'),(26,'12','Квартира № ',1,10,30,97,'112'),(32,'1','Квартира №',13,10,42,165,'124'),(33,'2','Квартира № ',13,10,43,165,'125'),(34,'13','Квартира № ',13,10,44,165,'126'),(35,'14','Квартира № ',13,10,45,165,'127'),(36,'15','Квартира № ',13,10,46,165,'128'),(37,'16','Квартира № ',13,10,47,165,'129'),(38,'1','Квартира № ',14,10,47,173,'129'),(39,'2','Квартира № ',14,10,18,173,'3'),(40,'3','Квартира № ',14,10,19,173,'4'),(41,'3','Квартира № ',15,10,57,174,'139'),(42,'4','Квартира № ',15,10,58,174,'140'),(43,'5','Квартира № ',15,10,59,174,'141'),(44,'1','Квартира № ',16,10,53,175,'135'),(45,'2','Квартира № ',16,10,54,175,'136'),(46,'4','Квартира № ',16,10,55,175,'137'),(47,'5','Квартира № ',16,10,56,175,'138');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `street`
--

DROP TABLE IF EXISTS `street`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `street` (
  `id_street` int NOT NULL AUTO_INCREMENT,
  `name_street` varchar(145) NOT NULL,
  `district` varchar(145) DEFAULT NULL,
  `id_type_object` int NOT NULL DEFAULT '9',
  `id_manag_company` int NOT NULL DEFAULT '0',
  `id_link_object` int DEFAULT '0',
  PRIMARY KEY (`id_street`),
  KEY `fk_company_street_idx` (`id_manag_company`),
  KEY `fk_street_type_obj_idx` (`id_type_object`),
  CONSTRAINT `fk_street_company` FOREIGN KEY (`id_manag_company`) REFERENCES `manag_company` (`id_manag_company`),
  CONSTRAINT `fk_street_type_obj` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `street`
--

LOCK TABLES `street` WRITE;
/*!40000 ALTER TABLE `street` DISABLE KEYS */;
INSERT INTO `street` VALUES (1,'ул. Щербакова','Чкаловский район',9,1,95),(4,'ул. Вилонова','Кировский р-он',9,1,95),(25,'ул. Строителей','Кировский р-он',9,1,95);
/*!40000 ALTER TABLE `street` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_object`
--

DROP TABLE IF EXISTS `type_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_object` (
  `id_type_object` int NOT NULL AUTO_INCREMENT,
  `name_type` varchar(255) NOT NULL,
  `lock_flag` int DEFAULT '0',
  PRIMARY KEY (`id_type_object`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_object`
--

LOCK TABLES `type_object` WRITE;
/*!40000 ALTER TABLE `type_object` DISABLE KEYS */;
INSERT INTO `type_object` VALUES (1,'Организация ЖКХ',0),(2,'Сервер связи',0),(3,'УСПД',0),(4,'Счетчик',0),(8,'Жилой дом',0),(9,'Улица',0),(10,'Помещение',0),(11,'Лицевой счет',0);
/*!40000 ALTER TABLE `type_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_uspd`
--

DROP TABLE IF EXISTS `type_uspd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_uspd` (
  `id_type_uspd` int NOT NULL AUTO_INCREMENT,
  `name_type` varchar(128) NOT NULL,
  `lock_flag` int DEFAULT NULL,
  `serial_num` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id_type_uspd`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_uspd`
--

LOCK TABLES `type_uspd` WRITE;
/*!40000 ALTER TABLE `type_uspd` DISABLE KEYS */;
INSERT INTO `type_uspd` VALUES (1,'УСПД версии-1',0,NULL),(2,'УСПД версия-2',0,NULL),(3,'USPD Тип - 3',0,NULL),(4,'УСПД Тип - 4',0,NULL),(5,'УСПД Тип - 5',0,NULL);
/*!40000 ALTER TABLE `type_uspd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` int NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`),
  CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1),(4,1),(5,1),(7,1),(8,1),(11,1),(16,1),(17,1),(18,1),(6,2),(15,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '','ch@yahoo.com','Валерий','Val1111','$2a$10$hYD7Jnw1z1wqgzP7XwsUgegqxYpe.vFnDuMaNoCW/r4R.g5emUXz6','admin'),(4,_binary '','as@hjhj.com','weewew','werwe','$2a$10$gKRRfZ/EWDTaa5Fmij4cp.Fs9QxE5PsdHZUufnKcDKhpG.jrke3T6','user8'),(5,_binary '','ch@yahoo.com','Алибабаевич','Василий','$2a$10$sZs2wqVgOjtUE1vKC91SIetCUFawpJ5FkUTe33PwP4ibu2Dy3cs4K','admin1'),(6,_binary '','cv@nmnm.com','user11','user11,','$2a$10$Afx5V4Nsy8CIHhyfsQgs2.4GqrYM1g5374knV7qTDiKq5VXuVCuEy','user11'),(7,_binary '','as@cvb.com','mmm','misha','$2a$10$mGwOL03/XcfzKnOEcdd/Pe/3P3pQvh5NyDpxyxxk8qQDGOI/38CxC','misha'),(8,_binary '','fack@ced.com','Юрий','Сидоров','$2a$10$KW3WXx9OE82tEle7kLNyYO1zs59/oUibeMGNQIBVe3JgJLoRC8aIq','сидоров'),(11,_binary '','vcvc@mmm.nb','Петров','Александр Петрович','$2a$10$hokf6Ea1awSlWZAanPphg.fyZtZQ.0ryUUkU5h4Zdsinmku1ubI9S','петров'),(15,_binary '','ch@mail.it','Валерий','Валерий','$2a$10$0lNIYUG1ycQ8VMBceBk6EeIyiAJ/tUC53uyGYtpUeRWQQ5Qorx.dm','val1111'),(16,_binary '','dfg@nm.com','Cho','Vlad','$2a$10$XXocR/1M0wXjQhrg.yLSZOo47aXuHeXVlIgYrrfvTIqMfA/fV6xay','vladimir'),(17,_binary '','baiden@Jo.com','Baiden','Jo','$2a$10$KmiFGQA4b68gLXgZi.u1ZeHT6BD/btEkW4afVdusJz9h2keTSYGzG','baiden'),(18,_binary '','xxx@vb.com','Петр','Петров','$2a$10$VkXZ8LDZG50WIhEzbOjxSuQcUk8snwqjAUa5.0T5iphcsfXRc1VwW','сергей');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uspd_dev`
--

DROP TABLE IF EXISTS `uspd_dev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uspd_dev` (
  `id_uspd_dev` int NOT NULL AUTO_INCREMENT,
  `name_uspd_dev` varchar(128) NOT NULL,
  `id_type_uspd_dev` int NOT NULL,
  `address_loc` varchar(255) DEFAULT '0.0.0.0',
  `id_counts` int DEFAULT NULL COMMENT 'Not used',
  `id_config_uspd` int DEFAULT NULL,
  `id_com_server` int DEFAULT NULL,
  `id_type_object` int DEFAULT NULL,
  `lock_flag` int DEFAULT NULL,
  `num_uspd_dev` varchar(28) DEFAULT NULL,
  PRIMARY KEY (`id_uspd_dev`),
  KEY `fk_uspd_dev_com_server_idx` (`id_com_server`),
  KEY `fk_uspd_dev_type_uspd1_idx` (`id_type_uspd_dev`),
  KEY `fk_uspd_dev_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_uspd_dev_com_server` FOREIGN KEY (`id_com_server`) REFERENCES `com_server` (`id_com_server`),
  CONSTRAINT `fk_uspd_dev_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`),
  CONSTRAINT `fk_uspd_dev_type_uspd1` FOREIGN KEY (`id_type_uspd_dev`) REFERENCES `type_uspd` (`id_type_uspd`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uspd_dev`
--

LOCK TABLES `uspd_dev` WRITE;
/*!40000 ALTER TABLE `uspd_dev` DISABLE KEYS */;
INSERT INTO `uspd_dev` VALUES (18,'УСПД №101',4,'Екатеринбург, Щербакова 39-1',0,0,22,3,9,'101'),(19,'УСПД №102',4,'Екатеринбург, Щербакова 39-2',0,0,22,3,2,'102'),(20,'УСПД №103',4,'Екатеринбург, Щербакова 39-3',0,0,22,3,3,'103'),(22,'УСПД№ 104',4,'Екатеринбург, Щербакова 39-3',0,0,22,3,0,'104'),(23,'УСПД№ 105',4,'Екатеринбург, Щербакова 39-5',0,0,22,3,0,'105'),(24,'УСПД№ 106',4,'Екатеринбург, Щербакова 39-6',0,0,22,3,0,'106'),(25,'УСПД№ 107',4,'Екатеринбург, Щербакова 39-7',0,0,22,3,0,'107'),(26,'УСПД№ 108',4,'Екатеринбург, Щербакова 39-8',0,0,22,3,0,'108'),(27,'УCПД№ 109',4,'Екатеринбург, Щербакова 39-9',0,0,22,3,1,'109'),(28,'УСПД№ 110',4,'Екатеринбург, Щербакова 39-10',0,0,22,3,0,'110'),(30,'УСПД№ 112',4,'Екатеринбург, Щербакова 39-12',0,0,22,3,0,'112'),(31,'УСПД№ 113',3,'Екатеринбург, Вилонова 14А-116',0,0,22,3,1,'113'),(32,'УСПД№ 114',3,'Екатеринбург, Вилонова 14А-117',0,0,22,3,0,'114'),(33,'УСПД№ 115',3,'Екатеринбург, Вилонова 14А-118',0,0,22,3,0,'115'),(34,'УСПД№116',3,'Екатеринбург, Вилонова 14А-119',0,0,22,3,0,'116'),(35,'УСПД№117',3,'Екатеринбург, Вилонова 14А-120',0,0,22,3,0,'117'),(36,'УСПД№ 118',3,'Екатеринбург, Вилонова 14А-121',0,0,22,3,0,'118'),(37,'УСПД№ 119',3,'Екатеринбург, Вилонова 14А-122',0,0,22,3,0,'119'),(38,'УСПД№ 120',3,'Екатеринбург, Вилонова 14А-123',0,0,22,3,0,'120'),(39,'УСПД№ 121',3,'Екатеринбург, Вилонова 14А-124',0,0,22,3,0,'121'),(40,'УСПД№ 122',3,'Екатеринбург, Вилонова 14А-125',0,0,22,3,0,'122'),(41,'УСПД№ 123',3,'Екатеринбург, Вилонова 14А-126',0,0,22,3,0,'123'),(42,'УСПД№ 124',5,'Екатеринбург, Вилонова 14-1',0,0,22,3,0,'124'),(43,'УСПД№ 125',5,'Екатеринбург, Вилонова 14-2',0,0,22,3,0,'125'),(44,'УСПД№ 126',5,'Екатеринбург, Вилонова 14-13',0,0,22,3,1,'126'),(45,'УСДП№ 127',5,'Екатеринбург, Вилонова 14-14',0,0,22,3,0,'127'),(46,'УСПД№ 128',5,'Екатеринбург, Вилонова 14-15',0,0,22,3,0,'128'),(47,'УСПД№ 129',5,'Екатеринбург, Вилонова 14-16',0,0,22,3,0,'129'),(48,'УСПД№ 130',2,'Екатеринбург, Строителей 10-1',0,0,22,3,0,'130'),(49,'УСПД№ 131',2,'Екатеринбург, Строителей 10-2',0,0,22,3,0,'131'),(50,'УСПД№ 132',2,'Екатеринбург, Строителей 10-3',0,0,22,3,0,'132'),(51,'УСПД№ 133',2,'Екатеринбург, Строителей 10-4',0,0,22,3,0,'133'),(52,'УСПД№ 134',2,'Екатеринбург, Строителей 10-5',0,0,22,3,0,'134'),(53,'УСПД№ 135',1,'Екатеринбург, Строителей 12-1',0,0,22,3,0,'135'),(54,'УСПД№ 136',1,'Екатеринбург, Строителей 12-2',0,0,22,3,0,'136'),(55,'УСПД№ 137',1,'Екатеринбург, Строителей 12-4',0,0,22,3,0,'137'),(56,'УСПД№ 138',1,'Екатеринбург, Строителей 12-5',0,0,22,3,0,'138'),(57,'УСПД№ 139',1,'Екатеринбург, Строителей 15-3',0,0,22,3,0,'139'),(58,'УСПД№ 140',1,'Екатеринбург, Строителей 15-4',0,0,22,3,0,'140'),(59,'УСПД№ 141',1,'Екатеринбург, Строителей 15-5',0,0,22,3,0,'141');
/*!40000 ALTER TABLE `uspd_dev` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uspd_dev_prev`
--

DROP TABLE IF EXISTS `uspd_dev_prev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uspd_dev_prev` (
  `id_uspd_dev_prev` int NOT NULL AUTO_INCREMENT,
  `id_uspd_dev` int NOT NULL COMMENT 'ID primary USPD',
  `name_uspd_dev_prev` varchar(128) NOT NULL,
  `num_uspd_dev_prev` varchar(28) NOT NULL COMMENT 'Number serial USPD!!!',
  `id_type_object` int NOT NULL,
  PRIMARY KEY (`id_uspd_dev_prev`),
  KEY `fk_uspd_dev_type_object2_idx` (`id_type_object`),
  KEY `fk_uspdprev_uspcurrent_idx` (`id_uspd_dev`),
  CONSTRAINT `fk_uspd_dev_type_uspd2` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`),
  CONSTRAINT `fk_uspdprev_uspcurrent` FOREIGN KEY (`id_uspd_dev`) REFERENCES `uspd_dev` (`id_uspd_dev`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uspd_dev_prev`
--

LOCK TABLES `uspd_dev_prev` WRITE;
/*!40000 ALTER TABLE `uspd_dev_prev` DISABLE KEYS */;
INSERT INTO `uspd_dev_prev` VALUES (22,20,'УСПД №4','4',3);
/*!40000 ALTER TABLE `uspd_dev_prev` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'housing'
--

--
-- Dumping routines for database 'housing'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-11-02 11:34:53
