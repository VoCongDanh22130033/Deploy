package vn.edu.hcmuaf.st.web.entity;

public class Role {
    private int idRole;
    private RoleName role;


    public enum RoleName {
        ROLE_ADMIN, ROLE_USER,ROLE_EMPLOYEE
    }

    public Role(int idRole, RoleName role) {
        this.idRole = idRole;
        this.role = role;
    }

    public Role() {
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public RoleName getRole() {
        return role;
    }

    public void setRole(RoleName role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Role{" +
                "idRole=" + idRole +
                ", role=" + role +
                '}';
    }
}
