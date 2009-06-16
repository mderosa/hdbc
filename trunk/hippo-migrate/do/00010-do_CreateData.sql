VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00010-do_CreateData.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
CREATE TABLE hippo.data (
	data_cd 		number CONSTRAINT pk_data PRIMARY KEY,
	user_cd			number,
	experiment_cd	number,
	response		XMLType NOT NULL,
	CONSTRAINT fk_data_to_users FOREIGN KEY (user_cd) REFERENCES hippo.users
);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(10);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/