package vn.tungnt.interview.service.dto.driver;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import vn.tungnt.interview.service.dto.BaseDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;

import java.util.Date;
import java.util.Set;

@JsonIgnoreProperties(value = "credentialId")
public class DriverDTO extends BaseDTO {

    private static final long serialVersionUID = -2659325374634490096L;

    private String name;

    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dateOfBirth;

    private Set<VehicleDTO> vehicles;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<VehicleDTO> getVehicles() {
        return vehicles;
    }

    public void setVehicles(final Set<VehicleDTO> vehicles) {
        this.vehicles = vehicles;
    }
}
