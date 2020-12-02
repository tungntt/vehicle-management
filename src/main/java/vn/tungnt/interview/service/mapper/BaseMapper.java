package vn.tungnt.interview.service.mapper;

import vn.tungnt.interview.domain.entity.BaseEntity;
import vn.tungnt.interview.service.dto.BaseDTO;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, D extends BaseDTO> {

    E toEntity(final D dto);

    D toDTO (final E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);

}

