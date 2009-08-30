VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00015-do_ConcludedToBoolean.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
ALTER TABLE hippo.experiments DROP (concluded);
ALTER TABLE hippo.experiments ADD (concluded CHAR(1) NOT NULL);
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(14);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/