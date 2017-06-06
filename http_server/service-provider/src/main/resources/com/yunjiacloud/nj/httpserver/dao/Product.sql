-- auto Generated on 2017-06-06 16:52:49 
-- DROP TABLE IF EXISTS `product`; 
CREATE TABLE `product`(
	`id` BIGINT (15) NOT NULL AUTO_INCREMENT COMMENT 'id',
	`name` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'name',
	`code` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'code',
	`net_region` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'netRegion',
	`ip` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'ip',
	`port` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'port',
	`username` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'username',
	`password` VARCHAR (50) NOT NULL DEFAULT '' COMMENT 'password',
	`product_status` INT (11) NOT NULL DEFAULT -1 COMMENT 'productStatus',
	`is_start_audit` INT (11) NOT NULL DEFAULT -1 COMMENT 'isStartAudit',
	`start_audit_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'startAuditTime',
	`is_start_monitor` INT (11) NOT NULL DEFAULT -1 COMMENT 'isStartMonitor',
	`start_monitor_time` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'startMonitorTime',
	`gmt_create` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'gmtCreate',
	`gmt_modified` DATETIME NOT NULL DEFAULT '1000-01-01 00:00:00' COMMENT 'gmtModified',
	`create_user_id` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'createUserId',
	`update_user_id` BIGINT (15) NOT NULL DEFAULT -1 COMMENT 'updateUserId',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '`product`';
