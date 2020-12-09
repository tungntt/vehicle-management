package vn.tungnt.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.VehicleEntity;

import java.util.List;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    List<VehicleEntity> getAllByDriver(final DriverEntity driver);
}
