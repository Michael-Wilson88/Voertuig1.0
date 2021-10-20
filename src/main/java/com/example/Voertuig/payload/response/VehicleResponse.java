package com.example.Voertuig.payload.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class VehicleResponse {

    private long id;
    private String brandName;
    private String brandModel;
    private String vehicleType;

    public VehicleResponse(long id, String brandName, String brandModel, String vehicleType) {
        this.id = id;
        this.brandName = brandName;
        this.brandModel = brandModel;
        this.vehicleType = vehicleType;
    }
}
