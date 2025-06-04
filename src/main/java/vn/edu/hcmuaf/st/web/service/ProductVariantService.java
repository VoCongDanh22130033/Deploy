package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.ProductVariantDao;
import vn.edu.hcmuaf.st.web.entity.ProductVariant;


import java.util.List;
import java.util.Optional;

public class ProductVariantService {

    private final ProductVariantDao productVariantDao;

    public ProductVariantService() {
        this.productVariantDao = new ProductVariantDao();
    }

    public List<ProductVariant> getProductVariantsByIdProduct(int idProduct) {
        return productVariantDao.getProductVariantsByIdProduct(idProduct);
    }

    public void reduceQuantity(int idVariant, int quantity) {
        productVariantDao.reduceQuantity(idVariant, quantity);
    }

    public int getStockQuantity(int idVariant) {
        return productVariantDao.getStockQuantity(idVariant);
    }


}
