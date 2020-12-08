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

@Service
public class VehicleServiceImpl extends AbstractService<VehicleEntity, VehicleDTO> implements VehicleService {

    private final DriverRepository driverRepository;

    public VehicleServiceImpl(final VehicleRepository repository, final DriverRepository driverRepository,
                              final VehicleMapper mapper) {
        super(repository, mapper);
        this.driverRepository = driverRepository;
    }

    @Override
    public VehicleDTO add(final VehicleDTO vehicleDTO) {
        final CredentialEntity currentCredential = this.getCurrentCredential();
        final DriverEntity driverEntity = this.driverRepository
                .findByCredential(currentCredential)
                .orElseThrow(() -> new BusinessException(String.format("Not found driver of user %s", currentCredential.getUserName())));
        vehicleDTO.setDriver(new DriverDTO());
        vehicleDTO.getDriver().setId(driverEntity.getId());
        return super.add(vehicleDTO);
    }
}
