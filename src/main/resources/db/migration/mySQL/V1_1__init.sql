CREATE TABLE `super_user`
(
  `id`           bigint(20) AUTO_INCREMENT,
  `username`     varchar(255) DEFAULT NULL,
  `password`     varchar(255),
  `email`        varchar(255) DEFAULT NULL,
  `phone_number` bigint(20)   DEFAULT NULL,
  adress         varchar(255),
  geo_code_id    bigint(20),
  PRIMARY KEY (`id`),
  CONSTRAINT building_kingdom_id FOREIGN KEY (`kingdom_id`) REFERENCES `kingdom` (`id`)

);


-- testUser1 password: pass1
-- testUser2 password: pass2
-- testUser3 password: pass3
INSERT INTO super_user(id, username, password, email, phone_number,)
VALUES (1, 'test1', '$2a$10$3A7YK9hDUpHN4plBoCphYOzk426CebJwnaFMk0kN4qEXoWUTiwejC', 'test1@test.test', null, 10, 20),
       (2, 'test2', '$2a$10$y1WkKt52SH8eDm6zvy63v.B0EstAaevqAgfo7plk8v9UuigsMcqxi', 'test2@test.test', null, 20, 10),
       (3, 'test3', '$2a$10$N.4V.83hs.5X2bI0qY0Tme2PYceHtDf2Suzh0QHEcYVZxeS0YhJL6', 'test3@test.test', null, 100, 200);


CREATE TABLE `animal`
(
  `id`                  bigint(20) AUTO_INCREMENT,
  `name`                varchar(255) DEFAULT NULL,
  `birth_date`          datetime(6),
  `type`                varchar(255) DEFAULT NULL,
  `gender`              varchar(255) DEFAULT NULL,
  `from_when_available` datetime(6),
  `photo_path`          varchar(255) DEFAULT NULL,
  `spayed`              bit          DEFAULT 0,
  `vaccinated`          bit          DEFAULT 0,
  `adopted`             bit          DEFAULT 0,
  PRIMARY KEY (`id`)
);

INSERT INTO animal(id, name, birth_date, type, gender, from_when_available, spayed, vaccinated, photo_path)
VALUES (1, 'Pinguee', '2010-02-04 03:00:00', 'penguin', 'female', '2019-02-04 03:00:00', true, true,
        '$HOME/assets/images/penguee.jpg'),
       (2, 'Doggo', '2012-10-04 03:00:00', 'dog', 'male', '2019-05-04 03:00:00', false, true,
        '$HOME/assets/images/doggo.jpg'),
       (3, 'Grumpy', '2015-10-04 03:00:00', 'cat', 'male', '2050-05-04 03:00:00', false, false,
        '$HOME/assets/images/grumpycat.jpg');

CREATE TABLE geo_code
(
  id  bigint auto_increment,
  lng varchar(255) default null,
  lat varchar(255) default null,
  PRIMARY KEY (id)
);



CREATE TABLE private_users_liked_animals
(
  animal_id       BIGINT NOT NULL,
  private_user_id BIGINT NOT NULL,
  PRIMARY KEY (animal_id, private_user_id),
  CONSTRAINT private_users_liked_animals_animal_id FOREIGN KEY (animal_id) REFERENCES animal (id),
  CONSTRAINT private_users_liked_animals_private_user_id FOREIGN KEY (private_user_id) REFERENCES super_user (id)
);
