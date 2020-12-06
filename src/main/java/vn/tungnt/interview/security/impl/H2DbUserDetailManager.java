package vn.tungnt.interview.security.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.repository.CredentialRepository;
import vn.tungnt.interview.security.dto.CredentialDTO;
import vn.tungnt.interview.security.dto.UserDetailDTO;
import vn.tungnt.interview.service.mapper.auth.UserDetailMapper;

@Service("securityProviderService")
public class H2DbUserDetailManager extends AbstractSecurityProviderService
        implements UserDetailsService {

    public H2DbUserDetailManager(final CredentialRepository repository, final UserDetailMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        final CredentialEntity entity = this.repository.findByUserName(userName)
                .orElseThrow(() -> new UsernameNotFoundException("Not found: " + userName));
        final CredentialDTO dto = this.mapper.toDTO(entity);
        return new UserDetailDTO(dto);
    }
}
