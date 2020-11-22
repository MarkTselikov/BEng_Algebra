create database RWA_Project1
go

use RWA_Project1
go

--drop table Person
--drop table City
--drop table [RoleLogin]

create table City(
	IDCity int constraint PK_IDCity primary key identity,
	[Name] varchar(50)
)
go

insert into City values('Moscow')
insert into City values('Boston')
insert into City values('London')
insert into City values('Amsterdam')
insert into City values('Vladivostok')
insert into City values('McMurdo Station, Antarctica')
go


create table [RoleLogin](
	IDRole int constraint PK_IDRole primary key identity,
	[Name] varchar(50)
)
go


insert into RoleLogin values('User')
insert into RoleLogin values('Administrator')
go


create table Person(
	IDPerson int constraint PK_IDPerson primary key identity,
	FName varchar(50) not null,
	LName varchar(50) not null,
	Phone varchar(50) not null,
	Email varchar(50) not null,
	Pass varchar(50) not null,
	[RoleID] int not null foreign key references RoleLogin(IDRole),
	City int not null foreign key references City(IDCity)
)
go


create procedure GetCity
	@cityID int
as
begin
	select * from City where IDCity = @cityID
end
go


create procedure GetAllCities
as
begin
	select * from City
end
go


create procedure GetRole
	@roleID int
as
begin
	select * from RoleLogin where IDRole = @roleID
end
go


create procedure GetAllRoles
as
begin
	select * from RoleLogin
end
go


create procedure GetPerson
	@id int
as
begin
	select * from Person
	where IDPerson = @id
end
go


create procedure GetAllPeople
as
begin
	select * from Person
end
go


create procedure DeletePerson
	@id int
as
begin
	delete from Person 
	where IDPerson = @id
end
go


create procedure CreatePerson
	@fName varchar(50),
	@lName varchar(50),
	@email varchar(50),
	@phone varchar(25),
	@password varchar(50),
	@roleID int,
	@cityID int
as
begin
	insert into Person values(@fName, @lName, @phone, @email, @password, @roleID, @cityID)
end
go


create procedure UpdatePersonGV
	@id int,
	@fName nvarchar(50),
	@lName nvarchar(50),
	@email nvarchar(50),
	@phone nvarchar(25),
	@roleID int
as
begin
	update Person set FName=@fName, LName=@lName, Phone=@phone, Email=@email, RoleID=@roleID
		where IDPerson=@id 

	--update Email set [Address]=@email where 
end
go


create procedure UpdatePerson
	@id int,
	@fName nvarchar(50),
	@lName nvarchar(50),
	@email nvarchar(50),
	@phone nvarchar(25),
	@password varchar(50),
	@roleID int,
	@cityID int
as
begin
	update Person set FName=@fName, LName=@lName, Phone=@phone, Email=@email, Pass = @password, [RoleID]=@roleID, City=@cityID
		where IDPerson=@id 

	--update Email set [Address]=@email where 
end
go


create proc CheckLogin
	@email varchar(50),
	@password varchar(50)
as
begin
	if exists(select * from Person where EMail = @email and Pass = @password)
	begin
		select 1 as 'Result'
	end
	else 
	begin
		select 0 as 'Result'
	end
end
go


create proc GetUserByEmail
	@email varchar(50)
as
begin
	select * from Person where EMail = @email
end
go




insert into 

insert into Person values('Testing', 'Test', '12345', 'Test@test.test', 1, 2)



grant execute to [Login]

DBCC CHECKIDENT('Person', RESEED, 0)
exec CreatePerson 'Adam', 'Trump', 'adam0@adventure-works.com', '7265550191', '123', 1, 2

select * from person

exec CheckLogin 'alan1@adventure-works.com', '123'


select * from [Person]
select * from [RoleLogin]
select * from [City]

update person set RoleID = 2 where IDPerson = 9