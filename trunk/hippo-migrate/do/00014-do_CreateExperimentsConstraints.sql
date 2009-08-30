VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00014-do_CreateExperimentsConstraints.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
ALTER TABLE hippo.experiments MODIFY (modified_dt NOT NULL, modified_by NOT NULL, concluded NOT NULL);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(14);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/