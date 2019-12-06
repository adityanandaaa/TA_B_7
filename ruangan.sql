-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 08, 2019 at 01:57 PM
-- Server version: 10.4.6-MariaDB
-- PHP Version: 7.1.32

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
-- Table structure for table `ruangan`
--

CREATE TABLE `ruangan` (
  `id` bigint(20) NOT NULL,
  `kapasitas` bigint(20) NOT NULL,
  `nama` varchar(200) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruangan`
--

INSERT INTO `ruangan` (`id`, `kapasitas`, `nama`) VALUES
(1, 45, 'Kelas X-1'),
(2, 45, 'Kelas X-2'),
(3, 45, 'Kelas X-3'),
(4, 45, 'Kelas X-4'),
(5, 45, 'Kelas X-5'),
(6, 45, 'Kelas X-6'),
(7, 45, 'Kelas X-7'),
(8, 45, 'Kelas X-8'),
(9, 45, 'Kelas X-9'),
(10, 45, 'Kelas X-10'),
(11, 45, 'Kelas X-11'),
(12, 45, 'Kelas X-12'),
(13, 45, 'Kelas XI-1'),
(14, 45, 'Kelas XI-2'),
(15, 45, 'Kelas XI-3'),
(16, 45, 'Kelas XI-4'),
(17, 45, 'Kelas XI-5'),
(18, 45, 'Kelas XI-6'),
(19, 45, 'Kelas XI-7'),
(20, 45, 'Kelas XI-8'),
(21, 45, 'Kelas XI-9'),
(22, 45, 'Kelas XI-10'),
(23, 45, 'Kelas XI-11'),
(24, 45, 'Kelas XI-12'),
(25, 45, 'Kelas XII-1'),
(26, 45, 'Kelas XII-2'),
(27, 45, 'Kelas XII-3'),
(28, 45, 'Kelas XII-4'),
(29, 45, 'Kelas XII-5'),
(30, 45, 'Kelas XII-6'),
(31, 45, 'Kelas XII-7'),
(32, 45, 'Kelas XII-8'),
(33, 45, 'Kelas XII-9'),
(34, 45, 'Kelas XII-10'),
(35, 45, 'Kelas XII-11'),
(36, 45, 'Kelas XII-12'),
(37, 100, 'Perpustakaan'),
(38, 75, 'Laboratorium 1'),
(39, 75, 'Laboratorium 2'),
(40, 75, 'Laboratorium 3'),
(41, 75, 'Laboratorium 4'),
(42, 75, 'Laboratorium 5'),
(43, 40, 'Olahraga 1'),
(44, 40, 'Olahraga 2'),
(45, 100, 'Olahraga 3'),
(46, 300, 'Aula'),
(47, 30, 'Kesehatan (UKS)'),
(48, 10, 'Koperasi'),
(49, 50, 'Tata Usaha'),
(50, 30, 'Guru 1'),
(51, 30, 'Guru 2'),
(52, 30, 'Guru 3'),
(53, 10, 'Kepala Sekolah'),
(54, 50, 'Serba Guna 1'),
(55, 50, 'Serba Guna 2'),
(56, 100, 'Serba Guna 3');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ruangan`
--
ALTER TABLE `ruangan`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ruangan`
--
ALTER TABLE `ruangan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=57;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
