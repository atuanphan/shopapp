-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: shopweb
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedBy` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,40,NULL,NULL,NULL,NULL),(2,2,NULL,NULL,NULL,NULL),(4,45,NULL,NULL,NULL,NULL),(5,54,NULL,NULL,NULL,NULL),(6,56,'2025-11-19 20:14:01','2025-11-19 20:14:01',NULL,NULL);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart_product`
--

DROP TABLE IF EXISTS `cart_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart_product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cart_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL DEFAULT '1',
  `added_at` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_cart_product` (`cart_id`),
  KEY `fk_product_cart` (`product_id`),
  CONSTRAINT `fk_cart_product` FOREIGN KEY (`cart_id`) REFERENCES `cart` (`id`),
  CONSTRAINT `fk_product_cart` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=94 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart_product`
--

LOCK TABLES `cart_product` WRITE;
/*!40000 ALTER TABLE `cart_product` DISABLE KEYS */;
INSERT INTO `cart_product` VALUES (84,5,9,3,NULL),(92,1,8,1,NULL);
/*!40000 ALTER TABLE `cart_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `quantity` int NOT NULL,
  `unit_price` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `order_detail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`),
  CONSTRAINT `order_detail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,123,1,1,222000.00,222000.00),(2,123,10,5,133333.00,666665.00),(5,125,8,1,111000.00,111000.00),(6,125,10,3,133333.00,399999.00),(7,128,10,10,133333.00,1333330.00),(9,130,8,4,111000.00,444000.00),(10,131,6,1,280000.00,280000.00),(11,131,28,2,280000.00,560000.00),(12,132,25,2,250000.00,500000.00),(14,132,29,3,295000.00,885000.00),(21,137,8,1,111000.00,111000.00);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `total_amount` decimal(10,2) DEFAULT NULL,
  `shipping_address` text NOT NULL,
  `recipient_name` varchar(255) DEFAULT NULL,
  `recipient_phone` varchar(255) DEFAULT NULL,
  `payment_method` varchar(255) NOT NULL,
  `order_status` varchar(255) DEFAULT NULL,
  `notes` text,
  `createdby` varchar(255) DEFAULT NULL,
  `modifieddate` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (123,40,NULL,6.00,'663/46 Trương Định','Mai Thi Mai Anh','0999999999','cod','PROCESSING','giao trước 17h',NULL,'2025-11-20 23:23:12.408',NULL),(125,54,NULL,4.00,'Điện Biên Phủ','Mai Thi Mai Anh','0999999999','online','PROCESSING','gọi trước 30p',NULL,'2025-11-20 23:26:45.744',NULL),(128,40,'2025-08-19 16:21:02',10.00,'5, Đông Thiên, Vĩnh Hưng, Hoàng Mai, Hà Nội','lan anh','0546454656','cod','PROCESSING','',NULL,'2025-11-20 23:23:23.359',NULL),(130,40,'2025-09-08 15:28:53',4.00,'hà nội','lan anh','0546454656','online','PROCESSING','',NULL,'2025-11-20 23:23:26.107',NULL),(131,40,'2025-11-17 15:17:03',3.00,'','','','online','PENDING','',NULL,'2025-11-17 15:17:02.933',NULL),(132,56,'2025-11-19 20:15:35',5.00,'Yên Hòa, Cầu Giấy, Hà Nội','Hoài Anh','098888223','cod','PROCESSING','gọi điện trước khi giao hàng',NULL,'2025-11-22 21:18:05.308',NULL),(137,56,'2025-11-22 20:16:44',1.00,'Trương Định, Thịnh Liệt, Hoàng Mai, Hà Nội','Hoài Anh','0546454656','cod','PENDING','giao hàng buổi tối',NULL,'2025-11-22 20:16:44.125',NULL);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `category` varchar(255) DEFAULT NULL,
  `description` text,
  `price` decimal(10,2) NOT NULL,
  `discount_price` decimal(10,2) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `stock_quantity` int NOT NULL DEFAULT '0',
  `weight` decimal(10,2) DEFAULT NULL,
  `flavor` varchar(255) DEFAULT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `is_featured` tinyint(1) DEFAULT '0',
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'Bánh trung thu kinh đô','TRUYEN_THONG','',200000.00,222000.00,'8f707666cb712f8b53b552b3fbc84673.jpg',30,NULL,'Đậu xanh','',1,NULL,NULL,NULL,NULL),(6,'Bánh Trung Thu Durian','DAC_BIET','Bánh cao cấp nhân sầu riêng Musang King',250000.00,280000.00,'10b0528bb0ecf76de665ac57df24a033.jpg',15,180.00,'Sầu riêng','Luxury',1,NULL,NULL,NULL,NULL),(8,'Bánh Trung Thu nhân sầu riêng','TRUYEN_THONG','Bánh trung thu tươi, thơm ngậy vị sầu riêng',100000.00,111000.00,'8c7c8d6e57ea4d280f124311170a3129.jpg',10,200.00,'sầu riêng','Buono',1,NULL,NULL,NULL,NULL),(9,'Bánh trung thu Kido','DAC_BIET','Bánh trung thu độc đáo, mới lạ',100000.00,144000.00,'8f707666cb712f8b53b552b3fbc84673.jpg',10,200.00,'sầu riêng','Kido',1,NULL,NULL,NULL,NULL),(10,'Bánh trung thu kinh đô','CAO_CAP','Bánh trung thu cao cấp, sang trọng, thơm ngon, bổ dưỡng',120000.00,133333.00,'9442ddfa965a8ccd1788e176f674c7f7.jpg',22,600.00,'Trứng ','Kinh đô',1,NULL,NULL,NULL,NULL),(11,'Bánh trung thu Trung Hoa','DAC_BIET','Bánh trung thu ngon, thơm, mang nét cổ kính đậm bản sắc Trung Quốc',200000.00,210000.00,'fc4e098a2cc224d68cb668002602c31f.jpg',18,300.00,'Đậu xanh','Xiao',1,NULL,NULL,NULL,NULL),(12,'Bánh Trung Thu Hạt Sen','TRUYEN_THONG','Bánh trung thu nhân hạt sen thơm bùi, truyền thống Việt Nam',180000.00,200000.00,'13a7e661681197591b034e1d999af669.jpg',20,200.00,'Hạt sen','Kinh đô',1,NULL,NULL,NULL,NULL),(13,'Bánh Trung Thu Trà Xanh','CAO_CAP','Bánh trung thu nhân trà xanh matcha Nhật Bản, hương vị thanh mát',220000.00,240000.00,'82dd9231b2c3d876b10b54841af45957.jpg',18,250.00,'Trà xanh','Xiao',1,NULL,NULL,NULL,NULL),(14,'Bánh Trung Thu Khoai Môn','DAC_BIET','Bánh trung thu nhân khoai môn tím mềm mịn, ngọt dịu',190000.00,210000.00,'b0ad48d33955dca363bd3f352e4b1e15.jpg',25,220.00,'Khoai môn','Buono',1,NULL,NULL,NULL,NULL),(15,'Bánh Trung Thu Thập Cẩm','TRUYEN_THONG','Bánh trung thu nhân thập cẩm truyền thống, hương vị đậm đà',200000.00,220000.00,'cd0059e6f3953bade23ecb0f40b72117.jpg',30,300.00,'Thập cẩm','Kido',1,NULL,NULL,NULL,NULL),(16,'Bánh Trung Thu Đậu Đỏ','CAO_CAP','Bánh trung thu nhân đậu đỏ thơm ngọt, giàu năng lượng',170000.00,185000.00,'897ea40cad0f9394cf966599045e7f0e.jpg',20,250.00,'Đậu đỏ','Luxury',1,NULL,NULL,NULL,NULL),(17,'Bánh Trung Thu Sầu Riêng Kem','DAC_BIET','Bánh cao cấp nhân sầu riêng kết hợp kem béo ngậy',260000.00,280000.00,'0820a75195b514bcd526c021aeb554a0.jpg',15,200.00,'Sầu riêng','Deluxe',1,NULL,NULL,NULL,NULL),(18,'Bánh Trung Thu Hạt Dẻ','CAO_CAP','Bánh trung thu nhân hạt dẻ thơm béo, mềm mịn',210000.00,230000.00,'fc48582e9a890816de0c100e1a6e9228.jpg',22,260.00,'Hạt dẻ','Xiao',1,NULL,NULL,NULL,NULL),(19,'Bánh Trung Thu Dừa Non','TRUYEN_THONG','Bánh trung thu nhân dừa non thơm béo, ngọt dịu',180000.00,200000.00,'c02a4589b914005f4c228043ed07f623.jpg',28,220.00,'Dừa non','Kinh đô',1,NULL,NULL,NULL,NULL),(20,'Bánh Trung Thu Mè Đen','DAC_BIET','Bánh trung thu nhân mè đen bổ dưỡng, vị bùi đặc trưng',190000.00,210000.00,'b8304ccea664221a5fb0dda0070147ae.jpg',25,230.00,'Mè đen','Buono',1,NULL,NULL,NULL,NULL),(21,'Bánh Trung Thu Trứng Muối Đậu Xanh','CAO_CAP','Bánh nhân đậu xanh trứng muối mặn ngọt hài hòa',220000.00,240000.00,'993c439ac2287197bff44d8a8e47f709.jpg',20,250.00,'Đậu xanh','Royal',1,NULL,NULL,NULL,NULL),(22,'Bánh Trung Thu Phô Mai','DAC_BIET','Bánh nhân phô mai béo ngậy, thơm ngon lạ miệng',250000.00,270000.00,'f891ea4a649b395db4ce2e2c44cdabda.jpg',18,260.00,'Phô mai','Luxury',1,NULL,NULL,NULL,NULL),(23,'Bánh Trung Thu Dâu Tây','CAO_CAP','Bánh nhân dâu tây ngọt dịu, phù hợp giới trẻ',200000.00,220000.00,'9095ecfd0258ed64b5d35066a72bfaeb.jpg',26,240.00,'Dâu tây','Kido',1,NULL,NULL,NULL,NULL),(24,'Bánh Trung Thu Đậu Phộng','TRUYEN_THONG','Bánh nhân đậu phộng thơm, giòn, mang đậm vị Việt',150000.00,165000.00,'26a31ffe3bab93f6f1e967760504c92a.jpg',32,210.00,'Đậu phộng','Kinh đô',1,NULL,NULL,NULL,NULL),(25,'Bánh Trung Thu Cam Tươi','CAO_CAP','Bánh trung thu vị cam tươi, hương thơm dịu nhẹ',230000.00,250000.00,'f8852818e4fb0c3732d7399e12cbceab.jpg',17,280.00,'Cam','Xiao',1,NULL,NULL,NULL,NULL),(26,'Bánh Trung Thu Việt Quất','DAC_BIET','Bánh nhân việt quất chua ngọt tự nhiên, hấp dẫn',240000.00,260000.00,'a204984425c8ad980e732e71a253fe32.jpg',16,270.00,'Việt quất','Buono',1,NULL,NULL,NULL,NULL),(27,'Bánh Trung Thu Hạnh Nhân','CAO_CAP','Bánh nhân hạnh nhân cao cấp, thơm bùi',210000.00,230000.00,'d119b0250df48525c62a20c6017b988b.jpg',20,260.00,'Hạnh nhân','Royal',1,NULL,NULL,NULL,NULL),(28,'Bánh Trung Thu Macca','DAC_BIET','Bánh trung thu nhân macca cao cấp, giòn rụm',260000.00,280000.00,'b236d287a53f07f57b4b0da70787fe7c.jpg',18,250.00,'Macca','Deluxe',1,NULL,NULL,NULL,NULL),(29,'Bánh Trung Thu Tiramisu','CAO_CAP','Bánh nhân tiramisu hương vị Ý, ngọt nhẹ, sang trọng',270000.00,295000.00,'2c44bcb944bbd412a00d0bcf55139e71.jpg',15,280.00,'Cà phê','Luxury',1,NULL,NULL,NULL,NULL),(30,'Bánh Trung Thu Chà Bông Trứng Muối','TRUYEN_THONG','Bánh mặn nhân chà bông trứng muối đậm đà',220000.00,240000.00,'be9834f4eb080010ccd41e3b670e6255.jpg',25,300.00,'Chà bông','Kido',1,NULL,NULL,NULL,NULL),(31,'Bánh Trung Thu Socola Hạt Dẻ','CAO_CAP','Bánh cao cấp nhân socola kết hợp hạt dẻ thơm ngon',250000.00,270000.00,'3d1a65b4e6fe1d470b2311a6bf521668.jpg',20,280.00,'Socola','Royal',1,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `promotion`
--

DROP TABLE IF EXISTS `promotion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `promotion` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL,
  `discount_type` varchar(255) NOT NULL,
  `discount_value` decimal(10,2) NOT NULL,
  `start_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
  `min_order_amount` decimal(10,2) DEFAULT NULL,
  `max_discount` decimal(10,2) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `promotion`
--

LOCK TABLES `promotion` WRITE;
/*!40000 ALTER TABLE `promotion` DISABLE KEYS */;
/*!40000 ALTER TABLE `promotion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `review` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `product_id` bigint NOT NULL,
  `rating` int NOT NULL,
  `comment` text,
  `review_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `review_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `review_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `review_chk_1` CHECK ((`rating` between 1 and 5))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `code` varchar(255) NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,'Quản lý','ADMIN',NULL,NULL,NULL,NULL),(2,'Người dùng','USER',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  `ward` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'phan','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Phan Anh Tuấn','patuan@gmail.com','0932228748','Tam Hoa','Hoàng Hoa Thám','HOAN_KIEM',1,NULL,NULL,NULL,NULL),(2,'anh','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Anh Anh','anhanh@gmail.com','0193544893','dfa','fdsaf','HOANG_MAI',0,NULL,NULL,NULL,NULL),(3,'tuan','$2a$10$/RUbuT9KIqk6f8enaTQiLOXzhnUkiwEJRdtzdrMXXwU7dgnLKTCYG','Tuấn Em','fska@gmail.com','0534594358','gdf','gdsg','THANH_XUAN',0,NULL,NULL,NULL,NULL),(4,'ffa','','df','f@gmail.com','05689758','cntt','ưter','THANH_TRI',0,NULL,NULL,NULL,NULL),(6,'ffưer','$2a$10$0PEWxGMmCye9a285k0.rWeC8S4j7si8ELGg6WUHc7JLJYdr0t0zey','df','f@gmail.com','05689758','cntt','ưter','HOAN_KIEM',0,NULL,NULL,NULL,NULL),(7,'nhatminh','$2a$10$ysqqwlTejT/gtAaXYiNShevoxrgbirqnRKbdV.zCwdiAZabEc9Sre','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(8,'nhatminh1','$2a$10$88U5.vjs1uovMBJ42FSGMeO5NYMVTtSBcnclcpdkwoJ0j9OPZqwam','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(9,'nhatminh2','$2a$10$1eonPUzarPogPp.kfKe9ye5uGNwrVnRwiM.WJoLfpnge4EMzOVDOq','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','BA_DINH',1,NULL,NULL,NULL,NULL),(10,'nhatminh3','$2a$10$f5rIaiuQMpdURBkVZv5HiO0Q/k/TMvYEkUqnZ4V8hKtYhDtJ3ZqM.','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','BA_DINH',1,NULL,NULL,NULL,NULL),(11,'nhatminh35','$2a$10$uUPQh1aJUSoO8kNGk03wSuJmZDpz1qmt0/YaAmT7LfGxRzs7hyjZm','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','BA_DINH',1,NULL,NULL,NULL,NULL),(14,'anhtuan25','$2a$10$EPgoDYBbudymYvTZ4o/JC.u8Xkq//WSF2ITt16yQiQbnAZKzlsj2K','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HA_DONG',1,NULL,NULL,NULL,NULL),(22,'anhtuan251rw','$2a$10$Fu527sIIF9wWTnPUQkN1f.ji3erCxw4cX6QEiiqFmVekYXKQdkH.i','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_BA',1,NULL,NULL,NULL,NULL),(23,'anhtuan251rwfr','$2a$10$ILI5W07rhADkf6iCEQO.jeGSX4SSrzS4CQkzOw/wsLHdPOdynCTUe','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOAN_KIEM',1,NULL,NULL,NULL,NULL),(24,'gh','$2a$10$U7gE9wFf6OTzvAMNdsGam.2ZaID4k8E0WMAJuk1W6I1SiVd.GaJ2.','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_MY',1,NULL,NULL,NULL,NULL),(25,'ghy','$2a$10$LqIQPuZjV.7vokjAAHhPI.Pzuj9dtX/Cl3VrcbkKwYwjv0x5.V3sK','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_MY',1,NULL,NULL,NULL,NULL),(26,'ghyf','$2a$10$HVcuG8ZwJ2TkJF3v07MMqemKMeJ3f05qKZ2DmRkkpvnq1NlMhYlCW','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_MY',1,NULL,NULL,NULL,NULL),(27,'ghyfdd','$2a$10$3cstruaJ4y6ruBmtKQJ.U.z7rHTTttJcMqknx.r8cXsmgpKTO/OIW','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_MY',1,NULL,NULL,NULL,NULL),(30,'ghyfddf','$2a$10$oD73GjVX53GQfkZZaVi0VeGsf6PLp39G8ozoTx8RuzuGSCnPa9f2W','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_MY',1,NULL,NULL,NULL,NULL),(31,'oooo','$2a$10$5EoLdP9ijBcjtpw6QVVHOuPvgAmVTIFIGgmM0DRtC/7myv1TbkIIO','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(32,'oooofgc ','$2a$10$awwZvJXQYlx1iW5DViXMauqP7/BzQjGq/MMdEKl1HkhgSbnM7Jbnq','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(34,'oooofgcy','$2a$10$6E/MIb/iicjv58X71.NF7eGbEzxw9JfZ5jrHfl28dgcLMG2dOf4im','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(35,'oooofgcydf','$2a$10$vvierqRcUq6AC5KIimYVZeazQVfZvVId1g3DB9jChkEA1hepwQ33W','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','ME_LINH',1,NULL,NULL,NULL,NULL),(37,'jon','$2a$10$kI7LFIKIWrTQgHomWyhvuOJE24Mg.52ivfxd70QPQYyV2/TIQ6ANq','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','ME_LINH',1,NULL,NULL,NULL,NULL),(38,'jo','$2a$10$yp3N.upk2hdLQ7lLoBRIYu0ka9dNsiUq5xtnAKcRBdaG.vUqAlfRy','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','PHU_XUYEN',1,NULL,NULL,NULL,NULL),(39,'yo','$2a$10$WJkj3PauYv0NMEZIUaGp3.iqOXl3kQ1iBn03tPq3JP2G..wyVPvVi','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','PHU_XUYEN',1,NULL,NULL,NULL,NULL),(40,'y','$2a$10$auKFEGXX3/NvAkvOas45rujvSL5ZULhvDnpAsbypNUJVGHLjGYBhK','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','PHU_XUYEN',1,NULL,NULL,NULL,NULL),(41,'yư','$2a$10$bjG8/ZKYww8WsXSF7kMmhOkVVRoC9MkWqRCCrQSs3W/.AGLfnoWLS','Phan Anh Nhật Minh','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','PHU_XUYEN',1,NULL,NULL,NULL,NULL),(42,'ytu','$2a$10$jmY.VnumoMUcGcQA3Z5NCOiz1xuDMsejFqSERE2ycx5e8otPCqbeC','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','SOC_SON',1,NULL,NULL,NULL,NULL),(44,'ytum','$2a$10$c3xuTI8sU9lnRz65uFjdt.phGcaoHbYraHTjuIR0n.IFqKG3.T5W.','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THACH_THAT',1,NULL,NULL,NULL,NULL),(45,'ytumf','$2a$10$SYuzVFtHn7gEPswmJHFv5.CgEyGW6tYPl4F7uuRqJmnbAyturKXHi','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','DONG_ANH',1,NULL,NULL,NULL,NULL),(48,'ytumf1','$2a$10$W3WuK6cunbprPnviICFrROQFLvzjt03xqas0kkB9S66zIORLhMeEy','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THACH_THAT',1,NULL,NULL,NULL,NULL),(49,'ytumf132','$2a$10$UrwTdl2K1OeLfxm4icJhT.IbPyUAF1jQ0wSAWTjJngoJgcse3zRWq','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THACH_THAT',1,NULL,NULL,NULL,NULL),(50,'ytumf132a','$2a$10$EkzvOMYh37szNAhCC2NjG.2yQnEX204Aq4XfX0neIFvLraU2TKO0q','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THACH_THAT',1,NULL,NULL,NULL,NULL),(51,'ytumf132a1','$2a$10$SaVoAyhPEa8.lLDKopd1E.srMHiw83YSn5k4X/iVoN1GWz.ukPLr2','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HOANG_MAI',1,NULL,NULL,NULL,NULL),(52,'more','$2a$10$WmAaw5Bzi0pPdruglrQ1nOuyzGW6RRo4lPZJEXQ7HtpF1SYPVtcwW','Phan Anh Minh Thư','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','HAI_BA_TRUNG',1,NULL,NULL,NULL,NULL),(53,'more11','$2a$10$SGvZbi5H4hFnNE19/xgEReS79KjsS6DeRkx8MovWo1HtAP.bB09ra','Phan Vân','panm@gmail.com','09347593','5 Lĩnh Nam','Vĩnh Hưng','THANH_XUAN',1,NULL,NULL,NULL,NULL),(54,'jone','$2a$10$6sN084XRa9bv5te6v7Z31uBg.G.y.yWSzPuV0wBMnnGzurLfgJDnK','Phan Phan','pp@gmail.com','0999999999','663/46 Trương Định','Thịnh Liệt','HOANG_MAI',1,NULL,NULL,NULL,NULL),(55,'','$2a$10$yCn1i.ZEVWkYaC1VxGygjeSbxkubtweEsFwLOIkfY81W6/gZK/e/G','','','','','','THANH_XUAN',0,'2025-11-08 15:07:14','2025-11-08 15:09:36',NULL,NULL),(56,'hoaianh','$2a$10$8gw3VXM.F5kPPSX/hXxXvuxNRiJx54dTwt/sEedrGtVnR0v.8xpr.','Hoài Anh','ha@gmail.com','0977818223','Yên Hòa','Cầu Giấy','CAU_GIAY',1,'2025-11-19 20:13:29','2025-11-19 20:13:29',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  `createddate` datetime DEFAULT NULL,
  `modifieddate` datetime DEFAULT NULL,
  `createdby` varchar(255) DEFAULT NULL,
  `modifiedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_role` (`user_id`),
  KEY `fk_role_user` (`role_id`),
  CONSTRAINT `fk_role_user` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
  CONSTRAINT `fk_user_role` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,1,NULL,NULL,NULL,NULL),(2,2,2,NULL,NULL,NULL,NULL),(3,2,3,NULL,NULL,NULL,NULL),(4,2,23,NULL,NULL,NULL,NULL),(5,2,24,NULL,NULL,NULL,NULL),(6,2,25,NULL,NULL,NULL,NULL),(7,2,26,NULL,NULL,NULL,NULL),(8,2,27,NULL,NULL,NULL,NULL),(9,2,30,NULL,NULL,NULL,NULL),(10,2,31,NULL,NULL,NULL,NULL),(11,2,32,NULL,NULL,NULL,NULL),(12,2,34,NULL,NULL,NULL,NULL),(13,2,35,NULL,NULL,NULL,NULL),(14,2,37,NULL,NULL,NULL,NULL),(15,2,38,NULL,NULL,NULL,NULL),(16,2,39,NULL,NULL,NULL,NULL),(17,2,40,NULL,NULL,NULL,NULL),(18,2,41,NULL,NULL,NULL,NULL),(19,2,42,NULL,NULL,NULL,NULL),(20,2,44,NULL,NULL,NULL,NULL),(21,2,45,NULL,NULL,NULL,NULL),(22,2,48,NULL,NULL,NULL,NULL),(23,2,49,NULL,NULL,NULL,NULL),(24,2,50,NULL,NULL,NULL,NULL),(25,2,51,NULL,NULL,NULL,NULL),(26,2,52,NULL,NULL,NULL,NULL),(27,2,53,NULL,NULL,NULL,NULL),(28,2,54,NULL,NULL,NULL,NULL),(29,2,55,NULL,NULL,NULL,NULL),(30,2,56,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-22 21:38:25
