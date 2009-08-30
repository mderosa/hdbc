CREATE OR REPLACE PROCEDURE sp_update_version
(
    version_no IN NUMBER
) AS
BEGIN
  UPDATE version SET version = version_no;
END;
/
