package vn.tungnt.interview.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity.BusinessMan;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity.PaymentDetailId;
import vn.tungnt.interview.domain.entity.PaymentEntity;
import vn.tungnt.interview.domain.entity.PaymentEntity.PaymentStatus;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.repository.PaymentDetailRepository;
import vn.tungnt.interview.repository.PaymentRepository;
import vn.tungnt.interview.service.PaymentService;
import vn.tungnt.interview.service.dto.PaymentDTO;
import vn.tungnt.interview.service.mapper.PaymentMapper;

import java.util.Arrays;
import java.util.List;


@Service
public class PaymentServiceImpl extends AbstractService<PaymentEntity, PaymentDTO> implements PaymentService {

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

        final PaymentEntity saved = this.repository.save(payment);
        LOG.info("Saved Payment For Checkout");

        final List<PaymentDetailEntity> paymentDetails = Arrays
                .asList(this.createPaymentDetail(owner, saved, BusinessMan.BUYER),
                        this.createPaymentDetail(customer, saved, BusinessMan.SELLER));
        this.paymentDetailRepository.saveAll(paymentDetails);
        LOG.info("Updated Payment Detail For Checkout");

        return this.mapper.toDTO(saved);
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
}