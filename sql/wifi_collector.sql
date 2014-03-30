-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Client: localhost
-- Généré le: Dim 30 Mars 2014 à 15:18
-- Version du serveur: 5.6.12-log
-- Version de PHP: 5.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `wifi_collector`
--
CREATE DATABASE IF NOT EXISTS `wifi_collector` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `wifi_collector`;

-- --------------------------------------------------------

--
-- Structure de la table `encryption`
--

CREATE TABLE IF NOT EXISTS `encryption` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Contenu de la table `encryption`
--

INSERT INTO `encryption` (`id`, `type`, `name`) VALUES
(1, 'WPA', ''),
(2, 'WEP', ''),
(3, 'WPA2', 'TKIP'),
(4, 'WPA2', 'CCMP'),
(5, 'None', 'no encryption');

-- --------------------------------------------------------

--
-- Structure de la table `hotspot`
--

CREATE TABLE IF NOT EXISTS `hotspot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `essid` varchar(255) NOT NULL,
  `signal_level` int(11) NOT NULL,
  `signal_quality` varchar(255) NOT NULL,
  `rates` int(11) NOT NULL,
  `encryption` int(11) NOT NULL,
  `frequency` int(11) NOT NULL,
  `extra_1` text NOT NULL,
  `extra_2` text NOT NULL,
  `extra_3` text NOT NULL,
  `x` decimal(10,0) NOT NULL DEFAULT '0',
  `y` decimal(10,0) NOT NULL DEFAULT '0',
  `z` decimal(10,0) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `encryption` (`encryption`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `hotspot`
--
ALTER TABLE `hotspot`
  ADD CONSTRAINT `encryption` FOREIGN KEY (`encryption`) REFERENCES `encryption` (`id`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
