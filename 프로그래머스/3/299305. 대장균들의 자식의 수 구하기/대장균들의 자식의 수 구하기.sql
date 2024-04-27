# select p.id, count(*) as child_count
# from ecoli_data p left outer join ecoli_data c on p.id = c.parent_id
# group by p.id;

select p.id, sum(case when c.id is null then 0 else 1 end) as child_count
from ecoli_data p left outer join ecoli_data c on p.id = c.parent_id
group by p.id
order by p.id;