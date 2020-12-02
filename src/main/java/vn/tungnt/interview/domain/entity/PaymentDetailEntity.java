package vn.tungnt.interview.domain.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "payment_detail")
public class PaymentDetailEntity implements Serializable{

    private static final long serialVersionUID = 7344094568362427509L;

    public enum BusinessMan {
        BUYER, SELLER
    }


    @Embeddable
    public static class PaymentDetailId implements Serializable {

        private static final long serialVersionUID = 7864925636759948605L;

        @Column(name = "driver_id")
        private Long driverId;

        @Column(name = "payment_id")
        private Long paymentId;

        public Long getDriverId() {
            return driverId;
        }

        public void setDriverId(final Long driverId) {
            this.driverId = driverId;
        }

        public Long getPaymentId() {
            return paymentId;
        }

        public void setPaymentId(final Long paymentId) {
            this.paymentId = paymentId;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            PaymentDetailId that = (PaymentDetailId) o;
            return driverId.equals(that.driverId) &&
                    paymentId.equals(that.paymentId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(driverId, paymentId);
        }
    }

    @GeneratedValue
    @Column(name = "transaction_id", updatable = false, nullable = false, unique = true)
    private UUID transactionId;

    @EmbeddedId
    private PaymentDetailId id;

    @ManyToOne
    @MapsId("driver_id")
    private DriverEntity driver;

    @ManyToOne
    @MapsId("payment_id")
    private PaymentEntity payment;

    @Enumerated(EnumType.STRING)
    private BusinessMan businessMan;

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final UUID transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentDetailId getId() {
        return id;
    }

    public void setId(final PaymentDetailId id) {
        this.id = id;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(final DriverEntity driver) {
        this.driver = driver;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(final PaymentEntity payment) {
        this.payment = payment;
    }

    public BusinessMan getBusinessMan() {
        return businessMan;
    }

    public void setBusinessMan(final BusinessMan businessMan) {
        this.businessMan = businessMan;
    }
}
