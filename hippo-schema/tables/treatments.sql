
CREATE TABLE treatments (
	user_cd 		number,
	experiment_cd 	number,
	treatment		XMLType NOT NULL,
	CONSTRAINT pk_treatments PRIMARY KEY (user_cd, experiment_cd),
	CONSTRAINT fk_trtmnts_to_users FOREIGN KEY (user_cd) REFERENCES users,
	CONSTRAINT fk_trtmnts_to_exprmnts FOREIGN KEY (experiment_cd) REFERENCES experiments
);