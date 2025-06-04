package vn.edu.hcmuaf.st.web.entity;

import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private int idUser;
    private int idRole;
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private Boolean active;
    private String birthDate;
    private String image;
    private String authProvider;
    private String socialId;
    private Date createAt;
    private Address address;
    private int loginAttempts;
    private boolean isLocked;
    private LocalDateTime lockedUntil;
    private String role;

    public User(int idUser, int idRole, String username, String password, String fullName, String email, String phoneNumber, Boolean active, String birthDate, String image, String authProvider, String socialId, Date createAt, Address address, int loginAttempts, boolean isLocked, LocalDateTime lockedUntil, String role) {
        this.idUser = idUser;
        this.idRole = idRole;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.birthDate = birthDate;
        this.image = image;
        this.authProvider = authProvider;
        this.socialId = socialId;
        this.createAt = createAt;
        this.address = address;
        this.loginAttempts = loginAttempts;
        this.isLocked = isLocked;
        this.lockedUntil = lockedUntil;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "idUser=" + idUser +
                ", idRole=" + idRole +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", active=" + active +
                ", birthDate='" + birthDate + '\'' +
                ", image='" + image + '\'' +
                ", authProvider='" + authProvider + '\'' +
                ", socialId='" + socialId + '\'' +
                ", createAt=" + createAt +
                ", address=" + address +
                ", loginAttempts=" + loginAttempts +
                ", isLocked=" + isLocked +
                ", lockedUntil=" + lockedUntil +
                ", role='" + role + '\'' +
                '}';
    }

    // Getter/Setter
    public int getLoginAttempts() { return loginAttempts; }
    public void setLoginAttempts(int loginAttempts) { this.loginAttempts = loginAttempts; }

    public boolean isLocked() { return isLocked; }
    public void setLocked(boolean isLocked) { this.isLocked = isLocked; }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User() {}

    public User(String fullName, String password, String username, String email) {
        this.fullName = fullName;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public int getIdUser() {
        return idUser;
    }


    public void setIdUser(int user) {
        this.idUser = user;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthProvider() {
        return authProvider;
    }

    public void setAuthProvider(String authProvider) {
        this.authProvider = authProvider;
    }

    public String getSocialId() {
        return socialId;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getLockedUntil() {
        return lockedUntil;
    }

    public void setLockedUntil(LocalDateTime lockedUntil) {
        this.lockedUntil = lockedUntil;
    }
}
