package com.autosale.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "carTypeName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SedanUpdateDto.class, name = "sedan"),
        @JsonSubTypes.Type(value = MinivanUpdateDto.class, name = "minivan"),
        @JsonSubTypes.Type(value = TruckUpdateDto.class, name = "truck")
})
public abstract class CarUpdateDto {

    @Pattern(regexp = "^[a-zA-Z0-9\\s]{1,50}$", message = "Brand must be 1-50 alphanumeric characters")
    private String brand;

    @Pattern(regexp = "^[a-zA-Z0-9\\s-]{1,50}$", message = "Model must be 1-50 alphanumeric characters")
    private String model;

    @Min(value = 1800, message = "Year must be at least 1800")
    @Max(value = 2050, message = "Year must be at most 2050")
    private Integer year;

    @Min(value = 0, message = "Price must be positive or zero")
    private BigDecimal price;

    private String carTypeName;

    public abstract String getCarTypeName();
}

