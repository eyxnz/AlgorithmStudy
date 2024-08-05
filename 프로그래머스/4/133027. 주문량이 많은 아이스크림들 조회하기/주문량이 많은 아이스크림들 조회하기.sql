select a.FLAVOR
from (
    select FLAVOR, TOTAL_ORDER
    from FIRST_HALF 

    union all

    select FLAVOR, TOTAL_ORDER
    from JULY
) as a
group by a.FLAVOR
order by sum(a.TOTAL_ORDER) desc
limit 3