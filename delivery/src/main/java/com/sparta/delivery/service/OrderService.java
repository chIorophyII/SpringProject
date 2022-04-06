package com.sparta.delivery.service;

import com.sparta.delivery.dto.OrderMenuResponseDto;
import com.sparta.delivery.dto.OrderRequestDto;
import com.sparta.delivery.dto.OrderResponseDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.model.OrderMenu;
import com.sparta.delivery.model.Ordering;
import com.sparta.delivery.model.Restaurant;
import com.sparta.delivery.repository.FoodRepository;
import com.sparta.delivery.repository.OrderMenuRepository;
import com.sparta.delivery.repository.OrderRepository;
import com.sparta.delivery.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final OrderRepository orderRepository;
    private final OrderMenuRepository orderMenuRepository;

    @Transactional
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId())
                .orElseThrow(
                () -> new NullPointerException("해당 음식점이 존재하지 않습니다."));

        // ordermenu db 저장
        List<OrderMenu> orderMenuList = new ArrayList<>();
        // ordermenu response
        List<OrderMenuResponseDto> orderMenuResponseList = new ArrayList<>();
        String restaurantName = restaurant.getName();
        Long deliveryFee = restaurant.getDeliveryFee();
        Long totalPrice = 0L;

        // 주문에 대한 음식을 반복(foods {id:, quantity:) -> orderMenuList
        for (OrderMenu ordermenuRequest : orderRequestDto.getFoods()) {
            // 각 음식에 대한 수량
            Long quantity = ordermenuRequest.getQuantity();

            Food food = foodRepository.findById(ordermenuRequest.getId()).orElseThrow(
                    () -> new NullPointerException("해당 메뉴가 없습니다."));

            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("허용 수량이 아닙니다.");
            }

            // food(restaurant, name, price), quantity
            OrderMenu orderMenu = new OrderMenu(food, quantity);
            // food(restaurant, name, price), quantity 하나씩 저장
            orderMenuRepository.save(orderMenu);

            // food(restaurant, name, price), quantity 리스트로 만듦
            orderMenuList.add(orderMenu);

            // 총 금액 = 음식 가격 * 수량
            totalPrice += food.getPrice()*quantity;
            // name, quantity, price 생성
            OrderMenuResponseDto orderMenuResponseDto = new OrderMenuResponseDto(
                    orderMenu.getFood().getName(),
                    quantity,
                    orderMenu.getFood().getPrice()*quantity);
            // name, quantity, price 리스트로
            orderMenuResponseList.add(orderMenuResponseDto);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액 미달입니다.");
        }

        totalPrice += deliveryFee;

        Ordering ordering = new Ordering(restaurantName, orderMenuList, deliveryFee, totalPrice);
        orderRepository.save(ordering);

        return new OrderResponseDto(restaurantName, orderMenuResponseList, deliveryFee, totalPrice);
    }

    @Transactional
    public List<OrderResponseDto> getOrder() {
        // 총 주문정보 리스트
        List<OrderResponseDto> orderAllList = new ArrayList<>();
        // 음식정보 리스트
        List<OrderMenuResponseDto> orderFoodList = new ArrayList<>();

        List<Ordering> orderingList = orderRepository.findAll();

        for (Ordering ordering : orderingList) {
            String restaurantName = ordering.getRestaurantName();
            Long deliveryFee = ordering.getDeliveryFee();
            Long totalPrice = ordering.getTotalPrice();
            // 주문정보 중에 음식정보를 빼냄
            List<OrderMenu> orderMenuList = orderMenuRepository.findAll();
   
            // orderMenuList == Foods
            for (OrderMenu orderMenu : orderMenuList) {
                OrderMenuResponseDto orderMenuResponseDto = new OrderMenuResponseDto(
                        orderMenu.getFood().getName(),
                        orderMenu.getQuantity(),
                        orderMenu.getFood().getPrice()*orderMenu.getQuantity());
                orderFoodList.add(orderMenuResponseDto);
            }
            OrderResponseDto orderResponseDto = new OrderResponseDto(
                    restaurantName,
                    orderFoodList,
                    deliveryFee,
                    totalPrice);
            orderAllList.add(orderResponseDto);

        }
        return orderAllList;
    }
}
