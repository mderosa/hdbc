CREATE OR REPLACE PROCEDURE hippo.sp_update_version
(
    version_no IN NUMBER
) AS
BEGIN
  UPDATE version SET version = version_no;
END;
/

UPDATE hippo.version SET version = 4;
COMMIT;