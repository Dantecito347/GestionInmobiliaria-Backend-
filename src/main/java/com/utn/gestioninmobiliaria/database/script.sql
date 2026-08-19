CREATE DATABASE gestion_inmobiliaria;

USE gestion_inmobiliaria;

CREATE TABLE perfiles (
    id_perfil INT AUTO_INCREMENT PRIMARY KEY,
    nombre_perfil VARCHAR(255) NOT NULL
);

INSERT INTO perfiles (id_perfil, nombre_perfil) VALUES
(1, 'Administrador/Gerente'),
(2, 'Agente Inmobiliario');

CREATE TABLE tipos_documentos (
    id_tipo_doc INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

INSERT INTO tipos_documentos (id_tipo_doc, descripcion) VALUES
(1, 'DNI - Documento Nacional de Identidad'),
(2, 'LC - Libreta Cívica'),
(3, 'LE - Libreta de Enrolamiento'),
(4, 'PAS - Pasaporte');

CREATE TABLE tipos_ajuste (
    id_ajuste INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(255) NOT NULL
);

INSERT INTO tipos_ajuste (id_ajuste, descripcion) VALUES
(1, 'Trimestral'),
(2, 'Cuatrimestral'),
(3, 'Semestral');

CREATE TABLE tipos_inmueble (
    id_tipo INT AUTO_INCREMENT PRIMARY KEY,
    descripcion VARCHAR(50) NOT NULL
);

INSERT INTO tipos_inmueble (id_tipo, descripcion) VALUES
(1, 'Casa'),
(2, 'Departamento'),
(3, 'Local'),
(4, 'Cochera'),
(5, 'Lote');

CREATE TABLE zonas (
    id_zona INT AUTO_INCREMENT PRIMARY KEY,
    nombre_barrio VARCHAR(100) NOT NULL,
    ciudad VARCHAR(100) DEFAULT 'Coronel Pringles',
    zona VARCHAR(100) NOT NULL
);

INSERT INTO zonas (id_zona, nombre_barrio, ciudad, zona) VALUES
(1, 'Barrio General Mitre', 'Coronel Pringles', 'Centro'),
(2, 'Barrio Roca', 'Coronel Pringles', 'Norte'),
(3, 'Barrio San Martín', 'Coronel Pringles', 'Sur'),
(4, 'Barrio Alem', 'Coronel Pringles', 'Este'),
(5, 'Barrio Dorrego', 'Coronel Pringles', 'Oeste');

CREATE TABLE personas (
    id_persona INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    id_tipo_doc INT NOT NULL,
    nro_documento VARCHAR(20) NOT NULL,
    cuil_cuit VARCHAR(20) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cbu_alias VARCHAR(100) NOT NULL,
    fecha_alta_sistema DATETIME DEFAULT CURRENT_TIMESTAMP,
    activo TINYINT(1) DEFAULT 1,

    UNIQUE (id_tipo_doc, nro_documento),
    FOREIGN KEY (id_tipo_doc) REFERENCES tipos_documentos(id_tipo_doc)
);

INSERT INTO personas (
    id_persona,
    nombre,
    apellido,
    id_tipo_doc,
    nro_documento,
    cuil_cuit,
    telefono,
    email,
    cbu_alias,
    fecha_alta_sistema,
    activo
) VALUES
(1, 'Juan', 'Pérez', 1, '12345678', '20-12345678-9', '291-4451890', 'juanperez22@mail.com', 'juan.perecito.61', '2026-07-27 21:28:10', 1),
(2, 'María', 'Gómez', 1, '87654321', '27-87654321-5', '291-4000001', 'mariagomez@mail.com', 'maria.alias', '2026-07-27 21:28:10', 1),
(3, 'Carlos', 'López', 1, 'AB123456', '23-11223344-7', '291-4000002', 'carloslopez@mail.com', 'carlos.alias', '2026-07-27 21:28:10', 1),
(5, 'Marcos ', 'Godoy', 1, '13980437', '224478954', '2923410901', 'materagodoy@gmail.com', 'la.matera.godoy', '2026-08-04 09:40:11', 1),
(6, 'Emir', 'Halter Kober', 1, '41331698', '5699234167', '2922413171', 'Emir271@gmail.com', 'emi.hal.kob', '2026-08-04 09:43:03', 1),
(11, 'Enzo', 'Fernandez', 1, '47891340', '6641553349', '2922345698', 'EnzoMundial@gmail.com', 'qatar.2022', '2026-08-06 14:46:50', 1),
(12, 'Lionel', 'Messi', 1, '22456989', '6755324771', '923987012', 'BarcaForever@gmail.com', 'messi.goat', '2026-08-06 21:48:03', 1),
(13, 'Victoria', 'Heredia', 1, '47742510', '27477425106', '2922432261', 'victoriaheredia850@gmail.com', 'perro.tomando.mate', '2026-08-15 21:14:16', 1);

CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    id_perfil INT NOT NULL,
    id_persona INT NOT NULL,
    activo TINYINT(1) DEFAULT 1,
    email VARCHAR(100) NOT NULL UNIQUE,
    reset_token VARCHAR(255) DEFAULT NULL,
    reset_token_expiration DATETIME(6) DEFAULT NULL,

    FOREIGN KEY (id_perfil) REFERENCES perfiles(id_perfil),
    FOREIGN KEY (id_persona) REFERENCES personas(id_persona)
);

INSERT INTO usuarios (
    id_usuario,
    username,
    password_hash,
    id_perfil,
    id_persona,
    activo,
    email,
    reset_token,
    reset_token_expiration
) VALUES
(1, 'admin', '$2a$10$8vOYZMAaqpHcNeg67SgDvu0IJqm3rsUznRYqfDYZ545sA4bl71lNy', 1, 1, 1, 'dantecoz18@gmail.com', '4690b55a-8a57-4fc1-ad1c-22f510c71e38', '2026-08-15 21:24:50.000000'),
(2, 'agente1', '$2y$10$aTvaGa382HBRI6dYcT91ZeKgboqb0fopY4WeziisKS9awD0XTLHvS', 2, 2, 1, 'agente1@mail.com', NULL, NULL);

CREATE TABLE propiedades (
    id_propiedad INT AUTO_INCREMENT PRIMARY KEY,
    direccion VARCHAR(200) NOT NULL,
    id_propietario INT NOT NULL,
    id_tipo INT NOT NULL,
    id_zona INT NOT NULL,
    estado VARCHAR(50) DEFAULT NULL,
    activo TINYINT(1) DEFAULT 1,

    FOREIGN KEY (id_propietario) REFERENCES personas(id_persona),
    FOREIGN KEY (id_tipo) REFERENCES tipos_inmueble(id_tipo),
    FOREIGN KEY (id_zona) REFERENCES zonas(id_zona)
);

INSERT INTO propiedades (
    id_propiedad,
    direccion,
    id_propietario,
    id_tipo,
    id_zona,
    estado,
    activo
) VALUES
(1, 'Av. Siempre Viva 123', 1, 1, 1, 'Reservada', 1),
(2, 'Calle Falsa 456', 2, 2, 2, 'Ocupada', 1),
(3, 'Av. Mitre 1425', 3, 3, 1, 'Reservada', 1),
(4, 'Alem 2450', 6, 4, 4, 'Ocupada', 1),
(5, 'Dorrego 1372', 5, 1, 5, 'Ocupada', 1),
(9, 'San Martin 623', 13, 2, 3, 'Disponible', 1);


CREATE TABLE contratos (
    id_contrato INT AUTO_INCREMENT PRIMARY KEY,
    id_inquilino INT NOT NULL,
    id_propiedad INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    valor_inicial DECIMAL(12,2) NOT NULL,
    id_ajuste INT NOT NULL,
    estado VARCHAR(50) DEFAULT NULL,

    FOREIGN KEY (id_inquilino) REFERENCES personas(id_persona),
    FOREIGN KEY (id_propiedad) REFERENCES propiedades(id_propiedad),
    FOREIGN KEY (id_ajuste) REFERENCES tipos_ajuste(id_ajuste)
);

INSERT INTO contratos (
    id_contrato,
    id_inquilino,
    id_propiedad,
    fecha_inicio,
    fecha_fin,
    valor_inicial,
    id_ajuste,
    estado
) VALUES
(1, 2, 1, '2026-01-01', '2026-12-31', 50000.00, 1, 'Activo'),
(2, 3, 2, '2026-02-01', '2027-01-31', 75000.00, 2, 'Activo'),
(3, 1, 3, '2026-08-04', '2027-02-04', 70000.00, 1, 'Activo'),
(4, 5, 4, '2026-08-05', '2027-01-05', 67000.00, 1, 'Activo'),
(5, 3, 5, '2026-08-07', '2027-02-07', 80000.00, 1, 'Activo');

CREATE TABLE obligaciones (
    id_obligacion INT AUTO_INCREMENT PRIMARY KEY,
    id_contrato INT NOT NULL,
    descripcion VARCHAR(150) NOT NULL,
    importe_referencia DECIMAL(10,2) NOT NULL,
    pagado_por_inquilino TINYINT(1) DEFAULT 0,

    FOREIGN KEY (id_contrato) REFERENCES contratos(id_contrato)
);

INSERT INTO obligaciones (
    id_obligacion,
    id_contrato,
    descripcion,
    importe_referencia,
    pagado_por_inquilino
) VALUES
(3, 2, 'Expensas', 12000.00, 0),
(5, 1, 'Expensas', 10000.00, 0),
(6, 1, 'Impuestos', 5000.00, 1),
(13, 4, 'Expensas', 16000.00, 1),
(14, 4, 'Impuestos', 20000.00, 1),
(22, 5, 'Expensas', 15000.00, 1),
(23, 5, 'Impuestos', 20000.00, 0);

CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_contrato INT NOT NULL,
    mes_cobertura INT NOT NULL,
    anio_cobertura INT NOT NULL,
    monto_obligatorio DECIMAL(12,2) NOT NULL,
    monto_pagado DECIMAL(12,2) NOT NULL,
    fecha_pago DATE DEFAULT NULL,
    estado_pago VARCHAR(50) DEFAULT NULL,

    FOREIGN KEY (id_contrato) REFERENCES contratos(id_contrato)
);

INSERT INTO pagos (
    id_pago,
    id_contrato,
    mes_cobertura,
    anio_cobertura,
    monto_obligatorio,
    monto_pagado,
    fecha_pago,
    estado_pago
) VALUES
(1, 1, 1, 2026, 50000.00, 50000.00, '2026-01-10', 'Pagado'),
(3, 2, 2, 2026, 75000.00, 75000.00, '2026-02-15', 'Pagado'),
(4, 4, 8, 2026, 50000.00, 150000.00, '2026-08-04', 'Atrasado');