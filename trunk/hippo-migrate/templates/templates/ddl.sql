
VARIABLE v_pk  NUMBER;

BEGIN
    ${schema}.sp_log_version_change_begin('${script_name}', &1, :v_pk);
END;
/

---- ddl below ------------------------------------------

---------------------------------------------------------

BEGIN
    ${schema}.sp_update_version(${version_no});
    ${schema}.sp_log_version_change_finish(:v_pk, 'success');
EXCEPTION
    WHEN OTHERS THEN
        ${schema}.sp_log_version_change_finish(:v_pk, 'fail');
END;
/