
DROP PROCEDURE ${schema}.sp_log_version_change_begin;

UPDATE ${schema}.version SET version = 4;
COMMIT;