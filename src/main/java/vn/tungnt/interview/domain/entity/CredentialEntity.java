package vn.tungnt.interview.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "credential")
public class CredentialEntity extends BaseEntity {

    private static final long serialVersionUID = -6188800250598310187L;

    @Column(name = "user_name", unique = true)
    private String userName;

    @Column(name = "pass_word")
    private String password;

    @Column
    private boolean active = true;

    @Column
    private String roles;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "driver_id", unique = true)
    private DriverEntity driverInfo;

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(final boolean active) {
        this.active = active;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(final String roles) {
        this.roles = roles;
    }

    public DriverEntity getDriverInfo() {
        return driverInfo;
    }

    public void setDriverInfo(final DriverEntity driverInfo) {
        this.driverInfo = driverInfo;
    }
}
