with rent as (
    select CAR_ID
    from CAR_RENTAL_COMPANY_RENTAL_HISTORY
    where START_DATE <= '2022-10-16' and END_DATE >= '2022-10-16'
)

select distinct 
    CAR_ID,
    case
        when CAR_ID in (select * from rent) then '대여중'
        else '대여 가능'
    end as AVAILABILITY
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
order by CAR_ID desc