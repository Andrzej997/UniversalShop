package com.dummy.universalshop.service.impl;

import com.dummy.universalshop.dto.UserDTO;
import com.dummy.universalshop.mapper.BaseMapper;
import com.dummy.universalshop.model.Authority;
import com.dummy.universalshop.model.User;
import com.dummy.universalshop.repository.AuthorityRepository;
import com.dummy.universalshop.repository.UserRepository;
import com.dummy.universalshop.service.UserService;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Mateusz on 11.05.2017.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier(value = "userDTOMapper")
    private BaseMapper<User, UserDTO> userMapper;

    @Override
    @Cacheable("username")
    public Boolean usernameExists(String username) {
        List<User> userList = userRepository.findByUsername(username);
        return userList != null && !userList.isEmpty();
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
        createDefaultUserAuthority(user);
        user = userRepository.save(user);
        return user != null && user.getUserId() != null;
    }

    private void createDefaultUserAuthority(User user) {
        if (user == null) {
            return;
        }
        Authority authority = authorityRepository.findOne(2L);
        if (authority != null) {
            Set<Authority> authoritySet = new HashSet<>();
            authoritySet.add(authority);
            user.setAuthoritySet(authoritySet);
        }
    }

    @CacheEvict(value = "username", allEntries = true)
    public void clearCache() {

    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setAuthorityRepository(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    public void setUserMapper(BaseMapper<User, UserDTO> userMapper) {
        this.userMapper = userMapper;
    }
}
