CREATE DATABASE  IF NOT EXISTS `padron` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `padron`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: padron
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `beneficio_por_socio`
--

DROP TABLE IF EXISTS `beneficio_por_socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficio_por_socio` (
  `idbeneficio` bigint NOT NULL AUTO_INCREMENT,
  `socio_id` bigint NOT NULL,
  `estado` int NOT NULL DEFAULT '1',
  `fecha_asignacion` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `beneficio_id` bigint NOT NULL,
  PRIMARY KEY (`idbeneficio`),
  KEY `FK6dnx8w9n4lh70yx7oeechfjb5` (`socio_id`),
  KEY `FKinbrmlml1u539hhkrq5pko0qu` (`beneficio_id`),
  CONSTRAINT `FK6dnx8w9n4lh70yx7oeechfjb5` FOREIGN KEY (`socio_id`) REFERENCES `socios` (`idsocio`),
  CONSTRAINT `FKinbrmlml1u539hhkrq5pko0qu` FOREIGN KEY (`beneficio_id`) REFERENCES `beneficio_socio` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficio_por_socio`
--

LOCK TABLES `beneficio_por_socio` WRITE;
/*!40000 ALTER TABLE `beneficio_por_socio` DISABLE KEYS */;
INSERT INTO `beneficio_por_socio` VALUES (1,3,1,'2024-11-13','2024-12-13',1),(6,1,1,'2024-11-16','2024-12-16',1),(7,3,1,'2024-11-16','2024-12-16',1),(8,3,1,'2024-11-16','2024-12-16',3),(9,3,1,'2024-11-16','2024-12-16',3),(10,3,1,'2024-11-16','2024-12-16',1),(11,3,1,'2024-11-16','2024-12-16',2);
/*!40000 ALTER TABLE `beneficio_por_socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficio_socio`
--

DROP TABLE IF EXISTS `beneficio_socio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficio_socio` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `nombre_beneficio` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficio_socio`
--

LOCK TABLES `beneficio_socio` WRITE;
/*!40000 ALTER TABLE `beneficio_socio` DISABLE KEYS */;
INSERT INTO `beneficio_socio` VALUES (1,'buena membresiaaa','2024-12-17','2024-11-16','Premiun'),(2,'Este benficio solo da acceso al plan basico','2024-12-17','2024-11-15','Basico'),(3,'Acesso medio al plan','2024-12-17','2024-11-15','Regular');
/*!40000 ALTER TABLE `beneficio_socio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `beneficios`
--

DROP TABLE IF EXISTS `beneficios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `beneficios` (
  `idbeneficio` bigint NOT NULL AUTO_INCREMENT,
  `socio_id` bigint NOT NULL,
  PRIMARY KEY (`idbeneficio`),
  KEY `FKfqv7vt9pepw2ve22fcm83qm4b` (`socio_id`),
  CONSTRAINT `FKfqv7vt9pepw2ve22fcm83qm4b` FOREIGN KEY (`socio_id`) REFERENCES `socios` (`idsocio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `beneficios`
--

LOCK TABLES `beneficios` WRITE;
/*!40000 ALTER TABLE `beneficios` DISABLE KEYS */;
/*!40000 ALTER TABLE `beneficios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos_membresia`
--

DROP TABLE IF EXISTS `pagos_membresia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagos_membresia` (
  `idpago` bigint NOT NULL AUTO_INCREMENT,
  `estado_pago` char(1) NOT NULL,
  `fecha_pago` date NOT NULL,
  `metodo_pago` varchar(50) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `socio_id` bigint NOT NULL,
  PRIMARY KEY (`idpago`),
  KEY `FKflxxx1xqu1jk8tajwabttm489` (`socio_id`),
  CONSTRAINT `FKflxxx1xqu1jk8tajwabttm489` FOREIGN KEY (`socio_id`) REFERENCES `socios` (`idsocio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos_membresia`
--

LOCK TABLES `pagos_membresia` WRITE;
/*!40000 ALTER TABLE `pagos_membresia` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagos_membresia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `socios`
--

DROP TABLE IF EXISTS `socios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `socios` (
  `idsocio` bigint NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) NOT NULL,
  `apellidop` varchar(60) NOT NULL,
  `apellidom` varchar(60) NOT NULL,
  `correo` varchar(60) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` varchar(60) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `ocupacion` varchar(60) NOT NULL,
  `dni` varchar(9) NOT NULL,
  `genero` char(1) NOT NULL,
  `estado` int NOT NULL,
  `tipo` int NOT NULL,
  `fecha_afiliacion` date NOT NULL,
  PRIMARY KEY (`idsocio`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `socios`
--

LOCK TABLES `socios` WRITE;
/*!40000 ALTER TABLE `socios` DISABLE KEYS */;
INSERT INTO `socios` VALUES (1,'Cristhiann','Medina','Camayo','cristhian@gmail.com','955195324','Las casuarinas , La molina','2000-07-12','Development','74625022','H',1,1,'2024-11-13'),(3,'Pepe Lucho','Lucho','Lucho','lucho@gmail.com','955154326','Los alamos,Lima','1996-06-05','Cachinero','74657425','H',1,1,'2024-11-13');
/*!40000 ALTER TABLE `socios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` bigint NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(60) NOT NULL,
  `clave` varchar(15) NOT NULL,
  `dni` varchar(8) NOT NULL,
  `estado` int NOT NULL DEFAULT '1',
  `nombres` varchar(60) NOT NULL,
  `tipo` int NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Medina','123','74625021',1,'Cristhian',1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'padron'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-18  3:39:12
