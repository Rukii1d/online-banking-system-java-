-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2024 at 05:24 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `onlinebanking_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_signup`
--

CREATE TABLE `tb_signup` (
  `fd_AccountId` int(11) NOT NULL,
  `fdName` varchar(100) DEFAULT NULL,
  `fd_PhoneNumber` varchar(15) DEFAULT NULL,
  `fdPinCode` varchar(10) DEFAULT NULL,
  `fd_AccountType` varchar(20) DEFAULT NULL,
  `fd_Address` varchar(255) DEFAULT NULL,
  `fd_Gender` varchar(10) DEFAULT NULL,
  `fd_NIC` varchar(50) DEFAULT NULL,
  `fd_Dob` date DEFAULT NULL,
  `fd_Amount` decimal(10,2) DEFAULT 0.00,
  `fdAccountId` int(11) DEFAULT NULL,
  `fdPhoneNumber` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_signup`
--

INSERT INTO `tb_signup` (`fd_AccountId`, `fdName`, `fd_PhoneNumber`, `fdPinCode`, `fd_AccountType`, `fd_Address`, `fd_Gender`, `fd_NIC`, `fd_Dob`, `fd_Amount`, `fdAccountId`, `fdPhoneNumber`) VALUES
(3, 'tom', '0987775', '2323', 'Saving', 'Sri lanka,Kandy', 'Male', '44567890', '2000-09-08', 15240.00, NULL, '078644443'),
(4, 'lio', NULL, '0000', 'Saving', 'Gotham City,US', 'Male', '8907745563V', '1989-08-07', 10939.00, NULL, '986464533'),
(5, 'eeeeeee', NULL, '4444', 'Saving', 'sl', 'Male', '666666', '0200-09-12', 0.00, NULL, '78888'),
(6, 'praneeth', NULL, '8989', 'Saving', 'katunayake,Kandy', 'Male', '200145663', '2001-01-23', 1590.00, NULL, '07864533'),
(7, 'Rukshan', NULL, '2000', 'Saving', 'NO.12, Kundasale road, Kandy', 'Male', '200027404185', '1999-09-30', 840.00, NULL, '0760510968'),
(8, 'Ranil Wickramasinghe', NULL, '2323', 'Saving', 'Colombo 07', 'Male', '476893836V', '1947-02-23', 0.00, NULL, '0813847415'),
(9, 'Mahinda Rajapakshe', NULL, '3434', 'Saving', 'Colombo 07', 'Male', '478634908V', '1947-09-08', 0.00, NULL, '07866363');

-- --------------------------------------------------------

--
-- Table structure for table `tb_transactions`
--

CREATE TABLE `tb_transactions` (
  `fd_TransactionId` int(11) NOT NULL,
  `fd_SenderId` varchar(10) DEFAULT NULL,
  `fd_RecieverId` varchar(10) DEFAULT NULL,
  `fd_Amount` decimal(10,2) DEFAULT NULL,
  `fd_DateTime` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tb_transactions`
--

INSERT INTO `tb_transactions` (`fd_TransactionId`, `fd_SenderId`, `fd_RecieverId`, `fd_Amount`, `fd_DateTime`) VALUES
(3, '2323', '8', 100.00, '2024-08-22 14:29:24'),
(4, '2323', NULL, 1000.00, '2024-08-22 15:08:15'),
(5, '2323', NULL, 1000.00, '2024-08-22 15:08:16'),
(6, '2323', NULL, 10000.00, '2024-08-22 15:08:20'),
(7, '2323', '2', 10.00, '2024-08-22 15:08:26'),
(8, '2323', '44444', 10.00, '2024-08-22 15:08:31'),
(9, '2323', '2', 1000.00, '2024-08-22 15:10:12'),
(10, '2323', '4', 100.00, '2024-08-22 15:58:59'),
(11, '0000', '0000', 10.00, '2024-08-22 16:08:45'),
(12, '0000', '0000', 12.00, '2024-08-22 16:08:51'),
(13, '0000', '0000', 100.00, '2024-08-22 16:08:58'),
(14, '0000', '0000', 20000.00, '2024-08-22 16:09:07'),
(15, '0000', '2323', 100.00, '2024-08-22 16:09:25'),
(16, '0000', '0000', 100.00, '2024-08-22 16:09:32'),
(17, '0000', '0000', 10.00, '2024-08-22 16:15:33'),
(18, '0000', '0000', 23.00, '2024-08-22 16:15:39'),
(19, '0000', '0000', 23.00, '2024-08-22 16:15:44'),
(20, '0000', '0000', 23.00, '2024-08-22 16:15:48'),
(21, '0000', '0000', 1000.00, '2024-08-22 16:16:04'),
(22, '0000', '0000', 1000.00, '2024-08-22 16:18:01'),
(23, '4', '4', 10.00, '2024-08-22 16:24:35'),
(24, '4', '4', 10.00, '2024-08-22 16:24:41'),
(25, '4', '1', 100.00, '2024-08-22 16:25:00'),
(26, '3', '4', 1600.00, '2024-08-22 16:25:58'),
(27, '6', '6', 2000.00, '2024-08-22 16:27:29'),
(28, '6', '6', 1000.00, '2024-08-22 16:27:37'),
(29, '6', '4', 500.00, '2024-08-22 16:27:58'),
(30, '4', '6', 12000.00, '2024-08-22 16:46:00'),
(31, '6', '4', 12000.00, '2024-08-22 16:47:03'),
(32, '6', '6', 40.00, '2024-08-22 16:47:52'),
(33, '4', '4', 12.00, '2024-08-24 12:45:43'),
(34, '4', '4', 12.00, '2024-08-24 12:45:48'),
(35, '4', '1', 100.00, '2024-08-24 12:46:04'),
(36, '7', '7', 2000.00, '2024-08-24 13:55:13'),
(37, '7', '7', 1000.00, '2024-08-24 13:55:20'),
(38, '7', '6', 50.00, '2024-08-24 13:55:39'),
(39, '7', '6', 50.00, '2024-08-24 13:56:03'),
(40, '7', '6', 50.00, '2024-08-24 13:56:24'),
(41, '4', '4', 10.00, '2024-08-24 14:20:42'),
(42, '4', '4', 10.00, '2024-08-24 14:20:48'),
(43, '4', '7', 1000.00, '2024-08-24 14:21:23'),
(44, '7', '7', 50.00, '2024-08-24 14:57:30'),
(45, '7', '7', 50.00, '2024-08-24 14:57:35'),
(46, '7', '6', 850.00, '2024-08-24 14:58:17'),
(47, '7', '9', 50.00, '2024-08-24 14:58:40'),
(48, '7', '6', 50.00, '2024-08-24 15:09:12'),
(49, '7', '10', 60.00, '2024-08-24 15:10:04'),
(50, '9', '9', 20.00, '2024-08-24 15:22:46'),
(51, '9', '9', 20.00, '2024-08-24 15:22:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_signup`
--
ALTER TABLE `tb_signup`
  ADD PRIMARY KEY (`fd_AccountId`);

--
-- Indexes for table `tb_transactions`
--
ALTER TABLE `tb_transactions`
  ADD PRIMARY KEY (`fd_TransactionId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_signup`
--
ALTER TABLE `tb_signup`
  MODIFY `fd_AccountId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `tb_transactions`
--
ALTER TABLE `tb_transactions`
  MODIFY `fd_TransactionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
