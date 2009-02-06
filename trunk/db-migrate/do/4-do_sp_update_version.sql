
CREATE OR REPLACE PROCEDURE ipso_atr.sp_update_version
(
    version_no IN NUMBER
) AS
BEGIN
	UPDATE version SET version = version_no;
END;
/