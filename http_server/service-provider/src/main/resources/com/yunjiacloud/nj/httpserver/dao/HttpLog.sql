-- auto Generated on 2017-06-06 20:44:04 
-- DROP TABLE IF EXISTS `http_log`; 
CREATE TABLE `http_log`(
	`id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`remote_addr` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'remoteAddr',
	`request` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'request',
	`time_local` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'timeLocal',
	`method` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'method',
	`body_bytes_sent` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'bodyBytesSent',
	`body_byte_receive` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'bodyByteReceive',
	`status` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'status',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`http_log`';
