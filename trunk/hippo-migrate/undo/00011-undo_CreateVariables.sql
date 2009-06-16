VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00011-do_CreateVariables.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
DROP TABLE hippo.variables;
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(10);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/