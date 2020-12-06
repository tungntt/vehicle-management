package vn.tungnt.interview.security.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserDetailDTO extends CredentialDTO implements UserDetails {

    private static final long serialVersionUID = -7805872777880462990L;

    private static final String COMMA_DELIMITER = ",";

    private final List<GrantedAuthority> authorities;

    public UserDetailDTO(final CredentialDTO credential) {
        this.userName = credential.getUserName();
        this.password = credential.getPassword();
        this.active = credential.isActive();
        this.authorities = Arrays.stream(credential.getRoles().split(COMMA_DELIMITER))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
