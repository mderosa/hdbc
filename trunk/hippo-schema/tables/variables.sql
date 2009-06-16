
CREATE TABLE variables (
	variable_cd 	number CONSTRAINT pk_variables PRIMARY KEY,
	experiment_cd 	number,
	variable_name	varchar2(64) NOT NULL,
	variable_desc	varchar2(128),
	CONSTRAINT fk_variables_to_exprmnts FOREIGN KEY (experiment_cd) REFERENCES experiments
);