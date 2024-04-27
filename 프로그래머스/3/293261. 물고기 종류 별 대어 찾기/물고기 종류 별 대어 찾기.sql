# 물고기 종류 별로 가장 큰 물고기의 ID, 물고기 이름, 길이
# 결과는 물고기의 ID에 대해 오름차순 정렬

select fish_info.id, fish_name, length
from fish_info join fish_name_info using(fish_type)
where (fish_type, length) in (
    select fish_type, max(length) 
    from fish_info
    group by fish_type
)
order by fish_info.id;