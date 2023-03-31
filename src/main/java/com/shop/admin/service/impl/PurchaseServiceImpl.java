package com.shop.admin.service.impl;

import com.shop.admin.dto.CharacteristicDTO;
import com.shop.admin.dto.PurchasedProductDTO;
import com.shop.admin.dto.PurchasedSerialProductDTO;
import com.shop.admin.model.product.Product;
import com.shop.admin.repository.PurchaseRepo;
import com.shop.admin.service.PurchaseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepo purchaseRepo;


    @Override
    public Map<PurchasedProductDTO, Set<PurchasedSerialProductDTO>> get(String username) {
        return purchaseRepo.findByUserUsername(username).orElse(new HashSet<>())
                .stream().flatMap(purchase ->
                        purchase.getSerials()
                                .stream().map(serial -> {
                                    PurchasedSerialProductDTO purchasedSerialProductDTO = new PurchasedSerialProductDTO();
                                    purchasedSerialProductDTO.setDateOfPurchase(serial.getPurchase().getDateOfPurchase());
                                    purchasedSerialProductDTO.setProductNumber(serial.getProductNumber());
                                    PurchasedProductDTO purchasedProductDTO = new PurchasedProductDTO();
                                    Product product = serial.getProduct();
                                    purchasedProductDTO.setProdId(product.getProdId());
                                    purchasedProductDTO.setName(product.getName());
                                    purchasedProductDTO.setDescription(product.getDescription());
                                    purchasedProductDTO.setPrice(product.getPrice());
                                    purchasedProductDTO.setOrganization(product.getOrganization().getName());
                                    purchasedProductDTO.getCharacteristicDtos().addAll(product.getCharacteristics()
                                            .stream().map(characteristic -> {
                                                CharacteristicDTO characteristicDto = new CharacteristicDTO();
                                                characteristicDto.setCharacteristic(characteristic.getCharacteristic());
                                                characteristicDto.setDescription(characteristic.getDescription());
                                                return characteristicDto;
                                            }).collect(Collectors.toSet()));
                                    purchasedSerialProductDTO.setPurchasedProductDTO(purchasedProductDTO);
                                    return purchasedSerialProductDTO;
                                }).collect(Collectors.toSet()).stream()
                ).collect(Collectors.groupingBy(PurchasedSerialProductDTO::getPurchasedProductDTO, Collectors.toSet()));
    }
}
