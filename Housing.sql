-- MySQL dump 10.13  Distrib 8.0.21, for Win64 (x86_64)
--
-- Host: localhost    Database: housing
-- ------------------------------------------------------
-- Server version	5.6.49-log

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
  `id_com_server` int(11) NOT NULL AUTO_INCREMENT,
  `name_server` varchar(128) NOT NULL,
  `ip_server` varchar(25) NOT NULL,
  `port_server` varchar(10) NOT NULL,
  `id_manag_company` int(11) DEFAULT NULL,
  `id_type_object` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_com_server`),
  KEY `fk_com_server_manag_company1_idx` (`id_manag_company`),
  KEY `fk_com_server_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_com_server_manag_company1` FOREIGN KEY (`id_manag_company`) REFERENCES `manag_company` (`id_manag_company`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_com_server_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `com_server`
--

LOCK TABLES `com_server` WRITE;
/*!40000 ALTER TABLE `com_server` DISABLE KEYS */;
INSERT INTO `com_server` VALUES (1,'JackServer','192.168.100.12','3306',NULL,NULL),(2,'ChloeServer','192.168.100.13','33005',NULL,NULL),(3,'KimServer','192.168.100.14','3307',NULL,NULL),(4,'DavidServer','192.168.100.15','3308',NULL,NULL),(5,'MichelleServer','192.168.100.16','3309',NULL,NULL),(6,'JackServer','192.168.100.12','3306',NULL,NULL),(7,'ChloeServer','192.168.100.13','33005',NULL,NULL),(8,'KimServer','192.168.100.14','3307',NULL,NULL),(9,'DavidServer','192.168.100.15','3308',NULL,NULL),(10,'MichelleServer','192.168.100.16','3309',NULL,NULL);
/*!40000 ALTER TABLE `com_server` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `counts`
--

DROP TABLE IF EXISTS `counts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `counts` (
  `id_counts` int(11) NOT NULL,
  `cool_water` int(1) DEFAULT '0',
  `hot_water` int(1) DEFAULT '0',
  `hot_energy` int(1) DEFAULT '0',
  `el_energy` int(1) DEFAULT '0',
  `id_uspd_dev` int(11) NOT NULL,
  `serial_num` varchar(128) NOT NULL,
  `date_plug` datetime DEFAULT NULL,
  `date_expire` datetime DEFAULT NULL,
  `name_count` varchar(128) NOT NULL,
  `address` varchar(255) NOT NULL,
  `id_type_object` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_counts`),
  KEY `fk_counts_uspd_dev1_idx` (`id_uspd_dev`),
  KEY `fk_counts_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_counts_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_counts_uspd_dev1` FOREIGN KEY (`id_uspd_dev`) REFERENCES `uspd_dev` (`id_uspd_dev`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `counts`
--

LOCK TABLES `counts` WRITE;
/*!40000 ALTER TABLE `counts` DISABLE KEYS */;
/*!40000 ALTER TABLE `counts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `foto_counts`
--

DROP TABLE IF EXISTS `foto_counts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `foto_counts` (
  `id_foto_counts` int(11) NOT NULL,
  `id_counts` int(11) NOT NULL,
  `name_file` varchar(128) DEFAULT NULL,
  `image_count` mediumblob,
  PRIMARY KEY (`id_foto_counts`),
  KEY `fk_foto_counts_counts1_idx` (`id_counts`),
  CONSTRAINT `fk_foto_counts_counts1` FOREIGN KEY (`id_counts`) REFERENCES `counts` (`id_counts`) ON DELETE NO ACTION ON UPDATE NO ACTION
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
-- Table structure for table `link_object`
--

DROP TABLE IF EXISTS `link_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `link_object` (
  `id_link_object` int(11) NOT NULL AUTO_INCREMENT,
  `id_parent` int(11) NOT NULL,
  `id_type_object` int(11) NOT NULL,
  `id_object` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_link_object`),
  KEY `fk_link_object_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_link_object_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `link_object`
--

LOCK TABLES `link_object` WRITE;
/*!40000 ALTER TABLE `link_object` DISABLE KEYS */;
/*!40000 ALTER TABLE `link_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manag_company`
--

DROP TABLE IF EXISTS `manag_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `manag_company` (
  `id_manag_company` int(11) NOT NULL AUTO_INCREMENT,
  `name_company` varchar(255) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `address_1` varchar(255) DEFAULT NULL,
  `address_2` varchar(255) DEFAULT NULL,
  `id_type_object` int(11) NOT NULL,
  PRIMARY KEY (`id_manag_company`),
  KEY `fk_manag_company_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_manag_company_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manag_company`
--

LOCK TABLES `manag_company` WRITE;
/*!40000 ALTER TABLE `manag_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `manag_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_object`
--

DROP TABLE IF EXISTS `type_object`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_object` (
  `id_type_object` int(11) NOT NULL AUTO_INCREMENT,
  `name_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id_type_object`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_object`
--

LOCK TABLES `type_object` WRITE;
/*!40000 ALTER TABLE `type_object` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_object` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_uspd`
--

DROP TABLE IF EXISTS `type_uspd`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_uspd` (
  `id_type_uspd` int(11) NOT NULL AUTO_INCREMENT,
  `serial_num` varchar(45) DEFAULT NULL,
  `name_type` varchar(128) NOT NULL,
  PRIMARY KEY (`id_type_uspd`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_uspd`
--

LOCK TABLES `type_uspd` WRITE;
/*!40000 ALTER TABLE `type_uspd` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_uspd` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `uspd_dev`
--

DROP TABLE IF EXISTS `uspd_dev`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `uspd_dev` (
  `id_uspd_dev` int(11) NOT NULL AUTO_INCREMENT,
  `name_uspd_dev` varchar(128) NOT NULL,
  `id_type_uspd_dev` int(11) NOT NULL,
  `address_loc` varchar(255) DEFAULT NULL,
  `id_counts` int(11) DEFAULT NULL,
  `id_config_uspd` int(11) DEFAULT NULL,
  `id_com_server` int(11) DEFAULT NULL,
  `id_type_object` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_uspd_dev`),
  KEY `fk_uspd_dev_com_server_idx` (`id_com_server`),
  KEY `fk_uspd_dev_type_uspd1_idx` (`id_type_uspd_dev`),
  KEY `fk_uspd_dev_type_object1_idx` (`id_type_object`),
  CONSTRAINT `fk_uspd_dev_com_server` FOREIGN KEY (`id_com_server`) REFERENCES `com_server` (`id_com_server`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_uspd_dev_type_object1` FOREIGN KEY (`id_type_object`) REFERENCES `type_object` (`id_type_object`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_uspd_dev_type_uspd1` FOREIGN KEY (`id_type_uspd_dev`) REFERENCES `type_uspd` (`id_type_uspd`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `uspd_dev`
--

LOCK TABLES `uspd_dev` WRITE;
/*!40000 ALTER TABLE `uspd_dev` DISABLE KEYS */;
/*!40000 ALTER TABLE `uspd_dev` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-01 15:35:36
