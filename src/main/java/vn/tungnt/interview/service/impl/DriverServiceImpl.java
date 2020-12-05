package vn.tungnt.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity.VehicleStatus;
import vn.tungnt.interview.repository.DriverRepository;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.PaymentService;
import vn.tungnt.interview.service.dto.DriverDTO;
import vn.tungnt.interview.service.dto.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;
import vn.tungnt.interview.service.exception.BusinessException;
import vn.tungnt.interview.service.mapper.DriverMapper;

import java.util.Arrays;
import java.util.List;

@Service
public class DriverServiceImpl extends AbstractService<DriverEntity, DriverDTO>
        implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final DriverRepository repository;

    private final DriverMapper mapper;

    private final PaymentService paymentService;

    public DriverServiceImpl(final DriverRepository repository, final DriverMapper mapper,
                             final PaymentService paymentService) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.paymentService = paymentService;
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
    public PaymentDTO requestToTransferVehicle(final TransferringVehicleRequestDTO requestDTO) {
        LOG.info("Begin To Make A Request For Transferring Vehicle");
        final long ownerId = requestDTO.getOwnerId();
        final long customerId = requestDTO.getCustomerId();
        final long vehicleId = requestDTO.getVehicleId();
        if (ownerId == customerId) {
            throw new BusinessException(String.format("Owner Id: %s equal Customer Id %s", ownerId, customerId));
        }
        final List<Long> ids = Arrays.asList(ownerId, customerId);
        final List<DriverEntity> driverEntities = this.repository.findAllByIdIsIn(ids);
        if (driverEntities.size() != 2) {
            throw new BusinessException(String.format("Owner Id: %s or Customer Id %s is not existed", ownerId, customerId));
        }
        final DriverEntity ownerEntity = driverEntities
                .stream().filter(d -> d.getId().equals(ownerId)).findFirst().get();
        final DriverEntity customerEntity = driverEntities
                .stream().filter(d -> d.getId().equals(customerId)).findFirst().get();
        final VehicleEntity transferredVehicle = ownerEntity.getVehicles().stream()
                .filter(v -> v.getId().equals(vehicleId) && v.getStatus().equals(VehicleStatus.ACTIVE))
                .findFirst()
                .orElseThrow(() -> new BusinessException(String.format("Not found vehicle with id %s for transferring", vehicleId)));
        final PaymentDTO bill = this.paymentService.checkout(ownerEntity, customerEntity, transferredVehicle);
        LOG.info("Make Order Successfully!!!");
        transferredVehicle.setStatus(VehicleStatus.TRANSFERRING);
        transferredVehicle.setDriver(customerEntity);
        customerEntity.getVehicles().add(transferredVehicle);
        ownerEntity.getVehicles().removeIf(v -> v.getId().equals(vehicleId));
        this.repository.saveAll(Arrays.asList(ownerEntity, customerEntity));
        LOG.info("Make Request For Transferring Successfully!!!");
        return bill;
    }

}
