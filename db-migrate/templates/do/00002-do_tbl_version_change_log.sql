
CREATE TABLE ${schema}.version_change_log (
       version_change_log_id		NUMBER,
       script_name		            VARCHAR2(128 CHAR),
       status                       VARCHAR2(16 CHAR),
       create_user                  VARCHAR2(64 CHAR),
       create_dt				    DATE
);

UPDATE ${schema}.version SET version = 2;
COMMIT;