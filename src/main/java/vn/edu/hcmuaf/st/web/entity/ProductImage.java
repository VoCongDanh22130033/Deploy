package vn.edu.hcmuaf.st.web.entity;


import java.io.Serializable;

public class ProductImage implements Serializable {

    private int idImage;
    private String imageUrl;
    private int order;

    public ProductImage() {
    }

    public ProductImage(int idImage, String imageUrl, int order) {
        this.idImage = idImage;
        this.imageUrl = imageUrl;
        this.order = order;
    }

    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    //Get & Set
    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "idImage=" + idImage +
                ", imageUrl='" + imageUrl + '\'' +
                ", order=" + order +
                '}';
    }

    public void setProduct(Product product) {
    }
}
