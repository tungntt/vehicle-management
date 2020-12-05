package vn.tungnt.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity;
import vn.tungnt.interview.domain.entity.PaymentDetailEntity.PaymentDetailId;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetailEntity, PaymentDetailId> {
}
