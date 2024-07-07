select sum(PRICE) as TOTAL_PRICE
from ITEM_INFO
where RARITY = 'LEGEND'
group by RARITY