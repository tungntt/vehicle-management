package vn.tungnt.interview.security;

import vn.tungnt.interview.security.dto.SignUpRequestDTO;

public interface CredentialService {

    void signUp(final SignUpRequestDTO request);
}
