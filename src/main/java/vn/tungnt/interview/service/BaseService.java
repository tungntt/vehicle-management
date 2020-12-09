package vn.tungnt.interview.service;

import org.springframework.transaction.annotation.Transactional;
import vn.tungnt.interview.domain.entity.BaseEntity;
import vn.tungnt.interview.service.dto.BaseDTO;

import java.util.List;
import java.util.Optional;

public interface BaseService<E extends BaseEntity, D extends BaseDTO> {

    D add(final D d);

    @Transactional(readOnly = true)
    List<D> readAll();

    @Transactional(readOnly = true)
    Optional<D> readById(final long id);

    D edit(final D d);

    D remove(final long id);
}
