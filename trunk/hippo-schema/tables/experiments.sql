
CREATE TABLE experiments(
	experiment_cd 		number CONSTRAINT pk_experiments PRIMARY KEY,
	type 				varchar2(16 char),
	title				varchar2(64 char) NOT NULL,
	purpose				varchar2(128 char) NOT NULL,
	method				varchar2(4000 char),
	conclusion			varchar2(4000 char),
	MODIFIED_DT			DATE NOT NULL, 
 	MODIFIED_BY			NUMBER NOT NULL,
 	active			NUMBER(1) NOT NULL
);