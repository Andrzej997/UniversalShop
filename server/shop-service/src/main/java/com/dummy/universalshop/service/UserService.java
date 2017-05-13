package com.dummy.universalshop.service;

import com.dummy.universalshop.dto.UserDTO;

/**
 * Created by Mateusz on 11.05.2017.
 */
public interface UserService {
    Boolean usernameExists(String username);

    Boolean registerUser(UserDTO userDTO);
}
