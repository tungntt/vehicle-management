package vn.tungnt.interview.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import vn.tungnt.interview.service.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static vn.tungnt.interview.config.Constants.USER_ROLE;

public class CredentialDTO extends BaseDTO {

    private static final long serialVersionUID = -1638485764597160636L;

    @NotNull
    @Size(min = 1, max = 50)
    @JsonProperty(value = "username")
    protected String userName;

    @NotNull
    @Size(min = 1, max = 100)
    protected String password;

    protected boolean active = true;

    protected String roles = USER_ROLE;

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

    @Override
    public String toString() {
        return "CredentialDTO{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", active=" + active +
                ", roles='" + roles + '\'' +
                '}';
    }
}
