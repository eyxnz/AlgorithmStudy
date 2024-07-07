select a.AUTHOR_ID, b.AUTHOR_NAME, a.CATEGORY, sum(a.PRICE * c.SALES) as TOTAL_SALES
from BOOK a
join AUTHOR b on a.AUTHOR_ID = b.AUTHOR_ID
join BOOK_SALES c on a.BOOK_ID = c.BOOK_ID
where year(c.SALES_DATE) = 2022 and month(c.SALES_DATE) = 1
group by a.AUTHOR_ID, b.AUTHOR_NAME, a.CATEGORY
order by a.AUTHOR_ID, a.CATEGORY desc