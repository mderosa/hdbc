CREATE SEQUENCE hippo.seq_version_change_log
    START WITH 1
    INCREMENT BY 1;

UPDATE hippo.version SET version = 3;
COMMIT;