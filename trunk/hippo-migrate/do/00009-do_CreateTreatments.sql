VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00009-do_CreateTreatments.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
CREATE TABLE hippo.treatments (
	user_cd 		number,
	experiment_cd 	number,
	treatment		XMLType NOT NULL,
	CONSTRAINT pk_treatments PRIMARY KEY (user_cd, experiment_cd),
	CONSTRAINT fk_trtmnts_to_users FOREIGN KEY (user_cd) REFERENCES hippo.users,
	CONSTRAINT fk_trtmnts_to_exprmnts FOREIGN KEY (experiment_cd) REFERENCES hippo.experiments
);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(9);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/