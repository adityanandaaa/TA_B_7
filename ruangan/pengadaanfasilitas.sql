-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 08, 2019 at 11:25 AM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.3.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `ruangan`
--

-- --------------------------------------------------------

--
-- Table structure for table `pengadaanfasilitas`
--

CREATE TABLE `pengadaanfasilitas` (
  `id` bigint(20) NOT NULL,
  `harga` bigint(20) NOT NULL,
  `jumlah` bigint(20) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `status` int(11) NOT NULL,
  `uuid_user` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pengadaanfasilitas`
--

INSERT INTO `pengadaanfasilitas` (`id`, `harga`, `jumlah`, `nama`, `status`, `uuid_user`) VALUES
(5, 50000, 3, 'Penghapus spidol', 0, 1),
(2, 2000000, 1, 'Air Conditioner', 1, 1),
(3, 700000, 3, 'kursi', 3, 1),
(4, 10000, 5, 'Spidol Papan Tulis', 2, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `pengadaanfasilitas`
--
ALTER TABLE `pengadaanfasilitas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK8lsdhp8igsr67qb20ana4r9fu` (`uuid_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pengadaanfasilitas`
--
ALTER TABLE `pengadaanfasilitas`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
