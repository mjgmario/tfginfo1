-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 29-05-2022 a las 21:29:38
-- Versión del servidor: 10.5.12-MariaDB
-- Versión de PHP: 7.3.32

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id18842187_androidpartyingout`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chats`
--

CREATE TABLE `chats` (
  `id_chat` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `foto_perfil_chat` blob DEFAULT NULL,
  `nombre` varchar(50) NOT NULL,
  `id_admin` int(11) NOT NULL,
  `fecha_ultimo_mensaje` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `chats`
--

INSERT INTO `chats` (`id_chat`, `fecha_creacion`, `foto_perfil_chat`, `nombre`, `id_admin`, `fecha_ultimo_mensaje`) VALUES
(1, '2022-05-07 19:25:27', NULL, 'jugones', 10, '2022-04-25 11:48:26'),
(2, '2022-05-16 12:38:10', NULL, 'equipo', 3, '0000-00-00 00:00:00'),
(3, '2022-05-16 12:38:24', NULL, 'fiesteros', 3, '2022-05-08 12:09:14'),
(4, '2022-05-16 12:39:34', NULL, 'bloque', 1, '0000-00-00 00:00:00'),
(5, '2022-05-16 12:39:46', NULL, 'grupo', 1, '0000-00-00 00:00:00'),
(6, '2022-05-16 12:42:12', NULL, 'nenas', 1, '2022-02-25 11:48:26'),
(7, '2022-05-16 12:44:56', NULL, 'nenas', 1, '2022-04-25 13:48:26'),
(8, '2022-04-21 12:52:31', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(9, '2022-04-21 12:52:32', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(10, '2022-04-21 12:52:33', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(11, '2022-04-21 12:52:33', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(12, '2022-04-21 12:52:33', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(13, '2022-04-21 12:52:34', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(14, '2022-04-21 12:52:35', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(15, '2022-04-21 12:52:36', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(16, '2022-04-21 12:52:38', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(17, '2022-04-21 12:52:38', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(18, '2022-04-21 12:52:39', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(19, '2022-04-21 12:52:40', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(20, '2022-04-21 12:52:41', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(21, '2022-04-21 12:52:42', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(22, '2022-04-21 12:52:42', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(23, '2022-04-21 12:52:43', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(24, '2022-04-21 12:52:44', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(25, '2022-04-21 13:16:27', NULL, 'nenas', 1, '0000-00-00 00:00:00'),
(26, '2022-05-16 12:44:39', NULL, 'ola', 1, '2022-04-25 11:50:26'),
(27, '2022-05-16 12:43:46', NULL, 'Madrid', 1, '2022-03-25 11:48:26'),
(28, '2022-05-07 19:24:13', NULL, 'Iván Mario\n', 1, '2022-05-07 18:23:02'),
(31, '2022-05-16 12:35:43', NULL, 'eeee', 12, '2022-05-16 12:35:43'),
(32, '2022-05-16 12:44:03', NULL, 'niños', 12, '2022-04-27 11:48:26'),
(33, '2022-05-16 13:08:43', NULL, 'piso', 1, '2022-05-16 13:08:43'),
(34, '2022-05-16 17:11:30', NULL, 'pisito', 1, '2022-05-16 17:11:30'),
(36, '2022-05-16 17:10:33', NULL, 'pisito', 1, '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `chat_local`
--

CREATE TABLE `chat_local` (
  `id_chat_local` int(11) NOT NULL,
  `nombre_chat` varchar(50) NOT NULL,
  `id_local` int(11) NOT NULL,
  `fecha_creacion` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `chat_local`
--

INSERT INTO `chat_local` (`id_chat_local`, `nombre_chat`, `id_local`, `fecha_creacion`, `descripcion`) VALUES
(2, 'andress', 3, '2022-04-20 08:39:12', 'el mejor'),
(3, 'andress', 4, '2022-04-20 08:39:19', 'vamooooos'),
(4, 'juan', 4, '2022-04-20 08:39:26', 'juaanit'),
(5, 'juaniiiiii', 4, '2022-04-20 08:40:54', 'el mejor chat'),
(6, 'pacha chat', 1, '2022-04-29 10:25:39', 'chat oficial'),
(7, 'ola', 1, '2022-05-08 10:06:29', 'eeyyyy'),
(8, 'amiguis', 1, '2022-05-16 12:58:22', 'los mejores'),
(9, 'fieston', 1, '2022-05-16 17:09:14', 'buena fiesta'),
(10, 'fiestecilla', 1, '2022-05-16 17:09:44', 'de tranquis'),
(11, 'enrique', 1, '2022-05-26 10:49:06', 'mojon');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `descripcion`
--

CREATE TABLE `descripcion` (
  `id_user` int(11) NOT NULL,
  `Contenido` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `descripcion`
--

INSERT INTO `descripcion` (`id_user`, `Contenido`) VALUES
(1, 'fdi'),
(3, 'el mejor'),
(12, 'jj');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `evento`
--

CREATE TABLE `evento` (
  `id_evento` int(11) NOT NULL,
  `id_local` int(11) NOT NULL,
  `nombre_evento` varchar(50) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `descrip` varchar(100) NOT NULL,
  `edad_min` int(11) NOT NULL,
  `edad_max` int(11) NOT NULL,
  `precio_copas` int(11) NOT NULL,
  `precio` int(11) NOT NULL,
  `n_clicks` int(100) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `evento`
--

INSERT INTO `evento` (`id_evento`, `id_local`, `nombre_evento`, `fecha`, `descrip`, `edad_min`, `edad_max`, `precio_copas`, `precio`, `n_clicks`) VALUES
(2, 4, 'fiesta de ligar', '2022-05-13 07:26:47', 'buen chat', 18, 25, 10, 20, 4),
(3, 1, 'graduaciones', '2022-05-16 12:56:58', 'fiestas de graduaciones', 18, 30, 10, 20, 14);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto_perfil`
--

CREATE TABLE `foto_perfil` (
  `id_foto_perfil` varchar(11) COLLATE utf8_unicode_ci NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `foto_perfil`
--

INSERT INTO `foto_perfil` (`id_foto_perfil`, `id_user`) VALUES
('1.jpg', 1),
('2.jpg', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto_publicacion`
--

CREATE TABLE `foto_publicacion` (
  `id_publicacion` int(11) NOT NULL,
  `id_foto_publicacion` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `foto_publicacion`
--

INSERT INTO `foto_publicacion` (`id_publicacion`, `id_foto_publicacion`) VALUES
(14, '14.jpg'),
(15, '15.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `imagenes`
--

CREATE TABLE `imagenes` (
  `id` char(30) NOT NULL,
  `imagen` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `local`
--

CREATE TABLE `local` (
  `id_local` int(11) NOT NULL,
  `imagen` blob NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `lat` double NOT NULL,
  `longi` double NOT NULL,
  `n_clicks` int(11) NOT NULL DEFAULT 0,
  `Precio` double NOT NULL,
  `edad_min` int(11) NOT NULL,
  `edad_max` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `local`
--

INSERT INTO `local` (`id_local`, `imagen`, `nombre`, `descripcion`, `lat`, `longi`, `n_clicks`, `Precio`, `edad_min`, `edad_max`) VALUES
(1, '', 'Pacha', 'hola', 40.42710080562606, -3.7000452227539156, 28, 15, 18, 30),
(2, '', 'paradiso', '', 0, 0, 5, 8, 18, 20),
(3, '', 'nuit', '', 0, 0, 0, 5, 18, 40),
(4, '', 'Mau mau', ' Situado por bernabeu', 0, 0, 3, 15, 18, 26);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes`
--

CREATE TABLE `mensajes` (
  `id_mensaje` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `contenido` varchar(60) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensajes`
--

INSERT INTO `mensajes` (`id_mensaje`, `id_autor`, `id_chat`, `contenido`, `fecha`) VALUES
(1, 7, 3, 'que tal julian?????', '2022-04-25 11:48:26'),
(2, 1, 3, 'eyyyyy', '2022-04-30 18:56:48'),
(3, 1, 25, 'hola', '2022-05-02 20:01:43'),
(4, 1, 27, 'hhh', '2022-05-05 13:29:03'),
(5, 1, 27, 'hh', '2022-05-05 13:29:17'),
(6, 1, 28, 'hola', '2022-05-06 21:55:22'),
(7, 1, 28, 'hola', '2022-05-07 18:08:19'),
(8, 1, 28, 'h', '2022-05-07 18:09:46'),
(9, 1, 28, 'g', '2022-05-07 18:09:49'),
(10, 1, 28, 'ddd', '2022-05-07 18:09:54'),
(11, 1, 28, 'a', '2022-05-07 18:10:53'),
(12, 1, 28, 'g', '2022-05-07 18:11:35'),
(13, 1, 28, 'eo', '2022-05-07 18:11:42'),
(14, 1, 28, 'eoooo', '2022-05-07 18:11:55'),
(15, 1, 28, 'ddd', '2022-05-07 18:12:10'),
(16, 1, 28, 'gghhh', '2022-05-07 18:12:22'),
(17, 1, 28, 'tty', '2022-05-07 18:12:48'),
(18, 1, 28, 'hola', '2022-05-07 18:13:55'),
(19, 1, 28, 'muy bien ', '2022-05-07 18:14:05'),
(20, 1, 28, 'y tú?', '2022-05-07 18:14:12'),
(21, 1, 28, 'yyy', '2022-05-07 18:15:09'),
(22, 1, 28, 'o', '2022-05-07 18:22:45'),
(23, 1, 28, 'ooooo', '2022-05-07 18:22:52'),
(24, 1, 28, 'eee', '2022-05-07 18:22:56'),
(25, 1, 28, 'ttt', '2022-05-07 18:23:02'),
(26, 1, 28, 'r', '2022-05-07 18:23:07'),
(27, 1, 28, 'yyyy', '2022-05-07 18:23:09'),
(28, 1, 28, 'ey', '2022-05-07 18:25:40'),
(29, 1, 28, 'eee', '2022-05-07 18:40:41'),
(30, 1, 27, 'eeeee', '2022-05-07 19:45:04'),
(31, 1, 3, 'hola', '2022-05-07 19:48:05'),
(32, 7, 3, 'eee', '2022-05-07 23:57:47'),
(33, 1, 3, 'hola', '2022-05-08 12:09:14'),
(34, 12, 31, 'holaaaa', '2022-05-16 12:35:43'),
(35, 1, 33, 'eo', '2022-05-16 13:08:43'),
(36, 1, 34, 'eoooo', '2022-05-16 17:11:30');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mensajes_chat_local`
--

CREATE TABLE `mensajes_chat_local` (
  `id_mensaje` int(11) NOT NULL,
  `id_autor` int(11) NOT NULL,
  `id_chat_local` int(11) NOT NULL,
  `contenido` varchar(100) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `mensajes_chat_local`
--

INSERT INTO `mensajes_chat_local` (`id_mensaje`, `id_autor`, `id_chat_local`, `contenido`, `fecha`) VALUES
(1, 1, 3, 'por que haces eso?\n', '2022-04-20 09:34:27'),
(2, 1, 3, 'por que haces eso?\n', '2022-04-20 09:34:29'),
(3, 1, 3, 'por que haces eso?\ndd', '2022-04-20 09:34:33'),
(4, 1, 6, 'hola', '2022-04-30 09:09:17'),
(5, 1, 6, 'pis', '2022-04-30 09:33:40'),
(6, 1, 6, 'c', '2022-04-30 09:34:09'),
(7, 1, 2, 'yyy', '2022-05-01 23:25:07'),
(8, 1, 6, 'el mejor ', '2022-05-03 16:22:02'),
(9, 1, 6, '', '2022-05-03 16:22:09'),
(10, 1, 6, 'eyyyyy', '2022-05-03 16:24:22'),
(11, 1, 6, 'eo', '2022-05-07 18:31:40'),
(12, 1, 6, 'eeeeoooo', '2022-05-07 18:31:50'),
(13, 1, 11, 'eeeee', '2022-05-26 10:49:24');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `publicacion`
--

CREATE TABLE `publicacion` (
  `id` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `n_clicks` int(50) NOT NULL DEFAULT 0,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `publicacion`
--

INSERT INTO `publicacion` (`id`, `id_user`, `fecha`, `n_clicks`, `descripcion`) VALUES
(2, 1, '2022-04-13 18:53:54', 0, 'andres y beni'),
(3, 1, '2022-04-13 18:53:59', 0, 'andres y juan'),
(4, 1, '2022-05-11 19:51:22', 1, 'andres y pico'),
(5, 1, '2022-05-15 11:07:50', 3, 'andres y jjjjj'),
(6, 3, '2022-05-16 15:41:15', 9, 'juliann'),
(7, 3, '2022-04-13 19:37:46', 0, 'jul'),
(8, 1, '2022-05-15 19:20:15', 0, 'paellita'),
(9, 1, '2022-05-15 19:21:13', 0, 'jh'),
(10, 1, '2022-05-15 19:27:00', 0, 'jhjj'),
(11, 1, '2022-05-15 19:27:19', 0, 'jhjj'),
(12, 1, '2022-05-15 19:31:45', 0, 'jhjjhh\n'),
(13, 1, '2022-05-15 19:36:28', 0, 'pel'),
(14, 1, '2022-05-16 15:41:05', 1, 'oae'),
(15, 1, '2022-05-16 12:58:59', 0, 'paella');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuacion_evento`
--

CREATE TABLE `puntuacion_evento` (
  `id_user` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL,
  `puntuacion` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `puntuacion_evento`
--

INSERT INTO `puntuacion_evento` (`id_user`, `id_evento`, `puntuacion`) VALUES
(1, 3, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `puntuacion_local`
--

CREATE TABLE `puntuacion_local` (
  `id_user` int(11) NOT NULL,
  `id_local` int(11) NOT NULL,
  `puntuacion` double NOT NULL DEFAULT 5
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `puntuacion_local`
--

INSERT INTO `puntuacion_local` (`id_user`, `id_local`, `puntuacion`) VALUES
(1, 1, 9),
(1, 3, 5),
(3, 1, 8),
(3, 2, 9),
(3, 4, 4),
(7, 1, 10),
(7, 2, 7.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_gestor_local`
--

CREATE TABLE `r_gestor_local` (
  `id_local` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_gestor_local`
--

INSERT INTO `r_gestor_local` (`id_local`, `id_user`) VALUES
(4, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_ha_estado_evento`
--

CREATE TABLE `r_ha_estado_evento` (
  `id_user` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_ha_estado_evento`
--

INSERT INTO `r_ha_estado_evento` (`id_user`, `id_evento`) VALUES
(2, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_ha_estado_local`
--

CREATE TABLE `r_ha_estado_local` (
  `id_user` int(11) NOT NULL,
  `id_local` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_ha_estado_local`
--

INSERT INTO `r_ha_estado_local` (`id_user`, `id_local`, `fecha`) VALUES
(1, 1, '2022-04-13 18:05:23'),
(1, 2, '2022-04-13 18:05:23'),
(1, 3, '2022-04-13 18:05:23'),
(1, 3, '2022-04-25 11:41:00'),
(1, 4, '2022-04-13 17:05:23'),
(1, 4, '2022-04-25 11:40:54'),
(3, 1, '2022-04-25 11:40:38'),
(3, 2, '2022-04-25 12:02:41'),
(3, 4, '2022-05-09 16:37:53'),
(7, 1, '2022-04-29 10:23:42');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_ha_leido_mensaje`
--

CREATE TABLE `r_ha_leido_mensaje` (
  `id_user` int(11) NOT NULL,
  `id_mensaje` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_ha_leido_mensaje`
--

INSERT INTO `r_ha_leido_mensaje` (`id_user`, `id_mensaje`) VALUES
(3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_pertenece_user_chat`
--

CREATE TABLE `r_pertenece_user_chat` (
  `id_chat` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_pertenece_user_chat`
--

INSERT INTO `r_pertenece_user_chat` (`id_chat`, `id_user`, `fecha`) VALUES
(2, 3, '2022-04-13 17:05:21'),
(3, 1, '2022-04-18 09:42:01'),
(5, 9, '2022-04-21 17:00:27'),
(6, 9, '2022-04-21 17:04:35'),
(25, 1, '2022-04-21 13:16:27'),
(25, 3, '2022-04-21 13:45:48'),
(25, 9, '2022-04-21 13:44:58'),
(26, 1, '2022-04-30 22:49:02'),
(26, 3, '2022-04-30 22:49:26'),
(26, 7, '2022-04-30 22:49:37'),
(27, 1, '2022-05-03 17:58:19'),
(27, 3, '2022-05-03 17:58:23'),
(27, 7, '2022-05-03 17:58:37'),
(28, 1, '2022-05-05 13:32:09'),
(28, 7, '2022-05-05 13:32:17'),
(29, 1, '2022-05-09 16:49:08'),
(30, 1, '2022-05-09 16:49:25'),
(31, 1, '2022-05-12 22:42:47'),
(31, 12, '2022-05-12 22:42:31'),
(32, 1, '2022-05-16 12:29:30'),
(32, 12, '2022-05-16 12:29:21'),
(33, 1, '2022-05-16 12:59:34'),
(33, 3, '2022-05-16 12:59:39'),
(33, 7, '2022-05-16 12:59:42'),
(34, 1, '2022-05-16 13:07:36'),
(34, 3, '2022-05-16 13:07:40'),
(35, 1, '2022-05-16 17:10:19'),
(36, 1, '2022-05-16 17:10:33'),
(36, 3, '2022-05-16 17:10:36'),
(36, 7, '2022-05-16 17:10:41');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_pertenece_user_chat_local`
--

CREATE TABLE `r_pertenece_user_chat_local` (
  `id_chat_local` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_pertenece_user_chat_local`
--

INSERT INTO `r_pertenece_user_chat_local` (`id_chat_local`, `id_user`, `fecha`) VALUES
(2, 1, '2022-05-01 23:24:59'),
(3, 1, '2022-04-19 16:32:18'),
(3, 3, '2022-04-25 11:49:29'),
(3, 8, '2022-04-25 11:49:34'),
(6, 1, '2022-04-30 09:09:08'),
(7, 1, '2022-05-08 10:06:29'),
(8, 1, '2022-05-16 12:58:22'),
(9, 1, '2022-05-16 17:09:14'),
(10, 1, '2022-05-16 17:09:44'),
(11, 1, '2022-05-26 10:49:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_relaciones`
--

CREATE TABLE `r_relaciones` (
  `id_relacion` int(11) NOT NULL,
  `id_local` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_relaciones`
--

INSERT INTO `r_relaciones` (`id_relacion`, `id_local`) VALUES
(1, 3),
(3, 1),
(3, 2),
(7, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_seguidor_seguido`
--

CREATE TABLE `r_seguidor_seguido` (
  `id_seguidor` int(11) NOT NULL,
  `id_seguido` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_seguidor_seguido`
--

INSERT INTO `r_seguidor_seguido` (`id_seguidor`, `id_seguido`) VALUES
(1, 3),
(1, 7),
(1, 8),
(1, 9),
(3, 1),
(3, 7),
(7, 1),
(7, 3),
(8, 1),
(9, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `r_va_a_evento`
--

CREATE TABLE `r_va_a_evento` (
  `id_user` int(11) NOT NULL,
  `id_evento` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `r_va_a_evento`
--

INSERT INTO `r_va_a_evento` (`id_user`, `id_evento`) VALUES
(1, 3),
(3, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tabla_registro_chat_user`
--

CREATE TABLE `tabla_registro_chat_user` (
  `id_user` int(11) NOT NULL,
  `id_chat` int(11) NOT NULL,
  `fecha_ultimo_acceso` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `tabla_registro_chat_user`
--

INSERT INTO `tabla_registro_chat_user` (`id_user`, `id_chat`, `fecha_ultimo_acceso`) VALUES
(1, 3, '2022-05-16 12:45:33'),
(1, 1, '2022-05-07 23:11:36'),
(1, 3, '2022-05-16 12:45:33'),
(1, 25, '2022-05-07 23:15:43'),
(1, 26, '2022-05-07 23:16:14'),
(1, 27, '2022-05-16 12:45:31'),
(1, 28, '2022-05-16 12:45:47'),
(1, 31, '2022-05-12 23:22:38'),
(12, 31, '2022-05-16 12:35:45'),
(1, 32, '2022-05-16 12:45:29'),
(12, 32, '2022-05-16 12:29:36'),
(3, 33, '2022-05-16 12:59:39'),
(7, 33, '2022-05-16 12:59:42'),
(1, 33, '2022-05-16 12:59:46'),
(3, 34, '2022-05-16 13:07:40'),
(1, 34, '2022-05-16 17:11:32'),
(3, 36, '2022-05-16 17:10:36'),
(7, 36, '2022-05-16 17:10:41'),
(1, 36, '2022-05-16 17:10:52');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(100) NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `password` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `publico` int(2) NOT NULL,
  `phone` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `password`, `correo`, `publico`, `phone`) VALUES
(1, 'ana', '12345', 'ana@juan', 1, ''),
(3, 'juan', '12345', 'juan@gmail.com', 0, '12245677888'),
(7, 'juanito', '12345', 'juanito@gmail.com', 0, '12245677888'),
(8, 'juanitocc', '35000', 'juanfffff@gmail.com', 0, '12245677888'),
(9, '', '', '', 0, ''),
(10, 'julianddd\n\n', '2', 'and@ggg.com', 0, '12245677889999999\n'),
(11, 'julianddddd\n\n\n', '2', 'and@ggddg.com', 0, '12245677889999999\n'),
(12, 'j', '2', 'jjjjj', 0, 'jjjjj');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `chats`
--
ALTER TABLE `chats`
  ADD PRIMARY KEY (`id_chat`),
  ADD UNIQUE KEY `id_chat` (`id_chat`),
  ADD KEY `id_admin` (`id_admin`),
  ADD KEY `id_admin_2` (`id_admin`);

--
-- Indices de la tabla `chat_local`
--
ALTER TABLE `chat_local`
  ADD PRIMARY KEY (`id_chat_local`),
  ADD UNIQUE KEY `id_chat_local` (`id_chat_local`),
  ADD KEY `id_local` (`id_local`);

--
-- Indices de la tabla `descripcion`
--
ALTER TABLE `descripcion`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `evento`
--
ALTER TABLE `evento`
  ADD PRIMARY KEY (`id_evento`),
  ADD UNIQUE KEY `id_evento` (`id_evento`),
  ADD KEY `id_local` (`id_local`);

--
-- Indices de la tabla `foto_perfil`
--
ALTER TABLE `foto_perfil`
  ADD PRIMARY KEY (`id_foto_perfil`);

--
-- Indices de la tabla `foto_publicacion`
--
ALTER TABLE `foto_publicacion`
  ADD PRIMARY KEY (`id_foto_publicacion`);

--
-- Indices de la tabla `imagenes`
--
ALTER TABLE `imagenes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`);

--
-- Indices de la tabla `local`
--
ALTER TABLE `local`
  ADD PRIMARY KEY (`id_local`),
  ADD UNIQUE KEY `id_local` (`id_local`);

--
-- Indices de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD PRIMARY KEY (`id_mensaje`),
  ADD UNIQUE KEY `id_mensaje` (`id_mensaje`),
  ADD KEY `id_autor` (`id_autor`),
  ADD KEY `id_chat` (`id_chat`),
  ADD KEY `id_autor_2` (`id_autor`);

--
-- Indices de la tabla `mensajes_chat_local`
--
ALTER TABLE `mensajes_chat_local`
  ADD PRIMARY KEY (`id_mensaje`),
  ADD KEY `id_autor` (`id_autor`),
  ADD KEY `id_autor_2` (`id_autor`),
  ADD KEY `id_chat_local` (`id_chat_local`);

--
-- Indices de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_user_2` (`id_user`);

--
-- Indices de la tabla `r_gestor_local`
--
ALTER TABLE `r_gestor_local`
  ADD PRIMARY KEY (`id_local`,`id_user`),
  ADD KEY `id_local` (`id_local`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `r_ha_estado_local`
--
ALTER TABLE `r_ha_estado_local`
  ADD PRIMARY KEY (`id_user`,`id_local`,`fecha`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_local` (`id_local`);

--
-- Indices de la tabla `r_ha_leido_mensaje`
--
ALTER TABLE `r_ha_leido_mensaje`
  ADD PRIMARY KEY (`id_user`,`id_mensaje`),
  ADD KEY `id_user` (`id_user`),
  ADD KEY `id_mensaje` (`id_mensaje`);

--
-- Indices de la tabla `r_pertenece_user_chat`
--
ALTER TABLE `r_pertenece_user_chat`
  ADD PRIMARY KEY (`id_chat`,`id_user`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `r_pertenece_user_chat_local`
--
ALTER TABLE `r_pertenece_user_chat_local`
  ADD PRIMARY KEY (`id_chat_local`,`id_user`),
  ADD KEY `id_chat_local` (`id_chat_local`),
  ADD KEY `id_user` (`id_user`);

--
-- Indices de la tabla `r_relaciones`
--
ALTER TABLE `r_relaciones`
  ADD PRIMARY KEY (`id_relacion`,`id_local`),
  ADD KEY `id_relacion` (`id_relacion`),
  ADD KEY `id_local` (`id_local`);

--
-- Indices de la tabla `r_seguidor_seguido`
--
ALTER TABLE `r_seguidor_seguido`
  ADD PRIMARY KEY (`id_seguidor`,`id_seguido`),
  ADD KEY `id_seguidor` (`id_seguidor`),
  ADD KEY `id_seguido` (`id_seguido`);

--
-- Indices de la tabla `r_va_a_evento`
--
ALTER TABLE `r_va_a_evento`
  ADD PRIMARY KEY (`id_user`,`id_evento`),
  ADD KEY `id_evento` (`id_evento`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id` (`id`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `chats`
--
ALTER TABLE `chats`
  MODIFY `id_chat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `chat_local`
--
ALTER TABLE `chat_local`
  MODIFY `id_chat_local` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `evento`
--
ALTER TABLE `evento`
  MODIFY `id_evento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `local`
--
ALTER TABLE `local`
  MODIFY `id_local` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `mensajes`
--
ALTER TABLE `mensajes`
  MODIFY `id_mensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT de la tabla `mensajes_chat_local`
--
ALTER TABLE `mensajes_chat_local`
  MODIFY `id_mensaje` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `publicacion`
--
ALTER TABLE `publicacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `descripcion`
--
ALTER TABLE `descripcion`
  ADD CONSTRAINT `descripcion_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `evento`
--
ALTER TABLE `evento`
  ADD CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensajes`
--
ALTER TABLE `mensajes`
  ADD CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mensajes_ibfk_2` FOREIGN KEY (`id_chat`) REFERENCES `chats` (`id_chat`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `mensajes_chat_local`
--
ALTER TABLE `mensajes_chat_local`
  ADD CONSTRAINT `mensajes_chat_local_ibfk_1` FOREIGN KEY (`id_autor`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `mensajes_chat_local_ibfk_2` FOREIGN KEY (`id_chat_local`) REFERENCES `chat_local` (`id_chat_local`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `publicacion`
--
ALTER TABLE `publicacion`
  ADD CONSTRAINT `publicacion_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_gestor_local`
--
ALTER TABLE `r_gestor_local`
  ADD CONSTRAINT `r_gestor_local_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_gestor_local_ibfk_2` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_ha_estado_local`
--
ALTER TABLE `r_ha_estado_local`
  ADD CONSTRAINT `r_ha_estado_local_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_ha_estado_local_ibfk_2` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_ha_leido_mensaje`
--
ALTER TABLE `r_ha_leido_mensaje`
  ADD CONSTRAINT `r_ha_leido_mensaje_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_ha_leido_mensaje_ibfk_2` FOREIGN KEY (`id_mensaje`) REFERENCES `mensajes` (`id_mensaje`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_pertenece_user_chat`
--
ALTER TABLE `r_pertenece_user_chat`
  ADD CONSTRAINT `r_pertenece_user_chat_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_pertenece_user_chat_local`
--
ALTER TABLE `r_pertenece_user_chat_local`
  ADD CONSTRAINT `r_pertenece_user_chat_local_ibfk_1` FOREIGN KEY (`id_user`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_relaciones`
--
ALTER TABLE `r_relaciones`
  ADD CONSTRAINT `r_relaciones_ibfk_1` FOREIGN KEY (`id_relacion`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_relaciones_ibfk_2` FOREIGN KEY (`id_local`) REFERENCES `local` (`id_local`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_seguidor_seguido`
--
ALTER TABLE `r_seguidor_seguido`
  ADD CONSTRAINT `r_seguidor_seguido_ibfk_1` FOREIGN KEY (`id_seguidor`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `r_seguidor_seguido_ibfk_2` FOREIGN KEY (`id_seguido`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `r_va_a_evento`
--
ALTER TABLE `r_va_a_evento`
  ADD CONSTRAINT `r_va_a_evento_ibfk_1` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`id_evento`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
