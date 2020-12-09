package vn.tungnt.interview.service.mapper;

import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.PaymentEntity;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.payment.TraderDTO;

public interface PaymentMapper extends BaseMapper<PaymentEntity, PaymentDTO> {

    TraderDTO toTraderDTO(final DriverEntity entity);

}
