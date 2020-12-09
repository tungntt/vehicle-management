package vn.tungnt.interview.service.mapper.impl;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import vn.tungnt.interview.domain.entity.DriverEntity;
import vn.tungnt.interview.domain.entity.PaymentEntity;
import vn.tungnt.interview.service.dto.payment.PaymentDTO;
import vn.tungnt.interview.service.dto.payment.TraderDTO;
import vn.tungnt.interview.service.dto.vehicle.VehicleDTO;
import vn.tungnt.interview.service.mapper.DriverMapper;
import vn.tungnt.interview.service.mapper.PaymentMapper;
import vn.tungnt.interview.service.mapper.VehicleMapper;

import java.util.*;

@Component
public class PaymentMapperImpl implements PaymentMapper {

    private final DriverMapper driverMapper;

    private final VehicleMapper vehicleMapper;

    public PaymentMapperImpl(final DriverMapper driverMapper, final VehicleMapper vehicleMapper) {
        this.driverMapper = driverMapper;
        this.vehicleMapper = vehicleMapper;
    }

    @Override
    public PaymentEntity toEntity(final PaymentDTO dto) {
        if ( dto == null ) {
            return null;
        }

        final PaymentEntity entity = new PaymentEntity();
        entity.setStatus(dto.getStatus());
        entity.setDrivers(new HashSet<>(2));
        entity.getDrivers().add(this.driverMapper.toEntity(dto.getBuyer()));
        entity.getDrivers().add(this.driverMapper.toEntity(dto.getSeller()));
        entity.setTransferredVehicle(this.vehicleMapper.toEntity(dto.getTransferredVehicle()));

        return entity;
    }

    @Override
    public PaymentDTO toDTO(final PaymentEntity entity) {

        if (entity == null) {
            return null;
        }

        final PaymentDTO dto = new PaymentDTO();
        final VehicleDTO vehicleDTO = new VehicleDTO();
        vehicleDTO.setId(entity.getTransferredVehicle().getId());
        vehicleDTO.setBrand(entity.getTransferredVehicle().getBrand());
        vehicleDTO.setModel(entity.getTransferredVehicle().getModel());
        vehicleDTO.setYear(entity.getTransferredVehicle().getYear());
        vehicleDTO.setOdo(entity.getTransferredVehicle().getOdo());
        vehicleDTO.setPlateNumber(entity.getTransferredVehicle().getPlateNumber());
        dto.setId(entity.getId());
        dto.setTransferredVehicle(vehicleDTO);
        dto.setCreatedBy(entity.getCreatedBy());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setStatus(entity.getStatus());


        return dto;
    }

    @Override
    public List<PaymentEntity> toEntity(final List<PaymentDTO> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Collections.emptyList();
        }

        List<PaymentEntity> list = new ArrayList<>( dtoList.size() );
        for ( PaymentDTO paymentDTO : dtoList ) {
            list.add( toEntity( paymentDTO ) );
        }

        return list;

    }

    @Override
    public List<PaymentDTO> toDto(final List<PaymentEntity> entityList) {
        return null;
    }

    @Override
    public TraderDTO toTraderDTO(final DriverEntity entity) {
        final TraderDTO traderDTO = new TraderDTO();
        traderDTO.setId(entity.getId());
        traderDTO.setName(entity.getName());
        traderDTO.setDateOfBirth(entity.getDateOfBirth());
        return traderDTO;
    }
}
