
DROP PROCEDURE ${schema}.sp_log_version_change_finish;

UPDATE ${schema}.version SET version = 5;
COMMIT;