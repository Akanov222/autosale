package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "carTypeName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SedanDto.class, name = "sedan"),
        @JsonSubTypes.Type(value = MinivanDto.class, name = "minivan"),
        @JsonSubTypes.Type(value = TruckDto.class, name = "truck")
})
public abstract class CarDto {

    private Long id;

    @NotBlank (message = "brand must not be blank" )
    private String brand;

    @NotBlank (message = "model must not be blank" )
    private String model;

    @NotNull (message = "year must not be null" )
    @Min (1800) @Max(2050)
    private Integer year;

    @NotBlank (message = "car type name must not be blank" )
    private String carTypeName;

    @NotNull (message = "price must not be null" )
    @Min (100) @Max(10_000_000)
    private BigDecimal price;

    public abstract String getCarTypeName();
}

