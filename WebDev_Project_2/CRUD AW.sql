use AdventureWorksOBP
go

--create procedure GetBuyers
alter procedure GetBuyers
as
begin 
	select top 200 * from Kupac
end
go


create procedure GetBuyer
	@idBuyer int
as
begin
	select * from Kupac
	where IDKupac = @idBuyer
end
go

create procedure GetBill
	@idBill int
as
begin
	select * from Racun
	where IDRacun = @idBill
end
go


create procedure GetBuyerBills
	@idBuyer int 
as
begin
	select r.* from Kupac as k
	inner join Racun as r
	on k.IDKupac = r.KupacID
	where k.IDKupac = @idBuyer
end
go


create procedure GetCity
	@cityID int
as
begin
	select * from Grad where IDGrad = @cityID
end
go


create procedure GetCities
as
begin
	select * from Grad
end
go


create procedure GetCommercial
	@comID int
as
begin
	select * from Komercijalist where IDKomercijalist = @comID
end
go


create procedure GetProduct
	@productID int
as
begin
	select * from Proizvod where IDProizvod = @productID
end
go


create procedure GetState
	@cityID int
as
begin
	select d.* from Drzava as d
	inner join Grad as g on d.IDDrzava = g.DrzavaID
	where g.IDGrad = @cityID
end
go


create procedure GetItemsByBill
	@idBill int
as
begin
	select * from Stavka where RacunID = @idBill
end
go




create procedure UpdateBuyer
	@id int,
	@fName nvarchar(50),
	@lName nvarchar(50),
	@email nvarchar(50),
	@phone nvarchar(25),
	@cityID int
as
begin
	update Kupac set Ime=@fName, Prezime=@lName, Email=@email, Telefon=@phone, GradID=@cityID
		where IDKupac=@id 
end
go


create procedure CreateBuyer
	@fName nvarchar(50),
	@lName nvarchar(50),
	@email nvarchar(50),
	@phone nvarchar(25),
	@cityID int
as
begin
	insert into Kupac values(@fName, @lName, @email, @phone, @cityID)
end
go


create procedure InsertKupac
	@fName nvarchar(50),
	@lName nvarchar(50),
	@email nvarchar(50),
	@phone nvarchar(25),
	@cityID int
as
begin
	insert into Kupac values(@fName, @lName, @email, @phone, @cityID)
end
go


