select * from app_user where login_id  ilike '%rup%'

update app_user set password = '371bb2c162284fcc3a60e4539214992903973cb0aedb8a68123404dc2334dc25' , first_login = 'f' , temp_pwd_creation_time = null where id = 2430;


select * from client  where name ilike '%voda%'
select id,* from child_sla  where     client_id  =1093 


select * from entity_type  where name  ilike '%sl%'