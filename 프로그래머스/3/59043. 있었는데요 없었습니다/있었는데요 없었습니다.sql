select a.ANIMAL_ID, a.NAME
from ANIMAL_INS a
join ANIMAL_OUTS b on a.ANIMAL_ID = b.ANIMAL_ID
where timediff(a.DATETIME, b.DATETIME) > 0
order by a.DATETIME