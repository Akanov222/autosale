package com.autosale.service.factory;

import com.autosale.dto.CarRequestDTO;
import com.autosale.dto.MinivanRequestDTO;
import com.autosale.dto.SedanRequestDTO;
import com.autosale.dto.TruckRequestDTO;

public class CarRequestFactory {

    public static CarRequestDTO createRequest(String carType) {
        switch (carType.toUpperCase()) {
            case "SEDAN":
                return new SedanRequestDTO();
            case "MINIVAN":
                return new MinivanRequestDTO();
            case "TRUCK":
                return new TruckRequestDTO();
            // Добавь другие типы автомобилей по мере необходимости
            default:
                throw new IllegalArgumentException("Unsupported car type: " + carType);
        }
    }
}
