
CREATE SEQUENCE ${schema}.seq_version_change_log
    START WITH 1
    INCREMENT BY 1;

UPDATE ${schema}.version SET version = 3;
COMMIT;