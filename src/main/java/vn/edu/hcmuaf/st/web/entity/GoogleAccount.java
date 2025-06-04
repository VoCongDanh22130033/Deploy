package vn.edu.hcmuaf.st.web.entity;

public class GoogleAccount {
    private String id, email, fullName, image;  // Thay picture thành image
    private int idRole;
    private String username;
    private String password;
    private String phoneNumber;
    private boolean verified_email;

    public GoogleAccount(String id, String email, String fullName, String image, boolean verified_email, int idRole, String username,String password) {
        this.id = id;
        this.email = email;
        this.fullName = fullName;
        this.image = image;  // Thay picture thành image
        this.verified_email = verified_email;
        this.idRole = idRole;
        this.username = username;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    // Getter and Setter methods
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "GoogleAccount{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", image='" + image + '\'' +
                ", idRole=" + idRole +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", verified_email=" + verified_email +
                '}';
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;  // Getter cho fullName
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;  // Setter cho fullName
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isVerified_email() {
        return verified_email;
    }

    public void setVerified_email(boolean verified_email) {
        this.verified_email = verified_email;
    }


}
