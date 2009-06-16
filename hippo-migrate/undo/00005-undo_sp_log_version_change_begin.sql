DROP PROCEDURE hippo.sp_log_version_change_begin;

UPDATE hippo.version SET version = 4;
COMMIT;