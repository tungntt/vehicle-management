package vn.tungnt.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.CredentialEntity;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity.VehicleStatus;
import vn.tungnt.interview.repository.CredentialRepository;
import vn.tungnt.interview.repository.DriverRepository;
import vn.tungnt.interview.service.CheckoutService;
import vn.tungnt.interview.service.DriverService;
import vn.tungnt.interview.service.PaymentService;
import vn.tungnt.interview.service.dto.driver.DriverDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.TransferringVehicleRequestDTO;
import vn.tungnt.interview.service.exception.BusinessException;
import vn.tungnt.interview.service.mapper.DriverMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DriverServiceImpl extends AbstractService<DriverEntity, DriverDTO>
        implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DriverServiceImpl.class);

    private final DriverRepository repository;

    private final DriverMapper mapper;

    private final CheckoutService checkoutService;

    public DriverServiceImpl(final DriverRepository repository, final DriverMapper mapper,
                             final CheckoutService checkoutService) {
        super(repository, mapper);
        this.repository = repository;
        this.mapper = mapper;
        this.checkoutService = checkoutService;
    }

    @Override
    public DriverDTO add(final DriverDTO driverDTO) {
        final DriverDTO savedDTO = super.add(driverDTO);
        final CredentialEntity currentCredential = this.getCurrentCredential();
        currentCredential.setDriverInfo(this.mapper.toEntity(savedDTO));
        this.updateCurrentCredential(currentCredential);
        return savedDTO;
    }

    @Override
    public DriverDTO remove(final long id) {
        final CredentialEntity currentCredential = this.getCurrentCredential();
        currentCredential.setDriverInfo(null);
        this.updateCurrentCredential(currentCredential);
        return super.remove(id);
    }

    @Override
    public DriverDTO removeVehicle(final long vehicleId) {
        final CredentialEntity currentCredential = this.getCurrentCredential();
        if (Objects.isNull(currentCredential.getDriverInfo())) {
            throw new BusinessException("User have no driver information");
        }
        final DriverEntity entity = currentCredential.getDriverInfo();
        entity.getVehicles().removeIf(v -> v.getId().equals(vehicleId));
        final DriverEntity updated = this.repository.save(entity);
        return this.mapper.toDTO(updated);
    }

    @Override
    public PaymentDTO requestToTransferVehicle(final TransferringVehicleRequestDTO requestDTO) {
        LOG.info("Begin To Make A Request For Transferring Vehicle");
        final CredentialEntity currentCredential = this.getCurrentCredential();
        if (Objects.isNull(currentCredential.getDriverInfo())) {
            throw new BusinessException("User have no driver information");
        }
        final DriverEntity ownerEntity = currentCredential.getDriverInfo();
        final long customerId = requestDTO.getCustomerId();
        if (ownerEntity.getId() == customerId) {
            throw new BusinessException("Owner id equal customer id");
        }
        final long vehicleId = requestDTO.getVehicleId();
        final DriverEntity customerEntity = this.repository.findById(customerId)
                .orElseThrow(() -> new BusinessException(String.format("Not found customer driver by id %s", customerId)));
        final VehicleEntity transferredVehicle = ownerEntity.getVehicles().stream()
                .filter(v -> v.getId().equals(vehicleId) && v.getStatus().equals(VehicleStatus.ACTIVE))
                .findFirst()
                .orElseThrow(() -> new BusinessException(String.format("Not found vehicle with id %s for transferring", vehicleId)));
        final PaymentDTO bill = this.checkoutService.checkout(ownerEntity, customerEntity, transferredVehicle);
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
