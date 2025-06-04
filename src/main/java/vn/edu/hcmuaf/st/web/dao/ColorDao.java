package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Color;

import java.util.List;
import java.util.Optional;

public class ColorDao {
    private final Jdbi jdbi;

    public ColorDao() {
        this.jdbi = JDBIConnect.get();
    }

    public List<Color> getAll() {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM colors")
                        .mapToBean(Color.class)
                        .list()
        );
    }

    public Optional<Color> getById(int idColor) {
        return jdbi.withHandle(handle ->
                handle.createQuery("SELECT * FROM colors WHERE idColor = :idColor")
                        .bind("idColor", idColor)
                        .mapToBean(Color.class)
                        .findOne()
        );
    }

    public boolean add(Color color) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("INSERT INTO colors (color, hexCode) VALUES (:color, :hexCode)")
                        .bind("color", color.getColor())
                        .bind("hexCode", color.getHexcode())
                        .execute() > 0
        );
    }

    public boolean update(Color color) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("UPDATE colors SET color = :color, hexCode = :hexCode WHERE idColor = :idColor")
                        .bind("idColor", color.getIdColor())
                        .bind("color", color.getColor())
                        .bind("hexCode", color.getHexcode())
                        .execute() > 0
        );
    }

    public boolean delete(int idColor) {
        return jdbi.withHandle(handle ->
                handle.createUpdate("DELETE FROM colors WHERE idColor = :idColor")
                        .bind("idColor", idColor)
                        .execute() > 0
        );
    }
}
