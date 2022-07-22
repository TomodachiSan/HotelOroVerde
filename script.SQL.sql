-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 22-07-2022 a las 22:42:49
-- Versión del servidor: 5.7.31
-- Versión de PHP: 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdhoteloroverde`
--
CREATE DATABASE IF NOT EXISTS `bdhoteloroverde` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `bdhoteloroverde`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquiler`
--

DROP TABLE IF EXISTS `alquiler`;
CREATE TABLE IF NOT EXISTS `alquiler` (
  `idalquiler` int(11) NOT NULL AUTO_INCREMENT,
  `idhabitacion` int(11) NOT NULL,
  `idcliente` int(11) NOT NULL,
  `idtrabajador` int(11) NOT NULL,
  `fecha_ingresa` date NOT NULL,
  `hora_ingresa` varchar(15) DEFAULT NULL,
  `fecha_salida` date NOT NULL,
  `hora_salida` varchar(15) DEFAULT NULL,
  `costo_Dia` decimal(7,2) NOT NULL,
  `costo_alojamiento` decimal(7,2) NOT NULL,
  PRIMARY KEY (`idalquiler`),
  KEY `fk_reserva_habitacion_idx` (`idhabitacion`),
  KEY `fk_reserva_cliente_idx` (`idcliente`),
  KEY `fk_reserva_trabajador_idx` (`idtrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `alquiler`
--

INSERT INTO `alquiler` (`idalquiler`, `idhabitacion`, `idcliente`, `idtrabajador`, `fecha_ingresa`, `hora_ingresa`, `fecha_salida`, `hora_salida`, `costo_Dia`, `costo_alojamiento`) VALUES
(1, 1, 15, 2, '2022-06-15', '10:00:00', '2022-06-18', '10:00:00', '50.00', '150.00'),
(2, 2, 16, 2, '2022-06-20', '11:00:00', '2022-06-24', '09:00:00', '80.00', '320.00'),
(3, 3, 17, 3, '2022-07-03', '11:00:00', '2022-07-05', '10:30:00', '90.00', '180.00'),
(4, 4, 18, 1, '2022-07-13', '12:40:44', '2022-07-19', '12:40:44', '90.00', '540.00'),
(5, 1, 19, 1, '2022-07-15', '02:22:30', '2022-07-17', '02:22:30', '70.00', '140.00'),
(6, 5, 22, 1, '2022-07-21', '06:15:18', '2022-07-24', '06:15:18', '60.00', '180.00'),
(7, 5, 23, 1, '2022-07-21', '06:23:25', '2022-07-23', '06:23:25', '60.00', '120.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `idpersona` int(11) NOT NULL,
  `estado` varchar(1) NOT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`idpersona`, `estado`) VALUES
(15, 'A'),
(16, 'A'),
(17, 'A'),
(18, 'A'),
(19, 'A'),
(22, 'A'),
(23, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `consumo`
--

DROP TABLE IF EXISTS `consumo`;
CREATE TABLE IF NOT EXISTS `consumo` (
  `idconsumo` int(11) NOT NULL AUTO_INCREMENT,
  `idalquiler` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_venta` decimal(8,2) NOT NULL,
  `monto` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idconsumo`),
  KEY `fk_consumo_alquiler` (`idalquiler`),
  KEY `fk_consumo_producto` (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `consumo`
--

INSERT INTO `consumo` (`idconsumo`, `idalquiler`, `idproducto`, `cantidad`, `precio_venta`, `monto`) VALUES
(1, 5, 1, 4, '2.50', '10.00'),
(2, 5, 4, 2, '1.50', '3.00'),
(4, 4, 3, 6, '1.50', '9.00'),
(5, 4, 1, 1, '2.50', '2.50'),
(6, 6, 5, 4, '2.00', '8.00'),
(7, 6, 2, 2, '2.50', '5.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_venta`
--

DROP TABLE IF EXISTS `detalle_venta`;
CREATE TABLE IF NOT EXISTS `detalle_venta` (
  `iddetalle_venta` int(11) NOT NULL AUTO_INCREMENT,
  `idventa` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio_venta` decimal(8,2) NOT NULL,
  `monto` decimal(8,2) NOT NULL,
  PRIMARY KEY (`iddetalle_venta`),
  KEY `idventa` (`idventa`,`idproducto`),
  KEY `idventa_2` (`idventa`),
  KEY `fk_detalle_venta_producto` (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `detalle_venta`
--

INSERT INTO `detalle_venta` (`iddetalle_venta`, `idventa`, `idproducto`, `cantidad`, `precio_venta`, `monto`) VALUES
(3, 4, 2, 2, '2.50', '5.00'),
(4, 4, 3, 3, '1.50', '4.50'),
(5, 4, 4, 6, '1.50', '9.00'),
(6, 5, 2, 4, '2.50', '10.00'),
(8, 5, 3, 3, '1.50', '4.50'),
(9, 6, 4, 10, '1.50', '15.00'),
(10, 6, 1, 20, '2.50', '50.00'),
(11, 6, 3, 10, '1.50', '15.00'),
(16, 9, 4, 10, '1.50', '15.00'),
(17, 9, 1, 15, '2.50', '37.50'),
(18, 10, 1, 4, '2.50', '10.00'),
(19, 10, 4, 5, '1.50', '7.50'),
(20, 11, 2, 10, '2.50', '25.00'),
(21, 11, 4, 5, '1.50', '7.50'),
(22, 12, 2, 4, '2.50', '10.00'),
(23, 12, 3, 4, '1.50', '6.00'),
(25, 13, 4, 15, '1.50', '22.50'),
(26, 14, 2, 4, '2.50', '10.00'),
(28, 15, 3, 3, '1.50', '4.50'),
(29, 15, 1, 4, '2.50', '10.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `habitacion`
--

DROP TABLE IF EXISTS `habitacion`;
CREATE TABLE IF NOT EXISTS `habitacion` (
  `idhabitacion` int(11) NOT NULL AUTO_INCREMENT,
  `numero` varchar(4) NOT NULL,
  `piso` varchar(2) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `caracteristicas` varchar(512) DEFAULT NULL,
  `precio_diario` decimal(7,2) NOT NULL,
  `estado` varchar(15) NOT NULL,
  `tipo_habitacion` varchar(20) NOT NULL,
  PRIMARY KEY (`idhabitacion`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `habitacion`
--

INSERT INTO `habitacion` (`idhabitacion`, `numero`, `piso`, `descripcion`, `caracteristicas`, `precio_diario`, `estado`, `tipo_habitacion`) VALUES
(1, '200', '1', 'Habitación Individual con baño incluido', 'TV Led 45\', ducha con agua caliente, sofa, mesa de noche', '100.00', 'Ocupado', 'Individual'),
(2, '201', '1', 'Habitación matrimonial con baño propio', 'TV Led 50\', cama doble plaza, sillon, mesa de noche', '140.00', 'Disponible', 'Matrimonial'),
(3, '202', '1', 'Habitación con 3 camas con baño propio', 'TV Led 50\', sofa, mesa de noche, ducha caliente', '160.00', 'Disponible', 'Familiar'),
(4, '203', '2', 'Habitación Doble con ducha caliente', 'TV Led 55\', Cama doble plaza, Sillón, Mesa de Noche', '120.00', 'Disponible', 'Doble'),
(5, '204', '2', 'Habitación Individual con TV 50\' Pulgadas y terma', 'Habitación con sillón, mesa de noche, baño propio', '100.00', 'Disponible', 'Individual'),
(6, '205', '2', 'Habitación Matrimonial con cama de dos plazas, mesa de noche, baño propio', 'TV Led 50\',Baño con ducha propia, terma caliente,sillon propio', '140.00', 'Disponible', 'Matrimonial'),
(7, '206', '3', 'Habitación con 3 camas , con ducha propia y silon', 'TV Led 50\' Smart TV, Sillon Propio, ducha con terma caliente', '160.00', 'Disponible', 'Familiar'),
(8, '207', '3', 'Habitación con cama doble plaza, sillon propio, mesa de noche, ducha propia', 'TV Led 45\' , sillón propio, ducha caliente, mesita de noche', '120.00', 'Disponible', 'Doble'),
(9, '208', '3', 'Habitación Individual con ducha caliente, mesita de noche', 'TV Led 50\', Ducha Caliente, mesa de noche', '100.00', 'Disponible', 'Individual');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `idpago` int(11) NOT NULL AUTO_INCREMENT,
  `idalquiler` int(11) NOT NULL,
  `tipo_comprobante` varchar(20) NOT NULL,
  `igv` decimal(4,2) NOT NULL,
  `total_pago` decimal(7,2) NOT NULL,
  `fecha_pago` date NOT NULL,
  `hora_pago` varchar(15) DEFAULT NULL,
  `pago_inicial` decimal(7,2) NOT NULL,
  PRIMARY KEY (`idpago`),
  KEY `fk_pago_reserva_idx` (`idalquiler`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`idpago`, `idalquiler`, `tipo_comprobante`, `igv`, `total_pago`, `fecha_pago`, `hora_pago`, `pago_inicial`) VALUES
(1, 4, 'Boleta', '99.27', '650.77', '2022-07-21', '12:53:45', '551.50'),
(2, 3, 'Boleta', '32.40', '212.40', '2022-07-21', '02:42:13', '180.00'),
(5, 1, 'Boleta', '27.00', '177.00', '2022-07-22', '01:37:32', '150.00'),
(6, 2, 'Boleta', '57.60', '377.60', '2022-07-22', '01:37:50', '320.00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

DROP TABLE IF EXISTS `persona`;
CREATE TABLE IF NOT EXISTS `persona` (
  `idpersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) NOT NULL,
  `apaterno` varchar(20) NOT NULL,
  `amaterno` varchar(20) NOT NULL,
  `tipo_documento` varchar(15) NOT NULL,
  `num_documento` varchar(30) NOT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefono_UNIQUE` (`telefono`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `nombre`, `apaterno`, `amaterno`, `tipo_documento`, `num_documento`, `direccion`, `telefono`, `email`) VALUES
(1, 'Sebastian', 'Fernandez', 'Morin', 'DNI', '98562380', 'Calle Olmos 5000', '987452100', 'sebas105@gmail.com'),
(2, 'Karen', 'Gómez', 'Oré', 'DNI', '78541236', 'Calle Paez 2154', '965847213', 'karen587@gmail.com'),
(3, 'Joe', 'Díaz', 'Rojas', 'DNI', '98745621', 'Calle Palo 8754', '963258741', 'joe@gmail.com'),
(6, 'Mayra', 'López', 'Alcántara', 'DNI', '98745630', 'Calle Perez 21548', '963258740', 'mayra456@gmail.com'),
(7, 'Juan', 'Lopez', 'Robles', 'DNI', '98745632', 'Calle Dasas 465', '987457453', 'juan@gmail,com'),
(15, 'Lia', 'Soto', 'Vera', 'DNI', '96325800', 'Calle Colo 658', '998877445', 'lia6584@gmail,com'),
(16, 'Edrick', 'Lamas', 'Rodas', 'DNI', '77441122', 'Calle Polo 5487', '998855663', 'edrick65@gmail.com'),
(17, 'Adan', 'Saenz', 'Runa', 'DNI', '78451629', 'Calle Peru 5487', '986533226', 'adan2154@gmail.com'),
(18, 'Jose', 'Paez', 'Mera', 'DNI', '74582211', 'Calle Dira 5487', '996633225', 'jose5484@gmail.com'),
(19, 'Alan', 'Morillo', 'Roez', 'DNI', '98745632', 'Calle Wiesse 4587', '968574231', 'alan548@gmail.com'),
(20, 'Erick', 'Rivas', 'Plata', 'DNI', '96547812', 'Calle Lobos 4587', '963258963', 'erick546@gmail.com'),
(21, 'Anderson', 'Duiz', 'Daba', 'DNI', '77441150', 'Calle Alamos 6589', '978855412', 'ander45@gmail.com'),
(22, 'Eva', 'Lera', 'Faez', 'DNI', '88552200', 'Calle Uruguay 458', '963696300', 'eva5478@gmail.com'),
(23, 'Alana', 'Pia', 'Ñanez', 'DNI', '11225540', 'Calle Wiesse 5487', '99225540', 'alana58@gmail.com'),
(24, 'Lola', 'Nuñez', 'Alas', 'DNI', '88565412', 'Calle Peru 6589', '998547850', 'lola5478@gmail.com'),
(25, 'Mulan', 'Polo', 'Campos', 'DNI', '11220365', 'Calle Wissie 5487', '965588410', 'mul554@gmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

DROP TABLE IF EXISTS `producto`;
CREATE TABLE IF NOT EXISTS `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `unidad_medida` varchar(20) NOT NULL,
  `precio_venta` decimal(7,2) NOT NULL,
  `stock` int(11) NOT NULL,
  `estado` varchar(1) NOT NULL,
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idproducto`, `nombre`, `descripcion`, `unidad_medida`, `precio_venta`, `stock`, `estado`) VALUES
(1, 'Gaseosa Coca Cola 500ml', 'Gaseosa Coca Cola de 500ml', 'UND', '3.00', 60, 'A'),
(2, 'Gaseosa Pepsi 500ml', 'Gaseosa Pepsi de 500 ml', 'UND', '2.50', 14, 'A'),
(3, 'Galleta Picaras', 'Galleta Picara de 6 unidades', 'UND', '1.50', 27, 'A'),
(4, 'Galleta Soda ', 'Galleta Marca Soda', 'UND', '1.50', 50, 'A'),
(5, 'Piqueo Snack', 'Piqueo Snack (Ches Tris,Papa Lays,Doritos)', 'UND', '2.00', 40, 'A'),
(6, 'Papas Lay\'s', 'Papa Lay\'s personal', 'UND', '2.00', 40, 'A'),
(7, 'Jabon Lux', 'Jabon de mano y cara Lux', 'UND', '3.00', 40, 'A'),
(8, 'Shampoo Pantene', 'Shampo Pantene sachet', 'UND', '2.00', 30, 'A'),
(9, 'Pasta Dental Colgate', 'Envase de pasta dental Colgate personal', 'UND', '4.00', 20, 'A'),
(10, 'Cepillo de dientes Colgate', 'Cepillo de dientes Colgate personal', 'UND', '3.00', 40, 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

DROP TABLE IF EXISTS `trabajador`;
CREATE TABLE IF NOT EXISTS `trabajador` (
  `idpersona` int(11) NOT NULL,
  `sueldo` decimal(7,2) NOT NULL,
  `acceso` varchar(15) NOT NULL,
  `login` varchar(15) NOT NULL,
  `password` varchar(20) NOT NULL,
  `estado` varchar(1) NOT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`idpersona`, `sueldo`, `acceso`, `login`, `password`, `estado`) VALUES
(1, '1200.00', 'Administrador', 'admin', 'admin', 'A'),
(2, '1200.00', 'Administrador', 'karen1254', 'karen125587', 'A'),
(3, '1200.00', 'Administrador', 'joe4587', 'joe12345', 'A'),
(6, '1200.00', 'Administrador', 'mayra546', 'mayra12345', 'A'),
(7, '1000.00', 'Recepcionista', 'juan3216', 'depruebacontra2', 'A'),
(20, '1000.00', 'Recepcionista', 'erick5448', 'contra1', 'A'),
(21, '1000.00', 'Recepcionista', 'ander2359', 'contra20', 'A'),
(24, '1000.00', 'Recepcionista', 'lola12354', 'contra3', 'A'),
(25, '1000.00', 'Recepcionista', 'mulan10', 'mulan12345', 'A');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta`
--

DROP TABLE IF EXISTS `venta`;
CREATE TABLE IF NOT EXISTS `venta` (
  `idventa` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `idtrabajador` int(11) NOT NULL,
  `tipo_comprobante` varchar(20) DEFAULT NULL,
  `monto` decimal(8,2) NOT NULL,
  `igv` decimal(8,2) NOT NULL,
  `monto_total` decimal(8,2) NOT NULL,
  PRIMARY KEY (`idventa`),
  KEY `fk_venta_trabajador` (`idtrabajador`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `venta`
--

INSERT INTO `venta` (`idventa`, `fecha`, `idtrabajador`, `tipo_comprobante`, `monto`, `igv`, `monto_total`) VALUES
(4, '2022-07-14', 1, 'Boleta', '18.50', '3.33', '21.83'),
(5, '2022-07-14', 1, 'Boleta', '14.50', '2.61', '17.11'),
(6, '2022-07-14', 7, 'Boleta', '80.00', '14.40', '94.40'),
(9, '2022-07-14', 20, 'Boleta', '52.50', '9.45', '61.95'),
(10, '2022-07-14', 21, 'Boleta', '17.50', '3.15', '20.65'),
(11, '2022-07-15', 21, 'Boleta', '32.50', '5.85', '38.35'),
(12, '2022-07-15', 24, 'Boleta', '16.00', '2.88', '18.88'),
(13, '2022-07-15', 24, 'Boleta', '22.50', '4.05', '26.55'),
(14, '2022-07-15', 7, 'Boleta', '10.00', '1.80', '11.80'),
(15, '2022-07-21', 7, 'Boleta', '14.50', '2.61', '17.11');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `consumo`
--
ALTER TABLE `consumo`
  ADD CONSTRAINT `fk_consumo_alquiler` FOREIGN KEY (`idalquiler`) REFERENCES `alquiler` (`idalquiler`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_consumo_producto` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
