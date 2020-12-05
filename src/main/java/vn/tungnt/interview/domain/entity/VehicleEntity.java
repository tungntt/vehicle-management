package vn.tungnt.interview.domain.entity;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
public class VehicleEntity extends BaseEntity {

    private static final long serialVersionUID = 3231387098298837469L;

    public enum VehicleStatus {
        ACTIVE,
        TRANSFERRING,
    }

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "construction_year", nullable = false)
    private Integer year;

    @Column(name = "plate_number")
    private String plateNumber;

    @Column(name = "odo")
    @ColumnDefault(value = "0")
    private Integer odo;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

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

    public VehicleStatus getStatus() {
        return status;
    }

    public void setStatus(final VehicleStatus status) {
        this.status = status;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(final DriverEntity driver) {
        this.driver = driver;
    }
}
