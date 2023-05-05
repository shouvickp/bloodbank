CREATE TABLE `rakta_bank`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR (45),
  `password` VARCHAR (64),
  `enable_yn` TINYINT (1),
  `role` VARCHAR (45),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `idx_username` (`user_name`) VISIBLE
);