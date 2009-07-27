VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('00012-do_CreateTableSequences.sql', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------
CREATE SEQUENCE hippo.seq_users START WITH 1;
CREATE SEQUENCE hippo.seq_experiments START WITH 1;
CREATE SEQUENCE hippo.seq_treatments START WITH 1;
CREATE SEQUENCE hippo.seq_data START WITH 1;
CREATE SEQUENCE hippo.seq_variables START WITH 1;
---------------------------------------------------------

BEGIN
    hippo.sp_update_version(12);
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/