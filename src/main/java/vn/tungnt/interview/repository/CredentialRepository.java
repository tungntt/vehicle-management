package vn.tungnt.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tungnt.interview.domain.entity.CredentialEntity;

import java.util.Optional;

@Repository
public interface CredentialRepository extends JpaRepository<CredentialEntity, Long> {

    Optional<CredentialEntity> findByUserName(final String userName);

}
