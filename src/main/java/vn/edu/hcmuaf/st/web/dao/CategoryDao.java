package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Category;


import java.util.List;
import java.util.Optional;

public class CategoryDao {

    private final Jdbi jdbi;

    public CategoryDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Category> getAll() {
        return jdbi.withHandle(handle ->
            handle.createQuery("SELECT * FROM categories")
                    .mapToBean(Category.class)
                    .list()
        );
    }

    public Optional<Category> getById(int idCategory) {
        return JDBIConnect.get().withHandle(handle ->
                handle.createQuery("SELECT * FROM categories WHERE idCategory = :idCategory")
                        .bind("idCategory", idCategory)
                        .mapToBean(Category.class)
                        .findOne()
        );
    }

    public boolean add(Category category) {
        return jdbi.withHandle(handle -> {
            Update update = handle.createUpdate("INSERT INTO categories (categoryType, name, description) VALUES (:categoryType, :name, :description)");
            update.bind("categoryType", category.getCategoryType());
            update.bind("name", category.getName());
            update.bind("description", category.getDescription());
            return update.execute() > 0;
        });
    }

    public boolean update(Category category) {
        return jdbi.withHandle(handle -> {
            Update update = handle.createUpdate("UPDATE categories SET categoryType = :categoryType, name = :name, description = :description WHERE idCategory = :idCategory");
            update.bind("categoryType", category.getCategoryType());
            update.bind("name", category.getName());
            update.bind("description", category.getDescription());
            update.bind("idCategory", category.getIdCategory());
            return update.execute() > 0;
        });
    }

    public boolean delete(int id) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM categories WHERE id = :id")
                        .bind("id", id)
                        .execute() > 0
        );
    }



}
