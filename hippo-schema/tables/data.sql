
CREATE TABLE data (
	data_cd 		number CONSTRAINT pk_data PRIMARY KEY,
	user_cd			number,
	experiment_cd	number,
	response		XMLType NOT NULL,
	CONSTRAINT fk_data_to_users FOREIGN KEY (user_cd) REFERENCES users
);