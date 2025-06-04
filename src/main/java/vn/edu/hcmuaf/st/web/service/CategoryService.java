package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.CategoryDao;
import vn.edu.hcmuaf.st.web.entity.Category;

import java.util.List;
import java.util.Optional;

public class CategoryService {

    private final CategoryDao categoryDao;

    public CategoryService() {
        this.categoryDao = new CategoryDao();
    }

    public List<Category> getAllCategory() {
        return categoryDao.getAll();
    }

    public Optional<Category> getCategoryById(int idCategory) {
        return categoryDao.getById(idCategory);
    }

    public boolean addCategory(Category category) {
        return categoryDao.add(category);
    }

    public boolean updateCategory(Category category) {
        return categoryDao.update(category);
    }

    public boolean deleteCategory(int id) {
        return categoryDao.delete(id);
    }

}
