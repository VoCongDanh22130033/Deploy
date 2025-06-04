package vn.edu.hcmuaf.st.web.service;


import vn.edu.hcmuaf.st.web.dao.SizeDao;
import vn.edu.hcmuaf.st.web.entity.Size;

import java.util.List;

public class SizeService {

    private SizeDao sizeDao = new SizeDao();

    public List<Size> getAllSizes() {
        return sizeDao.getAll();
    }
}