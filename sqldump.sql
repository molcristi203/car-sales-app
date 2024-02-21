-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: carsales
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `car_features`
--

DROP TABLE IF EXISTS `car_features`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_features` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_features`
--

LOCK TABLES `car_features` WRITE;
/*!40000 ALTER TABLE `car_features` DISABLE KEYS */;
INSERT INTO `car_features` VALUES (1,'Bluetooth Connectivity'),(2,'Backup Camera'),(3,'Leather Seats'),(4,'Navigation System'),(5,'Sunroof'),(6,'Keyless Entry'),(7,'Heated Seats'),(8,'Automatic Climate Control'),(9,'Lane Departure Warning'),(10,'Blind Spot Monitoring');
/*!40000 ALTER TABLE `car_features` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_manufacturers`
--

DROP TABLE IF EXISTS `car_manufacturers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_manufacturers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `manufacturer_name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_manufacturers`
--

LOCK TABLES `car_manufacturers` WRITE;
/*!40000 ALTER TABLE `car_manufacturers` DISABLE KEYS */;
INSERT INTO `car_manufacturers` VALUES (1,'Toyota'),(2,'Ford'),(3,'Honda'),(4,'BMW'),(5,'Mercedes-Benz');
/*!40000 ALTER TABLE `car_manufacturers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `car_models`
--

DROP TABLE IF EXISTS `car_models`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car_models` (
  `model_id` int NOT NULL AUTO_INCREMENT,
  `model_name` varchar(100) DEFAULT NULL,
  `manufacturer_id` int DEFAULT NULL,
  PRIMARY KEY (`model_id`),
  KEY `car_models_car_manufacturers_id_fk` (`manufacturer_id`),
  CONSTRAINT `car_models_car_manufacturers_id_fk` FOREIGN KEY (`manufacturer_id`) REFERENCES `car_manufacturers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car_models`
--

LOCK TABLES `car_models` WRITE;
/*!40000 ALTER TABLE `car_models` DISABLE KEYS */;
INSERT INTO `car_models` VALUES (1,'Camry',1),(2,'Corolla',1),(3,'RAV4',1),(4,'Accord',3),(5,'Civic',3),(6,'CR-V',3),(7,'F-150',2),(8,'Mustang',2),(9,'Escape',2),(10,'X5',4),(11,'3 Series',4),(12,'X3',4),(13,'C-Class',5),(14,'E-Class',5),(15,'GLC',5);
/*!40000 ALTER TABLE `car_models` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars_for_sale`
--

DROP TABLE IF EXISTS `cars_for_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars_for_sale` (
  `id` int NOT NULL,
  `price` double DEFAULT NULL,
  `mileage` int DEFAULT NULL,
  `manufacturing_year` int DEFAULT NULL,
  `model_id` int DEFAULT NULL,
  `vehicle_category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cars_for_sale_car_models_model_id_fk` (`model_id`),
  KEY `cars_for_sale_vehicle_category_vehicle_category_id_fk` (`vehicle_category_id`),
  CONSTRAINT `cars_for_sale_car_models_model_id_fk` FOREIGN KEY (`model_id`) REFERENCES `car_models` (`model_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cars_for_sale_vehicle_category_vehicle_category_id_fk` FOREIGN KEY (`vehicle_category_id`) REFERENCES `vehicle_category` (`vehicle_category_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars_for_sale`
--

LOCK TABLES `cars_for_sale` WRITE;
/*!40000 ALTER TABLE `cars_for_sale` DISABLE KEYS */;
INSERT INTO `cars_for_sale` VALUES (1,28000,10,2024,1,1),(2,20000,10000,2022,8,4);
/*!40000 ALTER TABLE `cars_for_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars_sold`
--

DROP TABLE IF EXISTS `cars_sold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars_sold` (
  `id` int NOT NULL AUTO_INCREMENT,
  `cars_for_sale_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cars_sold_cars_for_sale_id_fk` (`cars_for_sale_id`),
  KEY `cars_sold_customers_id_fk` (`customer_id`),
  CONSTRAINT `cars_sold_cars_for_sale_id_fk` FOREIGN KEY (`cars_for_sale_id`) REFERENCES `cars_for_sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cars_sold_customers_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars_sold`
--

LOCK TABLES `cars_sold` WRITE;
/*!40000 ALTER TABLE `cars_sold` DISABLE KEYS */;
/*!40000 ALTER TABLE `cars_sold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cars_sold_by_sellers`
--

DROP TABLE IF EXISTS `cars_sold_by_sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cars_sold_by_sellers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sellers_id` int DEFAULT NULL,
  `cars_for_sale_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cars_sold_by_sellers_cars_for_sale_id_fk` (`cars_for_sale_id`),
  KEY `cars_sold_by_sellers_sellers_id_fk` (`sellers_id`),
  CONSTRAINT `cars_sold_by_sellers_cars_for_sale_id_fk` FOREIGN KEY (`cars_for_sale_id`) REFERENCES `cars_for_sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `cars_sold_by_sellers_sellers_id_fk` FOREIGN KEY (`sellers_id`) REFERENCES `sellers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cars_sold_by_sellers`
--

LOCK TABLES `cars_sold_by_sellers` WRITE;
/*!40000 ALTER TABLE `cars_sold_by_sellers` DISABLE KEYS */;
INSERT INTO `cars_sold_by_sellers` VALUES (1,1,1),(2,1,2);
/*!40000 ALTER TABLE `cars_sold_by_sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_address` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'gigel@email.com','gigi','Gigel','0756752975');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `features_on_cars_for_sale`
--

DROP TABLE IF EXISTS `features_on_cars_for_sale`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `features_on_cars_for_sale` (
  `id` int NOT NULL AUTO_INCREMENT,
  `car_for_sale_id` int DEFAULT NULL,
  `car_features_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `features_on_cars_for_sale_cars_for_sale_id_fk` (`car_for_sale_id`),
  KEY `features_on_cars_for_sale_features_on_cars_for_sale_id_fk` (`car_features_id`),
  CONSTRAINT `features_on_cars_for_sale_cars_for_sale_id_fk` FOREIGN KEY (`car_for_sale_id`) REFERENCES `cars_for_sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `features_on_cars_for_sale_features_on_cars_for_sale_id_fk` FOREIGN KEY (`car_features_id`) REFERENCES `features_on_cars_for_sale` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `features_on_cars_for_sale`
--

LOCK TABLES `features_on_cars_for_sale` WRITE;
/*!40000 ALTER TABLE `features_on_cars_for_sale` DISABLE KEYS */;
INSERT INTO `features_on_cars_for_sale` VALUES (1,1,1),(2,1,2),(3,1,3),(4,1,4),(5,1,5),(6,1,6),(7,1,7),(8,1,8),(9,1,9),(10,1,10),(11,2,1),(12,2,2),(13,2,3),(14,2,4),(15,2,5),(16,2,6),(17,2,7),(18,2,8),(19,2,9),(20,2,10);
/*!40000 ALTER TABLE `features_on_cars_for_sale` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sellers`
--

DROP TABLE IF EXISTS `sellers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sellers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_address` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sellers`
--

LOCK TABLES `sellers` WRITE;
/*!40000 ALTER TABLE `sellers` DISABLE KEYS */;
INSERT INTO `sellers` VALUES (1,'ion@email.com','ion','0783295069','Ion');
/*!40000 ALTER TABLE `sellers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle_category`
--

DROP TABLE IF EXISTS `vehicle_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle_category` (
  `vehicle_category_id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`vehicle_category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle_category`
--

LOCK TABLES `vehicle_category` WRITE;
/*!40000 ALTER TABLE `vehicle_category` DISABLE KEYS */;
INSERT INTO `vehicle_category` VALUES (1,'Sedan'),(2,'SUV'),(3,'Truck'),(4,'Coupe'),(5,'Convertible');
/*!40000 ALTER TABLE `vehicle_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-02-21 20:32:30
