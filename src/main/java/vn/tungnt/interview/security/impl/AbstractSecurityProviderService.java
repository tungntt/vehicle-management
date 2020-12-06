package vn.tungnt.interview.security.impl;

import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.repository.CredentialRepository;
import vn.tungnt.interview.security.dto.CredentialDTO;
import vn.tungnt.interview.service.mapper.BaseMapper;

class AbstractSecurityProviderService {

    protected final CredentialRepository repository;

    protected final BaseMapper<CredentialEntity, CredentialDTO> mapper;

    public AbstractSecurityProviderService(final CredentialRepository repository,
                                           final BaseMapper<CredentialEntity, CredentialDTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }
}
