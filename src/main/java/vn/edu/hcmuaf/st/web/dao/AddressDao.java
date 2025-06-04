package vn.edu.hcmuaf.st.web.dao;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Update;
import vn.edu.hcmuaf.st.web.dao.db.JDBIConnect;
import vn.edu.hcmuaf.st.web.entity.Address;

public class AddressDao {

    private Jdbi jdbi;

    public AddressDao() {
        jdbi = JDBIConnect.get();
    }

    public int insert(Address address) {
        String sql = "INSERT INTO address (idUser, address, ward, district, province, isDefault) " +
                "VALUES (:idUser, :address, :ward, :district, :province, :isDefault)";

        return jdbi.withHandle(handle -> {
            Update update = handle.createUpdate(sql)
                    .bind("idUser", address.getUser().getIdUser())
                    .bind("address", address.getAddress())
                    .bind("ward", address.getWard())
                    .bind("district", address.getDistrict())
                    .bind("province", address.getProvince())
                    .bind("isDefault", address.isDefault());

            int idAddress = update.executeAndReturnGeneratedKeys("idAddress")
                    .mapTo(Integer.class)
                    .findOne()
                    .orElseThrow(() -> new RuntimeException("Failed to insert address"));

            return idAddress;
        });
    }

}
