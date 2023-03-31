package com.shop.admin.controller;

import com.shop.admin.constant.HttpAnswer;
import com.shop.admin.dto.DiscountDTO;
import com.shop.admin.model.HttpResponse;
import com.shop.admin.service.DiscountsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

import static com.shop.admin.constant.HttpAnswer.ADD_PRODUCT_TO_DISCOUNT;
import static com.shop.admin.constant.HttpAnswer.DISCOUNT_SUCCESSFULLY_CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin/discounts")
@AllArgsConstructor
@Slf4j
public class DiscountsController {

    private final DiscountsService discountsService;

    @PostMapping("/create")
    public ResponseEntity<HttpResponse> create(@RequestBody DiscountDTO discountDTO){
        discountsService.create(discountDTO);
        return HttpAnswer.response(OK, DISCOUNT_SUCCESSFULLY_CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpResponse> create(@RequestBody Map<String, Set<String>> discountProducts){
        discountsService.add(discountProducts);
        return HttpAnswer.response(OK, ADD_PRODUCT_TO_DISCOUNT);
    }





}
