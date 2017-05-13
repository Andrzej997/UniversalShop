package com.dummy.universalshop.service.impl;

import com.dummy.universalshop.model.User;
import com.dummy.universalshop.repository.UserRepository;
import com.dummy.universalshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Boolean usernameExists(String username) {
        List<User> userList = userRepository.findByUsername(username);
        if (userList == null || userList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
