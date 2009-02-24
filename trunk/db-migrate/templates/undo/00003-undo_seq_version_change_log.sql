
DROP SEQUENCE ${schema}.seq_version_change_log;

UPDATE ${schema}.version SET version = 2;
COMMIT;