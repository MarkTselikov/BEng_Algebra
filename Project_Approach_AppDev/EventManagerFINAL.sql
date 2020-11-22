go
create database EventManager
go
use EventManager
go

--use master
--drop database EventManager

create table Subscription
(
IDSubscription int primary key identity,
[Type] nvarchar(50),
Price money not null
)

create table [Role](
IDRole int  constraint PK_Role primary key identity,
Name nvarchar(50) not null
)

--mozda ipak maknut oib

--krajnji korisnik ne placa registraciju, a event manager moze izabrati koliko mjeseci zeli placati
--ako radimo sigurnu aplikaciju hocemo  li hashirat pass onda treba promijenit velicnu

create table [User]
(
IDUser int constraint PK_User primary key identity,
Name nvarchar(50) not null,
Surname nvarchar(50) not null,
Email nvarchar(50) not null,
--OIB nvarchar(11) unique not null,
Username nvarchar(50) unique not null,
Pass nvarchar(250) not null,
RoleID int foreign key references [Role](IDRole),
SubscriptionID int foreign key references Subscription(IDSubscription) null,

)

create table Company
(
IDCompany int primary key identity,
Name nvarchar(50),
OIB nvarchar(11),
Email nvarchar(50),
[Address] nvarchar(50),
IDUser int foreign key references [User](IDUser)
)

create table Category
(
IDCategory int constraint PK_Category primary key identity,
[Type]  nvarchar(50) not null
)

create table City
(
IDCity int primary key identity,
Name nvarchar(50)
)

create table EventLocation
(
IDEventLocation int primary key identity,
Name nvarchar(100) null,
[Address] nvarchar(250) not null,
CityID int foreign key references City(IDCity) not null
)
--NOVO CIJENA NA LECTURE, MANAGERID !!!!!!!!!!!!!!!!!!!!!!!!
create table [Event](
IDEvent int constraint PK_Event primary key identity,
Name nvarchar(100) unique not null,
[Description] nvarchar(1000),
EventStart datetime not null,
EventEnd datetime not null,
CategoryID int foreign key references Category(IDCategory),
--cijena za cijeli event, automatski bi se trebale sve dvorane popunit (za 1 ili vise mjesta) ili to maknut pa napravit kupovinu samo po dvoranama
--Price money not  null,
BreakTime int not null default 15,
ManagerID int foreign key references [User](IDUser)
)


--u bazu se ne moze spremit samo vrijeme tako da ce trebat castat ili vadit vrijeme
create table Lecture
(
IDLecture int primary key identity,
Name nvarchar(100),
[Description] nvarchar(500) not null,
StartTime datetime not null,
EndTime datetime not null,
--grad i adresa
LocationID int foreign key references EventLocation(IDEventLocation) not null, 
LecturerID int foreign key references [User](IDUser) not null,
Hall nvarchar(100) not null,
Capacity int not null check (Capacity > 10),
EventID int foreign key references [Event](IDEvent) not null,
Price money not  null
)


create table LectureUser
(
IDLectureUser int primary key identity,
UserID int foreign key references [User](IDUser),
LectureID int foreign key references Lecture(IDLecture),
NumOfTickets int
)

--NOVO NOVO NOVO NOVO NOVO NOVO NOVONOVO NOVO NOVONOVO
create table ManagerPayment
(
IDPayment int primary key identity,
CreditCard nvarchar(50),
DatePayed datetime default getdate(),
UserID int foreign key references [User](IDUser),
[Sum] money
)

--NOVO NOVO NOVO NOVO NOVO NOVO NOVONOVO NOVO NOVONOVO
create table UserPayment
(
IDPayment int primary key identity,
CreditCard nvarchar(50),
DatePayed datetime default getdate(),
LectureID int foreign key references [Lecture](IDLecture),
UserID int foreign key references [User](IDUser),
NoOfTickets int,
[Sum] money
)

--s cim da ju jos povezem jer moze odjednom platit vise rezervacija (za vise dvorana), nova tablica?? il ce to bit prekompleksno, a u payment ide i subscription
--ili ostavit samo ovako pa programski racunat, ali onda ne znamo kaj je placeno od evenata . . . 



--DATA
go
--cijene su proizvoljne jer nisu definirane od strane Naruèitelja
insert into Subscription values
('1 mjesec',250),
('2 mjeseca', 400),
('3 mjeseca', 600)

go
insert into [Role] values
('Admin'),
('Korisnik'),
('EventManager'),
('Predavaè')

go
insert into Category values
('Medicina'),
('Pravo'),
('Inženjerstvo'),
('Dizajn'),
('Programiranje')

go
insert into City values
('Zagreb'),
('Rijeka'),
('Zadar'),
('Karlovac'),
('Split'),
('Varaždin')

--DUMMY DATA
--insert into [dbo].[User]( Name, Surname, Email, Username, RoleID, SubscriptionID, Pass) values
--('Admin', 'Admin', 'admin@mail.com','admin',1,null,'kuku0')

go
--IDUser, Name, Surname, Email, Username, Password, RoleID, SubscriptionID
insert into [User](Name, Surname, Email, Username,RoleID, SubscriptionID, Pass) values
('Admin', 'Admin', 'admin@email.com', 'admin', 1, null,'trik'),
('Petar', 'Periæ', 'perica@gmail.com', 'petar', 2, null,'345'),
('Mia', 'Mijiæ','mia@gmail.com', 'mia',3,1, '789'),
('Krešo', 'Krešiæ', 'krešo@email.com', 'krešo', 4, null,'305'),
('Ana', 'Aniæ', 'ana@gmail.com', 'ana', 2, null,'ancica'),
('Marko', 'Periæ', 'marko@gmail.com', 'marko', 2, null,'kigh'),
('Ema', 'Emiæ', 'ema@gmail.com', 'ema', 2, null,'emili0'),
('Ilija', 'Ili','ili@gmail.com', 'ili', 3,1,'123'),
('Kristina', 'kristiæ','kr@gmail.com', 'kristinak', 4, 1,'kika254')

go
insert into Company values
('Algebra d.o.o.', '12345678901','management@algebra.hr','Ilica 1', 2)

go
--stavit da manager moze dodavat lokacije?
insert into EventLocation values
('Sheraton', 'Ul. kneza Borne 2',1),
('Arena Zagreb', 'Ulica Vice Vukova 8',1),
('Algebra', 'Ilica 242',1)

go
insert into [Event] values
('Event 1', 'opis', '20180620 12:00:00', '20180625 20:45:00', 1,15,3),
('Event 2', 'opis', '20180620 12:00:00', '20180625 20:00:00', 2,30,3),
('Event 3', 'opis', '20180720 09:00:00', '20180720 13:00:00', 3,15,3)

go
insert into Lecture values
('Predavanje 1','opis', '20180620 12:00:00', '20180620 14:00:00',1,4,'Dvorana 1',100, 1,200),
('Predavanje 2','opis', '20180620 14:15:00', '20180620 16:15:00',1,4,'Dvorana 1',100, 1,200),
('Predavanje 3','opis', '20180620 16:30:00', '20180620 18:30:00',1,4,'Dvorana 1',100, 1,200),
('Predavanje 4','opis', '20180625 16:30:00', '20180625 18:30:00',1,4,'Dvorana 1',100, 1,200),
('Predavanje 4','opis', '20180620 12:00:00', '20180625 14:00:00',3,9,'Dvorana 1',100, 2,100)

go
insert into LectureUser values
(2,1,5),
(3,1,1),
(3,2,3)


select * from [User]
select * from [Role]

--PROCEDURES
go

--DODAVANJE KORISNIKA 
create proc AddUser
@name nvarchar(50),
@surname nvarchar(50),
@email nvarchar(50),
@username nvarchar(50),
@pass nvarchar(20),
@role int,
@subscription int

as
	begin
	if not exists(select Username from [User]  where Username =@username)
	insert into [User](Name, Surname, Email, Username, Pass,RoleID,SubscriptionID) values
	(@name, @surname,@email,@username,@pass,@role,@subscription)
	end

go

--EDITIRANJE KORISNIKA
create proc EditUser
@id int,
@name nvarchar(50),
@surname nvarchar(50),
@email nvarchar(50),
@username nvarchar(50),
@pass nvarchar(20)
as
	begin
	if exists(select IDUser from [User]  where IDUser =@id)
	update [User] set
	Name=@name, 
	Surname=@surname,
	Email= @email,
	Username=@username,
	Pass=@pass
	end


--EDIT EventManagera
go
create proc EditEventManager
@id int,
@name nvarchar(50),
@surname nvarchar(50),
@email nvarchar(50),
@username nvarchar(50),
@pass nvarchar(20),
@subcription int
as
	begin
	if exists(select IDUser from [User]  where IDUser =@id)
	update [User] set
	Name=@name, 
	Surname=@surname,
	Email= @email,
	Username=@username,
	Pass=@pass,
	SubscriptionID=@subcription
	end

--ADD COMPANY
go
create proc AddCompany
@userID int,
@name nvarchar(50),
@oib nvarchar(11),
@email nvarchar(50),
@address nvarchar(50)
as
	begin
	if exists(select IDUser from [User]  where IDUser =@userID)
	insert into Company values
	(@name,@oib,@email,@address, @userID)
	end


go
create proc AddEvent
	@name varchar(100),
	@descr varchar(1000),
	@start datetime,
	@end datetime,
	@catID int,
	@break int,
	@managerID int
as
begin
	insert into Event values(@name, @descr, @start, @end, @catID, @break, @managerID)
end


--CHANGE PASSWORD
go
create proc ChangePassword
@userID int,
@old nvarchar(250),
@new nvarchar(250)
as
	begin
	if exists (select * from [User] where Pass=@old and IDUser=@userID)
	update [User] set
	Pass=@new
	where IDUser=@userID
	end

	--trebalo bi vratit neku poruku, ako je neuspjesno, tj ako je krivi pass


--ADD PAYMENT
--EVENT MANAGER
go
create proc PaySubscription
@managerID int,
@creditcard nvarchar(50),
@subcriptionID int
as	
declare @price  int
	begin
set @price=(select Price from Subscription where IDSubscription=@subcriptionID)
	
	insert into ManagerPayment(CreditCard,UserID, [Sum]) values
	(@creditcard,@managerID,@price)
	end

go
--???????????????????????????????????????????????????????????
create proc PayTickets
@userID int,
@creditcard nvarchar(50),
@lectureID int,
@noOfTickets int
AS
declare @price  int
	begin
set @price=(select Price from Lecture where IDLecture=@lectureID)
	
	insert into UserPayment(CreditCard,UserID,LectureID, NoOfTickets, [Sum]) values
	(@creditcard,@userID,@lectureID, @noOfTickets, @price * @noOfTickets)
	end




--GET
go
create proc GetUserByEmail
	@email varchar(50)
as
	select * from [User] where Email = @email
go
create proc GetSubscription as
select * from Subscription

go
create proc GetCities as
select * from City

go
create proc GetCategory as
select * from Category

go
create proc GetRole as
select * from [Role]

go
create proc GetAllUsers as
select * from [User]

go
create proc GetUser
@userID int
as
select u.Name, u.Surname, u.Username from [User] as u
where IDUser=@userID


go
create proc GetAllEvents as
select * from [Event]

go
create proc GetCompany
@userID int
as
	begin
	select c.Name, c.OIB, c.Email, c.[Address] from Company as c
	where IDUser=@userID
	end

go
create proc GetLecturesPerEvent 
@eventID int
as
	begin
	if exists(select IDEvent from [Event] where IDEvent=@eventID)
	select * from Lecture
	where EventID=@eventID
	end

go
--create proc GetPaymentHistory
--@userID int
--as
--begin
--	if exists(select IDUser from [User] where IDUser=@userID)
--		begin
--		select p.CreditCard, p.[Sum] from Payment as p
--		where p.UserID=@userID
--		--treba li tu onda stavit kaj su platili, znaci subscription i evente?
--end
--end


--USERI KOJI DOLAZE NA EVENT, EVENT MANAGER IH VIDI

go
create proc GetUsersPerEvent
@eventID int
as
	begin
	if exists(select IDEvent from [Event] where IDEvent=@eventID)
	begin
	select u.Name, u.Surname, l.Name, lu.NumOfTickets from LectureUser as lu
	inner join Lecture as l
	on l.IDLecture=lu.LectureID
	inner join [User] as u
	on lu.UserID=u.IDUser
	where l.EventID=@eventID
	end
	end

go

--NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW NEW 
--MANAGER EVENT HISTORY

go
create proc EventsCreatedByManager
@managerID int
as
select * from [Event]
where ManagerID=@managerID


go
create proc ManagerOfEvent
	@eventID int
as
select m.* from EventManager as m
inner join [Event] as e
on e.ManagerID = m.IDManager
where e.IDEvent=@eventID

--USER EVENT HISTORY
go
create proc UserEventHistory
@userID int
as
	begin
	select l.* from LectureUser as lu
	inner join Lecture as l
	on l.IDLecture=lu.LectureID
	where lu.UserID=userID
	end


go
create proc LecturerEventHistory
@lecturerID int
as 
select * from Lecture
where LecturerID=@lecturerID

go
select * from LectureUser


select * from [Role]

