CREATE TABLE geo_code
(
  id  bigint auto_increment,
  lng double default null,
  lat double default null,
  PRIMARY KEY (id)
);

INSERT INTO geo_code (id, lng, lat)
VALUES (1, 20, 20),
       (2, 30, 30),
       (3, 40, 40);

CREATE TABLE `parent_user`
(
  `id`           bigint(20)   AUTO_INCREMENT,
  `user_type`    varchar(255),
  `name`         varchar(255) DEFAULT NULL,
  `password`     varchar(255),
  `email`        varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `image_url`    varchar(255) DEFAULT NULL,
  `address`      varchar(255),
  PRIMARY KEY (`id`)
);

-- testUser1 password: pass1
-- testUser2 password: pass2
-- testUser3 password: pass3

INSERT INTO parent_user (id, name, password, email, phone_number, image_url, address, user_type)
VALUES (1,
        'test1',
        '$2a$10$3A7YK9hDUpHN4plBoCphYOzk426CebJwnaFMk0kN4qEXoWUTiwejC',
        'test1@test.test',
        '5353',
        'imageUrl',
        'Budapest, Szép u. 2, 1053',
        'PrivateUser'),
       (2,
        'test2',
        '$2a$10$y1WkKt52SH8eDm6zvy63v.B0EstAaevqAgfo7plk8v9UuigsMcqxi',
        'test2@test.test',
        '5555',
        'imageUrl',
        'Gitega, Burundi',
        'PrivateUser'),
       (3,
        'test3',
        '$2a$10$N.4V.83hs.5X2bI0qY0Tme2PYceHtDf2Suzh0QHEcYVZxeS0YhJL6',
        'test3@test.test',
        null,
        'imageUrl',
        'google'
        '1042',
        'PrivateUser');

CREATE TABLE `animal`
(
  `id`                  bigint(20)   AUTO_INCREMENT,
  `owner_id`            bigint(20),
  `adopter_id`          bigint(20),
  `animal_race`         varchar(255),
  `name`                varchar(255) DEFAULT NULL,
  `birth_date`          datetime(6),
  `type`                varchar(255) DEFAULT NULL,
  `gender`              varchar(255) DEFAULT NULL,
  `from_when_available` datetime(6),
  `photo_path`          varchar(255) DEFAULT NULL,
  `spayed`              bit          DEFAULT 0,
  `vaccinated`          bit          DEFAULT 0,
  `under_adoption`             bit          DEFAULT 0,
  CONSTRAINT parent_user_id FOREIGN KEY (`id`) REFERENCES `parent_user` (`id`),
  PRIMARY KEY (`id`)
);

INSERT INTO animal (id,
                    owner_id,
                    adopter_id,
                    name,
                    birth_date,
                    type,
                    gender,
                    from_when_available,
                    spayed,
                    vaccinated,
                    photo_path,
                    animal_race)
VALUES (1,
        1,
        1,
        'Pinguee',
        '2010-02-04 03:00:00',
        'beagle',
        'female',
        '2019-02-04 03:00:00',
        true,
        true,
        'penguin.jpg',
        'Dog'),
       (2,
        1,
        1,
        'Doggo',
        '2012-10-04 03:00:00',
        'labrador',
        'male',
        '2019-05-04 03:00:00',
        false,
        true,
        'doggo.jpg',
        'Dog'),
       (3,
        1,
        1,
        'Grumpy',
        '2015-10-04 03:00:00',
        'persian',
        'male',
        '2050-05-04 03:00:00',
        false,
        false,
        'cat.jpg',
        'Cat');


CREATE TABLE parent_users_liked_animals
  ##A PRIMARY KEY IS ALWAYS NEEDED
(
  `id`                  bigint(20)   AUTO_INCREMENT,
  animal_id      BIGINT NOT NULL,
  parent_user_id BIGINT NOT NULL,
  CONSTRAINT parent_users_liked_animals_animal_id FOREIGN KEY (animal_id) REFERENCES animal (id),
  CONSTRAINT parent_users_liked_animals_parent_user_id FOREIGN KEY (parent_user_id) REFERENCES parent_user (id),
  PRIMARY KEY (id)
);

INSERT INTO parent_users_liked_animals (id, animal_id, parent_user_id)
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 2),
       (5, 3, 1),
       (6, 3, 3);

CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `unseen` bigint(20) DEFAULT NULL,
  `animal_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm8cm7iqknrtsfbeuacclu1f7k` (`animal_id`),
  CONSTRAINT `FKm8cm7iqknrtsfbeuacclu1f7k` FOREIGN KEY (`animal_id`) REFERENCES `animal` (`id`)
);

CREATE TABLE `chat_message` (
                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `message` varchar(255) DEFAULT NULL,
                              `sent_at` datetime(6) DEFAULT NULL,
                              `author_id` bigint(20) DEFAULT NULL,
                              `chat_id` bigint(20) DEFAULT NULL,
                              PRIMARY KEY (`id`),
                              KEY `FK4ylgrvun73ef8n503f8ry6k11` (`author_id`),
                              KEY `FKax7xe8g71nf0wvpoychqkqeid` (`chat_id`),
                              CONSTRAINT `FK4ylgrvun73ef8n503f8ry6k11` FOREIGN KEY (`author_id`) REFERENCES `parent_user` (`id`),
                              CONSTRAINT `FKax7xe8g71nf0wvpoychqkqeid` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`)
);

CREATE TABLE `parent_user_chats` (
                                   `chat_id` bigint(20) NOT NULL,
                                   `parent_user_id` bigint(20) NOT NULL,
                                   PRIMARY KEY (`chat_id`,`parent_user_id`),
                                   KEY `FK5vo5gqy3ey3b532tqt0r4ckt2` (`parent_user_id`),
                                   CONSTRAINT `FK5spokj2kew0a2jng55uqmpylm` FOREIGN KEY (`chat_id`) REFERENCES `chat` (`id`),
                                   CONSTRAINT `FK5vo5gqy3ey3b532tqt0r4ckt2` FOREIGN KEY (`parent_user_id`) REFERENCES `parent_user` (`id`)
);