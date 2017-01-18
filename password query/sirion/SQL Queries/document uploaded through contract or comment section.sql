select indexed,contract_id,* from contract_document   order by date_created  desc


select indexed,contract_id,* from contract_document  where name  ilike '%Sirion%'


select * from contract_document_file    order by date_created  desc limit 10   where name  ilike '%sql%'

select * from contract_document_file_sequence     where name  ilike '%sql%'

select * from contract_document_sequence     where name  ilike '%sql%'

select * from contract_document   where name  ilike '%sql%'


select * from other_audit_log_document_file       order by date_created desc limit 10