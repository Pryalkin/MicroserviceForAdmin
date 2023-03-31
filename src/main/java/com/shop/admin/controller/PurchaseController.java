package com.shop.admin.controller;

import com.shop.admin.dto.PurchasedProductDTO;
import com.shop.admin.dto.PurchasedSerialProductDTO;
import com.shop.admin.exception.ExceptionHandling;
import com.shop.admin.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Set;

import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/admin/purchase")
@AllArgsConstructor
public class PurchaseController extends ExceptionHandling {

    private final PurchaseService purchaseService;

    @PostMapping("/get/{username}")
    @PreAuthorize("hasAnyAuthority('user:getPurchase')")
    public ResponseEntity<Map<PurchasedProductDTO, Set<PurchasedSerialProductDTO>>> get(@PathVariable String username){
        return new ResponseEntity<>(purchaseService.get(username), OK);
    }
}
