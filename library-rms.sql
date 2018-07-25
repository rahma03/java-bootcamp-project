-- phpMyAdmin SQL Dump
-- version 4.8.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 25, 2018 at 10:31 AM
-- Server version: 10.1.33-MariaDB
-- PHP Version: 7.2.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library-rms`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `id` int(100) NOT NULL,
  `author` varchar(100) NOT NULL,
  `title` varchar(150) NOT NULL,
  `stock` int(3) NOT NULL,
  `create_by` varchar(20) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_by` varchar(20) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`id`, `author`, `title`, `stock`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES
(1, 'J.K. Rowling', 'Harry Potter and The Sorcerer\'s Stone', 2, NULL, '2018-07-25 07:45:51', NULL, '2018-07-25 07:45:51');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `fullname` varchar(50) NOT NULL,
  `birthdate` date NOT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `login_status` int(1) NOT NULL DEFAULT '0',
  `block_status` int(1) NOT NULL DEFAULT '0',
  `pin_try` int(1) NOT NULL DEFAULT '0',
  `created_by` varchar(20) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`fullname`, `birthdate`, `alamat`, `username`, `password`, `login_status`, `block_status`, `pin_try`, `created_by`, `created_date`) VALUES
('Rahmawati', '1994-02-03', 'Jl. Tirtawangi 3 no. 5', 'amhar03', 'YmFza2V0YmFsbA==', 0, 0, 0, 'admin', '2018-07-25 07:17:43'),
('Rahmawati', '1945-03-03', 'Jl. Tirtawangi 3 no. 5', 'rahma', 'YmFza2V0YmFsbA==', 0, 0, 0, 'admin', '2018-07-24 09:02:47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`username`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
