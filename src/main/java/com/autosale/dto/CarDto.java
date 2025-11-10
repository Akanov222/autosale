package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank (message = "brand must not be blank" )
    private String brand;

    @Getter
    @NotBlank (message = "model must not be blank" )
    private String model;

    @Getter
    @NotNull (message = "year must not be null" )
    @Min (value = 1800)
    @Max(value = 2050)
    private Integer year;

    @NotBlank (message = "car type name must not be blank" )
    private String carTypeName;

    @Getter
    @NotNull (message = "price must not be null" )
//    @Min (value = 100)
//    @Max(value = 10_000_000)
    private BigDecimal price;

    public abstract String getCarTypeName();
}

