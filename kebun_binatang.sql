-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 15, 2026 at 01:23 PM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kebun_binatang`
--

-- --------------------------------------------------------

--
-- Table structure for table `hewan`
--

CREATE TABLE `hewan` (
  `id` int NOT NULL,
  `nama` varchar(100) NOT NULL,
  `jenis` varchar(100) NOT NULL,
  `umur` int NOT NULL,
  `habitat` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hewan`
--

INSERT INTO `hewan` (`id`, `nama`, `jenis`, `umur`, `habitat`) VALUES
(1, 'Leo', 'Singa', 5, 'Savana'),
(2, 'Kiko', 'Gajah', 8, 'Hutan Tropis'),
(3, 'Nemo', 'Lumba-lumba', 4, 'Kolam Laut Buatan'),
(4, 'Toby', 'Panda', 3, 'Hutan Bambu');

-- --------------------------------------------------------

--
-- Table structure for table `hewan_air`
--

CREATE TABLE `hewan_air` (
  `id` int NOT NULL,
  `kedalamanRenangMeter` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hewan_air`
--

INSERT INTO `hewan_air` (`id`, `kedalamanRenangMeter`) VALUES
(3, 200);

-- --------------------------------------------------------

--
-- Table structure for table `hewan_darat`
--

CREATE TABLE `hewan_darat` (
  `id` int NOT NULL,
  `kecepatanLariKmJam` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hewan_darat`
--

INSERT INTO `hewan_darat` (`id`, `kecepatanLariKmJam`) VALUES
(1, 80),
(2, 40),
(4, 40);

-- --------------------------------------------------------

--
-- Table structure for table `perawatan`
--

CREATE TABLE `perawatan` (
  `idPerawatan` int NOT NULL,
  `idHewan` int NOT NULL,
  `jenisPerawatan` varchar(150) NOT NULL,
  `tanggal` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `perawatan`
--

INSERT INTO `perawatan` (`idPerawatan`, `idHewan`, `jenisPerawatan`, `tanggal`) VALUES
(101, 1, 'Vaksinasi', '01-01-2026'),
(102, 2, 'Pemeriksaan Rutin', '15-02-2026'),
(103, 3, 'Pemeriksaan Sirip', '20-03-2026'),
(104, 4, 'Pengecekan Rutin', '15-10-2026');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hewan`
--
ALTER TABLE `hewan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hewan_air`
--
ALTER TABLE `hewan_air`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `hewan_darat`
--
ALTER TABLE `hewan_darat`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `perawatan`
--
ALTER TABLE `perawatan`
  ADD PRIMARY KEY (`idPerawatan`),
  ADD UNIQUE KEY `idHewan` (`idHewan`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `hewan_air`
--
ALTER TABLE `hewan_air`
  ADD CONSTRAINT `hewan_air_ibfk_1` FOREIGN KEY (`id`) REFERENCES `hewan` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `hewan_darat`
--
ALTER TABLE `hewan_darat`
  ADD CONSTRAINT `hewan_darat_ibfk_1` FOREIGN KEY (`id`) REFERENCES `hewan` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `perawatan`
--
ALTER TABLE `perawatan`
  ADD CONSTRAINT `perawatan_ibfk_1` FOREIGN KEY (`idHewan`) REFERENCES `hewan` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
