package com.dummy.universalshop.service.impl;

import com.dummy.universalshop.dto.UserDTO;
import com.dummy.universalshop.mapper.BaseMapper;
import com.dummy.universalshop.model.User;
import com.dummy.universalshop.model.UserAuthority;
import com.dummy.universalshop.repository.AuthorityRepository;
import com.dummy.universalshop.repository.UserAuthorityRepository;
import com.dummy.universalshop.repository.UserRepository;
import com.dummy.universalshop.service.UserService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthorityRepository userAuthorityRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier(value = "userDTOMapper")
    private BaseMapper<User, UserDTO> userMapper;

    @Override
    public Boolean usernameExists(String username) {
        List<User> userList = userRepository.findByUsername(username);
        if (userList == null || userList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Boolean registerUser(UserDTO userDTO) {
        if (userDTO == null) {
            return false;
        }
        if (usernameExists(userDTO.getUsername())) {
            return false;
        }
        User user = userMapper.convertToEntity(userDTO);
        Date expirationTime = new Date();
        expirationTime = DateUtils.addDays(expirationTime, 120);
        user.setExpirationTime(expirationTime);
        user.setAccountLocked(false);
        user.setPasswordExpirationTime(expirationTime);
        user.setUserEnabled(true);
        user = userRepository.save(user);
        return createNewUserAuthority(user);
    }

    private Boolean createNewUserAuthority(User user) {
        if (user == null || user.getUserId() == null) {
            return false;
        }
        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserId(user.getUserId());
        userAuthority.setUser(user);
        userAuthority.setAuthorityId(1L);
        userAuthority.setAuthority(authorityRepository.findOne(1L));
        userAuthority = userAuthorityRepository.save(userAuthority);
        return userAuthority != null;
    }
}
