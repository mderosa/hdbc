DECLARE
    v_migration_user    VARCHAR(64 CHAR);
    v_script_name       VARCHAR(128 CHAR);
    v_version_no        NUMBER;
    v_log_pk            NUMBER;
BEGIN
    v_migration_user := &1;
    v_version_no := ${version_no};
    v_script_name := '${script_name}';
    hippo.sp_log_version_change_begin(v_script_name, v_migration_user, v_log_pk); -- does commit
    ---- script below ---------------------------------


    ---------------------------------------------------
    hippo.sp_update_version(v_version_no);
    hippo.sp_log_version_change_finish(v_log_pk, 'success');                      -- does commit
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        hippo.sp_log_version_change_finish(v_log_pk, 'fail');                     -- does commit
END;
/