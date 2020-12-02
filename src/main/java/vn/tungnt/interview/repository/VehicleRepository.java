package vn.tungnt.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.tungnt.interview.domain.entity.VehicleEntity;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
