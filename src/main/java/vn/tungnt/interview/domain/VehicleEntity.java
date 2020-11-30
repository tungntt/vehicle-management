package vn.tungnt.interview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name = "vehicle")
@Data
@EqualsAndHashCode(callSuper = true)
public class VehicleEntity extends BaseEntity {

    private static final long serialVersionUID = 3231387098298837469L;

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

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private DriverEntity user;
}
