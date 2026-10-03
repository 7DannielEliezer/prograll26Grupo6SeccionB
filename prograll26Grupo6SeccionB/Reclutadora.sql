/*
 Navicat Premium Dump SQL

 Source Server         : Prueba2
 Source Server Type    : SQL Server
 Source Server Version : 16004265 (16.00.4265)
 Source Host           : 127.0.0.1:1483
 Source Catalog        : Reclutadora
 Source Schema         : dbo

 Target Server Type    : SQL Server
 Target Server Version : 16004265 (16.00.4265)
 File Encoding         : 65001

 Date: 02/10/2026 17:10:43
*/


-- ----------------------------
-- Table structure for BitacoraPostulacion
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[BitacoraPostulacion]') AND type IN ('U'))
	DROP TABLE [dbo].[BitacoraPostulacion]
GO

CREATE TABLE [dbo].[BitacoraPostulacion] (
  [IdBitacora] int  IDENTITY(1,1) NOT NULL,
  [IdPostulacion] int  NOT NULL,
  [IdUsuario] int  NOT NULL,
  [EstadoAnterior] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [EstadoNuevo] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Observacion] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [FechaCambio] datetime DEFAULT getdate() NULL
)
GO

ALTER TABLE [dbo].[BitacoraPostulacion] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of BitacoraPostulacion
-- ----------------------------
SET IDENTITY_INSERT [dbo].[BitacoraPostulacion] ON
GO

INSERT INTO [dbo].[BitacoraPostulacion] ([IdBitacora], [IdPostulacion], [IdUsuario], [EstadoAnterior], [EstadoNuevo], [Observacion], [FechaCambio]) VALUES (N'1', N'1006', N'1', N'Entrevistado', N'En Revisión', N'nada', N'2026-10-02 16:41:19.073')
GO

SET IDENTITY_INSERT [dbo].[BitacoraPostulacion] OFF
GO


-- ----------------------------
-- Table structure for Candidato
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Candidato]') AND type IN ('U'))
	DROP TABLE [dbo].[Candidato]
GO

CREATE TABLE [dbo].[Candidato] (
  [IdCandidato] int  IDENTITY(1,1) NOT NULL,
  [Nombres] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Apellidos] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [DPI] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Correo] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Telefono] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [FechaRegistro] datetime DEFAULT getdate() NULL,
  [Estado] bit DEFAULT 1 NULL
)
GO

ALTER TABLE [dbo].[Candidato] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Candidato
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Candidato] ON
GO

INSERT INTO [dbo].[Candidato] ([IdCandidato], [Nombres], [Apellidos], [DPI], [Correo], [Telefono], [FechaRegistro], [Estado]) VALUES (N'1', N'Manuel', N'Echeverria', N'1980564103025', N'm.echeverria@gmail.com', N'45568520', N'2026-10-02 04:47:55.077', N'0')
GO

INSERT INTO [dbo].[Candidato] ([IdCandidato], [Nombres], [Apellidos], [DPI], [Correo], [Telefono], [FechaRegistro], [Estado]) VALUES (N'4', N'Juan', N'Gomez', N'235625460511', N'jgomez@gmail.com', N'45856332', N'2026-10-02 04:53:01.643', N'0')
GO

INSERT INTO [dbo].[Candidato] ([IdCandidato], [Nombres], [Apellidos], [DPI], [Correo], [Telefono], [FechaRegistro], [Estado]) VALUES (N'1002', N'Antonio', N'Perez', N'2585654320511', N'aperez@gmail.com', N'58631254', NULL, N'0')
GO

INSERT INTO [dbo].[Candidato] ([IdCandidato], [Nombres], [Apellidos], [DPI], [Correo], [Telefono], [FechaRegistro], [Estado]) VALUES (N'1003', N'Emerson', N'Pineda', N'2356256310511', N'epineda@reclutadora.com', N'56853241', NULL, N'0')
GO

SET IDENTITY_INSERT [dbo].[Candidato] OFF
GO


-- ----------------------------
-- Table structure for Contratacion
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Contratacion]') AND type IN ('U'))
	DROP TABLE [dbo].[Contratacion]
GO

CREATE TABLE [dbo].[Contratacion] (
  [IdContratacion] int  IDENTITY(1,1) NOT NULL,
  [IdPostulacion] int  NOT NULL,
  [FechaContratacion] date DEFAULT getdate() NULL,
  [SueldoAcordado] decimal(10,2)  NOT NULL,
  [CostoServicio] decimal(10,2)  NOT NULL,
  [Estado] bit DEFAULT 1 NULL
)
GO

ALTER TABLE [dbo].[Contratacion] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Contratacion
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Contratacion] ON
GO

SET IDENTITY_INSERT [dbo].[Contratacion] OFF
GO


-- ----------------------------
-- Table structure for Empresa
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Empresa]') AND type IN ('U'))
	DROP TABLE [dbo].[Empresa]
GO

CREATE TABLE [dbo].[Empresa] (
  [IdEmpresa] int  IDENTITY(1,1) NOT NULL,
  [NombreEmpresa] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [NIT] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Telefono] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Correo] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Direccion] varchar(200) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Estado] bit DEFAULT 1 NULL
)
GO

ALTER TABLE [dbo].[Empresa] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Empresa
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Empresa] ON
GO

INSERT INTO [dbo].[Empresa] ([IdEmpresa], [NombreEmpresa], [NIT], [Telefono], [Correo], [Direccion], [Estado]) VALUES (N'1', N'Aje Maya Big Cola', N'654321', N'87654321333', N'bigcola@gmail.com', N'km 32.5 carretera', N'1')
GO

INSERT INTO [dbo].[Empresa] ([IdEmpresa], [NombreEmpresa], [NIT], [Telefono], [Correo], [Direccion], [Estado]) VALUES (N'2', N'TOLEDO', N'69094847', N'82890098', N'pegutierrez@hotmail.com', N'Palín', N'1')
GO

INSERT INTO [dbo].[Empresa] ([IdEmpresa], [NombreEmpresa], [NIT], [Telefono], [Correo], [Direccion], [Estado]) VALUES (N'1002', N'Macsa', N'8563421', N'65852341', N'macsa@gmail.com', N'km 21 carretera al pacifico Guatemala', N'1')
GO

SET IDENTITY_INSERT [dbo].[Empresa] OFF
GO


-- ----------------------------
-- Table structure for Evaluacion
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Evaluacion]') AND type IN ('U'))
	DROP TABLE [dbo].[Evaluacion]
GO

CREATE TABLE [dbo].[Evaluacion] (
  [IdEvaluacion] int  IDENTITY(1,1) NOT NULL,
  [IdPostulacion] int  NOT NULL,
  [IdEvaluador] int  NOT NULL,
  [TipoEvaluacion] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [FechaEvaluacion] datetime  NOT NULL,
  [Resultado] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Comentarios] varchar(max) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [Estado] bit DEFAULT 1 NOT NULL
)
GO

ALTER TABLE [dbo].[Evaluacion] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Evaluacion
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Evaluacion] ON
GO

INSERT INTO [dbo].[Evaluacion] ([IdEvaluacion], [IdPostulacion], [IdEvaluador], [TipoEvaluacion], [FechaEvaluacion], [Resultado], [Comentarios], [Estado]) VALUES (N'1', N'1007', N'1', N'Psicométrica', N'2026-10-02 16:39:53.843', N'satisfactorio', N'comprendimiento del 80% de la evaluacion', N'0')
GO

INSERT INTO [dbo].[Evaluacion] ([IdEvaluacion], [IdPostulacion], [IdEvaluador], [TipoEvaluacion], [FechaEvaluacion], [Resultado], [Comentarios], [Estado]) VALUES (N'2', N'1007', N'1', N'Entrevista RRHH', N'2026-10-02 16:40:54.097', N'satisfactorio', N'comprendimiento excelente en el area de excel', N'0')
GO

SET IDENTITY_INSERT [dbo].[Evaluacion] OFF
GO


-- ----------------------------
-- Table structure for Factura
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Factura]') AND type IN ('U'))
	DROP TABLE [dbo].[Factura]
GO

CREATE TABLE [dbo].[Factura] (
  [IdFactura] int  IDENTITY(1,1) NOT NULL,
  [IdEmpresa] int  NOT NULL,
  [IdContratacion] int  NOT NULL,
  [NumeroFactura] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [MontoTotal] decimal(10,2)  NOT NULL,
  [FechaEmision] datetime DEFAULT getdate() NULL,
  [EstadoFactura] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT 'Pendiente' NULL
)
GO

ALTER TABLE [dbo].[Factura] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Factura
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Factura] ON
GO

SET IDENTITY_INSERT [dbo].[Factura] OFF
GO


-- ----------------------------
-- Table structure for Postulacion
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Postulacion]') AND type IN ('U'))
	DROP TABLE [dbo].[Postulacion]
GO

CREATE TABLE [dbo].[Postulacion] (
  [IdPostulacion] int  IDENTITY(1,1) NOT NULL,
  [IdVacante] int  NOT NULL,
  [IdCandidato] int  NOT NULL,
  [FechaPostulacion] date DEFAULT getdate() NULL,
  [EstadoCandidato] varchar(30) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT 'aplicado' NULL
)
GO

ALTER TABLE [dbo].[Postulacion] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Postulacion
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Postulacion] ON
GO

INSERT INTO [dbo].[Postulacion] ([IdPostulacion], [IdVacante], [IdCandidato], [FechaPostulacion], [EstadoCandidato]) VALUES (N'1006', N'1', N'1', N'2026-10-02', N'aplicado')
GO

INSERT INTO [dbo].[Postulacion] ([IdPostulacion], [IdVacante], [IdCandidato], [FechaPostulacion], [EstadoCandidato]) VALUES (N'1007', N'2', N'1002', N'2026-10-02', N'aplicado')
GO

INSERT INTO [dbo].[Postulacion] ([IdPostulacion], [IdVacante], [IdCandidato], [FechaPostulacion], [EstadoCandidato]) VALUES (N'1008', N'1003', N'1002', N'2026-10-02', N'en evaluacion')
GO

INSERT INTO [dbo].[Postulacion] ([IdPostulacion], [IdVacante], [IdCandidato], [FechaPostulacion], [EstadoCandidato]) VALUES (N'1009', N'1002', N'4', N'2026-10-02', N'seleccionado')
GO

SET IDENTITY_INSERT [dbo].[Postulacion] OFF
GO


-- ----------------------------
-- Table structure for Rol
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Rol]') AND type IN ('U'))
	DROP TABLE [dbo].[Rol]
GO

CREATE TABLE [dbo].[Rol] (
  [IdRol] int  IDENTITY(1,1) NOT NULL,
  [NombreRol] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Descripcion] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL
)
GO

ALTER TABLE [dbo].[Rol] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Rol
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Rol] ON
GO

INSERT INTO [dbo].[Rol] ([IdRol], [NombreRol], [Descripcion]) VALUES (N'1', N'Gerente/Admin', N'Control total de la plataforma, usuarios y facturación')
GO

INSERT INTO [dbo].[Rol] ([IdRol], [NombreRol], [Descripcion]) VALUES (N'2', N'Reclutador Senior', N'Gestión de vacantes, evaluaciones y postulaciones')
GO

INSERT INTO [dbo].[Rol] ([IdRol], [NombreRol], [Descripcion]) VALUES (N'3', N'Reclutador Junior', N'Registro de candidatos y filtro inicial')
GO

INSERT INTO [dbo].[Rol] ([IdRol], [NombreRol], [Descripcion]) VALUES (N'1002', N'Admin', N'control total de la plataforma')
GO

SET IDENTITY_INSERT [dbo].[Rol] OFF
GO


-- ----------------------------
-- Table structure for Usuario
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Usuario]') AND type IN ('U'))
	DROP TABLE [dbo].[Usuario]
GO

CREATE TABLE [dbo].[Usuario] (
  [IdUsuario] int  IDENTITY(1,1) NOT NULL,
  [IdRol] int  NOT NULL,
  [Nombres] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Apellidos] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Correo] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [PasswordHash] varchar(255) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Estado] bit DEFAULT 1 NOT NULL,
  [FechaCreacion] datetime DEFAULT getdate() NULL
)
GO

ALTER TABLE [dbo].[Usuario] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Usuario
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Usuario] ON
GO

INSERT INTO [dbo].[Usuario] ([IdUsuario], [IdRol], [Nombres], [Apellidos], [Correo], [PasswordHash], [Estado], [FechaCreacion]) VALUES (N'1', N'1', N'Administrador', N'Principal', N'admin@reclutadora.com', N'admin123_hash', N'1', N'2026-09-27 11:03:55.017')
GO

INSERT INTO [dbo].[Usuario] ([IdUsuario], [IdRol], [Nombres], [Apellidos], [Correo], [PasswordHash], [Estado], [FechaCreacion]) VALUES (N'2', N'2', N'Carlos', N'Gómez', N'cgomez@reclutadora.com', N'recluta123_hash', N'1', N'2026-09-27 11:03:55.017')
GO

INSERT INTO [dbo].[Usuario] ([IdUsuario], [IdRol], [Nombres], [Apellidos], [Correo], [PasswordHash], [Estado], [FechaCreacion]) VALUES (N'1002', N'1002', N'Pedro', N'Gutierrez', N'pgutierrez@reclutadora.com', N'2015', N'1', N'2026-09-30 00:08:21.307')
GO

INSERT INTO [dbo].[Usuario] ([IdUsuario], [IdRol], [Nombres], [Apellidos], [Correo], [PasswordHash], [Estado], [FechaCreacion]) VALUES (N'2004', N'3', N'Ernesto', N'Garcia', N'egarcia@reclutadora.com', N'2323', N'1', N'2026-09-30 22:53:08.020')
GO

SET IDENTITY_INSERT [dbo].[Usuario] OFF
GO


-- ----------------------------
-- Table structure for Vacante
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[Vacante]') AND type IN ('U'))
	DROP TABLE [dbo].[Vacante]
GO

CREATE TABLE [dbo].[Vacante] (
  [IdVacante] int  IDENTITY(1,1) NOT NULL,
  [IdEmpresa] int  NOT NULL,
  [IdUsuarioCreador] int  NOT NULL,
  [TituloVacante] varchar(100) COLLATE SQL_Latin1_General_CP1_CI_AS  NOT NULL,
  [Descripcion] varchar(max) COLLATE SQL_Latin1_General_CP1_CI_AS  NULL,
  [SueldoOfrecido] decimal(10,2)  NULL,
  [EstadoVacante] varchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS DEFAULT 'Abierta' NULL,
  [FechaPublicacion] date DEFAULT getdate() NULL
)
GO

ALTER TABLE [dbo].[Vacante] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of Vacante
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Vacante] ON
GO

INSERT INTO [dbo].[Vacante] ([IdVacante], [IdEmpresa], [IdUsuarioCreador], [TituloVacante], [Descripcion], [SueldoOfrecido], [EstadoVacante], [FechaPublicacion]) VALUES (N'1', N'1', N'1', N'Piloto de Maquinaria', N'Experiencia comprobable para operación de maquinaria pesada', N'6500.00', N'Abierta', N'2026-09-27')
GO

INSERT INTO [dbo].[Vacante] ([IdVacante], [IdEmpresa], [IdUsuarioCreador], [TituloVacante], [Descripcion], [SueldoOfrecido], [EstadoVacante], [FechaPublicacion]) VALUES (N'2', N'2', N'1', N'Camara de canaels', N'seleccion y refrigeracion de canales para su procedimiento', N'5000.00', N'Activa', N'2026-10-02')
GO

INSERT INTO [dbo].[Vacante] ([IdVacante], [IdEmpresa], [IdUsuarioCreador], [TituloVacante], [Descripcion], [SueldoOfrecido], [EstadoVacante], [FechaPublicacion]) VALUES (N'1002', N'1', N'1', N'etiquetadora', N'verificacion y mantenimiento de etiquetadora para el proceso de envasado de producto', N'6500.00', N'Activa', N'2026-10-02')
GO

INSERT INTO [dbo].[Vacante] ([IdVacante], [IdEmpresa], [IdUsuarioCreador], [TituloVacante], [Descripcion], [SueldoOfrecido], [EstadoVacante], [FechaPublicacion]) VALUES (N'1003', N'1', N'1', N'Operador de Grua', N'operacion de grua para traslado de producto de materia prima', N'8500.00', N'Activa', N'2026-10-02')
GO

SET IDENTITY_INSERT [dbo].[Vacante] OFF
GO


-- ----------------------------
-- Table structure for VacanteReclutador
-- ----------------------------
IF EXISTS (SELECT * FROM sys.all_objects WHERE object_id = OBJECT_ID(N'[dbo].[VacanteReclutador]') AND type IN ('U'))
	DROP TABLE [dbo].[VacanteReclutador]
GO

CREATE TABLE [dbo].[VacanteReclutador] (
  [IdVacante] int  NOT NULL,
  [IdUsuario] int  NOT NULL,
  [FechaAsignacion] datetime DEFAULT getdate() NULL
)
GO

ALTER TABLE [dbo].[VacanteReclutador] SET (LOCK_ESCALATION = TABLE)
GO


-- ----------------------------
-- Records of VacanteReclutador
-- ----------------------------
INSERT INTO [dbo].[VacanteReclutador] ([IdVacante], [IdUsuario], [FechaAsignacion]) VALUES (N'1', N'1', N'2026-09-27 11:03:55.030')
GO

INSERT INTO [dbo].[VacanteReclutador] ([IdVacante], [IdUsuario], [FechaAsignacion]) VALUES (N'1', N'2', N'2026-09-27 11:03:55.030')
GO


-- ----------------------------
-- Auto increment value for BitacoraPostulacion
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[BitacoraPostulacion]', RESEED, 1)
GO


-- ----------------------------
-- Primary Key structure for table BitacoraPostulacion
-- ----------------------------
ALTER TABLE [dbo].[BitacoraPostulacion] ADD CONSTRAINT [PK_BitacoraPostulacion] PRIMARY KEY CLUSTERED ([IdBitacora])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Candidato
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Candidato]', RESEED, 2001)
GO


-- ----------------------------
-- Uniques structure for table Candidato
-- ----------------------------
ALTER TABLE [dbo].[Candidato] ADD CONSTRAINT [UQ_Candidato_DPI] UNIQUE NONCLUSTERED ([DPI] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Candidato
-- ----------------------------
ALTER TABLE [dbo].[Candidato] ADD CONSTRAINT [PK_Candidato] PRIMARY KEY CLUSTERED ([IdCandidato])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Contratacion
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Contratacion]', RESEED, 1)
GO


-- ----------------------------
-- Uniques structure for table Contratacion
-- ----------------------------
ALTER TABLE [dbo].[Contratacion] ADD CONSTRAINT [UQ_Contratacion_PostulacionID] UNIQUE NONCLUSTERED ([IdPostulacion] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Contratacion
-- ----------------------------
ALTER TABLE [dbo].[Contratacion] ADD CONSTRAINT [PK_Contratacion] PRIMARY KEY CLUSTERED ([IdContratacion])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Empresa
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Empresa]', RESEED, 2001)
GO


-- ----------------------------
-- Uniques structure for table Empresa
-- ----------------------------
ALTER TABLE [dbo].[Empresa] ADD CONSTRAINT [UQ_Empresa_NIT] UNIQUE NONCLUSTERED ([NIT] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Empresa
-- ----------------------------
ALTER TABLE [dbo].[Empresa] ADD CONSTRAINT [PK_Empresa] PRIMARY KEY CLUSTERED ([IdEmpresa])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Evaluacion
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Evaluacion]', RESEED, 1001)
GO


-- ----------------------------
-- Primary Key structure for table Evaluacion
-- ----------------------------
ALTER TABLE [dbo].[Evaluacion] ADD CONSTRAINT [PK_Evaluacion] PRIMARY KEY CLUSTERED ([IdEvaluacion])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Factura
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Factura]', RESEED, 1)
GO


-- ----------------------------
-- Uniques structure for table Factura
-- ----------------------------
ALTER TABLE [dbo].[Factura] ADD CONSTRAINT [UQ_Factura_NumeroFactura] UNIQUE NONCLUSTERED ([NumeroFactura] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Factura
-- ----------------------------
ALTER TABLE [dbo].[Factura] ADD CONSTRAINT [PK_Factura] PRIMARY KEY CLUSTERED ([IdFactura])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Postulacion
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Postulacion]', RESEED, 2001)
GO


-- ----------------------------
-- Checks structure for table Postulacion
-- ----------------------------
ALTER TABLE [dbo].[Postulacion] ADD CONSTRAINT [CK_Postulaciones_EstadoCandidato] CHECK ([EstadoCandidato]='rechazado' OR [EstadoCandidato]='seleccionado' OR [EstadoCandidato]='en evaluacion' OR [EstadoCandidato]='aplicado')
GO


-- ----------------------------
-- Primary Key structure for table Postulacion
-- ----------------------------
ALTER TABLE [dbo].[Postulacion] ADD CONSTRAINT [PK_Postulacion] PRIMARY KEY CLUSTERED ([IdPostulacion])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Rol
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Rol]', RESEED, 2001)
GO


-- ----------------------------
-- Primary Key structure for table Rol
-- ----------------------------
ALTER TABLE [dbo].[Rol] ADD CONSTRAINT [PK_Rol] PRIMARY KEY CLUSTERED ([IdRol])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Usuario
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Usuario]', RESEED, 3001)
GO


-- ----------------------------
-- Uniques structure for table Usuario
-- ----------------------------
ALTER TABLE [dbo].[Usuario] ADD CONSTRAINT [UQ_Usuario_Correo] UNIQUE NONCLUSTERED ([Correo] ASC)
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table Usuario
-- ----------------------------
ALTER TABLE [dbo].[Usuario] ADD CONSTRAINT [PK_Usuario] PRIMARY KEY CLUSTERED ([IdUsuario])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Auto increment value for Vacante
-- ----------------------------
DBCC CHECKIDENT ('[dbo].[Vacante]', RESEED, 2001)
GO


-- ----------------------------
-- Primary Key structure for table Vacante
-- ----------------------------
ALTER TABLE [dbo].[Vacante] ADD CONSTRAINT [PK_Vacante] PRIMARY KEY CLUSTERED ([IdVacante])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Primary Key structure for table VacanteReclutador
-- ----------------------------
ALTER TABLE [dbo].[VacanteReclutador] ADD CONSTRAINT [PK_VacanteReclutador] PRIMARY KEY CLUSTERED ([IdVacante], [IdUsuario])
WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)  
ON [PRIMARY]
GO


-- ----------------------------
-- Foreign Keys structure for table BitacoraPostulacion
-- ----------------------------
ALTER TABLE [dbo].[BitacoraPostulacion] ADD CONSTRAINT [FK_Bitacora_Postulacion] FOREIGN KEY ([IdPostulacion]) REFERENCES [dbo].[Postulacion] ([IdPostulacion]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[BitacoraPostulacion] ADD CONSTRAINT [FK_Bitacora_Usuario] FOREIGN KEY ([IdUsuario]) REFERENCES [dbo].[Usuario] ([IdUsuario]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Contratacion
-- ----------------------------
ALTER TABLE [dbo].[Contratacion] ADD CONSTRAINT [FK_Contratacion_Postulacion] FOREIGN KEY ([IdPostulacion]) REFERENCES [dbo].[Postulacion] ([IdPostulacion]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Evaluacion
-- ----------------------------
ALTER TABLE [dbo].[Evaluacion] ADD CONSTRAINT [FK_Evaluacion_Postulacion] FOREIGN KEY ([IdPostulacion]) REFERENCES [dbo].[Postulacion] ([IdPostulacion]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[Evaluacion] ADD CONSTRAINT [FK_Evaluacion_Evaluador] FOREIGN KEY ([IdEvaluador]) REFERENCES [dbo].[Usuario] ([IdUsuario]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Factura
-- ----------------------------
ALTER TABLE [dbo].[Factura] ADD CONSTRAINT [FK_Factura_Empresa] FOREIGN KEY ([IdEmpresa]) REFERENCES [dbo].[Empresa] ([IdEmpresa]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[Factura] ADD CONSTRAINT [FK_Factura_Contratacion] FOREIGN KEY ([IdContratacion]) REFERENCES [dbo].[Contratacion] ([IdContratacion]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Postulacion
-- ----------------------------
ALTER TABLE [dbo].[Postulacion] ADD CONSTRAINT [FK_Postulacion_Vacante] FOREIGN KEY ([IdVacante]) REFERENCES [dbo].[Vacante] ([IdVacante]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[Postulacion] ADD CONSTRAINT [FK_Postulacion_Candidato] FOREIGN KEY ([IdCandidato]) REFERENCES [dbo].[Candidato] ([IdCandidato]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Usuario
-- ----------------------------
ALTER TABLE [dbo].[Usuario] ADD CONSTRAINT [FK_Usuario_Rol] FOREIGN KEY ([IdRol]) REFERENCES [dbo].[Rol] ([IdRol]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table Vacante
-- ----------------------------
ALTER TABLE [dbo].[Vacante] ADD CONSTRAINT [FK_Vacante_Empresa] FOREIGN KEY ([IdEmpresa]) REFERENCES [dbo].[Empresa] ([IdEmpresa]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[Vacante] ADD CONSTRAINT [FK_Vacante_UsuarioCreador] FOREIGN KEY ([IdUsuarioCreador]) REFERENCES [dbo].[Usuario] ([IdUsuario]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO


-- ----------------------------
-- Foreign Keys structure for table VacanteReclutador
-- ----------------------------
ALTER TABLE [dbo].[VacanteReclutador] ADD CONSTRAINT [FK_VacanteReclutador_Vacante] FOREIGN KEY ([IdVacante]) REFERENCES [dbo].[Vacante] ([IdVacante]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

ALTER TABLE [dbo].[VacanteReclutador] ADD CONSTRAINT [FK_VacanteReclutador_Usuario] FOREIGN KEY ([IdUsuario]) REFERENCES [dbo].[Usuario] ([IdUsuario]) ON DELETE NO ACTION ON UPDATE NO ACTION
GO

