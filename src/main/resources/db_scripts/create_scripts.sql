USE `blood_bank_service`;

/*Table structure for table `blood_bank_series` */

DROP TABLE IF EXISTS `blood_bank_series`;

CREATE TABLE `blood_bank_series` (
  `name` varchar(255) NOT NULL,
  `next_series` bigint DEFAULT NULL,
  PRIMARY KEY (`name`)
);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `enable_yn` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `hospital` */

DROP TABLE IF EXISTS `hospital`;

CREATE TABLE `hospital` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_name` varchar(255) NOT NULL,
  `login_name` varchar(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact_number` varchar(10) NOT NULL,
  `address_line` varchar(4096) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `creation_ts` datetime(6) NOT NULL,
  `last_modification_ts` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `version` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_hos_login_name` (`login_name`),
  CONSTRAINT `fk_hos_login_name` FOREIGN KEY (`login_name`) REFERENCES `user` (`user_name`)
);

/*Table structure for table `reciever` */

DROP TABLE IF EXISTS `reciever`;

CREATE TABLE `reciever` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `login_name` varchar(8) NOT NULL,
  `email` varchar(255) NOT NULL,
  `mobile_no` varchar(10) NOT NULL,
  `address_line` varchar(4096) NOT NULL,
  `city` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `blood_group` varchar(12) NOT NULL,
  `created_by` varchar(255) NOT NULL,
  `creation_ts` datetime(6) NOT NULL,
  `last_modification_ts` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `version` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_rec_login_name` (`login_name`),
  CONSTRAINT `fk_rec_login_name` FOREIGN KEY (`login_name`) REFERENCES `user` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `requests` */

DROP TABLE IF EXISTS `requests`;

CREATE TABLE `requests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `reciever_id` bigint NOT NULL,
  `patient_name` varchar(255) NOT NULL,
  `request_ref_no` varchar(255) NOT NULL,
  `patient_email` varchar(255) NOT NULL,
  `hospital_id` bigint NOT NULL,
  `blood_group` varchar(12) NOT NULL,
  `bottles` bigint NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `creation_ts` datetime(6) NOT NULL,
  `last_modification_ts` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `version` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_req_hospital_id` (`hospital_id`),
  KEY `fk_req_reciever_id` (`reciever_id`),
  CONSTRAINT `fk_req_hospital_id` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`),
  CONSTRAINT `fk_req_reciever_id` FOREIGN KEY (`reciever_id`) REFERENCES `reciever` (`id`)
);

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `token_type` varchar(255) DEFAULT NULL,
  `expired` tinyint(1) NOT NULL DEFAULT '0',
  `revoked` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_pddrhgwxnms2aceeku9s2ewy5` (`token`),
  KEY `FKe32ek7ixanakfqsdaokm4q9y2` (`user_id`),
  CONSTRAINT `FKe32ek7ixanakfqsdaokm4q9y2` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Table structure for table `token_seq` */

DROP TABLE IF EXISTS `token_seq`;

CREATE TABLE `token_seq` (
  `next_val` bigint DEFAULT NULL
);

/*Table structure for table `blood_stock` */

DROP TABLE IF EXISTS `blood_stock`;

CREATE TABLE `blood_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `hospital_id` bigint NOT NULL,
  `blood_group` varchar(12) NOT NULL,
  `stock_count` bigint NOT NULL DEFAULT '0',
  `created_by` varchar(255) NOT NULL,
  `creation_ts` datetime(6) NOT NULL,
  `last_modification_ts` datetime(6) DEFAULT NULL,
  `last_modified_by` varchar(255) DEFAULT NULL,
  `version` bigint DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `fk_hospital_id` (`hospital_id`),
  CONSTRAINT `fk_hospital_id` FOREIGN KEY (`hospital_id`) REFERENCES `hospital` (`id`)
);

ALTER TABLE `requests`   
CHANGE `status` `status` VARCHAR(10) DEFAULT 'DRAFT' NOT NULL,
ADD COLUMN `documentsUploadedYn` TINYINT(1) DEFAULT 0 NOT NULL AFTER `status`;
