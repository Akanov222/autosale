package com.autosale.dto;

import com.autosale.model.enums.CarTypeEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SedanDto extends CarDto {

    @JsonProperty("trunkCapacity")
    @NotNull(message = "trunkCapacity must not be blank" )
    @DecimalMin("10.0")
    @DecimalMax("4000.0")
    private Double trunkCapacity;

    public SedanDto(Long id, String brand, String model, Integer year,
                    String carTypeName, BigDecimal price,
                    Double trunkCapacity) {
        super(id, brand, model, year, carTypeName, price);
        this.trunkCapacity = trunkCapacity;
    }

    @Override
    public String getCarTypeName() {
        return CarTypeEnum.SEDAN.getCode();
    }

}
