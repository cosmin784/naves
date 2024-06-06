create table IF NOT EXISTS nave (
	id 				int not null AUTO_INCREMENT,
	nombre			varchar(100) not null,
	color			varchar(6),
	tamano			varchar(100),
	primary key (id)
);