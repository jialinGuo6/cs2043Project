create database Bank;
show databases;
use Bank;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(15) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id Registration_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(200) DEFAULT NULL,
  `no` varchar(20) DEFAULT NULL,
  `sdate` varchar(45) DEFAULT NULL,
  `money` double(10,2) DEFAULT '0.00',
  `uid` int(11) DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idnew_table_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cardno` varchar(20) DEFAULT NULL,
  `type` varchar(25) DEFAULT NULL,
  `money` decimal(7,2) DEFAULT NULL,
  `tdate` varchar(45) DEFAULT NULL,
  `ctype` varchar(15) DEFAULT NULL,
  `ocardno` varchar(45) DEFAULT NULL,
  `pin` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idtransaction_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


