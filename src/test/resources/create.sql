drop table if exists powers;
drop table if exists heroes;

create table heroes(
	id uuid PRIMARY KEY,
	name varchar(30),
	private_name varchar(50),
	weakness varchar(50)
);

CREATE TABLE powers(
	id uuid PRIMARY KEY,
	hero_id uuid REFERENCES heroes(id),
	name varchar(30),
	description varchar(50)
);