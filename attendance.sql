-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th10 20, 2022 lúc 09:09 AM
-- Phiên bản máy phục vụ: 10.4.24-MariaDB
-- Phiên bản PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `attendance`
--
CREATE DATABASE IF NOT EXISTS `attendance` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `attendance`;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

--
-- Cắt ngắn bảng trước khi chèn `classes`
--

TRUNCATE TABLE `classes`;
--
-- Đang đổ dữ liệu cho bảng `classes`
--

INSERT DELAYED IGNORE INTO `classes` (`id`, `name`) VALUES
(1, 'BLUE'),
(2, 'PURPLE'),
(3, 'YELLOW'),
(4, 'PINK'),
(5, 'ORANGE');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nickname` varchar(255) DEFAULT NULL,
  `telephone_dad` varchar(255) DEFAULT NULL,
  `telephone_mom` varchar(255) DEFAULT NULL,
  `classes_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4l5dnicegnvpmu0pv6vdvrmb6` (`classes_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Cắt ngắn bảng trước khi chèn `student`
--

TRUNCATE TABLE `student`;
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `birthday` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `classes_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKa1wfo14cv62frb1koqplpx95p` (`classes_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Cắt ngắn bảng trước khi chèn `teacher`
--

TRUNCATE TABLE `teacher`;
--
-- Đang đổ dữ liệu cho bảng `teacher`
--

INSERT DELAYED IGNORE INTO `teacher` (`id`, `address`, `birthday`, `first_name`, `last_name`, `phone`, `classes_id`) VALUES
(1, 'Hai Phong', '06-08-1994', 'Nguyen', 'Tien', '0123456789', 3),
(2, 'Ha Noi', '12-09-1996', 'Nguyen', 'Van A', '0123456798', 2),
(3, 'Quang Ninh', '12-09-1996', 'Ta', 'Thi H', '0123455874', 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

--
-- Cắt ngắn bảng trước khi chèn `user`
--

TRUNCATE TABLE `user`;
--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT DELAYED IGNORE INTO `user` (`id`, `address`, `age`, `firstname`, `lastname`, `password`, `role`, `username`) VALUES
(1, 'Ha noi', 18, 'admin', 'admin', '$2a$12$GYL24rD3iccf7B2N9yZ.F.Npi.r.1KEKGOKx.m3wiCUguPPwL6Upm', 0, 'admin'),
(2, 'Ha noi', 18, 'admin', 'admin', '$2a$12$GYL24rD3iccf7B2N9yZ.F.Npi.r.1KEKGOKx.m3wiCUguPPwL6Upm', 1, 'admin1'),
(3, 'Ha noi', 18, 'admin', 'admin', '$2a$12$PKnWiIM1g.ufygBXk.5oBetNVdLLJ4FNPouaUAa9WxXCiATxEbx3K', 2, 'admin2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
