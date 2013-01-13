-- Creazione delle tabelle --
CREATE TABLE calendar__info (id_calendar INTEGER PRIMARY KEY, id_tag NUMERIC, start NUMERIC, end NUMERIC, title TEXT, description TEXT);
CREATE TABLE calendar__people (id_calendar_people INTEGER PRIMARY KEY, id_calendar NUMERIC, id_people NUMERIC);
CREATE TABLE people__info (id_people INTEGER PRIMARY KEY, first_name TEXT, last_name TEXT, gender TEXT, born TEXT, town TEXT, street TEXT, street_number TEXT);
CREATE TABLE people__phones (id_phone INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, phone TEXT);
CREATE TABLE people__emails (id_email INTEGER PRIMARY KEY, id_people NUMERIC, id_tag NUMERIC, email TEXT);
CREATE TABLE system__tags (id_tag INTEGER PRIMARY KEY, tag TEXT);

-- Creazione delle view -- 
CREATE VIEW people__phones_view AS
SELECT 
	people__phones.id_phone, people__phones.id_people, (people__phones.id_tag) AS id_tag_phone, (system__tags.tag) AS tag_phone, phone
FROM 
	people__phones
LEFT OUTER JOIN system__tags ON people__phones.id_tag = system__tags.id_tag ;


CREATE VIEW people__emails_view AS
SELECT 
	people__emails.id_email, people__emails.id_people, (people__emails.id_tag) AS id_tag_email, (system__tags.tag) AS tag_email, email
FROM 
	people__emails
LEFT OUTER JOIN system__tags ON people__emails.id_tag = system__tags.id_tag ;


-- Creazione indici nelle tabelle --

CREATE  INDEX "index__people__info" ON "people__info" ("first_name" ASC, "last_name" ASC);

	
-- Creazione trigger e comportamento eliminazione --
CREATE TRIGGER delete_people
BEFORE DELETE ON people
FOR EACH ROW BEGIN
	UPDATE calendar SET id_people = NULL WHERE calendar.id_people = OLD.id_people;
END;

CREATE TRIGGER delete_calendar__info
BEFORE DELETE ON calendar__info
FOR EACH ROW BEGIN
	DELETE FROM  calendar__people WHERE calendar__people.id_calendar = OLD.id_calendar;
END;

CREATE TRIGGER delete_system__tags
BEFORE DELETE ON system__tags
FOR EACH ROW BEGIN
	UPDATE calendar__info SET id_tag = NULL WHERE calendar__info.id_tag = OLD.id_tag;
	UPDATE people__phones SET id_tag = NULL WHERE people__phones.id_tag = OLD.id_tag;
	UPDATE people__emails SET id_tag = NULL WHERE people__emails.id_tag = OLD.id_tag;
END;



