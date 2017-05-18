DROP DATABASE IF EXISTS prs;
CREATE DATABASE prs;
USE prs;

CREATE TABLE Vendors(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
    Code 			Varchar(10),
    Name			varchar(255),
    Address			varchar(255),
    City			varchar(255),
    State			varchar(2),
    ZipCode			varchar(5),
    Phone			varchar(12),
	Email			varchar(255),
    Preapproved 	BIT(1),
    CONSTRAINT vcode unique(Code)
);	

CREATE TABLE Products(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
    Name			varchar(10),
    PartNumber		varchar(10),
    Price			Decimal(10,2),
    Unit			varchar(10),
    PhotoPath		varchar(255),
	VendorID		INT,
    constraint part_num unique(PartNumber),
    Foreign Key (VendorID) references Vendors(ID)
);

CREATE TABLE Role(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
    Code			varchar(13)
);

CREATE TABLE Users(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
    Username		varchar(20),
    Password		varchar(15),
	FirstName		varchar(20),
	LastName		varchar(20),
    Phone			varchar(12),
    Email			varchar(75),
    RoleID			INT,
    foreign key (RoleID) references Role(ID),
    CONSTRAINT uname unique(Username)
);

CREATE TABLE Requests(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
	Description		varchar(100),
    Justification	varchar(255),
    DateNeeded		date,
    UserID			int,
    DeliveryMode	varchar(25),
	DocAttached		bit(1),
    Status			varchar(10),
    Total			decimal(10,2),
    SubmittedDate	date,
    foreign key (UserID) references Users(ID)
);

CREATE TABLE LineItems(
	ID    			INT            PRIMARY KEY  AUTO_INCREMENT,
	RequestID		int,
    ProductID		int,
    Quantity		int,
    foreign key (RequestID) references Requests(ID),
    foreign key (ProductID) references Products(ID),
    CONSTRAINT req_pdt unique (RequestID, ProductID)
);

INSERT INTO Vendors VALUES
(1, 'Apple', 'Apple Inc.', '7875 Montgomery Rd,', 'Cincinnati', 'OH', '45236', '513-826-3620', 'avendor@apple.com', 0),
(2, 'OfficeDepo', 'Offie Depot Inc.', '7610 Voice of America Centre Dr.', 'West Chester Township', 'OH', '45069', '513-755-1676', 'officedepotvendor@gmail.com', 1);

INSERT INTO Products VALUES
(1, 'iPad Pro', '00131', 599.99, '9.7-inch','asdf',1),
(2, 'iPad Pro', '00123', 799.99, '12.9-inch','asdf',1);

INSERT INTO Role VALUES
(1, 'Employee'),
(2, 'Manager'),
(3, 'Administrator'); 

INSERT INTO Users values
(1, 'neyerj','Alligator7', 'James', 'Neyer','513-777-6672', 'jamesneyer@gmail.com', 3),
(2, 'rahill','Purple12','Rachel','Hill','555-564-8972','rahill@gmail.com',2),
(3, 'markp','E43rthw0rmj1m','Mark','Pruner','555-777-6672','markpruner@gmail.com',1);

INSERT INTO Requests VALUES
(1, 'iPad Pro','Would use to test apps on this device', '2017-08-03', 3, 'pickup', 0, 'submitted', 599.99, '2017-05-17'),
(2, 'iPad Pro','For fun', '2017-08-03', 3, 'pickup', 0, 'submitted', 599.99, '2017-05-17');

INSERT INTO LineItems VALUES
(1, 1, 1, 1),
(2, 2, 1, 1);

GRANT SELECT, INSERT, DELETE, UPDATE
ON prs.*
TO prs_user@localhost
IDENTIFIED BY 'sesame';