package com.autosale.mapper;

import com.autosale.dto.SedanDto;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.Sedan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SedanMapper {

    @Mapping(constant = "SEDAN", target = "carTypeName")
    SedanDto toDto(Sedan sedan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carType", source = "carType")
    Sedan toEntity(SedanDto sedanDto, CarType carType);
}
