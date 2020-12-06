package vn.tungnt.interview.service.mapper.auth.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import vn.tungnt.interview.service.mapper.auth.EncodedMapping;

@Component
public class PasswordEncodeMapper {

    protected final PasswordEncoder passwordEncoder;

    public PasswordEncodeMapper(final PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @EncodedMapping
    public String encode(String value) {
        return passwordEncoder.encode(value);
    }
}
