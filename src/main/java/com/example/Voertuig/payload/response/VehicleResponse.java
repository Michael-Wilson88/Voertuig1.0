package com.example.Voertuig.payload.response;

import lombok.Data;

@Data
public class VehicleResponse {

    private long id;
    private String brandName;
    private String brandModel;
    private String vehicleType;
    private boolean isAvailable;

    public VehicleResponse(long id, String brandName, String brandModel, String vehicleType, boolean isAvailable) {
        this.id = id;
        this.brandName = brandName;
        this.brandModel = brandModel;
        this.vehicleType = vehicleType;
        this.isAvailable = isAvailable;
    }
}
