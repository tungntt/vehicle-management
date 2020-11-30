package vn.tungnt.interview.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "driver")
@Data
@EqualsAndHashCode(callSuper = true)
@NamedEntityGraph(name = "graph.user.vehicles",
        attributeNodes = @NamedAttributeNode("vehicles"))
public class DriverEntity extends BaseEntity {

    private static final long serialVersionUID = 6634539184223857672L;

    @Column(nullable = false)
    private String name;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<VehicleEntity> vehicles = new HashSet<>();
}
