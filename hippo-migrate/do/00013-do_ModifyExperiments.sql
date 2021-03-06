VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00013-do_ModifyExperiments.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
ALTER TABLE hippo.experiments add (modified_dt DATE, modified_by NUMBER, concluded CHAR(1));
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(13);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/