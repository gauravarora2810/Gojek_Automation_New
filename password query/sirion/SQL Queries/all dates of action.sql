select * from issue_mgmt  where  id =1238 entity_id        client_id  =1093 and  


select resolution_date,    planned_completion_date,   approveal_date,   issue_date,   submission_date,   rejection_date  from action_item_mgmt  where      id=1533


select * from app_user  where login_id  ilike '%z%'

update app_user set password = 'e95b3ec334c15f4ef60458dd32cbc445c21ca664c56c5f04e972236e6baf5bdb' , first_login = 'f' , temp_pwd_creation_time = null where id = 2408;