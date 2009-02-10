
WHENEVER SQLERROR EXIT SQL.SQLCODE

VARIABLE    migrate_user    VARCHAR2(64)
BEGIN
	   :migrate_user := 'default';
END;
/

@@ do/1-do_tbl_version
@@ do/2-do_tbl_version_change_log
@@ do/3-do_seq_version_change_log
@@ do/4-do_sp_update_version
@@ do/5-do_sp_log_version_change_begin
@@ do/6-do_sp_log_version_change_finish
@@ do/7-do_comment_dm_vbpa

