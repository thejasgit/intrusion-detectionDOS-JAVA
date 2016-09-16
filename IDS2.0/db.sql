/*
SQLyog - Free MySQL GUI v5.19
Host - 5.0.15-nt : Database - ids1_db
*********************************************************************
Server version : 5.0.15-nt
*/

SET NAMES utf8;

SET SQL_MODE='';

create database if not exists `ids1_db`;

USE `ids1_db`;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO';

/*Table structure for table `address_tbl` */

DROP TABLE IF EXISTS `address_tbl`;

CREATE TABLE `address_tbl` (
  `node_no` int(5) NOT NULL auto_increment,
  `node_name` varchar(50) NOT NULL,
  `node_ip` varchar(30) NOT NULL,
  `node_port` varchar(5) NOT NULL,
  PRIMARY KEY  (`node_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `address_tbl` */

insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (1,'source','prasoon','5001');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (2,'r1','prasoon','9002');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (3,'f_rec_r1','prasoon','9003');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (4,'r2','prasoon','9004');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (5,'f_rec_r2','prasoon','9005');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (6,'r3','prasoon','9006');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (7,'f_rec_r3','prasoon','9007');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (8,'dest','prasoon','9008');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (9,'f_rec_dest','prasoon','9009');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (10,'h_rec_r1','prasoon','9010');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (11,'h_rec_r2','prasoon','9011');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (12,'h_rec_r3','prasoon','9012');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (13,'intrud_rec_r1','prasoon','9013');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (14,'intrud_rec_r2','prasoon','9014');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (15,'intrud_rec_r3','prasoon','9015');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (16,'intrud_h_rec_r1','prasoon','9016');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (17,'intrud_h_rec_r2','prasoon','9017');
insert into `address_tbl` (`node_no`,`node_name`,`node_ip`,`node_port`) values (18,'intrud_h_rec_r3','prasoon','9018');

/*Table structure for table `m_ddos_atk_status` */

DROP TABLE IF EXISTS `m_ddos_atk_status`;

CREATE TABLE `m_ddos_atk_status` (
  `r_no` int(2) NOT NULL auto_increment,
  `r_name` varchar(5) default NULL,
  `r_status` varchar(10) default NULL,
  PRIMARY KEY  (`r_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_ddos_atk_status` */

insert into `m_ddos_atk_status` (`r_no`,`r_name`,`r_status`) values (1,'r1','false');
insert into `m_ddos_atk_status` (`r_no`,`r_name`,`r_status`) values (2,'r2','false');
insert into `m_ddos_atk_status` (`r_no`,`r_name`,`r_status`) values (3,'r3','false');

/*Table structure for table `m_fname` */

DROP TABLE IF EXISTS `m_fname`;

CREATE TABLE `m_fname` (
  `f_name` varchar(100) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_fname` */

insert into `m_fname` (`f_name`) values ('.classpath');

/*Table structure for table `m_hash` */

DROP TABLE IF EXISTS `m_hash`;

CREATE TABLE `m_hash` (
  `hash_no` int(4) NOT NULL auto_increment,
  `ip_address` varchar(25) default NULL,
  `hmac` varchar(200) default NULL,
  `count` int(4) default '0',
  `ip_attacker` varchar(25) default NULL,
  PRIMARY KEY  (`hash_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_hash` */

insert into `m_hash` (`hash_no`,`ip_address`,`hmac`,`count`,`ip_attacker`) values (1,'S4A','829f71740aab1ab98b33eae21dee122',1,'192.168.1.3');

/*Table structure for table `m_r1` */

DROP TABLE IF EXISTS `m_r1`;

CREATE TABLE `m_r1` (
  `profile_no` int(3) NOT NULL auto_increment,
  `profile_name` varchar(15) default NULL,
  `tot_load` int(3) default NULL,
  `th_min` int(2) default NULL,
  `th_max` int(2) default NULL,
  `current_lod` int(3) default NULL,
  `profile_status` varchar(5) default NULL,
  PRIMARY KEY  (`profile_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_r1` */

insert into `m_r1` (`profile_no`,`profile_name`,`tot_load`,`th_min`,`th_max`,`current_lod`,`profile_status`) values (1,'S4A',25,1,25,1,'Open');

/*Table structure for table `m_r2` */

DROP TABLE IF EXISTS `m_r2`;

CREATE TABLE `m_r2` (
  `profile_no` int(3) NOT NULL auto_increment,
  `profile_name` varchar(15) default NULL,
  `tot_load` int(3) default NULL,
  `th_min` int(2) default NULL,
  `th_max` int(2) default NULL,
  `current_lod` int(3) default NULL,
  `profile_status` varchar(5) default NULL,
  PRIMARY KEY  (`profile_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_r2` */

insert into `m_r2` (`profile_no`,`profile_name`,`tot_load`,`th_min`,`th_max`,`current_lod`,`profile_status`) values (1,'S1',15,1,15,1,'Open');

/*Table structure for table `m_r3` */

DROP TABLE IF EXISTS `m_r3`;

CREATE TABLE `m_r3` (
  `profile_no` int(3) NOT NULL auto_increment,
  `profile_name` varchar(15) default NULL,
  `tot_load` int(3) default NULL,
  `th_min` int(2) default NULL,
  `th_max` int(2) default NULL,
  `current_lod` int(3) default NULL,
  `profile_status` varchar(5) default NULL,
  PRIMARY KEY  (`profile_no`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `m_r3` */

insert into `m_r3` (`profile_no`,`profile_name`,`tot_load`,`th_min`,`th_max`,`current_lod`,`profile_status`) values (1,'S3',15,1,7,1,'Open');
insert into `m_r3` (`profile_no`,`profile_name`,`tot_load`,`th_min`,`th_max`,`current_lod`,`profile_status`) values (2,'S2',15,1,7,1,'Open');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
