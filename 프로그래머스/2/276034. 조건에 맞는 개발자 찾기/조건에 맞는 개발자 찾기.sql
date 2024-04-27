select distinct d.id, d.email, d.first_name, d.last_name
from developers d join skillcodes s on d.skill_code & s.code != 0
where s.name = 'Python' or s.name = 'C#'
order by d.id;