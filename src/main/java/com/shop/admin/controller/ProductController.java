package com.shop.admin.controller;

import com.shop.admin.constant.HttpAnswer;
import com.shop.admin.model.HttpResponse;
import com.shop.admin.model.product.Product;
import com.shop.admin.model.user.Organization;
import com.shop.admin.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.shop.admin.constant.HttpAnswer.REGISTRATION_COMPLETED_SUCCESSFULLY;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/unregistered")
    @PreAuthorize("hasAnyAuthority('user:update_product')")
    public ResponseEntity<Set<Product>> unregistered(){
        Set<Product> products = productService.findUnregisteredProducts();
        return new ResponseEntity<>(products, OK);
    }

    @PostMapping("/registration")
    @PreAuthorize("hasAnyAuthority('user:update_product')")
    public ResponseEntity<HttpResponse> registration(@RequestBody Map<Boolean, List<String>> products){
        productService.registration(products);
        return HttpAnswer.response(OK, REGISTRATION_COMPLETED_SUCCESSFULLY);
    }
}
