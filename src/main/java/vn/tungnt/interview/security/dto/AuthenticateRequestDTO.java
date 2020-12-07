package vn.tungnt.interview.security.dto;

public class AuthenticateRequestDTO extends SignUpRequestDTO{

    private static final long serialVersionUID = -4794069233162458794L;

    public AuthenticateRequestDTO(final String userName, final String password) {
        super(userName, password);
    }
}
