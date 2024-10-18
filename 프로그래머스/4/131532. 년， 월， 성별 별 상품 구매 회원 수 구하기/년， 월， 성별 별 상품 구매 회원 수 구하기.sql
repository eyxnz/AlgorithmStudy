select year(sales_date) as YEAR, month(sales_date) as MONTH, b.GENDER, count(distinct a.USER_ID) as USERS
from ONLINE_SALE a
join USER_INFO b on a.USER_ID = b.USER_ID
where b.GENDER is not null
group by year(sales_date), month(sales_date), b.GENDER
order by year(sales_date), month(sales_date), b.GENDER