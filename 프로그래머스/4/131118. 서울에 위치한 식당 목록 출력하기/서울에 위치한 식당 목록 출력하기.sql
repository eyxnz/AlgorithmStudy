select a.REST_ID, a.REST_NAME, a.FOOD_TYPE, a.FAVORITES, a.ADDRESS, round(avg(b.REVIEW_SCORE), 2) as SCORE
from REST_INFO a
join REST_REVIEW b on a.REST_ID = b.REST_ID
group by a.REST_ID
having a.ADDRESS like '서울%'
order by SCORE desc, a.FAVORITES desc