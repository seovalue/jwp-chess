CREATE
DATABASE chess DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

use
chess;

DROP TABLE IF EXISTS `Command`;
DROP TABLE IF EXISTS `History`;

CREATE TABLE `History`
(
    `history_id` int          not null auto_increment,
    `name`       varchar(100) not null,
    `is_end`     boolean      not null default false,
    PRIMARY KEY (`history_id`)
) ENGINE=InnoDB;

CREATE TABLE `Command`
(
    `command_id` int  not null auto_increment,
    `data`       text not null,
    `history_id` int  not null,
    PRIMARY KEY (`command_id`),
    FOREIGN KEY (`history_id`) REFERENCES History (history_id)
) ENGINE=InnoDB;