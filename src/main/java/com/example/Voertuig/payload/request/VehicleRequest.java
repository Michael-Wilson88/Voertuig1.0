package com.example.Voertuig.payload.request;

import lombok.Data;

@Data
public class VehicleRequest {

    private long id;
    private String brandName;
    private String brandModel;
    private String vehicleType;
    private boolean isAvailable;

}
