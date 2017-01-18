select * from system_emails order by date_created desc   limit 100


select password_updated_time,last_login_time,* from app_user  where login_id  ilike '%ajay%'

update app_user set password_updated_time = '2016-02-07 16:20:47.935+00' where login_id  ilike '%ajay%'dn


select deleted,* from dno  where id = 1046

update dno set deleted=false where id = 1046


update app_user set last_login_time   = '2016-02-10 09:05:59.039+00' where login_id  ilike '%ajay%'

select * from client_password_policy  where client_id =1002

select * from client  where alias  ilike '%tech%' 

update app_user set password = 'd214f3811acb257c750d7aeb1d3ca6d24bdc1eaf05b7fdbcd9ef9a5e7094abcd' , first_login = 'f' , temp_pwd_creation_time = null where id = 1040;


update app_user set password = '31dd215aa4c752fd8e4463d95b92910eddab3c27465f1efc3f681614627bef05' , first_login = 'f' , temp_pwd_creation_time = null where id = 1039;


2630
2632
2633
2631
2626
2628
2629
2627
2625
2623
2624
2622
2620
2618
2619
2621
