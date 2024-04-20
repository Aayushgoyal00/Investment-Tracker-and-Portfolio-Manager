CREATE DATABASE  IF NOT EXISTS `new_project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `new_project`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: new_project
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
-- Table structure for table `mutualfund`
--

DROP TABLE IF EXISTS `mutualfund`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mutualfund` (
  `ID` int DEFAULT NULL,
  `NAME` varchar(50) DEFAULT NULL,
  `RiskRating` varchar(50) DEFAULT NULL,
  `SIP` double DEFAULT NULL,
  `NAV` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mutualfund`
--

LOCK TABLES `mutualfund` WRITE;
/*!40000 ALTER TABLE `mutualfund` DISABLE KEYS */;
INSERT INTO `mutualfund` VALUES (1,'Vanguard Total Stock Market Index Fund','Low',100.5,150.25),(2,'Fidelity 500 Index Fund','Medium',150.75,2700.75),(3,'SPDR S&P 500 ETF Trust','High',200.25,300.5),(4,'Vanguard Total Bond Market Index Fund','Low',75.25,3300),(5,'PepsiCo Inc.','Medium',50.75,700),(6,'Fidelity ZERO Total Market Index Fund','Low',80.25,400.25),(7,'Alibaba Group Holding Limited','High',120.5,20.5),(8,'Vanguard Total Stock Market ETF','Medium',90.75,60.75),(9,'Fidelity ZERO International Index Fund','Low',110.25,30),(10,'iShares Core S&P 500 ETF','High',150.25,100.75);
/*!40000 ALTER TABLE `mutualfund` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mutualfundstocks`
--

DROP TABLE IF EXISTS `mutualfundstocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mutualfundstocks` (
  `MutualFundID` int DEFAULT NULL,
  `StockID` varchar(254) DEFAULT NULL,
  `Quantity` int DEFAULT NULL,
  KEY `MutualFundID` (`MutualFundID`),
  KEY `StockID` (`StockID`),
  CONSTRAINT `mutualfundstocks_ibfk_1` FOREIGN KEY (`MutualFundID`) REFERENCES `mutualfunds` (`MutualFundID`),
  CONSTRAINT `mutualfundstocks_ibfk_2` FOREIGN KEY (`StockID`) REFERENCES `stocks` (`StockID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mutualfundstocks`
--

LOCK TABLES `mutualfundstocks` WRITE;
/*!40000 ALTER TABLE `mutualfundstocks` DISABLE KEYS */;
INSERT INTO `mutualfundstocks` VALUES (1,'AAPL',100),(1,'MSFT',75),(1,'GOOGL',50),(2,'AAPL',80),(2,'AMZN',60),(2,'FB',90),(3,'TSLA',120),(3,'MSFT',55),(4,'AMZN',70),(4,'AAPL',40),(5,'FB',100),(5,'GOOGL',85),(5,'MSFT',60),(6,'AMZN',110),(6,'VOD',45),(7,'NIKE',130),(7,'ULPL',70),(8,'AAPL',60),(8,'MSFT',40),(9,'GOOGL',150),(9,'PEP',80),(9,'HSBC',110),(10,'MSFT',50),(10,'CHA',30),(10,'NVDA',100),(1,'AAPL',100),(1,'MSFT',75),(1,'GOOGL',50),(2,'AAPL',80),(2,'AMZN',60),(2,'FB',90),(3,'TSLA',120),(3,'MSFT',55),(4,'AMZN',70),(4,'AAPL',40),(5,'FB',100),(5,'GOOGL',85),(5,'MSFT',60),(6,'AMZN',110),(6,'VOD',45),(7,'NIKE',130),(7,'ULPL',70),(8,'AAPL',60),(8,'MSFT',40),(9,'GOOGL',150),(9,'PEP',80),(9,'HSBC',110),(10,'MSFT',50),(10,'CHA',30),(10,'NVDA',100);
/*!40000 ALTER TABLE `mutualfundstocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portfolio`
--

DROP TABLE IF EXISTS `portfolio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `portfolio` (
  `Username` varchar(255) DEFAULT NULL,
  `StockID` varchar(254) DEFAULT NULL,
  `StockQuantity` int DEFAULT NULL,
  `MutualFundID` int DEFAULT NULL,
  `MutualFundQuantity` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portfolio`
--

LOCK TABLES `portfolio` WRITE;
/*!40000 ALTER TABLE `portfolio` DISABLE KEYS */;
INSERT INTO `portfolio` VALUES ('amitbansal','PEP',2,4,7),('amitbansal','NVDA',5,7,3),('anitapatel','UL',1,9,7),('deepikachopra','PGK',6,9,7),('deepikachopra','PRG',3,4,1),('meenagupta','TSLA',5,1,3),('meenagupta','UBS',9,2,2),('poojadeshmukh','VOD',1,1,5),('priyasharma','VZ',3,6,7),('rahulsingh','NIKE',4,6,6),('rajeshkumar','NTT',7,8,1),('sureshreddy','MA',2,9,1),('vijayverma','MSFT',6,3,9),('Yogesh Bagotia','v',3,NULL,NULL),('rahulsingh','T',5,NULL,NULL),('@jainil','AMZN',10,NULL,NULL),('dev','T',15,NULL,NULL),('dev','',1,NULL,NULL),('dev',NULL,NULL,1,2323),('dev','v',5,NULL,NULL),('dev','vz',100,NULL,NULL),('dev','pep',1000,NULL,NULL),('@kammo','BABA',3,NULL,NULL),('#jevik','tsla',100,NULL,NULL),('#jevik','u',40,NULL,NULL),('#jevik','V',20,NULL,NULL),('#jevik',NULL,NULL,5,10);
/*!40000 ALTER TABLE `portfolio` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocks`
--

DROP TABLE IF EXISTS `stocks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocks` (
  `StockID` varchar(254) NOT NULL,
  `StockName` varchar(255) DEFAULT NULL,
  `StockPrice` double DEFAULT NULL,
  `MarketCap` bigint DEFAULT NULL,
  `PE_Ratio` decimal(10,2) DEFAULT NULL,
  `DividendYield` decimal(10,2) DEFAULT NULL,
  `Sector` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`StockID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocks`
--

LOCK TABLES `stocks` WRITE;
/*!40000 ALTER TABLE `stocks` DISABLE KEYS */;
INSERT INTO `stocks` VALUES ('AAPL','Apple Inc.',150.25,2300000000000,28.50,1.50,'Technology'),('AMD','Advanced Micro Devices Inc.',100.5,200000000000,30.20,0.50,'Technology'),('AMZN','Amazon.com Inc.',3300,1700000000000,65.80,0.50,'Technology'),('AXP','American Express Company',100.75,400000000000,30.60,1.75,'Finance'),('BABA','Alibaba Group Holding Limited',200.75,400000000000,40.80,1.25,'Technology'),('BAC','Bank of America Corporation',40.25,1000000000000,15.60,3.00,'Finance'),('BCE','BCE Inc.',45.75,180000000000,15.80,4.00,'Telecommunication'),('C','Citigroup Inc.',60.75,600000000000,18.20,2.25,'Finance'),('CHA','China Telecom Corporation Limited',25.25,80000000000,6.80,7.25,'Telecommunication'),('CHL','China Mobile Limited',40,100000000000,8.60,6.00,'Telecommunication'),('CRM','Salesforce.com Inc.',250,350000000000,45.60,0.75,'Technology'),('FB','Meta Platforms Inc.',300.75,600000000000,22.50,1.00,'Technology'),('GOOGL','Alphabet Inc.',2700.75,1800000000000,35.20,0.75,'Technology'),('GS','The Goldman Sachs Group Inc.',400.25,700000000000,60.80,1.50,'Finance'),('HSBC','HSBC Holdings plc',30,300000000000,8.50,4.75,'Finance'),('INTC','Intel Corporation',50,150000000000,15.20,2.50,'Technology'),('JPM','JPMorgan Chase & Co.',150,2000000000000,25.80,2.75,'Finance'),('KO','The Coca-Cola Company',50,200000000000,15.80,3.00,'Consumer Goods'),('L','Procter & Gamble Company',40,150000000000,12.50,3.50,'Consumer Goods'),('MA','Mastercard Incorporated',350.75,800000000000,50.20,0.25,'Finance'),('MSFT','Microsoft Corporation',300.5,2200000000000,32.00,1.25,'Technology'),('NE','PepsiCo Inc.',150.75,400000000000,25.80,1.75,'Consumer Goods'),('NFLX','Netflix Inc.',500.25,250000000000,50.80,0.25,'Technology'),('NIKE','NIKE Inc.',150.75,400000000000,25.80,1.75,'Consumer Goods'),('NK','Procter & Gamble Company',150.75,400000000000,25.80,1.75,'Consumer Goods'),('NKEC','The Coca-Cola Company',150.75,400000000000,25.80,1.75,'Consumer Goods'),('NTT','Nippon Telegraph and Telephone Corporation',60.25,200000000000,18.20,3.00,'Telecommunication'),('NVDA','NVIDIA Corporation',750.5,500000000000,60.30,0.75,'Technology'),('ORAN','Orange S.A.',35,150000000000,10.20,5.75,'Telecommunication'),('PE','The Coca-Cola Company',60.25,250000000000,18.30,2.50,'Consumer Goods'),('PEP','PepsiCo Inc.',60.25,250000000000,18.30,2.50,'Consumer Goods'),('PEPR','NIKE Inc.',60.25,250000000000,18.30,2.50,'Consumer Goods'),('PEPS','Unilever PLC',60.25,250000000000,18.30,2.50,'Consumer Goods'),('PG','Unilever PLC',70.5,300000000000,20.20,2.25,'Consumer Goods'),('PGC','Procter & Gamble Company',70.5,300000000000,20.20,2.25,'Consumer Goods'),('PGK','The Coca-Cola Company',70.5,300000000000,20.20,2.25,'Consumer Goods'),('PRG','PepsiCo Inc.',70.5,300000000000,20.20,2.25,'Consumer Goods'),('T','AT&T Inc.',30.25,200000000000,10.50,5.00,'Telecommunication'),('TMUS','T-Mobile US Inc.',75.5,150000000000,20.30,3.25,'Telecommunication'),('TSLA','Tesla Inc.',700,900000000000,120.60,0.00,'Technology'),('U','PepsiCo Inc.',40,150000000000,12.50,3.50,'Consumer Goods'),('UBS','UBS Group AG',20.5,200000000000,5.20,6.25,'Finance'),('UL','NIKE Inc.',40,150000000000,12.50,3.50,'Consumer Goods'),('ULPL','Unilever PLC',40,150000000000,12.50,3.50,'Consumer Goods'),('V','Visa Inc.',200.5,600000000000,40.50,0.50,'Finance'),('VOD','Vodafone Group Plc',25.5,120000000000,6.50,7.50,'Telecommunication'),('VZ','Verizon Communications Inc.',50.75,300000000000,12.80,4.50,'Telecommunication'),('WFC','Wells Fargo & Company',50.5,500000000000,12.30,3.50,'Finance');
/*!40000 ALTER TABLE `stocks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES ('_aaditya@05','adi$2005','Aaditya Thakkar'),('_pankaj343','@pankaj_664','Pankaj Kumar'),('@aryan','$aryan594','Aryan Chillar'),('@jainil','1234','Jainil Jagtap'),('@pulkit','$hooda','Pulkit Hooda'),('#jevik','$jevik312','jevik'),('105 OR 1=1','105 OR 1=1','105 OR 1=1'),('aayush_','aayyush','Aayush Goyal'),('amitbansal','Am1tBansal312','Amit Bansal'),('anitapatel','An1taPatel789','Anita Patel'),('bee','enkee','efiefei'),('bsks','4244f','eecoc'),('deepikachopra','D33pikaChopra213','Deepika Chopra'),('dev','123321','Devarsh Vasani'),('dwidw','ksck','did'),('meenagupta','M33naGupta654','Meena Gupta'),('poojadeshmukh','PoojaDeshmukh879','Pooja Deshmukh'),('priyasharma','Pr1yaSharma456','Priya Sharma'),('rahulsingh','RahulS1ngh546','Rahul Singh'),('rajeshkumar','R@jeshKumar123','Rajesh Kumar'),('sureshreddy','Sur3shReddy321','Suresh Reddy'),('vijayverma','V1jayVerma987','Vijay Verma'),('Yogi123','_yogesh@312','Yogesh Bagotia');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-20 16:21:27
