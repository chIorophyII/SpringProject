package com.sparta.delivery.service;

import com.sparta.delivery.dto.FoodRequestDto;
import com.sparta.delivery.model.Food;
import com.sparta.delivery.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service // bean으로 등록하기 위해
public class FoodService {

    private final FoodRepository foodRepository;

    public List<Food> registerFood(FoodRequestDto requestDto) {

    }
}
