package com.autosale.mapper;

import com.autosale.dto.MinivanDto;
import com.autosale.model.entity.car.CarType;
import com.autosale.model.entity.car.Minivan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MinivanMapper {

    @Mapping(constant = "MINIVAN", target = "carTypeName")
    MinivanDto toDto(Minivan minivan);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "carType", source = "carType")
    Minivan toEntity(MinivanDto minivanDto, CarType carType);
}
