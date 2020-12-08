package vn.tungnt.interview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.domain.entity.DriverEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<DriverEntity, Long> {

    Optional<DriverEntity> findByCredential(final CredentialEntity credentialEntity);

    @EntityGraph(value = "graph.user.vehicles", type = EntityGraph.EntityGraphType.LOAD)
    Optional<DriverEntity> findById(long id);

    @EntityGraph(value = "graph.user.vehicles", type = EntityGraph.EntityGraphType.LOAD)
    List<DriverEntity> findAllByIdIsIn(final List<Long> ids);
}
