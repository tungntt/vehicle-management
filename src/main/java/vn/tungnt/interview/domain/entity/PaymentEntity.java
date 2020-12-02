package vn.tungnt.interview.domain.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "payment_history")
public class PaymentEntity extends BaseEntity {

    private static final long serialVersionUID = -8152988905970434357L;

    public enum PaymentStatus {
        IN_PROGRESS,
        PAYMENT_DONE,
        PAYMENT_FAILED;
    }

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "payment_detail",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private Set<DriverEntity> drivers;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(final PaymentStatus status) {
        this.status = status;
    }

    public Set<DriverEntity> getDrivers() {
        return drivers;
    }

    public void setDrivers(final Set<DriverEntity> drivers) {
        this.drivers = drivers;
    }
}
