
CREATE OR REPLACE PROCEDURE ${schema}.sp_log_version_change_finish
(
    p_pk IN NUMBER,
    p_status IN VARCHAR2
) AS
BEGIN
    UPDATE version_change_log
        SET status = p_status
        WHERE version_change_log_id = p_pk;
    COMMIT;
END;
/

UPDATE ${schema}.version SET version = 6;
COMMIT;