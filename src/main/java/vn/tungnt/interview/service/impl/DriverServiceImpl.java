package vn.tungnt.interview.service.impl;

import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.repository.DriverRepository;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.dto.DriverDTO;
import vn.tungnt.interview.service.dto.VehicleDTO;
import vn.tungnt.interview.service.mapper.DriverMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class DriverServiceImpl extends AbstractService<DriverEntity, DriverDTO>
        implements DriverService {

    private static final double FIXED_TRANSFERRING_COST = 5.5;

    private final DriverRepository repository;

    private final DriverMapper mapper;

    public DriverServiceImpl(final DriverRepository repository, final DriverMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DriverDTO removeVehicle(final long driverId, final long vehicleId) {
        final DriverEntity entity = this.repository.findById(driverId)
                .orElseThrow(() -> new IllegalArgumentException(String.format("Not found driver with id %s", driverId)));
        entity.getVehicles().removeIf(v -> v.getId().equals(vehicleId));
        final DriverEntity updated = this.repository.save(entity);
        return this.mapper.toDTO(updated);
    }

    @Override
    public VehicleDTO transferVehicle(final long ownerId, final long customerId, final long vehicleId) {
        if (ownerId == customerId) {
            throw new IllegalArgumentException(String.format("Owner Id: %s equal Customer Id %s", ownerId, customerId));
        }
        final List<Long> ids = Arrays.asList(ownerId, customerId);
        final List<DriverEntity> driverEntities = this.repository.findAllByIdIsIn(ids);

        return null;
    }

}
