CREATE TABLE boat
(
    id          UUID         NOT NULL,
    name        VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_boat PRIMARY KEY (id)
);


INSERT INTO boat (id, name, description) VALUES ('19ba0988-2173-418f-b88e-d0fad90d0cef', 'Sailors Delight', 'A classic sailboat perfect for weekend getaways.');
INSERT INTO boat (id, name, description) VALUES ('29ba0988-2173-418f-b88e-d0fad90d0cef', 'Ocean Explorer', 'A robust yacht equipped for deep-sea adventures.');
INSERT INTO boat (id, name, description) VALUES ('39ba0988-2173-418f-b88e-d0fad90d0cef', 'Lake Cruiser', 'A comfortable motorboat ideal for lake and river cruises.');
