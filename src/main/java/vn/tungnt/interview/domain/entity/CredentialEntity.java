package vn.tungnt.interview.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
public class CredentialEntity extends BaseEntity {

    private static final long serialVersionUID = -6188800250598310187L;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "pass_word")
    private String password;

    @Column
    private boolean active = true;

    @Column
    private String roles;

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
}
