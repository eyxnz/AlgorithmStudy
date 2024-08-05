select a.USER_ID, a.PRODUCT_ID
from ONLINE_SALE a
join ONLINE_SALE b on a.USER_ID = b.USER_ID and a.PRODUCT_ID = b.PRODUCT_ID
group by a.USER_ID, a.PRODUCT_ID
having count(*) > 1
order by a.USER_ID, a.PRODUCT_ID desc