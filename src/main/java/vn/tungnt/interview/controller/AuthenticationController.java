package vn.tungnt.interview.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.security.CredentialService;
import vn.tungnt.interview.security.dto.SignUpRequestDTO;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final CredentialService credentialService;

    public AuthenticationController(final CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequestDTO request) {
        LOG.info("Begin to signup process");
        this.credentialService.signUp(request);
    }
}
