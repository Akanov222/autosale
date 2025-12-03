package com.autosale.mapper;

import com.autosale.dto.SedanDto;
import com.autosale.dto.TruckDto;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.Sedan;
import com.autosale.model.entity.car.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TruckMapper {

    @Mapping(constant = "TRUCK", target = "carTypeName")
    TruckDto toDto(Truck truck);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carType", source = "carType")
    Truck toEntity(TruckDto truckDto, CarType carType);
}
