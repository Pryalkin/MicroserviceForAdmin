package com.shop.admin.controller;

import com.shop.admin.constant.HttpAnswer;
import com.shop.admin.exception.ExceptionHandling;
import com.shop.admin.model.HttpResponse;
import com.shop.admin.model.user.User;
import com.shop.admin.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.shop.admin.constant.HttpAnswer.MONEY_HAS_BEEN_SUCCESSFULLY_DEPOSITED;
import static com.shop.admin.constant.HttpAnswer.USER_ACCOUNT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class UserController extends ExceptionHandling {

    private final UserService userService;

    @GetMapping("/getAllUsers")
    @PreAuthorize("hasAnyAuthority('user:getAllUsers')")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userSet = userService.getAllUsers();
        return new ResponseEntity<>(userSet, OK);
    }

    @PostMapping("/activation/{username}/{activation}")
    @PreAuthorize("hasAnyAuthority('user:update_user')")
    public ResponseEntity<HttpResponse> activation(@PathVariable String username,
                                                   @PathVariable String activation){
        String message = userService.activation(username, activation);
        return HttpAnswer.response(OK, USER_ACCOUNT + message);
    }

    @PostMapping("/creditMoney/{username}")
    @PreAuthorize("hasAnyAuthority('user:update_user')")
    public ResponseEntity<HttpResponse> creditMoney(@PathVariable String username,
                                                   @RequestParam Double summa){
        userService.creditMoney(username, summa);
        return HttpAnswer.response(OK, MONEY_HAS_BEEN_SUCCESSFULLY_DEPOSITED);
    }


}
