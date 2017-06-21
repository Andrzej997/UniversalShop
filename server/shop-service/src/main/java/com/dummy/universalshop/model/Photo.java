package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "photo", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Photo extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "data")
    private byte[] data;
    @Basic
    @Column(name = "extension")
    private String extension;

    @OneToMany(mappedBy = "photo")
    private List<ProductPhoto> productPhotos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public List<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(List<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Photo photo = (Photo) o;

        if (id != null ? !id.equals(photo.id) : photo.id != null) return false;
        if (name != null ? !name.equals(photo.name) : photo.name != null) return false;
        if (!Arrays.equals(data, photo.data)) return false;
        if (extension != null ? !extension.equals(photo.extension) : photo.extension != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(data);
        result = 31 * result + (extension != null ? extension.hashCode() : 0);
        return result;
    }
}
