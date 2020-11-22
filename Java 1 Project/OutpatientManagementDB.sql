use outpatientdb
go


--
-- Creating the database tables

create table Doctor(
	IDDoctor int constraint PK_IDDoctor primary key identity,
	FirstName varchar(50) not null,
	MiddleName varchar(50),
	LastName varchar(50) not null,
	Field varchar(100) not null,
	Sex varchar(1),
	BirthDate varchar(20)
)
go

create table Patient(
	IDPatient int constraint PK_IDPatient primary key identity,
	IncomingDate varchar(20)
)
go

create table Record(
	IDRecord int constraint PK_IDRecord primary key identity,
	Diagnosis varchar(100),
	DiagnosisDescription varchar(500),
	Appointments varchar(500),
	Tests varchar(500),
	Prescription varchar(500),
	BillAmount int,
	DoctorID int constraint FK_DictorID
		foreign key references Doctor(IDDoctor),
	PatientID int constraint FK_PatientID_Record
		foreign key references Patient(IDPatient),
)
go



create table BasicInformation(
	IDBasicInformation int constraint PK_IDBasicInformation primary key identity,
	FirstName varchar(50) not null,
	MiddleName varchar(50),
	LastName varchar(50) not null,
	Sex varchar(1),
	BirthDate varchar(20),
	IsComplete Bit,
	PatientID int constraint FK_Basic_Details
		foreign key references Patient(IDPatient)
)
go


create table ContactDetails(
	IDContactDetails int constraint PK_IDContactDetails primary key identity,
	PresentAddress varchar(150),
	PermanentAddress varchar(150),
	TelephoneWork varchar(20),
	TelephoneHome varchar(20),
	Mobile varchar(20),
	Pager varchar(20),
	Fax varchar(20),
	Email varchar(50),
	PatientID int constraint FK_Patient_Contact
		foreign key references Patient(IDPatient)
)
go

create table NextOfKin(
	IDNextOfKin int constraint PK_IDNextOfKin primary key identity,
	FirstNameNOK varchar(50) not null,
	MiddleNameNOK varchar(50),
	LastNameNOK varchar(50) not null,
	PresentAddress varchar(150),
	PermanentAddress varchar(150),
	TelephoneWork varchar(20),
	TelephoneHome varchar(20),
	Mobile varchar(20),
	Pager varchar(20),
	Fax varchar(20),
	Email varchar(50),
	PatientID int constraint FK_Patient_NOK
		foreign key references Patient(IDPatient)
)
go


create table PersonalData(
	IDPersonalData int constraint PK_IDPersonalData primary key identity,
	MartialStatus varchar(50),
	NumOfDependents int,
	Height int,
	[Weight] int,
	BloodType varchar(20),
	Occupation varchar(100),
	Income int,
	PatientID int constraint FK_Patient_Personal
		foreign key references Patient(IDPatient)
)
go

create table Lifestyle(
	IDLifestyle int constraint PK_IDLifestyle primary key identity,
	Vegetarian Bit,
	Smoker Bit,
	Alcohol Bit,
	Stimulants Bit,
	CoffeeTeaDay int,
	SoftDrinksDay int,
	RegularMeals Bit,
	HomeFood Bit,
	PatientID int constraint FK_Patient_Lifestyle
		foreign key references Patient(IDPatient)
)
go

create table BasicComplaints(
	IDBasicComplaints int constraint PK_IDBasicComplaints primary key identity,
	StatementComplaint varchar(500),
	PreviousTreatment varchar(300),
	PhysicianHospital varchar(100),
	PatientID int constraint FK_Patient_BasicComp
		foreign key references Patient(IDPatient)
)
go

create table MedicalComplaints(
	IDMedicalComplaints int constraint PK_IDMedicalComplaints primary key identity,
	Diabetic Bit,
	Hypertensive Bit,
	Cardiac Bit,
	Respiratory Bit,
	Digestive Bit,
	Orthopedic Bit,
	Muscular Bit,
	Neurological Bit,
	Allergies varchar(300),
	AdverseReaction varchar(300),
	Surgeries varchar(300),
	PatientID int constraint FK_Patient_MedComp
		foreign key references Patient(IDPatient)
)
go


--
-- CRUD operations


--
-- Reading

create procedure GetPatientsIndexes
as
begin
	select IDPatient from Patient
end
go

create procedure GetPatient
	@idPatient int
as
begin
	select * from Patient
	where IDPatient = @idPatient 
end
go


create procedure GetDoctor
	@idDoctor int
as
begin
	select * from Doctor
	where IDDoctor = @idDoctor
end
go


create procedure GetRecords
	@idPatient int
as
begin
	select * from Record where PatientID = @idPatient
end
go


create procedure GetPatientsByDoctor
	@idDoctor int
as
begin
	select p.IDPatient from Patient as p 
		inner join Record as r on p.IDPatient = r.PatientID
		inner join Doctor as d on d.IDDoctor = r.DoctorID
	where d.IDDoctor = @idDoctor
end
go


create procedure GetAllDoctors
as
begin
	select * from Doctor
end
go


create procedure GetPatientBasicInformation
	@idPatient int
as
begin
	select * from BasicInformation where PatientID = @idPatient
end
go


create procedure GetPatientContact
	@idPatient int
as
begin
	select * from ContactDetails where PatientID = @idPatient
end
go


create procedure GetPatientLifestyle
	@idPatient int
as
begin
	select * from Lifestyle where PatientID = @idPatient
end
go


create procedure GetPatientNOK
	@idPatient int
as
begin
	select * from NextOfKin where PatientID = @idPatient
end
go


create procedure GetPatientPersonalData
	@idPatient int
as
begin
	select * from PersonalData where PatientID = @idPatient
end
go


create procedure GetPatientBasicComplaints
	@idPatient int
as
begin
	select * from BasicComplaints where PatientID = @idPatient
end
go


create procedure GetPatientMedicalComplaints
	@idPatient int
as
begin
	select * from MedicalComplaints where PatientID = @idPatient
end
go


create procedure GetAllPatients
as
begin
	select * from Patient
end
go


--
-- Deleting

create procedure DeleteBasicInformation
	@idPatient int
as
begin
	delete from BasicInformation 
	where PatientID = @idPatient
end
go


create procedure DeleteRecord
	@id int
as
begin
	delete from Record where IDRecord = @id
end
go


create procedure DeleteDoctor
	@id int
as
begin
	delete from Doctor where IDDoctor = @id
end
go


create procedure DeleteContactDetails
	@idPatient int
as
begin
	delete from ContactDetails 
	where PatientID = @idPatient
end
go


create procedure DeleteBasicComplaints
	@idPatient int
as
begin
	delete from BasicComplaints 
	where PatientID = @idPatient
end
go


create procedure DeleteLifestyle
	@idPatient int
as
begin
	delete from Lifestyle 
	where PatientID = @idPatient
end
go


create procedure DeleteMedicalComplaints
	@idPatient int
as
begin
	delete from MedicalComplaints 
	where PatientID = @idPatient
end
go

create procedure DeleteNOK
	@idPatient int
as
begin
	delete from NextOfKin 
	where PatientID = @idPatient
end
go


create procedure DeletePersonalData
	@idPatient int
as
begin
	delete from PersonalData 
	where PatientID = @idPatient
end
go



create procedure DeletePatient
	@idPatient int
as
begin
	exec DeleteBasicInformation @idPatient
	exec DeleteContactDetails @idPatient
	exec DeleteNOK @idPatient
	exec DeleteLifestyle @idPatient
	exec DeletePersonalData @idPatient
	exec DeleteBasicComplaints @idPatient
	exec DeleteMedicalComplaints @idPatient
	delete from Patient where IDPatient = @idPatient
end
go


--
-- Creating

create procedure CreatePatient
	@incomingDate varchar(20),
	@idPatient int output
as
begin
	insert into Patient values(@incomingDate)
	set @idPatient = SCOPE_IDENTITY()
end
go


create procedure CreateDoctor
	@fName varchar(50),
	@mName varchar(50),
	@lName varchar(50),
	@field varchar(100),
	@sex varchar(1),
	@bDate varchar(20)
as
begin
	insert into Doctor values(@fName, @mName, @lName, @field, @sex, @bDate)
end
go
	


create procedure CreateRecord
	@diagnosis varchar(100),
	@diagnosisDescription varchar(500),
	@billAmount int,
	@appointments varchar(500),
	@tests varchar(500),
	@perscription varchar(500),
	@doctorID int,
	@patientID int
as
begin
	insert into Record values(@diagnosis, @diagnosisDescription, @billAmount, @doctorID, @patientID, @appointments,
	@tests, @perscription)
end
go


create procedure CreateBasicInformation
	@idPatient int,
	@firstName varchar(50),
	@middleName varchar(50),
	@lastName varchar(50),
	@sex varchar(1),
	@birthDate varchar(20),
	@isComplete Bit
as
begin
	insert into BasicInformation values(@firstName, @middleName, @lastName, @sex, @birthDate, @isComplete, @idPatient)
end
go


create procedure CreateContact
	@patientID int,
	@presentAddress varchar(150),
	@permanentAddress varchar(150),
	@telephoneWork varchar(20),
	@telephoneHome varchar(20),
	@mobile varchar(20),
	@pager varchar(20),
	@fax varchar(20),
	@email varchar(50)
as
begin
	insert into ContactDetails values(@presentAddress, 
		@permanentAddress, @telephoneWork, @telephoneHome, @mobile, @pager, @fax, @email, @patientID)
	
end
go


create procedure InsertPersonalData
	@patientID int,
	@martialStatus varchar(50),
	@numOfDependents int,
	@weight int,
	@height int,
	@bloodType varchar(20),
	@occupation varchar(100),
	@income int
as
begin
	insert into PersonalData values(@martialStatus, 
		@numOfDependents, @weight, @height, @bloodType, @occupation, @income, @patientID)
	
end
go


create procedure InsertLifestyle
	@patientID int,
	@vegetarian Bit,
	@smoker Bit,
	@alcohol Bit,
	@stimulants Bit,
	@coffeeTeaDay int,
	@softDrinksDay int,
	@regularMeals Bit,
	@homeFood Bit
as
begin
	insert into Lifestyle values(@vegetarian, @smoker,
		@alcohol, @stimulants, @coffeeTeaDay, @softDrinksDay, @regularMeals, @homeFood, @patientID)
	
end
go


create procedure InsertBasicComplaints
	@patientID int,
	@statementComplaint varchar(500),
	@previousTreatment varchar(300),
	@physicianHospital varchar(100)
as
begin
	insert into BasicComplaints values(@statementComplaint, @previousTreatment,
		@physicianHospital, @patientID)
	
end
go


create procedure InsertMedComplaints
	@patientID int,
	@diabetic Bit,
	@hypertensive Bit,
	@cardiac Bit,
	@respiratory Bit,
	@digestive int,
	@orthopedic int,
	@muscular Bit,
	@neurological Bit,
	@allergies varchar(300),
	@adverseReaction varchar(300),
	@surgeries varchar(300)
as
begin
	insert into MedicalComplaints values(@diabetic, @hypertensive, @cardiac, @respiratory,
		@digestive, @orthopedic, @muscular, @neurological, @allergies, @adverseReaction,
		@surgeries, @patientID)
	
end
go


create procedure CreateNextOfKin
	@firstName varchar(50),
	@middleName varchar(50),
	@lastName varchar(50),
	@presentAddress varchar(150),
	@permanentAddress varchar(150),
	@telephoneWork varchar(20),
	@telephoneHome varchar(20),
	@mobile varchar(20),
	@pager varchar(20),
	@fax varchar(20),
	@email varchar(50),
	@idPatient int
as
begin
	insert into NextOfKin values(@firstName, @middleName, @lastName, @presentAddress, 
		@permanentAddress, @telephoneWork, @telephoneHome, @mobile, @pager, @fax, @email, @idPatient)
end
go


--
-- Updates

create procedure UpdatePatient
	@idPatient int,
	@incomingDate varchar(20)
as
begin
	update Patient set IncomingDate = @incomingDate
	where IDPatient = @idPatient
end
go


create procedure UpdateDoctor
	@id int,
	@fName varchar(50),
	@mName varchar(50),
	@lName varchar(50),
	@field varchar(100),
	@sex varchar(1),
	@bDate varchar(20)
as
begin
	update Doctor set FirstName = @fName,
			MiddleName = @mName,
			LastName = @lName,
			Field = @field,
			Sex = @sex,
			BirthDate = @bDate
	where IDDoctor = @id
end
go


create procedure UpdateRecord
	@id int,
	@diagnosis varchar(100),
	@diagnosisDescription varchar(500),
	@billAmount int,
	@appointments varchar(500),
	@tests varchar(500),
	@perscription varchar(500),
	@doctorID int,
	@patientID int
as
begin
	update Record set Diagnosis = @diagnosis,
			DiagnosisDescription = @diagnosisDescription,
			BillAmount = @billAmount,
			Appointments = @appointments,
			Tests = @tests,
			Prescription = @perscription,
			DoctorID = @doctorID,
			PatientID = @patientID
	where IDRecord = @id
end
go


create procedure UpdateBasicInformation
	@idPatient int,
	@firstName varchar(50),
	@middleName varchar(50),
	@lastName varchar(50),
	@sex varchar(1),
	@birthDate varchar(20),
	@isComplete Bit
as
begin
	update BasicInformation set FirstName = @firstName,
			MiddleName = @middleName,
			LastName = @lastName,
			Sex = @sex,
			BirthDate = @birthDate,
			IsComplete = @isComplete
		where PatientID = @idPatient
end
go


create procedure UpdateContact
	@idPatient int,
	@presentAddress varchar(150),
	@permanentAddress varchar(150),
	@telephoneWork varchar(20),
	@telephoneHome varchar(20),
	@mobile varchar(20),
	@pager varchar(20),
	@fax varchar(20),
	@email varchar(50)
as
begin
	update ContactDetails set PresentAddress = @presentAddress,
			PermanentAddress = @permanentAddress,
			TelephoneWork = @telephoneWork,
			TelephoneHome = @telephoneHome,
			Mobile = @mobile,
			Pager = @pager,
			Fax = @fax,
			Email = @email
		where PatientID = @idPatient
end
go


create procedure UpdatePersonalData
	@idPatient int,
	@martialStatus varchar(50),
	@numOfDependents int,
	@weight int,
	@height int,
	@bloodType varchar(20),
	@occupation varchar(100),
	@income int
as
begin
	update PersonalData set MartialStatus = @martialStatus,
			NumOfDependents = @numOfDependents,
			[Weight] = @weight,
			Height = @height,
			BloodType = @bloodType,
			Occupation = @occupation,
			Income = @income,
			PatientID = @idPatient
		where PatientID = @idPatient
end
go


create procedure UpdateLifestyle
	@idPatient int,
	@vegetarian Bit,
	@smoker Bit,
	@alcohol Bit,
	@stimulants Bit,
	@coffeeTeaDay int,
	@softDrinksDay int,
	@regularMeals Bit,
	@homeFood Bit
as
begin
	update Lifestyle set Vegetarian = @vegetarian,
			Smoker = @smoker,
			Alcohol = @alcohol,
			Stimulants = @stimulants,
			CoffeeTeaDay = @coffeeTeaDay,
			SoftDrinksDay = @softDrinksDay,
			RegularMeals = @regularMeals,
			HomeFood = @homeFood
		where PatientID = @idPatient
end
go


create procedure UpdateBasicComplaints
	@idPatient int,
	@statementComplaint varchar(500),
	@previousTreatment varchar(300),
	@physicianHospital varchar(100)
as
begin
	update BasicComplaints set StatementComplaint = @statementComplaint,
			PreviousTreatment = @previousTreatment,
			PhysicianHospital = @physicianHospital
		where PatientID = @idPatient
end
go


create procedure UpdateMedComplaints
	@idPatient int,
	@diabetic Bit,
	@hypertensive Bit,
	@cardiac Bit,
	@respiratory Bit,
	@digestive int,
	@orthopedic int,
	@muscular Bit,
	@neurological Bit,
	@allergies varchar(300),
	@adverseReaction varchar(300),
	@surgeries varchar(300)
as
begin
	update MedicalComplaints set Diabetic = @diabetic,
			Hypertensive = @hypertensive,
			Cardiac = @cardiac,
			Respiratory = @respiratory,
			Digestive = @digestive,
			Orthopedic = @orthopedic,
			Muscular = @muscular,
			Neurological = @neurological,
			Allergies = @allergies,
			AdverseReaction = @adverseReaction,
			Surgeries = @surgeries
		where PatientID = @idPatient
end
go


alter procedure UpdateNextOfKin
	@patientID int,
	@firstName varchar(50),
	@middleName varchar(50),
	@lastName varchar(50),
	@presentAddress varchar(150),
	@permanentAddress varchar(150),
	@telephoneWork varchar(20),
	@telephoneHome varchar(20),
	@mobile varchar(20),
	@pager varchar(20),
	@fax varchar(20),
	@email varchar(50)
as
begin
	update NextOfKin set FirstNameNOK = @firstName,
			MiddleNameNOK = @middleName,
			LastNameNOK = @lastName,
			PresentAddress = @presentAddress,
			PermanentAddress = @permanentAddress,
			TelephoneWork = @telephoneWork,
			TelephoneHome = @telephoneHome,
			Mobile = @mobile,
			Pager = @pager,
			Fax = @fax,
			Email = @email
		where PatientID = @patientID
end
go





--
-- Filling the DB and testing

use outpatientdb

insert into Doctor values('Bilbo', 'A', 'Baggins', 'Dragons & Elves Dept.', 'M', '2001-12-17')
insert into Doctor values('Banjo', 'B', 'Bobbins', 'Responsible Drug Abuse', 'M', '1984-04-20')
insert into Doctor values('Bilbette', 'C', 'Bayonet', 'Medicine and More', 'F', '1812-12-17')
insert into Doctor values('Blarbo', '', 'Braggins', 'Name Treatment', 'M', '2000-12-17')
insert into Doctor values('Blumbo', '', 'Swaggins', 'Existentialist', 'M', '2000-12-17')
go

insert into Patient values('2001-12-17')
insert into BasicInformation values('Bendertrust', '', 'Testingbatch', 'M', '2001-12-17', 1, 1)
insert into ContactDetails values('Bakers St.', '', '', '', '', '', '', 'smart.guy@weird.name', 1)
insert into NextOfKin values('Bubblebus', 'B', 'Beatingbrat', 'Bakers St. 2', '', '', '', '', '', '', 'smart.guy@weird.name', 1)
insert into PersonalData values('Single', 0, 180, 180, '', 'No, Just Visiting', 228, 1)
insert into Lifestyle values(1, 1, 1, 1, 20, 30, 0, 0, 1)
insert into BasicComplaints values('Cant find the meaning of life', 'Sartre books', 'JP Sartre', 1)
insert into MedicalComplaints values(1, 1, 1, 1, 1, 0, 0, 0, 'Determinism', 'Nihilism', 'Kafka books', 1)
go

insert into Record values('Existential Crisis', 'Patient cant find the meaning of life', 1000, 1, 1, 'Philosopy lectures', 'MBTI', 'Cookies')


select * from Patient
select * from Doctor



delete from Record
delete from Doctor
DBCC CHECKIDENT('Doctor', RESEED, 0)
DBCC CHECKIDENT('Record', RESEED, 0)




delete from BasicInformation
delete from ContactDetails
delete from NextOfKin
delete from PersonalData
delete from Lifestyle
delete from BasicComplaints
delete from MedicalComplaints
delete from Patient
DBCC CHECKIDENT('Patient', RESEED, 0)

