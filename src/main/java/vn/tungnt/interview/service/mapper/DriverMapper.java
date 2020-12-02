package vn.tungnt.interview.service.mapper;

import org.mapstruct.Mapper;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.service.dto.DriverDTO;

@Mapper(componentModel = "spring", uses = {VehicleMapper.class})
public interface DriverMapper extends BaseMapper<DriverEntity, DriverDTO> {

    @Override
    DriverEntity toEntity(final DriverDTO dto);

    @Override
    DriverDTO toDTO(final DriverEntity entity);

    default DriverEntity fromId(final Long id) {
        if (id == null) {
            return null;
        }
        DriverEntity entity = new DriverEntity();
        entity.setId(id);
        return entity;
    }

}
