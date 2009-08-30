VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00008-do_CreateExperiments.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
CREATE TABLE hippo.experiments(
	experiment_cd 		number CONSTRAINT pk_experiments PRIMARY KEY,
	type 				varchar2(16 char),
	title				varchar2(64 char) NOT NULL,
	purpose				varchar2(128 char) NOT NULL,
	method				varchar2(4000 char),
	conclusion			varchar2(4000 char)
);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(8);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/