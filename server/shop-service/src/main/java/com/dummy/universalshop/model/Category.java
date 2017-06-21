package com.dummy.universalshop.model;

import com.dummy.universalshop.model.base.BaseEntity;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category", schema = "shop_schema", catalog = "shop")
@org.hibernate.annotations.Cache(region = "EntityCache", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Category extends BaseEntity {
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "supperior_category_id")
    private Long supperiorCategoryId;

    @ManyToOne
    @JoinColumn(name = "supperior_category_id", referencedColumnName = "id", updatable = false, insertable = false)
    private Category superiorCategory;

    @OneToMany(mappedBy = "superiorCategory")
    private List<Category> categoryList;

    @OneToMany(mappedBy = "category")
    private List<Product> productList;

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

    public Long getSupperiorCategoryId() {
        return supperiorCategoryId;
    }

    public void setSupperiorCategoryId(Long supperiorCategoryId) {
        this.supperiorCategoryId = supperiorCategoryId;
    }

    public Category getSuperiorCategory() {
        return superiorCategory;
    }

    public void setSuperiorCategory(Category superiorCategory) {
        this.superiorCategory = superiorCategory;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (id != null ? !id.equals(category.id) : category.id != null) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (supperiorCategoryId != null ? !supperiorCategoryId.equals(category.supperiorCategoryId) : category.supperiorCategoryId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (supperiorCategoryId != null ? supperiorCategoryId.hashCode() : 0);
        return result;
    }
}
