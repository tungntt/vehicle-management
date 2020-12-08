package vn.tungnt.interview.domain.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "driver")
@NamedEntityGraph(name = "graph.user.vehicles",
        attributeNodes = @NamedAttributeNode("vehicles"))
public class DriverEntity extends BaseEntity {

    private static final long serialVersionUID = 6634539184223857672L;

    @Column(nullable = false)
    private String name;

    @OneToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", unique = true)
    private CredentialEntity credential;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_of_birth", nullable = false)
    private Date dateOfBirth;

    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Set<VehicleEntity> vehicles = new HashSet<>();

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public CredentialEntity getCredential() {
        return credential;
    }

    public void setCredential(final CredentialEntity credential) {
        this.credential = credential;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(final Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Set<VehicleEntity> getVehicles() {
        return vehicles;
    }

    public void setVehicles(final Set<VehicleEntity> vehicles) {
        this.vehicles = vehicles;
    }

}
