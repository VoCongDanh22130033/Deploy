package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;

public class Category implements Serializable {

    private int idCategory;
    private String categoryType;
    private String name;
    private String description;

    public Category() {}

    public Category(int idCategory, String categoryType, String name, String description) {
        this.idCategory = idCategory;
        this.categoryType = categoryType;
        this.name = name;
        this.description = description;
    }

    //Get & Set
    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Category{" +
                "idCategory=" + idCategory +
                ", categoryType='" + categoryType + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
