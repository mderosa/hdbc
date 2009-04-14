
-thoughts
* we can get the list of pk - fk relationships with the query
select uc.table_name as parent_table, ucc.column_name as parent_field, uc2.table_name as child_table, UCC2.COLUMN_NAME as child_field
from user_tables ut
inner join user_constraints uc on uc.table_name = ut.table_name
inner join user_cons_columns ucc on ucc.constraint_name = uc.constraint_name and ucc.table_name = uc.table_name
left join user_constraints uc2 on UC2.R_CONSTRAINT_NAME = uc.constraint_name
left join user_cons_columns ucc2 on ucc2.constraint_name = uc2.constraint_name and ucc2.table_name = uc2.table_name
where uc.constraint_type = 'P'
and (uc2.constraint_type = 'R' or uc2.constraint_type is null)

I will probably want to redo this as a hierachical query however....
