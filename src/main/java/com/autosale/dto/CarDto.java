package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "carTypeName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SedanDto.class, name = "sedan"),
        @JsonSubTypes.Type(value = MinivanDto.class, name = "minivan"),
        @JsonSubTypes.Type(value = TruckDto.class, name = "truck")
})
public abstract class CarDto {

    @Getter
    private String brand;

    @Getter
    private String model;

    @Getter
    private Integer year;

    private String carTypeName;

    @Getter
    private BigDecimal price;

    public abstract String getCarTypeName();
}

