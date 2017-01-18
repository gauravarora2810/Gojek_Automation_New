select * from mgmt_audit_log  where    client_id = 1094 and entity_type_id  =18 and entity_id in(1514,1513) and work_flow_status_id is not null

select * from work_flow_status  where id in(15695,15694,15693)

select * from entity_type  where entity_type_name  ilike '%action%'

select * from client  where alias ilike '%mc%'


select * from app_user where  login_id  ilike '%sirion_clien%'

"sirion_client_setup_2"


select * from action_item_mgmt  where id in(1528,1527)