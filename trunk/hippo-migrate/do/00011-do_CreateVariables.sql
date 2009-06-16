VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00011-do_CreateVariables.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
CREATE TABLE hippo.variables (
	variable_cd 	number CONSTRAINT pk_variables PRIMARY KEY,
	experiment_cd 	number,
	variable_name	varchar2(64) NOT NULL,
	variable_desc	varchar2(128),
	CONSTRAINT fk_variables_to_exprmnts FOREIGN KEY (experiment_cd) REFERENCES hippo.experiments
);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(11);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/