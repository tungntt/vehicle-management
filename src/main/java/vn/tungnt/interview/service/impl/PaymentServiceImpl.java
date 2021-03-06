package vn.tungnt.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.*;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity.BusinessMan;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity.PaymentDetailId;
import vn.tungnt.interview.domain.entity.PaymentEntity.PaymentStatus;
import vn.tungnt.interview.domain.entity.VehicleEntity.VehicleStatus;
import vn.tungnt.interview.repository.PaymentDetailRepository;
import vn.tungnt.interview.repository.PaymentRepository;
import vn.tungnt.interview.service.CheckoutService;
import vn.tungnt.interview.service.PaymentManagementService;
import vn.tungnt.interview.service.PaymentService;
import vn.tungnt.interview.service.dto.payment.CommitTransferringVehicleRequestDTO;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.exception.BusinessException;
import vn.tungnt.interview.service.mapper.PaymentMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public class PaymentServiceImpl extends AbstractService<PaymentEntity, PaymentDTO>
        implements PaymentService, CheckoutService, PaymentManagementService {

    private static final Logger LOG = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final PaymentRepository repository;

    private final PaymentDetailRepository paymentDetailRepository;

    private final PaymentMapper mapper;

    public PaymentServiceImpl(final PaymentRepository repository, final PaymentDetailRepository paymentDetailRepository,
                              final PaymentMapper mapper) {
        super(repository, mapper);
        this.repository = repository;
        this.paymentDetailRepository = paymentDetailRepository;
        this.mapper = mapper;
    }

    private static final double FIXED_TRANSFERRING_COST = 5.5;

    @Override
    public PaymentDTO checkout(final DriverEntity owner, final DriverEntity customer, final VehicleEntity transferredVehicle) {
        LOG.info("Begin to Checkout Transferring Vehicle");
        PaymentEntity payment = new PaymentEntity();
        payment.setTransferredVehicle(transferredVehicle);
        payment.setStatus(PaymentStatus.IN_PROGRESS);
        payment.setCost(FIXED_TRANSFERRING_COST);
        payment.setCreatedBy(this.getCurrentCredential().getUserName());
        payment.setCreatedDate(new Date());

        final PaymentEntity saved = this.repository.save(payment);
        LOG.info("Saved Payment For Checkout");

        final List<PaymentDetailEntity> paymentDetails = Arrays
                .asList(this.createPaymentDetail(owner, saved, BusinessMan.SELLER),
                        this.createPaymentDetail(customer, saved, BusinessMan.BUYER));
        this.paymentDetailRepository.saveAll(paymentDetails);
        LOG.info("Updated Payment Detail For Checkout");

        final PaymentDTO paymentDTO = this.mapper.toDTO(saved);
        paymentDTO.setBuyer(this.mapper.toTraderDTO(customer));
        paymentDTO.setSeller(this.mapper.toTraderDTO(owner));
        return paymentDTO;
    }

    @Override
    public PaymentDTO payTransferringBill(final CommitTransferringVehicleRequestDTO request) {
        final CredentialEntity currentCredential = this.getCurrentCredential();
        final DriverEntity driverInfo = currentCredential.getDriverInfo();
        final PaymentEntity payment = this.repository.findById(request.getTransactionId())
                .orElseThrow(() -> new BusinessException(String.format("There is no transaction transferring vehicle with id %s", request.getBankAccountId())));
        final PaymentDetailEntity paymentOfBuyer = payment.getTransactionDetails().stream()
                .filter(t -> t.getBusinessMan().equals(BusinessMan.BUYER))
                .findFirst().orElseThrow(() -> new BusinessException("Invalid transferring vehicle transaction. Due to miss buyer!!"));
        if (!driverInfo.getId().equals(paymentOfBuyer.getDriver().getId())) {
            throw new BusinessException("The buyer must be the one submitting this payment!!");
        }
        payment.setStatus(PaymentStatus.PAYMENT_DONE);
        payment.getTransferredVehicle().setStatus(VehicleStatus.ACTIVE);
        this.repository.save(payment);
        return this.mapper.toDTO(payment);
    }

    private PaymentDetailEntity createPaymentDetail(final DriverEntity driver, final PaymentEntity payment, final BusinessMan businessMan) {
        final PaymentDetailEntity paymentDetail = new PaymentDetailEntity();
        PaymentDetailId id = new PaymentDetailId(driver.getId(), payment.getId());
        paymentDetail.setId(id);
        paymentDetail.setDriver(driver);
        paymentDetail.setPayment(payment);
        paymentDetail.setBusinessMan(businessMan);
        return paymentDetail;
    }

    @Override
    public List<PaymentDTO> readAll() {
        final List<PaymentEntity> payments = this.findAll();
        payments.forEach(p -> {
            final Set<DriverEntity> drivers = p.getDrivers();
            drivers.forEach(d -> d.setVehicles(null));
            p.getTransferredVehicle().setDriver(null);
        });
        return this.mapper.toDto(payments);
    }
}
