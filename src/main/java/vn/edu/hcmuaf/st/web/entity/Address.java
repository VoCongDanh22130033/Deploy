package vn.edu.hcmuaf.st.web.entity;

import java.io.Serializable;

public class Address implements Serializable
{
    private int idAddress;
    private User user;
    private String address;
    private String ward;
    private String district;
    private String province;
    private boolean isDefault;

    public Address() {
    }

    public Address(boolean isDefault, String province, String district, String ward, String address, User user, int idAddress) {
        this.isDefault = isDefault;
        this.province = province;
        this.district = district;
        this.ward = ward;
        this.address = address;
        this.user = user;
        this.idAddress = idAddress;
    }

    public Address(int idAddress, User user, String address, String ward, String district, String province, boolean isDefault) {
        this.idAddress = idAddress;
        this.user = user;
        this.address = address;
        this.ward = ward;
        this.district = district;
        this.province = province;
        this.isDefault = isDefault;
    }


    public int getIdAddress() {
        return idAddress;
    }

    public void setIdAddress(int idAddress) {
        this.idAddress = idAddress;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User idUser) {
        this.user = idUser;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWard() {
        return ward;
    }

    public void setWard(String ward) {
        this.ward = ward;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    @Override
    public String toString() {
        return "Address{" +
                "idAddress=" + idAddress +
                ", user=" + (user != null ? user.getUsername() : "No user") +  // Lấy thông tin cần thiết từ User thay vì gọi toàn bộ toString()
                ", address='" + address + '\'' +
                ", ward='" + ward + '\'' +
                ", district='" + district + '\'' +
                ", province='" + province + '\'' +
                ", isDefault=" + isDefault +
                '}';
    }

}

