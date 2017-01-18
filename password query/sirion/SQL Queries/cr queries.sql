
select client_entity_seq_id,work_flow_id  ,* from change_request  where   id in (select user_id from domain_user  where role_group_id = 2395)



select deleted , approval_date ,client_entity_seq_id,work_flow_id  ,* from change_request  where     client_entity_seq_id  in(1001,1002,1003,1008,1009,1006) and client_id=1095

select * from app_user  where id in (select user_id from domain_user  where role_group_id = 2395)


= 1095 and client_entity_seq_id  in (1008,1001)


select * from work_flow  where id  in(819,841)

select alias,* from client  where alias like 'wu%'