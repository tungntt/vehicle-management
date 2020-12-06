package vn.tungnt.interview.security.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.repository.CredentialRepository;
import vn.tungnt.interview.security.CredentialService;
import vn.tungnt.interview.security.dto.SignUpRequestDTO;
import vn.tungnt.interview.service.mapper.auth.CredentialMapper;

@Service("credentialService")
class CredentialServiceImpl extends AbstractSecurityProviderService
        implements CredentialService {

    private static final Logger LOG = LoggerFactory.getLogger(CredentialServiceImpl.class);

    public CredentialServiceImpl(final CredentialRepository repository, final CredentialMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public void signUp(final SignUpRequestDTO request) {
        final CredentialEntity entity = this.mapper.toEntity(request);
        this.repository.save(entity);
        LOG.debug("Register User {} Successfully", request.getUserName());
    }
}
