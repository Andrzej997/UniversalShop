package com.dummy.universalshop.service;

import com.dummy.universalshop.model.User;
import com.dummy.universalshop.repository.AuthorityRepository;
import com.dummy.universalshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService, ClientDetailsService, ClientRegistrationService {

    @Autowired
    @Lazy
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        List<User> userList = userRepository.findByUsername(username);
        if (userList != null && !userList.isEmpty()) {
            User user = userList.get(0);
            if (user == null) {
                throw new UsernameNotFoundException("User " + username + " was not found in the database");
            }
            return user;
        }
        return null;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        if (StringUtils.isEmpty(clientId)) {
            return null;
        }
        List<User> userList = userRepository.findByUsername(clientId);
        if (userList != null && !userList.isEmpty()) {
            User user = userList.get(0);
            if (user == null) {
                throw new UsernameNotFoundException("User " + clientId + " was not found in the database");
            }
            return user;
        }
        return null;
    }


    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        User user = createUserFromClientDetails(clientDetails);
        userRepository.save(user);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        User user = createUserFromClientDetails(clientDetails);
        userRepository.save(user);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        User user = userRepository.findOne(Long.parseLong(clientId));
        user.setPassword(secret);
        userRepository.save(user);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        userRepository.delete(Long.parseLong(clientId));
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<User> userList = userRepository.findAll();
        List<ClientDetails> clientDetails = new ArrayList<>();
        clientDetails.addAll(userList);
        return clientDetails;
    }

    private User createUserFromClientDetails(ClientDetails clientDetails) {
        if (clientDetails == null || clientDetails.getClientId() == null) {
            return null;
        }
        User user = userRepository.findOne(Long.parseLong(clientDetails.getClientId()));
        if (user == null) {
            user = new User();
            user.setUsername(clientDetails.getClientId());
            user.setPassword(clientDetails.getClientSecret());
            user.setAuthoritySet(clientDetails.getAuthorities()
                    .stream()
                    .map(grantedAuthority ->
                            authorityRepository.findByAuthority(grantedAuthority.getAuthority()))
                    .collect(Collectors.toSet())
            );
            user.setName(" ");
            user.setSurname(" ");
            user.setBirthYear(new Date());
            user.setAccountLocked(false);
            user.setUserEnabled(true);
        }
        user.setResourceIds(StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()));
        user.setScope(StringUtils.collectionToCommaDelimitedString(clientDetails.getScope()));
        user.setAuthorizedGrantTypes(StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()));
        user.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
        user.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());

        return user;
    }
}
