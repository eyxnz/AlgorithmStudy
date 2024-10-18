with TOTAL_RESULT as (
    select date_format(sales_date, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, USER_ID, SALES_AMOUNT
    from ONLINE_SALE
    where year(sales_date) = 2022 and month(sales_date) = 3

    union all

    select date_format(sales_date, '%Y-%m-%d') as SALES_DATE, PRODUCT_ID, NULL as USER_ID, SALES_AMOUNT
    from OFFLINE_SALE
    where year(sales_date) = 2022 and month(sales_date) = 3
)

select *
from TOTAL_RESULT
order by SALES_DATE, PRODUCT_ID, USER_ID