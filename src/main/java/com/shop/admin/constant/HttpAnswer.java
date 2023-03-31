package com.shop.admin.constant;

import com.shop.admin.model.HttpResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class HttpAnswer {
    public static String USER_ACCOUNT = "User account: ";
    public static String REGISTRATION_COMPLETED_SUCCESSFULLY = "registration completed successfully";
    public static String ORGANIZATION = "Organization: ";
    public  static String NOTIFICATION_SENT_SUCCESSFULLY = "Notification sent successfully";
    public static final String MONEY_HAS_BEEN_SUCCESSFULLY_DEPOSITED = "Money has been successfully deposited";
    public static final String DISCOUNT_SUCCESSFULLY_CREATED = "Discount successfully created";
    public static final String ADD_PRODUCT_TO_DISCOUNT = "Products have been successfully added to the discount";

    public static ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String message) {
        HttpResponse body = new HttpResponse(httpStatus.value(), httpStatus, httpStatus.getReasonPhrase().toUpperCase(), message.toUpperCase());
        return new ResponseEntity<>(body, httpStatus);
    }
}
