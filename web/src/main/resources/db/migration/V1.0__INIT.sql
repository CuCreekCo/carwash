CREATE TABLE carwash_user (
  id BIGINT GENERATED BY DEFAULT AS IDENTITY,
  email VARCHAR(500) NOT NULL,
  password VARCHAR(500) NOT NULL
);