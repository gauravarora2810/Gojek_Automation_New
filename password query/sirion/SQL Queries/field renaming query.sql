
select * from system_label limit 100 where id=  4939

select * from client_label  where client_id =1093    and label_id in(select id from system_label)  

select * from entity_field    where entity_type_id =18   and client_id is null

select * from entity_field_validation where message ilike '%SL ID should not contain more t%'limit 10 where id =4939

select * from entity_field_groups where id=2

select * from entity_