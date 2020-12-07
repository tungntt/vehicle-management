package vn.tungnt.interview.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import vn.tungnt.interview.security.CredentialService;
import vn.tungnt.interview.security.dto.AuthenticateRequestDTO;
import vn.tungnt.interview.security.dto.SignUpRequestDTO;
import vn.tungnt.interview.security.jwt.JwtFilter;
import vn.tungnt.interview.security.jwt.TokenProvider;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);

    private final CredentialService credentialService;

    private final TokenProvider tokenProvider;

    private final AuthenticationManager authenticationManager;

    public AuthenticationController(final CredentialService credentialService, final TokenProvider tokenProvider,
                                    final AuthenticationManager authenticationManager) {
        this.credentialService = credentialService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<JwtToken> authenticate(@RequestBody AuthenticateRequestDTO request) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
        );

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.createToken(authentication, false);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);
        return new ResponseEntity<>(new JwtToken(jwt), httpHeaders, HttpStatus.OK);
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody SignUpRequestDTO request) {
        LOG.info("Begin to signup process");
        this.credentialService.signUp(request);
    }

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JwtToken {
        private String idToken;

        JwtToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
