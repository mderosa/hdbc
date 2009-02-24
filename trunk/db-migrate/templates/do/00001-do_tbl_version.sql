
CREATE TABLE ${schema}.version (
       version	      NUMBER
);

INSERT INTO ${schema}.version (version) VALUES (1);
COMMIT;
