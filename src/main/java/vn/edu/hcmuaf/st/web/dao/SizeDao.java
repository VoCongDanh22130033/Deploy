package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Size;


import java.util.List;
import java.util.Optional;

public class SizeDao {

    private final Jdbi jdbi;

    public SizeDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Size> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM sizes")
                        .mapToBean(Size.class)
                        .list()
        );
    }

    public Optional<Size> getById(int idSize) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM sizes WHERE idSize = :idSize")
                        .bind("idSize", idSize)
                        .mapToBean(Size.class)
                        .findOne()
        );
    }

    public boolean add(Size size) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO sizes (size) VALUES (:size)")
                        .bind("size", size.getSize())
                        .execute() > 0
        );
    }

    public boolean update(Size size) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE sizes SET size = :size WHERE idSize = :idSize")
                        .bind("idSize", size.getIdSize())
                        .bind("size", size.getSize())
                        .execute() > 0
        );
    }

    public boolean delete(int idSize) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM sizes WHERE idSize = :idSize")
                        .bind("idSize", idSize)
                        .execute() > 0
        );
    }


    public static void main(String[] args) {
        // Khởi tạo đối tượng SizeDao để gọi phương thức getAll()
        SizeDao sizeDao = new SizeDao();

        // Lấy tất cả các kích thước từ cơ sở dữ liệu
        List<Size> sizes = sizeDao.getAll();

        // In kết quả ra console
        for (Size size : sizes) {
            System.out.println("ID: " + size.getIdSize() + ", Size: " + size.getSize());
        }
    }
}
