package com.shop.admin.service;

import com.shop.admin.dto.PurchasedProductDTO;
import com.shop.admin.dto.PurchasedSerialProductDTO;

import java.util.Map;
import java.util.Set;

public interface PurchaseService {

    Map<PurchasedProductDTO, Set<PurchasedSerialProductDTO>> get(String username);
}
