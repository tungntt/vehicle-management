package vn.tungnt.interview.service.dto.payment;

import vn.tungnt.interview.domain.entity.PaymentEntity.PaymentStatus;
import vn.tungnt.interview.service.dto.BaseDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;
import vn.tungnt.interview.service.dto.driver.DriverDTO;

public class PaymentDTO extends BaseDTO {

    private static final long serialVersionUID = -855814479114870025L;

    private String transactionId;

    private PaymentStatus status;

    private DriverDTO buyer;

    private DriverDTO seller;

    private VehicleDTO transferredVehicle;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(final String transactionId) {
        this.transactionId = transactionId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(final PaymentStatus status) {
        this.status = status;
    }

    public DriverDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(final DriverDTO buyer) {
        this.buyer = buyer;
    }

    public DriverDTO getSeller() {
        return seller;
    }

    public void setSeller(final DriverDTO seller) {
        this.seller = seller;
    }

    public VehicleDTO getTransferredVehicle() {
        return transferredVehicle;
    }

    public void setTransferredVehicle(final VehicleDTO transferredVehicle) {
        this.transferredVehicle = transferredVehicle;
    }
}
