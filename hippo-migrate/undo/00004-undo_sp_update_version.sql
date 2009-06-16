DROP PROCEDURE hippo.sp_update_version;

UPDATE hippo.version SET version = 3;
COMMIT;