VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00012-do_CreateTableSequences.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
DROP SEQUENCE hippo.seq_users;
DROP SEQUENCE hippo.seq_experiments;
DROP SEQUENCE hippo.seq_treatments;
DROP SEQUENCE hippo.seq_data;
DROP SEQUENCE hippo.seq_variables;
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(11);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/