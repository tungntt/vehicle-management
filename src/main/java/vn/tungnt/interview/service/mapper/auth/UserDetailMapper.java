package vn.tungnt.interview.service.mapper.auth;

import org.mapstruct.Mapper;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.security.dto.CredentialDTO;
import vn.tungnt.interview.service.mapper.BaseMapper;

@Mapper(componentModel = "spring")
public interface UserDetailMapper extends BaseMapper<CredentialEntity, CredentialDTO> {
}
