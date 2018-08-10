-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Ven 10 Août 2018 à 14:39
-- Version du serveur :  5.6.36-log
-- Version de PHP :  5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `db_testmanagement`
--

-- --------------------------------------------------------

--
-- Structure de la table `campagne_test`
--

CREATE TABLE `campagne_test` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `concepteur_test_id` bigint(20) DEFAULT NULL,
  `projet_parent_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `campagne_test`
--

INSERT INTO `campagne_test` (`id`, `date_creation`, `description`, `nom`, `concepteur_test_id`, `projet_parent_id`) VALUES
(1, '2018-08-01 00:00:00', 'Description de la campagne de tests', 'Campagne de test1', 1, 1),
(3, '2018-08-09 23:57:27', 'Campagne de test 2', 'Campagne de test 2', 2, 1),
(8, '2018-08-10 15:20:15', 'Desc', 'Campagne 3', 1, 3),
(9, '2018-08-10 15:20:23', 'Desc', 'Campagne 4', 1, 3);

-- --------------------------------------------------------

--
-- Structure de la table `cas_test`
--

CREATE TABLE `cas_test` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `etapes` varchar(255) DEFAULT NULL,
  `jeu_de_test` varchar(255) DEFAULT NULL,
  `precondition` varchar(255) DEFAULT NULL,
  `priorite` varchar(255) DEFAULT NULL,
  `remarques` varchar(255) DEFAULT NULL,
  `resultat_attendu` varchar(255) DEFAULT NULL,
  `statut` varchar(255) DEFAULT NULL,
  `titre` varchar(255) DEFAULT NULL,
  `scenario_parent_id` bigint(20) DEFAULT NULL,
  `testeur_id` bigint(20) DEFAULT NULL,
  `type_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cas_test`
--

INSERT INTO `cas_test` (`id`, `commentaire`, `date_creation`, `description`, `etapes`, `jeu_de_test`, `precondition`, `priorite`, `remarques`, `resultat_attendu`, `statut`, `titre`, `scenario_parent_id`, `testeur_id`, `type_id`) VALUES
(1, NULL, '2018-08-01 00:00:00', 'Description du cas de test', '<p>Etape1... Etape2....</p>', 'jeu de données', 'Préconditions...', 'Moyenne', 'Remarques...', 'Connexion établie', NULL, 'Titre du cas de test', 1, 1, 1),
(2, NULL, '2018-08-08 00:00:00', 'Description du cas de test2', '<p>Etape1... Etape2....2</p>', 'jeu de donnees', 'Préconditions...2', 'Moyenne', 'Remarques...2', 'Connexion établie2', NULL, 'Titre du cas de test', 1, 1, 1),
(3, NULL, '2018-08-09 21:00:30', 'description du cas de test', '<p>az</p>', 'donnees...', 'preconditions ...', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 2, 1, 1),
(4, NULL, '2018-08-10 01:52:51', 'description du cas de test', '<p>a</p>', 'donnees...', 'preconditions ...', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 3, 2, 1),
(5, NULL, '2018-08-10 01:53:24', 'description du cas de test', '<p>e</p>', 'donnees...', 'preconditions ...', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 3, 2, 1),
(6, NULL, '2018-08-10 01:56:32', 'description du cas de test', '<p>a</p>', 'donnees...', 'eaze', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 4, 3, 1),
(7, NULL, '2018-08-10 15:20:56', 'Description', '<p>Etape1...</p>\r\n<p>Etape2...</p>', 'Données....', 'Précondition 1...', 'Faible', 'des remarques', 'résultats...', NULL, 'Cas de test', 5, 1, 1),
(8, NULL, '2018-08-10 15:27:37', 'description du cas de test', '<p>Etape1...</p>\r\n<p>Etape2...a</p>', 'des donnees...', 'preconditions ...', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 1, 3, 1),
(9, NULL, '2018-08-10 15:28:41', 'description du cas de test', '<p>Etape1...</p>\r\n<p>Etape2...</p>', 'des donnees...', 'preconditions ..', 'Faible', 'des remarques', 'résultats...', NULL, 'Titre du cas de test', 6, 3, 1);

-- --------------------------------------------------------

--
-- Structure de la table `execution_test`
--

CREATE TABLE `execution_test` (
  `id` bigint(20) NOT NULL,
  `commentaire` varchar(255) DEFAULT NULL,
  `date_creation` datetime DEFAULT NULL,
  `resultat_obtenu` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `cas_test_parent_id` bigint(20) DEFAULT NULL,
  `testeur_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `execution_test`
--

INSERT INTO `execution_test` (`id`, `commentaire`, `date_creation`, `resultat_obtenu`, `status`, `cas_test_parent_id`, `testeur_id`) VALUES
(1, 'za', '2018-08-08 11:12:11', 'za', 'Réussi', 1, 1),
(2, 'eaz', '2018-08-08 11:12:15', 'zea', 'Réussi', 1, 1),
(3, 'z', '2018-08-08 11:12:26', 'g', 'Bloqué', 1, 1),
(4, 'e', '2018-08-09 09:30:24', 'eazeaz', 'Echoué', 1, 1),
(5, 'a', '2018-08-09 11:42:06', 'a', 'Echoué', 1, 1),
(6, 'az', '2018-08-09 21:00:37', '', 'Réussi', 3, 1),
(7, 'az', '2018-08-09 21:00:44', 'zaeezaeaz', 'Réussi', 3, 1),
(8, 'eazeaz', '2018-08-09 21:00:53', 'aze', 'Echoué', 3, 1),
(9, 'aeazeazeaz', '2018-08-09 21:00:56', 'aze', 'Réussi', 3, 1),
(10, 'eazeaz', '2018-08-09 21:01:01', 'azeaze', 'Bloqué', 3, 1),
(11, 'azeazeaz', '2018-08-09 21:01:06', 'azeaze', 'Echoué', 3, 1),
(12, 'Commentaire', '2018-08-10 01:18:57', '', 'Réussi', 2, 2),
(13, 'eazeaz', '2018-08-10 01:56:40', 'azeaze', 'Réussi', 6, 3),
(14, 'aezeaz', '2018-08-10 01:56:44', 'aze', 'Echoué', 6, 3),
(15, 'commentaire\r\n', '2018-08-10 15:21:10', 'resultat', 'Réussi', 7, 1),
(16, 'aaaa', '2018-08-10 15:27:01', 'aaaa', 'Echoué', 6, 3),
(17, 'azeaz', '2018-08-10 15:27:50', 'azeaz', 'Réussi', 2, 3),
(18, 'aaaaaaaaa', '2018-08-10 15:28:46', 'aaaa', 'Réussi', 9, 3),
(19, 'zzzzzz', '2018-08-10 15:28:52', 'zzz', 'Echoué', 9, 3);

-- --------------------------------------------------------

--
-- Structure de la table `projet`
--

CREATE TABLE `projet` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `projet`
--

INSERT INTO `projet` (`id`, `date_creation`, `description`, `nom`) VALUES
(1, '2018-07-29 09:30:05', 'Description du premier projet', 'Projet 1'),
(2, '2018-08-09 09:30:05', 'DESCRIPTION', 'Un autre projet'),
(3, '2018-08-09 09:30:05', 'DESCRIPTION', 'Un troisième projet');

-- --------------------------------------------------------

--
-- Structure de la table `role`
--

CREATE TABLE `role` (
  `id` bigint(20) NOT NULL,
  `nom` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `role`
--

INSERT INTO `role` (`id`, `nom`) VALUES
(1, 'Admin'),
(2, 'Testeur'),
(3, 'Manager');

-- --------------------------------------------------------

--
-- Structure de la table `scenario`
--

CREATE TABLE `scenario` (
  `id` bigint(20) NOT NULL,
  `date_creation` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `campagne_parent_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `scenario`
--

INSERT INTO `scenario` (`id`, `date_creation`, `description`, `nom`, `campagne_parent_id`) VALUES
(1, '2018-08-01 21:00:21', 'L\'utilisateur essaye de s\'authentifier en entrant son login et mot de passe', 'Authentification', 1),
(2, '2018-08-09 21:00:21', 'L\'utilisateur essaye de faire un achat', 'Achat', 1),
(3, '2018-08-10 01:52:44', 'L\'utilisateur veut se déconnecter', 'Déconnexion', 1),
(4, '2018-08-10 01:56:23', 'L\'utilisateur veut supprimer un achat', 'Supprimer achat', 1),
(5, '2018-08-10 15:20:31', 'DEsc', 'Scenario 1', 8),
(6, '2018-08-10 15:28:29', 'eaze', 'eaze', 9);

-- --------------------------------------------------------

--
-- Structure de la table `type_test`
--

CREATE TABLE `type_test` (
  `id` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `type_test`
--

INSERT INTO `type_test` (`id`, `type`) VALUES
(1, 'Test Fonctionnel'),
(2, 'Test de stress'),
(3, 'Test boîte noire'),
(4, 'Test d\'utilisabilité');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `enabled` bit(1) NOT NULL,
  `mot_de_pass` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `enabled`, `mot_de_pass`, `nom`, `prenom`, `role_id`) VALUES
(1, 'o.elbahaoui@gmail.com', b'1', 'dev', 'El Bahaoui ', 'Oussama', 1),
(2, 'Manager@gmail.com', b'1', 'manager', 'Manager ', 'Manager', 3),
(3, 'Testeur@gmail.com', b'1', 'dev', 'Testeur', 'Testeur', 2);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur_projet`
--

CREATE TABLE `utilisateur_projet` (
  `utilisateur_id` bigint(20) NOT NULL,
  `projet_id` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `utilisateur_projet`
--

INSERT INTO `utilisateur_projet` (`utilisateur_id`, `projet_id`) VALUES
(1, 7),
(1, 5),
(1, 1),
(1, 4),
(1, 6),
(1, 8),
(3, 1),
(1, 2),
(1, 3),
(2, 1),
(3, 2),
(3, 9),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(3, 5),
(3, 6),
(3, 7),
(3, 8),
(3, 9),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(2, 5),
(2, 6),
(2, 7),
(2, 8),
(2, 9);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `campagne_test`
--
ALTER TABLE `campagne_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKn4ikgfw9php73dp1i8ogp8gq2` (`concepteur_test_id`),
  ADD KEY `FKa077ipmb25ov20vgg3y9jr8bh` (`projet_parent_id`);

--
-- Index pour la table `cas_test`
--
ALTER TABLE `cas_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKq43elbxtk83vhod4jg20bs3aw` (`scenario_parent_id`),
  ADD KEY `FKqwatyofdhlu2ecpnh2mjq5rg1` (`testeur_id`),
  ADD KEY `FKd8hh3i9f73hh02xn4p1y37fom` (`type_id`);

--
-- Index pour la table `execution_test`
--
ALTER TABLE `execution_test`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK98y6fokae1wfw5kyni8bbahha` (`cas_test_parent_id`),
  ADD KEY `FK3wqkubfs9sof8cecycd700kj4` (`testeur_id`);

--
-- Index pour la table `projet`
--
ALTER TABLE `projet`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `scenario`
--
ALTER TABLE `scenario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh54ihic81xi3dtrw5dijheg78` (`campagne_parent_id`);

--
-- Index pour la table `type_test`
--
ALTER TABLE `type_test`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKaqe8xtajee4k0wlqrvh2pso4l` (`role_id`);

--
-- Index pour la table `utilisateur_projet`
--
ALTER TABLE `utilisateur_projet`
  ADD KEY `FKhw54xf5u2xaan4sbl8v3chvsl` (`projet_id`),
  ADD KEY `FK2nkanv611mpp46a4q3q4ymr14` (`utilisateur_id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `campagne_test`
--
ALTER TABLE `campagne_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `cas_test`
--
ALTER TABLE `cas_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `execution_test`
--
ALTER TABLE `execution_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `scenario`
--
ALTER TABLE `scenario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `type_test`
--
ALTER TABLE `type_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
