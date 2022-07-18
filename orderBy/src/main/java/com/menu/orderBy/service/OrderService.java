package com.menu.orderBy.service;

import com.menu.orderBy.model.Food;
import com.menu.orderBy.model.FoodOrder;
import com.menu.orderBy.model.Order;
import com.menu.orderBy.model.Restaurant;
import com.menu.orderBy.model.dto.FoodOrderDto;
import com.menu.orderBy.model.dto.OrderDto;
import com.menu.orderBy.model.requsetDto.FoodOrderRequestDto;
import com.menu.orderBy.model.requsetDto.OrderRequestDto;
import com.menu.orderBy.repository.FoodOrderRepository;
import com.menu.orderBy.repository.FoodRepository;
import com.menu.orderBy.repository.OrderRepository;
import com.menu.orderBy.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;

    public OrderDto crateOrder(OrderRequestDto orderRequestDto) throws Exception {
        validatedOrder(orderRequestDto);
        return setOrder(orderRequestDto);
    }
    public List<OrderDto> findAllOrder() {
        return createOrderDto();
    }

    private List<OrderDto> createOrderDto() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (Order order : orders) {
            List<FoodOrderDto> foodOrderDtoList = new ArrayList<>();
            for (FoodOrder foodOrder : order.getFoodOrders()) {
                foodOrderDtoList.add(new FoodOrderDto(foodOrder.getName(), foodOrder.getQuantity(), foodOrder.getPrice()));
            }
            orderDtoList.add(new OrderDto(order.getRestaurantName(), foodOrderDtoList, order.getDeliveryFee(), order.getTotalPrice()));
        }
        return orderDtoList;
    }

    private void validatedOrder(OrderRequestDto orderRequestDto) throws Exception {
        int inputPrice = 0;
        Restaurant repositoryById = restaurantRepository.getById(orderRequestDto.getRestaurantId());
        List<FoodOrderRequestDto> foods = orderRequestDto.getFoods();
        // 주문한 음식의 수량 범위를 체크하는 로직
        foods.stream().map(FoodOrderRequestDto::getQuantity).forEach( f->
                {
                    if (f > 100 || f < 1) {throw new IllegalArgumentException("주문한 음식의 수량이 허용 범위를 넘습니다");}
                });
        // 주문한 음식들의 가격이 주문 음식점의 최소 가격을 넘는 지
        for (FoodOrderRequestDto RequestFood : foods) {
            Food food = foodRepository
                    .findFoodsByRestaurantIdAndId(orderRequestDto.getRestaurantId(), RequestFood.getId())
                    .orElseThrow(() -> new Exception("Food가 존재하지 앖습니다."));
            inputPrice += food.getPrice();
        }
        if (repositoryById.getMinOrderPrice() > inputPrice) {
            throw new IllegalArgumentException("주문하신 음식들의 가격이 최소 주문 가격보다 작습니다");
        }
    }


    private OrderDto setOrder(OrderRequestDto orderRequestDto) throws Exception {
        Restaurant repositoryById = restaurantRepository.getById(orderRequestDto.getRestaurantId());
        Order order = new Order(repositoryById.getName(), repositoryById.getDeliveryFee());

        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();

        List<FoodOrderRequestDto> foods = orderRequestDto.getFoods();
        for (FoodOrderRequestDto RequestFood : foods) {
            Food food = foodRepository
                    .findFoodsByRestaurantIdAndId(orderRequestDto.getRestaurantId(), RequestFood.getId())
                    .orElseThrow(() -> new Exception("Food가 존재하지 앖습니다."));
            FoodOrderDto foodOrderDto = new FoodOrderDto(food.getName(), RequestFood.getQuantity(), food.getPrice() * RequestFood.getQuantity());

            foodOrderDtos.add(foodOrderDto);

            FoodOrder foodOrder = new FoodOrder(foodOrderDto);
            foodOrder.setFood(food);
            foodOrder.setOrder(order);
            foodOrderRepository.save(foodOrder);
        }

        order.setTotalPrice();
        return new OrderDto(order.getRestaurantName(), foodOrderDtos, order.getDeliveryFee(), order.getTotalPrice());
    }

}
