select a.USER_ID, a.NICKNAME, sum(b.PRICE) TOTAL_SALES
from USED_GOODS_USER a
join USED_GOODS_BOARD b on a.USER_ID = b.WRITER_ID
where b.STATUS = "DONE"
group by a.USER_ID
having sum(b.PRICE) >= 700000
order by TOTAL_SALES