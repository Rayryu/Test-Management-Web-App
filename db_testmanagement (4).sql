-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Client :  127.0.0.1
-- Généré le :  Lun 27 Août 2018 à 14:38
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
(13, '2018-08-26 14:42:38', 'Cette campagne de test va traiter les principales exigences fonctionnelles  du projet', 'Une première campagne de tests', 1, 14),
(14, '2018-08-26 14:43:12', 'Cette campagne de test va traiter le reste des exigences fonctionnels du projet', 'Une seconde campagne de tests', 1, 14);

-- --------------------------------------------------------

--
-- Structure de la table `cas_test`
--

CREATE TABLE `cas_test` (
  `id` bigint(20) NOT NULL,
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
  `type_id` bigint(20) DEFAULT NULL,
  `resultat_obtenu` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

--
-- Contenu de la table `cas_test`
--

INSERT INTO `cas_test` (`id`, `date_creation`, `description`, `etapes`, `jeu_de_test`, `precondition`, `priorite`, `remarques`, `resultat_attendu`, `statut`, `titre`, `scenario_parent_id`, `testeur_id`, `type_id`, `resultat_obtenu`) VALUES
(15, '2018-08-26 15:02:33', 'L\'utilisateur essaye de se connecter en utilisant un nom d\'utilisateur et un mot de passe erronés', '<p>Cliquer sur le champs nom d\'utilisateur</p>\r\n<p>Entrer le nom d\'utilisateur</p>\r\n<p>Cliquer sur le champs mot de passe</p>\r\n<p>Entrer le mot de passe</p>\r\n<p>Cliquer sur le bouton s\'authentifier</p>', '{nom d\'utilisateur} {mot de passe}\r\nAhmad Gt@lY8aX\r\nAli CxZ&%UYE\r\nMouad JUq!dtOO\r\nJamal Ysi!CwCE', 'Etre présent sur la page d\'authentification', 'Haute', '', 'Message d\'erreur "Combinaison nom d\'utilisateur/mot de passe erronée"', 'Réussi', 'Nom d\'utilisateur et mot de passe erronés', 10, 1, 1, 'Message d\'erreur "Combinaison nom d\'utilisateur/mot de passe erronée"'),
(16, '2018-08-26 15:07:38', 'Ajouter un premier produit dans une caisse vide', '<p>Choisir la quantit&eacute;</p>\r\n<p>Cliquer sur mettre dans mon panier</p>\r\n<p>Confirmer&nbsp;</p>', '{idProduit}\r\n41\r\n125\r\n1\r\n98\r\n45\r\n52\r\n52\r\n', 'Etre présent sur une page de produit', 'Moyenne', '', 'Message succès : Produit ajouté dans votre panier', 'Réussi', 'Premier produit', 11, 1, 1, 'Message succès : Produit ajouté dans votre panier'),
(17, '2018-08-26 15:09:47', 'Se déconnecter du site d\'achat ', '<p>Cliquer sur le bouton se d&eacute;connecter</p>\r\n<p>Confirmer le message de deconnexion</p>', '#Aucun', 'Etre connecté', 'Faible', '', 'Redirection vers la page d\'authentification', 'Réussi', 'Deconnexion', 13, 1, 1, 'Redirection vers la page d\'authentification'),
(18, '2018-08-26 15:14:37', 'Payer l\'ensemble des produits dans le panier avec une carte VISA', '<p>Cliquer sur payer par carte</p>\r\n<p>Choisir le type de carte : VISA</p>\r\n<p>Lire les conditions d\'utilisation et confrmer</p>\r\n<p>Entrer les informations personnelles</p>\r\n<p>Confirmer les informations</p>\r\n<p>Confirmer le payement</p>', '#Des codes de carte VISA', 'Avoir des produits dans le panier\r\nEtre sur la page de payement', 'Haute', '', 'Message : Payement effectué ', 'Réussi', 'Paiement par carte VISA', 12, 1, 1, 'Message : Payement effectué '),
(19, '2018-08-27 00:39:29', 'L\'utilisateur essaye de passer un payement via son compte Paypal', '<p>Cliquer sur payer via Paypal</p>\r\n<p>Entrer les identifiants</p>\r\n<p>Confirmer le payement sur Paypal</p>\r\n<p>Confirmer la transition</p>\r\n<p>&nbsp;</p>', '#Comptes Paypal', 'Etre présent sur la page de payement\r\nAvoir au moins un produit sur son panier', 'Moyenne', '', 'Message de succès : Payement effectué ', 'Echoué', 'Paiement via Paypal', 12, 1, 1, 'Message d\'erreur'),
(20, '2018-08-27 00:39:29', 'L\'utilisateur essaye de passer un payement via son compte Paypal', '<p>Cliquer sur payer via Paypal</p>\r\n<p>Entrer les identifiants</p>\r\n<p>Confirmer le payement sur Paypal</p>\r\n<p>Confirmer la transition</p>\r\n<p>&nbsp;</p>', '#Comptes Paypal', 'Etre présent sur la page de payement\r\nAvoir au moins un produit sur son panier', 'Moyenne', '', 'Message de succès : Payement effectué ', 'Echoué', 'Paiement via Paypal', 12, 1, 1, 'Message d\'erreur'),
(21, '2018-08-26 15:02:33', 'L\'utilisateur essaye de se connecter en utilisant un nom d\'utilisateur et un mot de passe erronés', '<p>Cliquer sur le champs nom d\'utilisateur</p>\r\n<p>Entrer le nom d\'utilisateur</p>\r\n<p>Cliquer sur le champs mot de passe</p>\r\n<p>Entrer le mot de passe</p>\r\n<p>Cliquer sur le bouton s\'authentifier</p>', '{nom d\'utilisateur} {mot de passe}\r\nAhmad Gt@lY8aX\r\nAli CxZ&%UYE\r\nMouad JUq!dtOO\r\nJamal Ysi!CwCE', 'Etre présent sur la page d\'authentification', 'Haute', '', 'Message d\'erreur "Combinaison nom d\'utilisateur/mot de passe erronée"', 'Réussi', 'Nom d\'utilisateur et mot de passe erronés', 10, 1, 1, 'Message d\'erreur "Combinaison nom d\'utilisateur/mot de passe erronée"'),
(22, '2018-08-26 15:07:38', 'Ajouter un premier produit dans une caisse vide', '<p>Choisir la quantit&eacute;</p>\r\n<p>Cliquer sur mettre dans mon panier</p>\r\n<p>Confirmer&nbsp;</p>', '{idProduit}\r\n41\r\n125\r\n1\r\n98\r\n45\r\n52\r\n52\r\n', 'Etre présent sur une page de produit', 'Moyenne', '', 'Message succès : Produit ajouté dans votre panier', 'Réussi', 'Premier produit', 11, 1, 1, 'Message succès : Produit ajouté dans votre panier'),
(23, '2018-08-26 15:09:47', 'Se déconnecter du site d\'achat ', '<p>Cliquer sur le bouton se d&eacute;connecter</p>\r\n<p>Confirmer le message de deconnexion</p>', '#Aucun', 'Etre connecté', 'Faible', '', 'Redirection vers la page d\'authentification', 'Réussi', 'Deconnexion', 13, 1, 1, 'Redirection vers la page d\'authentification'),
(24, '2018-08-26 15:14:37', 'Payer l\'ensemble des produits dans le panier avec une carte VISA', '<p>Cliquer sur payer par carte</p>\r\n<p>Choisir le type de carte : VISA</p>\r\n<p>Lire les conditions d\'utilisation et confrmer</p>\r\n<p>Entrer les informations personnelles</p>\r\n<p>Confirmer les informations</p>\r\n<p>Confirmer le payement</p>', '#Des codes de carte VISA', 'Avoir des produits dans le panier\r\nEtre sur la page de payement', 'Haute', '', 'Message : Payement effectué ', 'Réussi', 'Paiement par carte VISA', 12, 1, 1, 'Message : Payement effectué '),
(25, '2018-08-27 00:39:29', 'L\'utilisateur essaye de passer un payement via son compte Paypal', '<p>Cliquer sur payer via Paypal</p>\r\n<p>Entrer les identifiants</p>\r\n<p>Confirmer le payement sur Paypal</p>\r\n<p>Confirmer la transition</p>\r\n<p>&nbsp;</p>', '#Comptes Paypal', 'Etre présent sur la page de payement\r\nAvoir au moins un produit sur son panier', 'Moyenne', '', 'Message de succès : Payement effectué ', 'Echoué', 'Paiement via Paypal', 12, 1, 1, 'Message d\'erreur'),
(26, '2018-08-27 00:39:29', 'L\'utilisateur essaye de passer un payement via son compte Paypal', '<p>Cliquer sur payer via Paypal</p>\r\n<p>Entrer les identifiants</p>\r\n<p>Confirmer le payement sur Paypal</p>\r\n<p>Confirmer la transition</p>\r\n<p>&nbsp;</p>', '#Comptes Paypal', 'Etre présent sur la page de payement\r\nAvoir au moins un produit sur son panier', 'Moyenne', '', 'Message de succès : Payement effectué ', 'Echoué', 'Paiement via Paypal', 12, 1, 1, 'Message d\'erreur'),
(27, '2018-08-26 15:07:38', 'Ajouter un premier produit dans une caisse vide', '<p>Choisir la quantit&eacute;</p>\r\n<p>Cliquer sur mettre dans mon panier</p>\r\n<p>Confirmer&nbsp;</p>', '{idProduit}\r\n41\r\n125\r\n1\r\n98\r\n45\r\n52\r\n52\r\n', 'Etre présent sur une page de produit', 'Moyenne', '', 'Message succès : Produit ajouté dans votre panier', 'Réussi', 'Premier produit', 11, 1, 1, 'Message succès : Produit ajouté dans votre panier'),
(28, '2018-08-26 15:07:38', 'Ajouter un premier produit dans une caisse vide', '<p>Choisir la quantit&eacute;</p>\r\n<p>Cliquer sur mettre dans mon panier</p>\r\n<p>Confirmer&nbsp;</p>', '{idProduit}\r\n41\r\n125\r\n1\r\n98\r\n45\r\n52\r\n52\r\n', 'Etre présent sur une page de produit', 'Moyenne', '', 'Message succès : Produit ajouté dans votre panier', 'Réussi', 'Premier produit', 11, 1, 1, 'Message succès : Produit ajouté dans votre panier');

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
(36, '', '2018-08-26 15:04:18', 'Message d\'erreur "Combinaison nom d\'utilisateur/mot de passe erronée"', 'Réussi', 15, 1),
(37, '', '2018-08-26 15:07:46', 'Message succès : Produit ajouté dans votre panier', 'Réussi', 16, 1),
(38, '', '2018-08-26 15:09:53', 'Redirection vers la page d\'authentification', 'Réussi', 17, 1),
(39, '', '2018-08-26 15:14:45', 'Message : Payement effectué ', 'Réussi', 18, 1),
(40, 'Cette fonctionnalité n\'est pas encore disponible ', '2018-08-27 00:40:13', 'Message d\'erreur', 'Echoué', 19, 1);

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
(14, '2018-08-26 14:41:02', 'présenter de façon claire et argumentée votre projet. Soyez synthétique et allez à l\'essentiel', 'Un projet quelconque');

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
(10, '2018-08-26 14:53:52', 'Connexion à l\'interface de l\'application', 'Connexion', 14),
(11, '2018-08-26 14:54:44', 'Ajouter de nouveaux produits au panier', 'Ajout de produits', 13),
(12, '2018-08-26 14:55:06', 'Paiement des différents produits dans le panier', 'Paiement', 13),
(13, '2018-08-26 14:55:21', 'Déconnexion du compte personnel ', 'Déconnexion', 14);

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
(3, 'testeur@gmail.com', b'0', 'testeur', 'testeur', 'testeur', 2),
(2, 'Manager@gmail.com', b'1', 'dev', 'Manager', 'Manager', 3),
(5, 'AAAAAA', b'0', 'AAAAA', 'AAAAAAAAAA', 'AAAAAA', 2);

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
(1, 14),
(3, 14),
(2, 14),
(5, 14);

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
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT pour la table `cas_test`
--
ALTER TABLE `cas_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;
--
-- AUTO_INCREMENT pour la table `execution_test`
--
ALTER TABLE `execution_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
--
-- AUTO_INCREMENT pour la table `projet`
--
ALTER TABLE `projet`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT pour la table `role`
--
ALTER TABLE `role`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `scenario`
--
ALTER TABLE `scenario`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT pour la table `type_test`
--
ALTER TABLE `type_test`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
