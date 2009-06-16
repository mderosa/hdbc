VARIABLE v_pk  NUMBER;

BEGIN
    hippo.sp_log_version_change_begin('${script_name}', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------

---------------------------------------------------------

BEGIN
    hippo.sp_update_version(${version_no});
    hippo.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        hippo.sp_log_version_change_finish(:v_pk, 'fail');
END;
/