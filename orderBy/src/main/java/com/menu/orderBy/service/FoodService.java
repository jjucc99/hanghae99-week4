package com.menu.orderBy.service;

import com.menu.orderBy.model.dto.FoodDto;
import com.menu.orderBy.model.Food;
import com.menu.orderBy.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
    public void foodRegister(List<FoodDto> foodDtoList, Long restaurantId){
        //검증로직
        validateFood(foodDtoList, restaurantId);

        for (FoodDto foodDto : foodDtoList) {
            foodRepository.save(new Food(foodDto, restaurantId));
        }
    }

    private void validateFood(List<FoodDto> foodDtoList, Long restaurantId) {
        int copy = foodDtoList.size();

        // 가격에 대한 검증
        foodDtoList.forEach(f -> {
                    if (this.validatedFoodPrice(f)) {
                        throw new IllegalArgumentException("올바른 값을 입력해주세요");}});

        // 제공된 리스트가 중복인지 체크
        if (foodDtoList.stream().map(FoodDto::getName).distinct().count() != copy) {
            throw new IllegalArgumentException("제공된 음식들의 이름들이 중복되어 있습니다");
        }
        // 중복이 되었는지 검증
        foodDtoList.forEach(f->{
            Optional<Food> food = foodRepository.findFoodByNameAndAndRestaurantId(f.getName(), restaurantId);
            if (!food.isEmpty()) {
                throw new IllegalArgumentException("이미 저장되어 있는 메뉴입니다.");
            }
        });
    }

    private Boolean validatedFoodPrice(FoodDto foodDto) {
        if (foodDto.getPrice() < 100 || foodDto.getPrice() > 1_000_000) {
            return true;
        }
        if (foodDto.getPrice()%100 !=0) {
            return true;
        }
        return false;
    }
    

    public List<Food> findByRestaurantId(Long restaurantId) {
        return foodRepository.findFoodByRestaurantId(restaurantId);
    }
}
