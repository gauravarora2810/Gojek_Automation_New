select tier_id, issue_date , planned_completion_date,governance_body_child_id  ,deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, 

* from action_item_mgmt  where        client_id =1093  and cycle_time is null  and   relation_id  in(1093,1096, 1097, 1098)  and 
status_id in(14705, 14706, 14717,14718,16083,1,2,4) and  contract_id in (select id from contract  where client_id=1093   and deleted!='t') 


select * from tier  where id  =1093 and  issue_date >= '2015-09-30'  and  issue_date <'2015-10-01'    

planned_completion_date> '2015-10-31' and  planned_completion_date< '2015-11-01'    submission_date >= '2015-10-01 ' and  submission_date <= '2015-10-06'          20:59:59+0020:59:59+00









select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(14706)
select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(14718)
select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(16083)
select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(1)
select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(3)
select deleted,contract_id,status_id, client_entity_seq_id  ,cycle_time, ageing, * from action_item_mgmt  where   client_id =1093  and cycle_time is null  and relation_id  in(1093,1096, 1097, 1098)  and status_id in(4)
select deleted,* from contract  where client_id=1093 and id=1666 

select deleted,client_id ,create_child,num_of_recurrences,client_entity_seq_id  ,* from governance_body  where     client_id=1093 

select deleted,title,* from governance_body  where  id  =1007 and title ilike '%nmdbdnbnqbqnbnb%'  and   client_id  =1093

update  governance_body  set deleted='t' where title ilike '%nmdbdnbnqbqnbnb%'  and   client_id  =1093
 
select deleted,* from governance_body_child where    id=1327   

update governance_body_child  set deleted  ='t' where governance_body_id =1012 



select * from governance_body_child  

select * from work_flow_status      where  entity_type_id =18 and client_id=1093 
select * from work_flow_status      where  id<100 

select * from client where name ilike '%voda%'    

update app_user set password = '40a1936632f27055c127e6a0f4ceb4bfc5ef572e29229c2de11554cf7d7d153d' , first_login = 'f' , temp_pwd_creation_time = null where id = 2354;

select * from app_user where login_id  ilike '%gaurav.user2%'

select deleted,* from contract  where client_id=1093   and deleted!='t'

select * from relation  where    client_id=1093


select client_entity_seq_id,ageing,cycle_time,* from action_item_mgmt  where  client_id  =1093 and client_entity_seq_id  =1064

select work_flow_task_id  ,   work_flow_manual_task_id  , work_flow_id, client_entity_seq_id,ageing,cycle_time,* from action_item_mgmt  where     client_id  =1093 and client_entity_seq_id  in(1071,1064)
 and cycle_time  is not null  and ageing is not null   and    


select client_entity_seq_id,ageing,cycle_time,* from action_item_mgmt  where     client_id  =1093 and cycle_time  is not null  and ageing is not null   

select excel_name, * from entity_field  where excel_name ilike '%dependent%' and entity_type_id =63

select ageing, * from action_item_mgmt  where id  =1602
