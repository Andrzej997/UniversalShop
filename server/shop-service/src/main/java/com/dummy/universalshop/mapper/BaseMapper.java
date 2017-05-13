package com.dummy.universalshop.mapper;

import com.dummy.universalshop.dto.base.BaseDTO;
import com.dummy.universalshop.model.base.BaseEntity;

import java.util.Collection;

/**
 * Created by Mateusz on 13.05.2017.
 */
public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {

    D convertToTransportObject(E entity);

    E convertToEntity(D transportObject);

    Collection<D> convertToTransportObjectList(Collection<E> entityCollection);

    Collection<E> convertToEntityList(Collection<D> transportObjectList);
}
