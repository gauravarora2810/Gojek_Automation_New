select * from client  where alias ilike '%vesta%'



CREATE SEQUENCE governance_body_sequence_1095
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1001
  CACHE 1;
 
CREATE SEQUENCE governance_body_child_sequence_1095
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1001
  CACHE 1;


select create_child  ,* from governance_body where id in(1022,1020,1019) and client_id  =1096    limit 10  