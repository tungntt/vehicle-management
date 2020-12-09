package vn.tungnt.interview.service.dto.payment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.tungnt.interview.domain.entity.PaymentEntity.PaymentStatus;
import vn.tungnt.interview.service.dto.BaseDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;

@JsonIgnoreProperties(value = "credentialId")
public class PaymentDTO extends BaseDTO {

    private static final long serialVersionUID = -855814479114870025L;

    private PaymentStatus status;

    private TraderDTO buyer;

    private TraderDTO seller;

    @JsonIgnoreProperties(value = {"createdBy", "createdDate", "credentialId"})
    private VehicleDTO transferredVehicle;

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(final PaymentStatus status) {
        this.status = status;
    }

    public TraderDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(final TraderDTO buyer) {
        this.buyer = buyer;
    }

    public TraderDTO getSeller() {
        return seller;
    }

    public void setSeller(final TraderDTO seller) {
        this.seller = seller;
    }

    public VehicleDTO getTransferredVehicle() {
        return transferredVehicle;
    }

    public void setTransferredVehicle(final VehicleDTO transferredVehicle) {
        this.transferredVehicle = transferredVehicle;
    }
}
