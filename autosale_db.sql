DROP TABLE IF EXISTS car;

CREATE TABLE car (
                     id SERIAL PRIMARY KEY NOT NULL,
                     brand VARCHAR NOT NULL,
                     model VARCHAR NOT NULL,
                     year BIGINT NOT NULL,
                     type VARCHAR NOT NULL,
                     price DOUBLE PRECISION NOT NULL,
                     trunk_capacity DOUBLE PRECISION,
                     load_capacity DOUBLE PRECISION,
                     seating_capacity DOUBLE PRECISION
);

INSERT INTO car VALUES (1,'Chevrolet', 'COLODADO', 2010, 'TRUCK',
                        25000.0,null,456.0, null);




