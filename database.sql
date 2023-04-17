-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1:3306
-- Thời gian đã tạo: Th4 16, 2023 lúc 09:12 AM
-- Phiên bản máy phục vụ: 10.4.10-MariaDB
-- Phiên bản PHP: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `mai`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `brand`
--

DROP TABLE IF EXISTS `brand`;
CREATE TABLE IF NOT EXISTS `brand` (
  `brand_id` int(11) NOT NULL AUTO_INCREMENT,
  `brand_name` varchar(50) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`brand_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `brand`
--

INSERT INTO `brand` (`brand_id`, `brand_name`, `active`) VALUES
(1, 'Amado', b'1'),
(2, 'Ikea', b'1'),
(3, 'Furniture Inc', b'1'),
(4, 'The Factory', b'1'),
(5, 'Art Deco', b'1'),
(6, 'Coco', b'1'),
(7, 'Nino', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cart`
--

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `total_price` double NOT NULL,
  PRIMARY KEY (`cart_id`),
  UNIQUE KEY `UK_9emlp6m95v5er2bcqkjsw48he` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `cart`
--

INSERT INTO `cart` (`cart_id`, `user_id`, `total_price`) VALUES
(8, 1, 4050),
(9, 2, 1419),
(10, 3, 1790),
(11, 4, 0),
(12, 5, 1718);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `cartitem`
--

DROP TABLE IF EXISTS `cartitem`;
CREATE TABLE IF NOT EXISTS `cartitem` (
  `cartitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantity` int(11) DEFAULT NULL,
  `cart_id` int(11) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`cartitem_id`),
  KEY `UK_3ar4drv9grj082pu1hktv4fin` (`product_id`) USING BTREE,
  KEY `FKcj0jvvlv7mum72m5qblw5m1tc` (`cart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `cartitem`
--

INSERT INTO `cartitem` (`cartitem_id`, `quantity`, `cart_id`, `product_id`) VALUES
(41, 8, 8, 34),
(42, 1, 8, 43),
(43, 2, 10, 35),
(44, 3, 10, 34),
(45, 1, 9, 42),
(51, 3, 9, 34);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(50) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `category`
--

INSERT INTO `category` (`category_id`, `category_name`, `active`) VALUES
(1, 'Chairs', b'1'),
(2, 'Beds', b'1'),
(3, 'Accesories', b'1'),
(4, 'Furniture', b'1'),
(5, 'Home Deco2', b'1'),
(6, 'Furniture2', b'1'),
(7, 'Tables3', b'1'),
(8, 'Furniture3', b'1'),
(9, 'Beds2', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `color`
--

DROP TABLE IF EXISTS `color`;
CREATE TABLE IF NOT EXISTS `color` (
  `color_id` int(11) NOT NULL AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`color_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `color`
--

INSERT INTO `color` (`color_id`, `color`, `active`) VALUES
(1, '#ffffff', b'1'),
(2, '#000000', b'1'),
(3, '#ff0000', b'1'),
(4, '#ffc0cb', b'1'),
(5, '#8f00ff', b'1'),
(6, '#808080', b'1'),
(7, '#0000ff', b'1'),
(9, '#ff1493', b'1'),
(10, '#921c1c', b'1'),
(11, '#ffcccc', b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) NOT NULL,
  `product_id` int(11) NOT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=143 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `image`
--

INSERT INTO `image` (`image_id`, `url`, `product_id`) VALUES
(95, 'chair.jpg', 34),
(96, 'chair.jpg', 34),
(97, 'chair.jpg', 34),
(98, 'chair.jpg', 34),
(99, 'chair.jpg', 35),
(100, 'chair.jpg', 35),
(101, 'chair.jpg', 35),
(102, 'chair.jpg', 35),
(103, 'chair.jpg', 36),
(104, 'chair.jpg', 36),
(105, 'chair.jpg', 36),
(106, 'chair.jpg', 36),
(107, 'chair.jpg', 37),
(108, 'chair.jpg', 37),
(109, 'chair.jpg', 37),
(110, 'chair.jpg', 37),
(111, 'chair.jpg', 38),
(112, 'chair.jpg', 38),
(113, 'chair.jpg', 38),
(114, 'chair.jpg', 38),
(115, 'chair.jpg', 39),
(116, 'chair.jpg', 39),
(117, 'chair.jpg', 39),
(118, 'chair.jpg', 39),
(119, 'chair.jpg', 40),
(120, 'chair.jpg', 40),
(121, 'chair.jpg', 40),
(122, 'chair.jpg', 40),
(123, 'chair.jpg', 41),
(124, 'chair.jpg', 41),
(125, 'chair.jpg', 41),
(126, 'chair.jpg', 41),
(127, 'chair.jpg', 42),
(128, 'chair.jpg', 42),
(129, 'chair.jpg', 42),
(130, 'chair.jpg', 42),
(131, 'chair.jpg', 43),
(132, 'chair.jpg', 43),
(133, 'chair.jpg', 43),
(134, 'chair.jpg', 43),
(135, 'chair.jpg', 44),
(136, 'chair.jpg', 44),
(137, 'chair.jpg', 44),
(138, 'chair.jpg', 44),
(139, 'chair.jpg', 45),
(140, 'chair.jpg', 45),
(141, 'chair.jpg', 45),
(142, 'chair.jpg', 45);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetail`
--

DROP TABLE IF EXISTS `orderdetail`;
CREATE TABLE IF NOT EXISTS `orderdetail` (
  `orderitem_id` int(11) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(255) NOT NULL,
  `item_image` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `order_id` int(11) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`orderitem_id`),
  KEY `FKj8ackge8175q1tysq976rut4q` (`order_id`)
) ENGINE=MyISAM AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `orderdetail`
--

INSERT INTO `orderdetail` (`orderitem_id`, `item_name`, `item_image`, `price`, `quantity`, `order_id`, `total`) VALUES
(11, 'White Chair', 'chair.jpg', 450, 2, 6, 900),
(12, 'White Chair', 'chair.jpg', 450, 1, 6, 450),
(13, 'White Chair', 'chair.jpg', 450, 2, 5, 900),
(14, 'A water bottle', 'chair.jpg', 200, 1, 6, 200),
(15, 'White Chair', 'chair.jpg', 450, 1, 5, 450),
(16, 'A box', 'chair.jpg', 99, 1, 6, 99),
(17, 'A water bottle', 'chair.jpg', 200, 1, 5, 200),
(18, 'A boy', 'chair.jpg', 69, 1, 6, 69),
(19, 'White Chair', 'chair.jpg', 450, 2, 7, 900),
(20, 'A box', 'chair.jpg', 99, 1, 5, 99),
(21, 'White Chair', 'chair.jpg', 450, 1, 7, 450),
(22, 'A boy', 'chair.jpg', 69, 1, 5, 69),
(23, 'White Chair', 'chair.jpg', 450, 2, 8, 900),
(24, 'White Chair', 'chair.jpg', 450, 1, 8, 450),
(25, 'A water bottle', 'chair.jpg', 200, 1, 8, 200),
(26, 'A box', 'chair.jpg', 99, 1, 8, 99),
(27, 'A boy', 'chair.jpg', 69, 1, 8, 69),
(28, 'A water bottle', 'chair.jpg', 200, 1, 7, 200),
(29, 'A box', 'chair.jpg', 99, 1, 7, 99),
(30, 'A boy', 'chair.jpg', 69, 1, 7, 69);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) NOT NULL,
  `max_quantity` int(11) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `brand_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `main_image` varchar(255) DEFAULT NULL,
  `color_id` int(11) NOT NULL,
  `active` bit(1) NOT NULL,
  PRIMARY KEY (`product_id`),
  KEY `UK_gr625e8gvblq7wg76bxcl6e4c` (`color_id`) USING BTREE,
  KEY `FKs6cydsualtsrprvlf2bb3lcam` (`brand_id`),
  KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`)
) ENGINE=MyISAM AUTO_INCREMENT=46 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `max_quantity`, `description`, `price`, `brand_id`, `category_id`, `main_image`, `color_id`, `active`) VALUES
(34, 'A Chair 1', 45, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 450, 1, 1, 'chair.jpg', 2, b'1'),
(35, 'A Chair 2', 65, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 220, 1, 1, 'chair.jpg', 3, b'1'),
(36, 'A Chair 3', 70, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 99, 2, 2, 'chair.jpg', 4, b'1'),
(37, 'A Chair 4', 30, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 39, 3, 1, 'chair.jpg', 2, b'1'),
(38, 'A Chair 5', 20, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 44, 4, 4, 'chair.jpg', 3, b'1'),
(39, 'A Chair 6', 40, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 99, 1, 1, 'chair.jpg', 5, b'1'),
(40, 'A Chair 7', 999, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 200, 3, 1, 'chair.jpg', 3, b'1'),
(41, 'A Chair 8', 30, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 29, 4, 5, 'chair.jpg', 3, b'1'),
(42, 'A Chair 9', 30, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 69, 4, 5, 'chair.jpg', 2, b'1'),
(43, 'A Chair 10', 45, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 450, 1, 1, 'chair.jpg', 2, b'1'),
(44, 'A chair 11', 4, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 2, 1, 'chair.jpg', 10, b'0'),
(45, 'A chair 12', 3, 'ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 2, 6, 3, 'chair.jpg', 10, b'1');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `name`) VALUES
(1, 'USER'),
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `shippingaddress`
--

DROP TABLE IF EXISTS `shippingaddress`;
CREATE TABLE IF NOT EXISTS `shippingaddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `street` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1k81cjyhhwohy5tmb60uayxt` (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `test`
--

DROP TABLE IF EXISTS `test`;
CREATE TABLE IF NOT EXISTS `test` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `userorder`
--

DROP TABLE IF EXISTS `userorder`;
CREATE TABLE IF NOT EXISTS `userorder` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `total_amount` double NOT NULL,
  `created_date` datetime NOT NULL,
  `status` varchar(50) NOT NULL,
  `user__id` int(11) NOT NULL,
  `order_description` varchar(255) DEFAULT NULL,
  `payment` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `phone_number` bigint(20) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `FKawaat6fudvg8qquod63ucr9k1` (`user__id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `userorder`
--

INSERT INTO `userorder` (`order_id`, `total_amount`, `created_date`, `status`, `user__id`, `order_description`, `payment`, `address`, `email`, `gender`, `phone_number`, `user_name`) VALUES
(5, 1718, '2023-04-11 10:06:21', 'Checking', 5, '123', NULL, 'rain', '2@gmail.com', 'female', 12345, 'Smith'),
(6, 1718, '2023-04-11 10:06:21', 'COMPLETED', 5, '123', NULL, 'rain', '2@gmail.com', 'female', 12345, 'Smith'),
(7, 1718, '2023-04-11 10:06:22', 'COMPLETED', 5, '123', NULL, 'rain', '2@gmail.com', 'female', 12345, 'Smith'),
(8, 1718, '2023-04-11 10:06:23', 'COMPLETED', 5, '123', NULL, 'rain', '2@gmail.com', 'female', 12345, 'Smith');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` bigint(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `main_address` varchar(255) DEFAULT NULL,
  `main_image` varchar(255) DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `verification_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `user_name`, `password`, `phone`, `gender`, `email`, `main_address`, `main_image`, `created_date`, `enabled`, `verification_code`) VALUES
(1, 'ngocmai', '$2a$10$rUGlWdLArkZwIlU746/rb.lsfLApjDwzaEyXrtS3ZTbcf5ZLJaWma', 123, 'female', 'hoangmailinh@gmail.com', 'mua', 'chair.jpg', '2022-01-01 12:04:01', b'1', NULL),
(2, 'janesmith2', '$2a$10$rUGlWdLArkZwIlU746/rb.lsfLApjDwzaEyXrtS3ZTbcf5ZLJaWma', 5555678, 'male', 'nhuthienlang3@gmail.com', 'phan thiet', 'chair.jpg', '2021-01-01 12:04:01', b'1', NULL),
(4, 'will', '$2a$10$rUGlWdLArkZwIlU746/rb.lsfLApjDwzaEyXrtS3ZTbcf5ZLJaWma', 12345, 'male', '1@gmail.com', 'Chicago', 'chair.jpg', '2023-03-28 16:47:31', b'1', NULL),
(5, 'Smith', '$2a$10$rUGlWdLArkZwIlU746/rb.lsfLApjDwzaEyXrtS3ZTbcf5ZLJaWma', 12345, 'female', '2@gmail.com', 'New york', 'chair.jpg', '2023-03-28 16:49:52', b'1', NULL),
(28, 'anonymous', '$2a$10$rUGlWdLArkZwIlU746/rb.lsfLApjDwzaEyXrtS3ZTbcf5ZLJaWma', 12345, 'male', 'anhduongtrieu5@gmail.com', NULL, 'chair.jpg', '2023-04-10 18:54:36', b'1', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
CREATE TABLE IF NOT EXISTS `users_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  KEY `user_fk_idx` (`user_id`),
  KEY `role_fk_idx` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Đang đổ dữ liệu cho bảng `users_roles`
--

INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 1),
(28, 2);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
