select category, price, product_name
from food_product
where (category, price) in (
    select category, max(price) 
    from food_product 
    group by category
    having category in ('과자', '국', '김치', '식용유')
)
order by price desc;