
DROP TABLE ${schema}.version_change_log;

UPDATE ${schema}.version SET version = 1;
COMMIT;
