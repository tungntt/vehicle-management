package vn.tungnt.interview.service.dto;

import vn.tungnt.interview.service.dto.BaseDTO;
import vn.tungnt.interview.service.dto.DriverDTO;

import java.io.Serializable;

public class VehicleDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 617057763259040614L;

    private String brand;

    private String model;

    private Integer year;

    private String plateNumber;

    private Integer odo;

    private DriverDTO driver;

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(final String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Integer getOdo() {
        return odo;
    }

    public void setOdo(final Integer odo) {
        this.odo = odo;
    }

    public DriverDTO getDriver() {
        return driver;
    }

    public void setDriver(final DriverDTO driver) {
        this.driver = driver;
    }
}
