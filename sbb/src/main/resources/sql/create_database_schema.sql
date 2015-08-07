create database sbbproject;
use sbbproject;

create table Users (
 id int not null auto_increment,
 firstName varchar(50)  not null,
 lastName varchar(50)  not null,
 loginName varchar(20)  not null,
 `password` varchar(255) NULL,
 email varchar(50),
 isLocked bool default 0,
 lastLogin datetime,
 insDate datetime not null,
 updDate datetime not null,
 primary key (id)
);

create table Trains (
id int not null auto_increment,
trainNumber varchar(10) not null, 
placesAmount int not null,
insDate datetime not null,
updDate datetime not null,
primary key (id)
);

create table Stations (
id int not null auto_increment,
stationName varchar(50),
insDate datetime not null,
updDate datetime not null,
primary key (id)
);


create table Passengers (
 id int not null auto_increment,
 firstName varchar(50),
 lastName varchar(50),
 birthDate date,
 insDate datetime not null,
 updDate datetime not null,
 primary key (id)
);

create table Trips (
 id int not null auto_increment,
 tripDate date,
 trainId int,
 insDate datetime not null,
 updDate datetime not null,
 primary key (id),
 constraint `fk_trips_train` FOREIGN KEY (`trainId`) REFERENCES `Trains` (`id`)
);

create table Tickets (
id int not null auto_increment,
tripId int not null,
passengerId int not null,
insDate datetime not null,
updDate datetime not null,
primary key (id),
constraint `fk_ticket_trip` FOREIGN KEY (`tripId`) REFERENCES `Trips` (`id`),
constraint `fk_ticket_passenger` FOREIGN KEY (`passengerId`) REFERENCES `Passengers` (`id`)
);

create table Schedules (
id int not null auto_increment,
trainId int not null,
stationId int not null,
trainTime time null,
insDate datetime not null,
updDate datetime not null,
isTrainStop bool not null DEFAULT 0,
primary key (id),
constraint `fk_schedule_train` FOREIGN KEY (`trainId`) REFERENCES `Trains` (`id`),
constraint `fk_schedule_station` FOREIGN KEY (`stationId`) REFERENCES `Stations` (`id`)
);
