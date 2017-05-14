package com.dummy.universalshop.model.base;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Mateusz on 11.05.2017.
 */
@SuppressWarnings("EmptyMethod")
@MappedSuperclass
public abstract class BaseEntity implements Serializable, Cloneable {

    private static final long serialVersionUID = 123456789L;

    @PreUpdate
    protected void preUpdate() {

    }

    @PrePersist
    protected void prePersist() {

    }

    @PreRemove
    protected void preRemove() {

    }

    @PostPersist
    protected void postPersist() {

    }

    @PostUpdate
    protected void postUpdate() {

    }

    @PostRemove
    protected void postRemove() {

    }
}
