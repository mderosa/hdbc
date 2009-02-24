
DROP PROCEDURE ${schema}.sp_update_version;

UPDATE ${schema}.version SET version = 3;
COMMIT;