CREATE DATABASE  IF NOT EXISTS `pos` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pos`;
-- MySQL dump 10.13  Distrib 8.0.30, for Win64 (x86_64)
--
-- Host: localhost    Database: pos
-- ------------------------------------------------------
-- Server version	8.0.30

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
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT,
  `product_code` varchar(25) NOT NULL,
  `product_name` varchar(45) NOT NULL,
  `unit_id` int NOT NULL,
  `category_id` int NOT NULL,
  `unit_instock` float NOT NULL,
  `unit_price(MMK)` float NOT NULL,
  `discount_percentage` float DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`product_id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`),
  UNIQUE KEY `product_code_UNIQUE` (`product_code`)
) ENGINE=InnoDB AUTO_INCREMENT=91 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'850007073671','Coca Cola',2,1,100,100,2,1),(2,'6151100031495','Milo',2,1,45,500,0,1),(4,'8734378707367','Yo Good Yogurt',5,11,726,1650,0,2),(5,'9556001000750','Mother\'s choice Margarine Large',6,11,924,4800,0,2),(6,'9556001000774','Mother\'s choice Margarine Small',7,11,492,2800,0,2),(7,'9556001015464','De De strawberry yogurt',5,11,763,400,0,6),(8,'0280007049718','Pringles(Original)',8,2,480,2000,0,6),(10,'0280007133241','COPP Cereals',10,2,485,350,0,1),(11,'4800361370448','Ready ကြက်သားနှပ်',11,3,650,2250,0,6),(12,'8941100294628','Mighty Sardine',12,3,75,700,0,6),(13,'9556001275424','Ready ငါးကြင်းပေါင်း',11,3,961,2200,0,2),(14,'4800361339360','ထူးရှယ်ပဲနှပ်',13,4,285,650,0,1),(15,'8941100294543','ELAN Detergent Ultra',8,7,585,700,5,2),(16,'8901058865998','ELAN Detergent Ultra',6,7,485,11300,10,6),(17,'8941100295151','OREO Strawberry Cream',14,8,529,1750,0,6),(18,'8941100294710','LIPO Cream Egg Cookies',14,11,500,4100,0,2),(19,'4800361416603','Overtine cookies',15,8,745,1800,0,1),(21,'6271014000483','ဇလုံကြီးမာလာရှမ်းကောအနှစ်',11,9,88,350,0,6),(22,'0780001021612','Lucky Egg Noodle',17,6,765,550,0,6),(23,'9555022304052','Mamee Chicken Flavored Noodle',9,6,821,900,0,2),(24,'0780001134649','Spicy Samyang Hot Chicken',14,6,885,2250,5,2),(25,'0780001034657','Mama Tom Yum Seafood Instant Noodles',8,6,975,350,0,1),(26,'849607055312','Topokki Ramen',14,6,756,1350,0,1),(27,'781520315163','CP Egg',18,10,625,2350,5,1),(28,'841179121499','LUX Shower Soft Rose',19,12,325,6000,0,1),(29,'758149166322','VASELINE Aloe Soothe Lotion',19,12,782,5700,10,1),(30,'812122012737','Eyebrow razor',20,13,285,1600,0,2),(31,'8809559629814','Bella Thanakha cleanser',8,13,379,2600,0,1),(32,'610585216002','Golen Caviar Eye Mask',21,13,185,400,0,1),(33,'685646120277','အမေထွားပဲဆီ',22,16,699,7000,0,2),(34,'819192024547','Goody Sunflower Oil',22,16,898,6100,0,2),(35,'021959017215','PANTENE Shampoo Silky Smooth Care',2,15,600,3500,0.2,1),(36,'037000446491','Head&Shoulders',19,15,330,6600,5,2),(37,'702868415483','Eva Comfort Night',23,5,441,2300,0,6),(38,'702868415112','Sofy Cooling Night ',23,5,663,4350,0,2),(40,'760887983112','Blue Day Pink ',24,5,970,800,0,2),(41,'781946152541','Colgate Soft Toothbrush',26,17,771,1800,0,1),(42,'645175520319','Listerine Mouth Wash',25,17,440,2600,5,2),(43,'645175520139','Signal Original Toothpaste',5,17,327,2000,0,6),(44,'085964222903','TOP Strawberry jam',27,18,636,2800,0,1),(46,'718540727720','Elastics Hair Band',29,20,878,350,0,2),(47,'024589915036','Mirror&Comb',8,14,219,2000,0,2),(48,'760887983185','ORCHID Tissue Box',30,21,545,2000,0,6),(49,'6902395789574','Maybelline Liquid Matte',31,14,185,2800,0,2),(50,'6933996289538','Beichong Tissue',38,21,185,600,0,1),(51,'6933631599602','M & G Glue Stick',32,22,169,200,0,1),(52,'8858790900183','Hi Chef Sardines in Tomato Sauce',11,3,185,1000,0,1),(53,'4902505482021','Pilot BP-1 RT Fine Ball Pen',40,22,185,1600,2,1),(54,'8997035337718','Pocari Sweat ION Supply Drink',41,1,280,1000,0,1),(55,'8886474921025','Life Purified Drinking Water',2,1,248,400,0,1),(56,'8855790000028','Carabao Energy Drink',3,1,165,1100,0,1),(57,'6973578383179','CHOSCH Black Pen',42,22,155,300,0,1),(58,'8850228002841','Sponsor Electrolyte Drink',43,1,200,1000,0,1),(75,'701772797708','Blue Mountain Lemon Sparkling',46,1,190,600,0,1),(80,'850116008717','မက်စ်တူးအို',50,23,200,500,0,1),(82,'8996001307021','Jam O Jam Biscuit',52,8,20,2500,0,1),(83,'8834000028038','Wow Potato Chip (Snack)',52,2,198,600,0,1),(84,'850007073183 ','Max+ Orange Juice',53,1,200,1500,2,1),(85,'850007073657','Sprite Sparkling Water',54,1,7,1500,2,1),(86,'8834000109850',' Good Morning Pan Cake',55,25,100,1000,0,1),(87,'793869474173','Gusto Chocolate Dry Cake',56,25,95,2800,0,1),(88,'8834320032494','သားသား ကြက်သားဆန်ပြုတ်',57,26,180,800,0,1),(89,'9342625002344','Flumox Oral Antibiotic',58,27,200,3400,0,1),(90,'8851123211239','Lipovitan',25,1,200,1000,10,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-10 18:44:40
