select * from work_flow  where entity_type_id=63 and client_id =1093  

select  ageing, cycle_time, * from change_request  where client_entity_seq_id in  (1123)   and client_id  =1093


select * from app_user where login_id  ilike '%z%'
update app_user set password = '47be5ee932de2e6b048f1a159de1583c7765efe2b077188c1dca2f152f8dab7c' , first_login = 'f' , temp_pwd_creation_time = null where id = 2414;


select countries_mapped,* from contract  where client_entity_seq_id  =1106

SELECT *
FROM information_schema.columns
WHERE table_schema = 'public'
  AND table_name   = 'contract' and column_name ilike '%map%'


select * from relation  where name ilike '%supplier work%'

select excel_name,* from entity_field  where  excel_name ilike '%depe%' and  entity_type_id  =63

select * from entity_type  where entity_type_name  ilike '%work%' 

select excel_name,* from entity_field  where ex and is_dynamic_field is null     and entity_type_id =63 and client_id is null

select * from entity_type  where entity_type_name  ilike '%wor%',id=1
