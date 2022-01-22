create table hibernate_sequence (
    next_val bigint
) engine=MyISAM;

CREATE TABLE sum_values (
    name UNIQUE varchar(64),
    value int(20),
    PRIMARY KEY (name)
) engine=MyISAM;

