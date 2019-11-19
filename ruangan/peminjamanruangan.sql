-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Nov 08, 2019 at 01:55 PM
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
-- Table structure for table `peminjamanruangan`
--

CREATE TABLE `peminjamanruangan` (
  `id` bigint(20) NOT NULL,
  `is_disetujui` bit(1) NOT NULL,
  `jumlah_peserta` bigint(20) NOT NULL,
  `keterangan` varchar(200) NOT NULL,
  `tanggal_selesai` datetime NOT NULL,
  `tanggal_mulai` datetime NOT NULL,
  `tujuan` varchar(200) NOT NULL,
  `waktu_mulai` varchar(200) NOT NULL,
  `waktu_selesai` varchar(200) NOT NULL,
  `id_ruang` bigint(20) DEFAULT NULL,
  `uuid_user_peminjam` varchar(255) DEFAULT NULL,
  `uuid_user_penyetuju` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `peminjamanruangan`
--

INSERT INTO `peminjamanruangan` (`id`, `is_disetujui`, `jumlah_peserta`, `keterangan`, `tanggal_selesai`, `tanggal_mulai`, `tujuan`, `waktu_mulai`, `waktu_selesai`, `id_ruang`, `uuid_user_peminjam`, `uuid_user_penyetuju`) VALUES
(1, b'0', 20, 'Gaada', '2019-11-05 00:00:00', '2019-11-06 00:00:00', 'Mewarnai', '08:00', '10:00', 1, '1', '2');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `peminjamanruangan`
--
ALTER TABLE `peminjamanruangan`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKsu1vagr1lm2p1bf5fiwfl49vf` (`id_ruang`),
  ADD KEY `FKd3pi1mgclhvehpwx5khhoyyg9` (`uuid_user_peminjam`),
  ADD KEY `FK5igdwhdwp3kds91hshpd6xsh6` (`uuid_user_penyetuju`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `peminjamanruangan`
--
ALTER TABLE `peminjamanruangan`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
