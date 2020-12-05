package vn.tungnt.interview.domain.entity;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(cascade = CascadeType.REMOVE)
    @JoinTable(name = "payment_detail",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id"))
    private Set<DriverEntity> drivers = new HashSet<>(2);

    @OneToMany(mappedBy = "payment", cascade = CascadeType.REMOVE)
    private Set<PaymentDetailEntity> transactionDetails = new HashSet<>(2);

    @Column
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity transferredVehicle;

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

    public Set<PaymentDetailEntity> getTransactionDetails() {
        return transactionDetails;
    }

    public void setTransactionDetails(final Set<PaymentDetailEntity> transactionDetails) {
        this.transactionDetails = transactionDetails;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(final Double cost) {
        this.cost = cost;
    }

    public VehicleEntity getTransferredVehicle() {
        return transferredVehicle;
    }

    public void setTransferredVehicle(final VehicleEntity transferredVehicle) {
        this.transferredVehicle = transferredVehicle;
    }
}
