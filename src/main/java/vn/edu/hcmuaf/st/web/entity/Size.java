package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;

public class Size implements Serializable {

    private int idSize;
    private String size;

    public Size() {}

    public Size(int idSize, String size) {
        this.idSize = idSize;
        this.size = size;
    }

    //Get & Set
    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Size{" +
                "idSize=" + idSize +
                ", size='" + size + '\'' +
                '}';
    }
}
