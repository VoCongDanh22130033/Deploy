package vn.edu.hcmuaf.st.web.service;

import vn.edu.hcmuaf.st.web.dao.AddressDao;
import vn.edu.hcmuaf.st.web.entity.Address;

public class AddressService {

    private final AddressDao addressDao;

    public AddressService() {
        this.addressDao = new AddressDao();
    }

    public int addAddress(Address address) {
        return addressDao.insert(address);
    }

}
