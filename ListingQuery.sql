child_dno_listing = SELECT count(distinct cdno.id)
		FROM
		 
		child_dno cdno

		LEFT JOIN dno dno ON dno.id=cdno.dnoid
		LEFT JOIN contract ctr ON ctr.id = dno.contract_id
		LEFT JOIN relation rl ON ctr.relation_id = rl.id
		left outer join time_zone tz on tz.id = cdno.time_zone_id
		
		WHERE cdno.deleted=false and cdno.client_id = '1011'
		 
		 
		and cdno.dnoid =
		 
		ANY ( ( select array (
			with recursive hierarchy (data_id, entity_type_id, entity_id) as (
				select ed.id as data_id, ed.entity_type_id, ed.entity_id 
					from entity_data ed, data_access da 
					where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id
				union all
				select ed.id as data_id, ed.entity_type_id, ed.entity_id
				from hierarchy hr, entity_data ed, link_entity_data_parent edp
				where hr.data_id = edp.parent_id and ed.id = edp.data_id
				)
			select distinct entity_id from hierarchy where entity_type_id = '12'
		  )	)::integer[] )	
and (select date(change_tz(cdno.due_date,'UTC',tz.time_zone)))  <  
			(select date(to_timestamp(to_char((
			 
    select change_tz(now(),'UTC',ctz.time_zone) from client cl
    inner join time_zone ctz on ctz.id=cl.primary_time_zone_id
    where cl.id='1009'
   
			),'YYYYMM'),'YYYYMM') + interval '3 months'))
			


action_listing_query = select count(*)
    from
    action_item_mgmt aim
    LEFT JOIN time_zone tz on tz.id=aim.time_zone_id
    
    where aim.deleted=false and aim.client_id = '1011'
	 
     
		and aim.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '18'
	 
				 )	)::integer[] )

				 
				 
cr_listing_query = SELECT count(*)
    FROM change_request cr
    LEFT JOIN work_flow_status ets ON cr.status_id = ets.id
    
    WHERE cr.deleted=false and cr.client_id ='1011'
     
     
		and cr.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '63'
	 
				 )	)::integer[] )
				 
invoice_listing_query = SELECT count(*)
		FROM base_invoice bin
		LEFT JOIN relation rl ON bin.relation_id = rl.id
		LEFT JOIN time_zone tz
		on tz.id=bin.time_zone_id
		WHERE bin.deleted=false and bin.client_id
		='1011'
		 
		 
		and bin.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '67'
	 
				 )	)::integer[] )

sl_listing_query = select count(*) from sla sl
		 
		WHERE sl.deleted=false and sl.client_id = '1011'
		 
		 
		and sl.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '14'
	 
				 )	)::integer[] )
				 

wor_listing_query = select count(*) from work_order_request wor
		LEFT JOIN time_zone tz on tz.id=wor.time_zone_id
		WHERE wor.deleted=false and wor.client_id = '1011'
		 
		
		 
		and wor.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '80'
	 
				 )	)::integer[] )

				 
dno_listing_query = select count(*)
		from
		 
		dno dn
		left join relation rl on dn.relation_id=rl.id
		left join contract ctr on dn.contract_id=ctr.id
		
		where dn.deleted=false and dn.client_id = '1009'
		 
		 
		and dn.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1026' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '12'
	 
				 )	)::integer[] )

ci_listing_query = select count(distinct (ci.id)) from
		 
		contract_intpn ci
		LEFT JOIN time_zone tz on tz.id=ci.time_zone_id
		
		WHERE ci.deleted=false and ci.client_id = '1011'
		 
		 
		and ci.id =
		 
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '16'
	 
				 )	)::integer[] )

				and (select date(change_tz(ci.date_requested,'UTC',tz.time_zone)))  >= 
				(select date(date_trunc('month', (
				 
    select change_tz(now(),'UTC',ctz.time_zone) from client cl
    inner join time_zone ctz on ctz.id=cl.primary_time_zone_id
    where cl.id='1019'
   
				)) - INTERVAL '5 months'))

contract_listing_query = select count(*)
    from
    contract c
    LEFT JOIN  time_zone tz on tz.id=c.time_zone_id
   
     where c.deleted=false and c.document_type_id != 76 and c.client_id = '1011'
    and c.id =  
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '61'
	 
				 )	)::integer[] )

supplier_listing_query = select count(*)
    from
    relation r

    where deleted=false
     
    and r.id =
     
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '1'
	 
				 )	)::integer[] )
	 
    and r.client_id = '1011'
     
    and r.id =
     
		ANY ( ( select array (
					 
		with recursive hierarchy (data_id, entity_type_id, entity_id) as (
						select ed.id as data_id, ed.entity_type_id, ed.entity_id 
							from entity_data ed, data_access da 
							where da.user_id = '1019' and (da.read_access = true or da.write_access = true) and ed.id = da.data_id and da.deleted = false
						union all
						select ed.id as data_id, ed.entity_type_id, ed.entity_id
						from hierarchy hr, entity_data ed, link_entity_data_parent edp
						where hr.data_id = edp.parent_id and ed.id = edp.data_id
						)
					select distinct entity_id from hierarchy where entity_type_id = '1'
	 
				 )	)::integer[] )				 