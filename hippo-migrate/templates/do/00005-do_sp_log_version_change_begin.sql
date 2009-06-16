
CREATE OR REPLACE PROCEDURE ${schema}.sp_log_version_change_begin
(
    p_script_name IN VARCHAR2,
    p_create_user IN VARCHAR2,
    p_nextval OUT NUMBER
) AS
BEGIN
    SELECT seq_version_change_log.nextval INTO p_nextval FROM dual;

    INSERT INTO version_change_log
        (version_change_log_id, script_name, status, create_user, create_dt)
        VALUES (p_nextval,
                p_script_name,
                'begin',
                p_create_user,
                sysdate);
    COMMIT;
END;
/

UPDATE ${schema}.version SET version = 5;
COMMIT;