package com.sparta.delivery.service;

import com.sparta.delivery.dto.OrderMenuRequestDto;
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
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

        List<OrderMenu> orderMenuList = new ArrayList<>();
        List<OrderMenuResponseDto> orderMenuResponseList = new ArrayList<>();
        Long totalPrice = 0L;

        for (OrderMenu ordermenuRequest : orderRequestDto.getFoods()) {
            Long quantity = ordermenuRequest.getQuantity();

            if (quantity < 1 || quantity > 100) {
                throw new IllegalArgumentException("허용 수량이 아닙니다.");
            }

            Food food = foodRepository.findById(ordermenuRequest.getId()).orElseThrow(
                    () -> new NullPointerException("해당 메뉴가 없습니다."));


            OrderMenu orderMenu = new OrderMenu(food, quantity);
            orderMenuRepository.save(orderMenu);

            orderMenuList.add(orderMenu);
            totalPrice += food.getPrice()*quantity;

            OrderMenuResponseDto orderMenuResponseDto = new OrderMenuResponseDto(
                    orderMenu.getFood().getName(),
                    quantity,
                    orderMenu.getFood().getPrice()*quantity);

            orderMenuResponseList.add(orderMenuResponseDto);
        }

        if (totalPrice < restaurant.getMinOrderPrice()) {
            throw new IllegalArgumentException("최소주문금액 미달입니다.");
        }

        Long deliveryFee = restaurant.getDeliveryFee();
        totalPrice += deliveryFee;

        Ordering ordering = new Ordering(restaurant.getName(), orderMenuList, totalPrice, deliveryFee
        );
        orderRepository.save(ordering);

        return new OrderResponseDto(ordering, orderMenuResponseList, deliveryFee, totalPrice);
    }

//    public List<OrderResponseDto> getOrder(@RequestBody ) {
//        Ordering ordering = new Ordering()
//        return orderRepository.findByRestaurantName()
//    }

}
