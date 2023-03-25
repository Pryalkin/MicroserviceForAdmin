package com.shop.admin.service.impl;

import com.shop.admin.enumeration.Activity;
import com.shop.admin.exception.model.NotFoundOrganizationException;
import com.shop.admin.exception.model.UsernameExistException;
import com.shop.admin.model.user.UserPrincipal;
import com.shop.admin.model.user.User;
import com.shop.admin.repository.UserRepo;
import com.shop.admin.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.shop.admin.constant.UserImplConstant.*;

@Service
@Qualifier("userDetailsService")
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username));
        UserPrincipal userPrincipal = new UserPrincipal(user);
        log.info(FOUND_USER_BY_USERNAME + username);
        return userPrincipal;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public String activation(String username, String activation) throws NotFoundOrganizationException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME));
        if (activation.toUpperCase().equals(Activity.ACTIVE.name())) {
            user.setActivity(Activity.ACTIVE.name());
            userRepository.save(user);
            return Activity.ACTIVE.name();
        }
        else if (activation.toUpperCase().equals(Activity.FROZEN.name())) {
            user.setActivity(Activity.FROZEN.name());
            userRepository.save(user);
            return Activity.FROZEN.name();
        }
        else if (activation.toUpperCase().equals(Activity.DELETED.name())) {
            user.setActivity(Activity.DELETED.name());
            userRepository.save(user);
            return Activity.DELETED.name();
        }
        return "NOT USED";
    }

    @Override
    public void creditMoney(String username, Double summa) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME));
        user.setBalance(user.getBalance() + summa);
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) throws UsernameExistException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameExistException(USERNAME_ALREADY_EXISTS));
    }



}
