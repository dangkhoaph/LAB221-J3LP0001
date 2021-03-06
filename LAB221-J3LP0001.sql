USE [master]
GO
/****** Object:  Database [UserManager]    Script Date: 1/19/2020 10:54:42 PM ******/
CREATE DATABASE [UserManager]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'UserManager', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\UserManager.mdf' , SIZE = 3264KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'UserManager_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.MSSQLSERVER\MSSQL\DATA\UserManager_log.ldf' , SIZE = 816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [UserManager] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [UserManager].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [UserManager] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [UserManager] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [UserManager] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [UserManager] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [UserManager] SET ARITHABORT OFF 
GO
ALTER DATABASE [UserManager] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [UserManager] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [UserManager] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [UserManager] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [UserManager] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [UserManager] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [UserManager] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [UserManager] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [UserManager] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [UserManager] SET  ENABLE_BROKER 
GO
ALTER DATABASE [UserManager] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [UserManager] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [UserManager] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [UserManager] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [UserManager] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [UserManager] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [UserManager] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [UserManager] SET RECOVERY FULL 
GO
ALTER DATABASE [UserManager] SET  MULTI_USER 
GO
ALTER DATABASE [UserManager] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [UserManager] SET DB_CHAINING OFF 
GO
ALTER DATABASE [UserManager] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [UserManager] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [UserManager] SET DELAYED_DURABILITY = DISABLED 
GO
EXEC sys.sp_db_vardecimal_storage_format N'UserManager', N'ON'
GO
USE [UserManager]
GO
/****** Object:  Table [dbo].[History]    Script Date: 1/19/2020 10:54:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[History](
	[ID] [int] IDENTITY(1,1) NOT NULL,
	[HistoryID] [int] NOT NULL,
	[PromoID] [varchar](5) NOT NULL,
	[UserID] [varchar](5) NOT NULL,
	[Rank] [int] NULL,
	[Status] [bit] NULL,
	[Date] [datetime] NULL,
 CONSTRAINT [PK_History] PRIMARY KEY CLUSTERED 
(
	[ID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Promotions]    Script Date: 1/19/2020 10:54:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Promotions](
	[PromoID] [varchar](5) NOT NULL,
	[PromoName] [nvarchar](50) NULL,
	[PromoDes] [nvarchar](50) NULL,
 CONSTRAINT [PK_Promotions] PRIMARY KEY CLUSTERED 
(
	[PromoID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[Users]    Script Date: 1/19/2020 10:54:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[Users](
	[UserID] [varchar](5) NOT NULL,
	[Password] [varchar](100) NULL,
	[Username] [varchar](50) NULL,
	[Email] [varchar](30) NULL,
	[Phone] [varchar](10) NULL,
	[PhotoName] [varchar](20) NULL,
	[Role] [varchar](10) NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK__User__1788CCAC818BED20] PRIMARY KEY CLUSTERED 
(
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[UsersInPro]    Script Date: 1/19/2020 10:54:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[UsersInPro](
	[PromoID] [varchar](5) NOT NULL,
	[UserID] [varchar](5) NOT NULL,
	[HistoryID] [int] IDENTITY(1,1) NOT NULL,
	[Rank] [int] NULL,
	[Status] [bit] NULL,
 CONSTRAINT [PK_UsersInPro] PRIMARY KEY CLUSTERED 
(
	[PromoID] ASC,
	[UserID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
SET IDENTITY_INSERT [dbo].[History] ON 

INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (0, 0, N'P0001', N'U0001', 5, 1, CAST(N'2020-01-19 22:28:54.950' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (1, 1, N'P0001', N'U0003', 5, 1, CAST(N'2020-01-19 22:29:26.273' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (2, 2, N'P0001', N'U0006', 5, 1, CAST(N'2020-01-19 22:29:30.127' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (3, 3, N'P0005', N'U0001', 5, 1, CAST(N'2020-01-19 22:30:01.520' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (4, 4, N'P0003', N'U0004', 5, 1, CAST(N'2020-01-19 22:30:45.967' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (5, 5, N'P0003', N'U0006', 5, 1, CAST(N'2020-01-19 22:30:49.127' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (6, 6, N'P0003', N'U0003', 5, 1, CAST(N'2020-01-19 22:30:52.550' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (7, 1, N'P0001', N'U0003', 8, 1, CAST(N'2020-01-19 22:37:23.940' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (8, 7, N'P0001', N'U0008', 5, 1, CAST(N'2020-01-19 22:38:27.043' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (9, 8, N'P0004', N'U0008', 5, 1, CAST(N'2020-01-19 22:38:32.140' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (10, 9, N'P0002', N'U0008', 5, 1, CAST(N'2020-01-19 22:38:36.070' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (11, 9, N'P0002', N'U0008', 5, 0, CAST(N'2020-01-19 22:39:11.033' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (12, 8, N'P0004', N'U0008', 5, 0, CAST(N'2020-01-19 22:40:31.027' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (13, 9, N'P0002', N'U0008', 5, 1, CAST(N'2020-01-19 22:41:04.353' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (14, 8, N'P0004', N'U0008', 5, 1, CAST(N'2020-01-19 22:41:43.073' AS DateTime))
INSERT [dbo].[History] ([ID], [HistoryID], [PromoID], [UserID], [Rank], [Status], [Date]) VALUES (15, 6, N'P0003', N'U0003', 2, 1, CAST(N'2020-01-19 22:44:37.370' AS DateTime))
SET IDENTITY_INSERT [dbo].[History] OFF
INSERT [dbo].[Promotions] ([PromoID], [PromoName], [PromoDes]) VALUES (N'P0001', N'Ưu đãi Tết', N'Giảm 25%')
INSERT [dbo].[Promotions] ([PromoID], [PromoName], [PromoDes]) VALUES (N'P0002', N'Ưu đãi Hè', N'Giảm 20%')
INSERT [dbo].[Promotions] ([PromoID], [PromoName], [PromoDes]) VALUES (N'P0003', N'Ưu đãi Thu', N'Giảm 15%')
INSERT [dbo].[Promotions] ([PromoID], [PromoName], [PromoDes]) VALUES (N'P0004', N'Ưu đãi Đông', N'Giảm 10%')
INSERT [dbo].[Promotions] ([PromoID], [PromoName], [PromoDes]) VALUES (N'P0005', N'Ưu đãi Khách hàng thân thiết', N'Giảm 30%')
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0001', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Phan Huynh Dang Khoa', N'khoaphdse140609@fpt.edu.vn', N'0834450018', N'khoaphd.jpg', N'Admin', 1)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0002', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Huynh The Hien', N'hienhtse140848@fpt.edu.vn', N'0918076861', N'hienht.png', N'Admin', 0)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0003', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Nguyen Ngoc Quynh Nhu', N'nhunnqse140857@fpt.edu.vn', N'0379755214', N'nhunnq.jpg', N'User', 1)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0004', N'b3a8e0e1f9ab1bfe3a36f231f676f78bb30a519d2b21e6c530c0eee8ebb4a5d0', N'Nguyen Luong Trung Phong', N'phongnltse140644@fpt.edu.vn', N'1130113011', N'phongnlt.png', N'User', 1)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0005', N'b3a8e0e1f9ab1bfe3a36f231f676f78bb30a519d2b21e6c530c0eee8ebb4a5d0', N'Hua Nhut Quang', N'quanghnse140846@fpt.edu.vn', N'0778481948', N'quanghn.png', N'User', 0)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0006', N'b3a8e0e1f9ab1bfe3a36f231f676f78bb30a519d2b21e6c530c0eee8ebb4a5d0', N'Cao Quynh Trang', N'trangcqse130688@fpt.edu.vn', N'0896360926', N'trangcq.png', N'User', 1)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0007', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Nguyen Admin 7', N'admin-7@yahoo.com', N'7777777777', N'admin7.jpg', N'Admin', 0)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0008', N'8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', N'Tran User 8', N'user-8@gmail.com.vn', N'1818181818', N'user-8.png', N'User', 1)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0009', N'b3a8e0e1f9ab1bfe3a36f231f676f78bb30a519d2b21e6c530c0eee8ebb4a5d0', N'Le User 9', N'user9@gmail.com', N'1234567890', N'user9.png', N'User', 0)
INSERT [dbo].[Users] ([UserID], [Password], [Username], [Email], [Phone], [PhotoName], [Role], [Status]) VALUES (N'U0010', N'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', N'Cao Admin 10', N'admin-10@yahoo.com.vn', N'1234567890', N'admin10.png', N'Admin', 1)
SET IDENTITY_INSERT [dbo].[UsersInPro] ON 

INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0001', N'U0001', 0, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0001', N'U0003', 1, 8, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0001', N'U0006', 2, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0001', N'U0008', 7, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0002', N'U0008', 9, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0003', N'U0003', 6, 2, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0003', N'U0004', 4, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0003', N'U0006', 5, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0004', N'U0008', 8, 5, 1)
INSERT [dbo].[UsersInPro] ([PromoID], [UserID], [HistoryID], [Rank], [Status]) VALUES (N'P0005', N'U0001', 3, 5, 1)
SET IDENTITY_INSERT [dbo].[UsersInPro] OFF
/****** Object:  Index [IX_UsersInPro]    Script Date: 1/19/2020 10:54:42 PM ******/
ALTER TABLE [dbo].[UsersInPro] ADD  CONSTRAINT [IX_UsersInPro] UNIQUE NONCLUSTERED 
(
	[HistoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[History]  WITH CHECK ADD  CONSTRAINT [FK_History_UsersInPro] FOREIGN KEY([HistoryID])
REFERENCES [dbo].[UsersInPro] ([HistoryID])
GO
ALTER TABLE [dbo].[History] CHECK CONSTRAINT [FK_History_UsersInPro]
GO
ALTER TABLE [dbo].[UsersInPro]  WITH CHECK ADD  CONSTRAINT [FK_UsersInPro_Promotions] FOREIGN KEY([PromoID])
REFERENCES [dbo].[Promotions] ([PromoID])
GO
ALTER TABLE [dbo].[UsersInPro] CHECK CONSTRAINT [FK_UsersInPro_Promotions]
GO
ALTER TABLE [dbo].[UsersInPro]  WITH CHECK ADD  CONSTRAINT [FK_UsersInPro_Users] FOREIGN KEY([UserID])
REFERENCES [dbo].[Users] ([UserID])
GO
ALTER TABLE [dbo].[UsersInPro] CHECK CONSTRAINT [FK_UsersInPro_Users]
GO
/****** Object:  Trigger [dbo].[InsertToHistory]    Script Date: 1/19/2020 10:54:42 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TRIGGER [dbo].[InsertToHistory] ON [dbo].[UsersInPro]
AFTER INSERT, UPDATE
AS
	BEGIN
		INSERT INTO History
		SELECT HistoryID, PromoID, UserID, Rank, Status, GETDATE()
		FROM inserted
	END
GO
USE [master]
GO
ALTER DATABASE [UserManager] SET  READ_WRITE 
GO
