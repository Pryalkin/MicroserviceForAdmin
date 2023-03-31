package com.shop.admin.service.impl;

import com.shop.admin.dto.CharacteristicDTO;
import com.shop.admin.dto.ProductDTO;
import com.shop.admin.model.product.Product;
import com.shop.admin.repository.ProductRepo;
import com.shop.admin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    @Override
    public Set<ProductDTO> findUnregisteredProducts() {
        Set<Product> products = productRepo.findByRegistered(FALSE).orElse(new HashSet<>());
        return products.stream().map(product -> {
            ProductDTO productDto = new ProductDTO();
            productDto.setProdId(product.getProdId());
            productDto.setName(product.getName());
            productDto.setDescription(product.getDescription());
            productDto.setOrganization(product.getOrganization().getName());
            productDto.setPrice(product.getPrice());
            productDto.setRegistered(product.getRegistered());
            productDto.setIsActive(product.getIsActive());
            product.getKeywords().forEach(keyword -> productDto.getKeywords().add(keyword.getWord()));
            product.getCharacteristics().forEach( characteristic -> {
                CharacteristicDTO characteristicDto = new CharacteristicDTO();
                characteristicDto.setCharacteristic(characteristic.getCharacteristic());
                characteristicDto.setDescription(characteristic.getDescription());
                productDto.getCharacteristics().add(characteristicDto);
            });
            return productDto;
        }).collect(Collectors.toSet());
    }

    @Override
    public void registration(Map<Boolean, List<String>> products) {
        products.keySet().forEach(key -> {
            if (key){
                products.get(TRUE).forEach(productId -> {
                    Product product = productRepo.findByProdId(productId).get();
                    product.setRegistered(TRUE);
                    productRepo.save(product);
                });
            } else {
                products.get(FALSE).forEach(productId -> {
                    Product product = productRepo.findByProdId(productId).get();
                    productRepo.delete(product);
                });
            }
        });
    }
}
