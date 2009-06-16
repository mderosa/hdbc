DROP SEQUENCE hippo.seq_version_change_log;

UPDATE hippo.version SET version = 2;
COMMIT;