package vn.tungnt.interview.service.mapper.auth;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.security.dto.CredentialDTO;
import vn.tungnt.interview.service.mapper.BaseMapper;
import vn.tungnt.interview.service.mapper.auth.impl.PasswordEncodeMapper;

@Mapper(componentModel = "spring", uses = PasswordEncodeMapper.class)
public interface CredentialMapper extends BaseMapper<CredentialEntity, CredentialDTO> {

    @Override
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    CredentialEntity toEntity(final CredentialDTO dto);
}
