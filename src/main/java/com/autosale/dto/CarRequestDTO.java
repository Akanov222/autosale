package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "carTypeName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SedanRequestDTO.class, name = "sedan"),
        @JsonSubTypes.Type(value = MinivanRequestDTO.class, name = "minivan"),
        @JsonSubTypes.Type(value = TruckRequestDTO.class, name = "truck")
})
public abstract class CarRequestDTO {
    private String brand;
    private String model;
    private Integer year;
    private String carTypeName;
    private BigDecimal price;

    public abstract String getCarTypeName();

}

