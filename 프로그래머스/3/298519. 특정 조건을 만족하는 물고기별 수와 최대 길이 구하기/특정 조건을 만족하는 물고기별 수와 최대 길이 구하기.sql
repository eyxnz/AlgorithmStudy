select count(*) FISH_COUNT, max(LENGTH) MAX_LENGTH, FISH_TYPE
from FISH_INFO
where FISH_TYPE in (
    select FISH_TYPE
    from FISH_INFO
    group by FISH_TYPE
    having avg(case
               when LENGTH is NULL then 10
               else LENGTH
               end) >= 33
)
group by FISH_TYPE
order by FISH_TYPE

# SELECT COUNT(FISH_TYPE) AS FISH_COUNT, MAX(LENGTH) AS MAX_LENGTH,
#        FISH_TYPE
# FROM (SELECT 
#              CASE WHEN LENGTH IS NULL THEN 10
#                   ELSE LENGTH
#                   END AS LENGTH, FISH_TYPE
#         FROM FISH_INFO) AS fish
# GROUP BY 3
# HAVING AVG(LENGTH) >= 33
# ORDER BY 3;