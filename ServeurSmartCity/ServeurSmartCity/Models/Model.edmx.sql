
-- --------------------------------------------------
-- Entity Designer DDL Script for SQL Server 2005, 2008, 2012 and Azure
-- --------------------------------------------------
-- Date Created: 05/04/2015 09:04:38
-- Generated from EDMX file: C:\Users\Simon\Documents\GitHub\SmartCity\ServeurSmartCity\ServeurSmartCity\Models\Model.edmx
-- --------------------------------------------------

SET QUOTED_IDENTIFIER OFF;
GO
USE [Database1];
GO
IF SCHEMA_ID(N'dbo') IS NULL EXECUTE(N'CREATE SCHEMA [dbo]');
GO

-- --------------------------------------------------
-- Dropping existing FOREIGN KEY constraints
-- --------------------------------------------------


-- --------------------------------------------------
-- Dropping existing tables
-- --------------------------------------------------

IF OBJECT_ID(N'[dbo].[LieuSet]', 'U') IS NOT NULL
    DROP TABLE [dbo].[LieuSet];
GO

-- --------------------------------------------------
-- Creating all tables
-- --------------------------------------------------

-- Creating table 'LieuSet'
CREATE TABLE [dbo].[LieuSet] (
    [Id] int IDENTITY(1,1) NOT NULL,
    [nom] nvarchar(max)  NULL,
    [type] nvarchar(max)  NULL,
    [type_detail] nvarchar(max)  NULL,
    [classement] nvarchar(max)  NULL,
    [adresse] nvarchar(max)  NULL,
    [codepostal] nvarchar(max)  NULL,
    [commune] nvarchar(max)  NULL,
    [telephone] nvarchar(max)  NULL,
    [fax] nvarchar(max)  NULL,
    [email] nvarchar(max)  NULL,
    [siteweb] nvarchar(max)  NULL,
    [facebook] nvarchar(max)  NULL,
    [ouverture] nvarchar(max)  NULL,
    [tarifsenclair] nvarchar(max)  NULL,
    [tarifsmin] nvarchar(max)  NULL,
    [tarifsmax] nvarchar(max)  NULL,
    [producteur] nvarchar(max)  NULL,
    [latitude] float  NULL,
    [longitude] float  NULL,
    [abscisses] smallint  NULL,
    [ordonnees] smallint  NULL
);
GO

-- --------------------------------------------------
-- Creating all PRIMARY KEY constraints
-- --------------------------------------------------

-- Creating primary key on [Id] in table 'LieuSet'
ALTER TABLE [dbo].[LieuSet]
ADD CONSTRAINT [PK_LieuSet]
    PRIMARY KEY CLUSTERED ([Id] ASC);
GO

-- --------------------------------------------------
-- Creating all FOREIGN KEY constraints
-- --------------------------------------------------

-- --------------------------------------------------
-- Script has ended
-- --------------------------------------------------