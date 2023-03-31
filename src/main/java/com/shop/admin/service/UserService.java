package com.shop.admin.service;

import com.shop.admin.exception.model.NotFoundOrganizationException;
import com.shop.admin.exception.model.UsernameExistException;
import com.shop.admin.model.user.User;

import java.util.List;

public interface UserService {

    User findByUsername(String username) throws UsernameExistException;
    List<User> getAllUsers();

    String activation(String username, String activation);

    void creditMoney(String username, Double summa);
}
