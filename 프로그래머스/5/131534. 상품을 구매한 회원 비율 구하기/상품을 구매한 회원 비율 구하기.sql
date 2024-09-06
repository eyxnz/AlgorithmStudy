# 2021년에 가입한 회원 정보
with SELECTED_USER as (
    select *
    from USER_INFO
    where year(JOINED) = 2021
)

select year(b.SALES_DATE) YEAR, month(b.SALES_DATE) MONTH, count(distinct a.USER_ID) PURCHASED_USERS, round(count(distinct a.USER_ID) / (select count(*) from SELECTED_USER), 1) PUCHASED_RATIO
from SELECTED_USER a
join ONLINE_SALE b on a.USER_ID = b.USER_ID
group by year(b.SALES_DATE), month(b.SALES_DATE)
order by YEAR, MONTH