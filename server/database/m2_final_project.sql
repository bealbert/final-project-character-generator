-- database m2_final_project
BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS character_names, appearances, defining_traits, adventurer_roles, users, characters CASCADE;

-- *************************************************************************************************
-- Create the tables and constraints
-- *************************************************************************************************
CREATE TABLE character_names (
	name_id serial PRIMARY KEY,
	name VARCHAR UNIQUE
);

CREATE TABLE appearances (
	appearance_id serial PRIMARY KEY,
	appearance_description VARCHAR UNIQUE
);

CREATE TABLE defining_traits (
	defining_trait_id serial PRIMARY KEY,
	defining_trait VARCHAR UNIQUE
);

CREATE TABLE adventurer_roles (
	adventurer_role_id serial PRIMARY KEY,
	adventurer_role VARCHAR UNIQUE
);
--users (name is pluralized because 'user' is a SQL keyword)
CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE characters (
	character_id serial PRIMARY KEY,
	name_id INT REFERENCES character_names(name_id),
	appearance_id INT REFERENCES appearances(appearance_id),
	defining_trait_id INT REFERENCES defining_traits(defining_trait_id),
	adventurer_role_id INT REFERENCES adventurer_roles(adventurer_role_id),
	user_id INT REFERENCES users(user_id)
);

-- *************************************************************************************************
-- Insert some sample starting data
-- *************************************************************************************************

INSERT INTO adventurer_roles (adventurer_role) VALUES ('Warrior');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Cleric');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Wizard');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Thief');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Druid');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Bard');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Martial Artist');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Necromancer');
INSERT INTO adventurer_roles (adventurer_role) VALUES ('Witch');

INSERT INTO appearances (appearance_description) VALUES ('red hair and a dour expression.');
INSERT INTO appearances (appearance_description) VALUES ('an enormous scar bisects their face, and they avoid eye contact.');
INSERT INTO appearances (appearance_description) VALUES ('a hulking stature, but a kind smile.');
INSERT INTO appearances (appearance_description) VALUES ('short and stout, like a barrel.');
INSERT INTO appearances (appearance_description) VALUES ('lithe, with long, glossy hair loose down their back.');
INSERT INTO appearances (appearance_description) VALUES ('buttoned up, literally. Wears modest and formal clothing.');
INSERT INTO appearances (appearance_description) VALUES ('wears full plate mail at all times. Their face is obscured by the helmet they wear.');
INSERT INTO appearances (appearance_description) VALUES ('dressed in black, and keeps strange and unusual trinkets at their belt.');


INSERT INTO character_names (name) VALUES ('Lena Brightspear');
INSERT INTO character_names (name) VALUES ('Jaime Fellseeker');
INSERT INTO character_names (name) VALUES ('Bale Houndstooth');
INSERT INTO character_names (name) VALUES ('Gregori Vallkas');
INSERT INTO character_names (name) VALUES ('Heather Thornspur');
INSERT INTO character_names (name) VALUES ('Bright Will');
INSERT INTO character_names (name) VALUES ('Lynd the Righteous');
INSERT INTO character_names (name) VALUES ('Ingrid Welkin');
INSERT INTO character_names (name) VALUES ('Yrsa Ufgood');
INSERT INTO character_names (name) VALUES ('Sybil the Strange');
INSERT INTO character_names (name) VALUES ('Fang Oxblood');

INSERT INTO defining_traits (defining_trait) VALUES ('the holy symbol always around their neck.');
INSERT INTO defining_traits (defining_trait) VALUES ('their habit of humming or whistling at all times.');
INSERT INTO defining_traits (defining_trait) VALUES ('their loyal animal companion always at their side.');
INSERT INTO defining_traits (defining_trait) VALUES ('happy-go-lucky, but can never resist a good gamble.');
INSERT INTO defining_traits (defining_trait) VALUES ('callous and aloof. Has an incredible singing voice, but hates to perform.');


-- Users
-- Password for all users is password
INSERT INTO
    users (username, password_hash, role)
VALUES
    ('user', '$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_USER'),
    ('admin','$2a$10$tmxuYYg1f5T0eXsTPlq/V.DJUKmRHyFbJ.o.liI1T35TFbjs2xiem','ROLE_ADMIN');

INSERT INTO characters (name_id, appearance_id, defining_trait_id, adventurer_role_id, user_id) VALUES (1, 2, 3, 4, 1);
INSERT INTO characters (name_id, appearance_id, defining_trait_id, adventurer_role_id, user_id) VALUES (4, 3, 2, 1, 1);
INSERT INTO characters (name_id, appearance_id, defining_trait_id, adventurer_role_id, user_id) VALUES (5, 4, 4, 2, 2);

COMMIT TRANSACTION;
