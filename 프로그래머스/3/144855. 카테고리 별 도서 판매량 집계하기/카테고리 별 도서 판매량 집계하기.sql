select a.CATEGORY, sum(b.SALES) as TOTAL_SALES
from BOOK a
join BOOK_SALES b on a.BOOK_ID = b.BOOK_ID and year(b.SALES_DATE) = 2022 and month(b.SALES_DATE) = 1
group by a.CATEGORY
order by a.CATEGORY