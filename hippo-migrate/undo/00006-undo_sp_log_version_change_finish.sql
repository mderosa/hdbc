DROP PROCEDURE hippo.sp_log_version_change_finish;

UPDATE hippo.version SET version = 5;
COMMIT;