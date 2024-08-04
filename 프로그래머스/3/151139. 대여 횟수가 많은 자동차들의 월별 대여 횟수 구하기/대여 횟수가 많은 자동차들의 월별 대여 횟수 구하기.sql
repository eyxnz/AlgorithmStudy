select month(START_DATE) as month, CAR_ID, count(*) as RECORDS
from CAR_RENTAL_COMPANY_RENTAL_HISTORY
where year(START_DATE) = 2022 
    and month(START_DATE) >= 8 
    and month(START_DATE) <= 10 
    and CAR_ID in (
        select CAR_ID
        from CAR_RENTAL_COMPANY_RENTAL_HISTORY
        where year(START_DATE) = 2022 and month(START_DATE) >= 8 and month(START_DATE) <= 10
        group by CAR_ID
        having count(*) >= 5
    )
group by month(START_DATE), CAR_ID
having count(*) > 0
order by month, CAR_ID desc