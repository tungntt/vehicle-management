package vn.tungnt.interview.service.dto;

import java.io.Serializable;

public class TransferringVehicleRequestDTO implements Serializable {

    private static final long serialVersionUID = -4587127605973605642L;

    private long ownerId;

    private long customerId;

    private long vehicleId;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(final long ownerId) {
        this.ownerId = ownerId;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(final long customerId) {
        this.customerId = customerId;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(final long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
