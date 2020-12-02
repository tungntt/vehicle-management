package vn.tungnt.interview.service.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import vn.tungnt.interview.domain.entity.VehicleEntity;
import vn.tungnt.interview.service.VehicleService;
import vn.tungnt.interview.service.dto.VehicleDTO;
import vn.tungnt.interview.service.mapper.VehicleMapper;

@Service
public class VehicleServiceImpl extends AbstractService<VehicleEntity, VehicleDTO> implements VehicleService {

    public VehicleServiceImpl(final JpaRepository<VehicleEntity, Long> repository, final VehicleMapper mapper) {
        super(repository, mapper);
    }

}
