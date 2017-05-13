package com.dummy.universalshop.mapper.impl;

import com.dummy.universalshop.dto.UserDTO;
import com.dummy.universalshop.mapper.BaseMapper;
import com.dummy.universalshop.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Mateusz on 13.05.2017.
 */
@Component(value = "userDTOMapper")
public class UserDTOMapper implements BaseMapper<User, UserDTO> {

    private ModelMapper mapper = new ModelMapper();

    @Override
    public UserDTO convertToTransportObject(User entity) {
        if (entity == null) {
            return null;
        }
        return mapper.map(entity, UserDTO.class);
    }

    @Override
    public User convertToEntity(UserDTO transportObject) {
        if (transportObject == null) {
            return null;
        }
        return mapper.map(transportObject, User.class);
    }

    @Override
    public Collection<UserDTO> convertToTransportObjectList(Collection<User> entityCollection) {
        if (entityCollection == null) {
            return null;
        }
        Collection<UserDTO> result = new ArrayList<>();
        entityCollection.forEach(user -> result.add(convertToTransportObject(user)));
        return result;
    }

    @Override
    public Collection<User> convertToEntityList(Collection<UserDTO> transportObjectList) {
        if (transportObjectList == null) {
            return null;
        }
        Collection<User> result = new ArrayList<>();
        transportObjectList.forEach(userDTO -> result.add(convertToEntity(userDTO)));
        return result;
    }
}
