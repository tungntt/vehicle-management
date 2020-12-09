package vn.tungnt.interview.service.impl;

import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.repository.DriverRepository;
import vn.tungnt.interview.repository.VehicleRepository;
import vn.tungnt.interview.service.VehicleService;
import vn.tungnt.interview.service.dto.driver.DriverDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;
import vn.tungnt.interview.service.exception.BusinessException;
import vn.tungnt.interview.service.mapper.VehicleMapper;

import java.util.List;
import java.util.Objects;

@Service
public class VehicleServiceImpl extends AbstractService<VehicleEntity, VehicleDTO> implements VehicleService {

    private final VehicleMapper mapper;

    public VehicleServiceImpl(final VehicleRepository repository, final VehicleMapper mapper) {
        super(repository, mapper);
        this.mapper = mapper;
    }

    @Override
    public VehicleDTO add(final VehicleDTO vehicleDTO) {
        final CredentialEntity currentCredential = this.getCurrentCredential();
        if (Objects.isNull(currentCredential.getDriverInfo())) {
            throw new BusinessException("User have no driver information");
        }
        final DriverEntity driverEntity = currentCredential.getDriverInfo();
        vehicleDTO.setDriver(new DriverDTO());
        vehicleDTO.getDriver().setId(driverEntity.getId());
        return super.add(vehicleDTO);
    }

    @Override
    public List<VehicleDTO> readAll() {
        final List<VehicleEntity> vehicles = this.findAll();
        vehicles.forEach(v -> v.setDriver(null));
        return this.mapper.toDto(vehicles);
    }
}
