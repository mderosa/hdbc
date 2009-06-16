VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00009-do_CreateTreatments.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
DROP TABLE hippo.treatments;
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(8);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/