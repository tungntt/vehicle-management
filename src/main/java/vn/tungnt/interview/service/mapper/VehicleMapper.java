package vn.tungnt.interview.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;
import vn.tungnt.interview.domain.entity.VehicleEntity;

@Mapper(componentModel = "spring", uses = {DriverMapper.class})
public interface VehicleMapper extends BaseMapper<VehicleEntity, VehicleDTO> {

    @Mapping(source = "driver.id", target = "driver.id")
    VehicleEntity toEntity(final VehicleDTO dto);

    @Mapping(source = "driver.id", target = "driver.id")
    VehicleDTO toDTO(final VehicleEntity entity);

    default VehicleEntity fromId(final Long id) {
        if (id == null) {
            return null;
        }
        VehicleEntity entity = new VehicleEntity();
        entity.setId(id);
        return entity;
    }
}
