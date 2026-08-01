SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "-03:00";

CREATE DATABASE IF NOT EXISTS `gestion_inmobiliaria` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `gestion_inmobiliaria`;

CREATE TABLE `perfiles` (
  `id_perfil` int(11) NOT NULL,
  `nombre_perfil` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tipos_ajuste` (
  `id_ajuste` int(11) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tipos_documentos` (
  `id_tipo_doc` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `tipos_inmueble` (
  `id_tipo` int(11) NOT NULL,
  `descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `zonas` (
  `id_zona` int(11) NOT NULL,
  `nombre_barrio` varchar(100) NOT NULL,
  `ciudad` varchar(100) DEFAULT 'Coronel Pringles',
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `personas` (
  `id_persona` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `id_tipo_doc` int(11) NOT NULL,
  `nro_documento` varchar(20) NOT NULL,
  `cuil_cuit` varchar(20) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `cbu_alias` varchar(100) DEFAULT NULL,
  `fecha_alta_sistema` datetime DEFAULT current_timestamp(),
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  `id_persona` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT 1,
  `email` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `propiedades` (
  `id_propiedad` int(11) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `id_propietario` int(11) NOT NULL,
  `id_tipo` int(11) NOT NULL,
  `id_zona` int(11) NOT NULL,
  `estado` varchar(50) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `contratos` (
  `id_contrato` int(11) NOT NULL,
  `id_inquilino` int(11) NOT NULL,
  `id_propiedad` int(11) NOT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `valor_inicial` decimal(12,2) NOT NULL,
  `id_ajuste` int(11) NOT NULL,
  `estado` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `obligaciones` (
  `id_obligacion` int(11) NOT NULL,
  `id_contrato` int(11) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `importe_referencia` decimal(18,2) DEFAULT NULL,
  `pagado_por_inquilino` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `pagos` (
  `id_pago` int(11) NOT NULL,
  `id_contrato` int(11) NOT NULL,
  `mes_cobertura` int(2) NOT NULL,
  `anio_cobertura` int(4) NOT NULL,
  `monto_obligatorio` decimal(12,2) NOT NULL,
  `monto_pagado` decimal(12,2) NOT NULL,
  `fecha_pago` date DEFAULT NULL,
  `estado_pago` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

INSERT INTO `perfiles` (`id_perfil`, `nombre_perfil`) VALUES
(1, 'Administrador/Gerente'),
(2, 'Agente Inmobiliario');

INSERT INTO `tipos_ajuste` (`id_ajuste`, `descripcion`) VALUES
(1, 'Trimestral'),
(2, 'Cuatrimestral'),
(3, 'Semestral');

INSERT INTO `tipos_documentos` (`id_tipo_doc`, `descripcion`) VALUES
(1, 'DNI - Documento Nacional de Identidad'),
(2, 'LC - Libreta Cívica'),
(3, 'LE - Libreta de Enrolamiento'),
(4, 'PAS - Pasaporte');

INSERT INTO `tipos_inmueble` (`id_tipo`, `descripcion`) VALUES
(1, 'Casa'),
(2, 'Departamento'),
(3, 'Local'),
(4, 'Cochera'),
(5, 'Lote');

INSERT INTO `zonas` (`id_zona`, `nombre_barrio`, `ciudad`, `nombre`) VALUES
(1, 'Centro', 'Coronel Pringles', ''),
(2, 'Norte', 'Coronel Pringles', ''),
(3, 'Sur', 'Coronel Pringles', ''),
(4, 'Este', 'Coronel Pringles', ''),
(5, 'Oeste', 'Coronel Pringles', '');

ALTER TABLE `contratos`
  ADD PRIMARY KEY (`id_contrato`),
  ADD KEY `contratos_ibfk_1` (`id_inquilino`),
  ADD KEY `contratos_ibfk_2` (`id_propiedad`),
  ADD KEY `contratos_ibfk_3` (`id_ajuste`);

ALTER TABLE `obligaciones`
  ADD PRIMARY KEY (`id_obligacion`),
  ADD KEY `obligaciones_ibfk_1` (`id_contrato`);

ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `pagos_ibfk_1` (`id_contrato`);

ALTER TABLE `perfiles`
  ADD PRIMARY KEY (`id_perfil`);

ALTER TABLE `personas`
  ADD PRIMARY KEY (`id_persona`),
  ADD UNIQUE KEY `ux_tipo_nro_documento` (`id_tipo_doc`,`nro_documento`);

ALTER TABLE `propiedades`
  ADD PRIMARY KEY (`id_propiedad`),
  ADD KEY `propiedades_ibfk_1` (`id_propietario`),
  ADD KEY `propiedades_ibfk_2` (`id_tipo`),
  ADD KEY `propiedades_ibfk_3` (`id_zona`);

ALTER TABLE `tipos_ajuste`
  ADD PRIMARY KEY (`id_ajuste`);

ALTER TABLE `tipos_documentos`
  ADD PRIMARY KEY (`id_tipo_doc`);

ALTER TABLE `tipos_inmueble`
  ADD PRIMARY KEY (`id_tipo`);

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `UKkfsp0s1tflm1cwlj8idhqsad0` (`email`),
  ADD KEY `usuarios_ibfk_1` (`id_perfil`),
  ADD KEY `usuarios_ibfk_2` (`id_persona`);

ALTER TABLE `zonas`
  ADD PRIMARY KEY (`id_zona`);

ALTER TABLE `contratos` MODIFY `id_contrato` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `obligaciones` MODIFY `id_obligacion` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `pagos` MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `perfiles` MODIFY `id_perfil` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
ALTER TABLE `personas` MODIFY `id_persona` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `propiedades` MODIFY `id_propiedad` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `tipos_ajuste` MODIFY `id_ajuste` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
ALTER TABLE `tipos_documentos` MODIFY `id_tipo_doc` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
ALTER TABLE `tipos_inmueble` MODIFY `id_tipo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
ALTER TABLE `usuarios` MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;
ALTER TABLE `zonas` MODIFY `id_zona` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

ALTER TABLE `contratos`
  ADD CONSTRAINT `contratos_ibfk_1` FOREIGN KEY (`id_inquilino`) REFERENCES `personas` (`id_persona`),
  ADD CONSTRAINT `contratos_ibfk_2` FOREIGN KEY (`id_propiedad`) REFERENCES `propiedades` (`id_propiedad`),
  ADD CONSTRAINT `contratos_ibfk_3` FOREIGN KEY (`id_ajuste`) REFERENCES `tipos_ajuste` (`id_ajuste`);

ALTER TABLE `obligaciones`
  ADD CONSTRAINT `obligaciones_ibfk_1` FOREIGN KEY (`id_contrato`) REFERENCES `contratos` (`id_contrato`);

ALTER TABLE `pagos`
  ADD CONSTRAINT `pagos_ibfk_1` FOREIGN KEY (`id_contrato`) REFERENCES `contratos` (`id_contrato`);

ALTER TABLE `personas`
  ADD CONSTRAINT `personas_ibfk_1` FOREIGN KEY (`id_tipo_doc`) REFERENCES `tipos_documentos` (`id_tipo_doc`);

ALTER TABLE `propiedades`
  ADD CONSTRAINT `propiedades_ibfk_1` FOREIGN KEY (`id_propietario`) REFERENCES `personas` (`id_persona`),
  ADD CONSTRAINT `propiedades_ibfk_2` FOREIGN KEY (`id_tipo`) REFERENCES `tipos_inmueble` (`id_tipo`),
  ADD CONSTRAINT `propiedades_ibfk_3` FOREIGN KEY (`id_zona`) REFERENCES `zonas` (`id_zona`);

ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_perfil`) REFERENCES `perfiles` (`id_perfil`),
  ADD CONSTRAINT `usuarios_ibfk_2` FOREIGN KEY (`id_persona`) REFERENCES `personas` (`id_persona`);

COMMIT;