package vn.tungnt.interview.security.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value = {"active", "roles"})
public class SignUpRequestDTO extends CredentialDTO implements Serializable {

    private static final long serialVersionUID = 1674096543581715805L;

    public SignUpRequestDTO(final String userName, final String password) {
        this.userName = userName;
        this.password = password;
    }



}
