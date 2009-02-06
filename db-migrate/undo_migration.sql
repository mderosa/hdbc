
WHENEVER SQLERROR EXIT SQL.SQLCODE

VARIABLE    migrate_user    VARCHAR2(64)
BEGIN
       :migrate_user := 'default';
END;
/

@ ./undo/7-undo_comment_dm_vbpa :migrate_user
@ ./undo/6-undo_sp_log_version_change_finish
@ ./undo/5-undo_sp_log_version_change_begin
@ ./undo/4-undo_sp_update_version
@ ./undo/3-undo_seq_version_change_log
@ ./undo/2-undo_tbl_version_change_log
@ ./undo/1-undo_tbl_version :migrate_user


