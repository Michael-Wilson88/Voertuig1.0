package com.example.Voertuig.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class VehicleRequest {

    private Long id;
    @NotBlank(message = "BrandName is mandatory.")
    private String brandName;
    @NotBlank(message = "BrandModel is mandatory.")
    private String brandModel;
    @NotBlank(message = "Vehicle type is mandatory")
    private String vehicleType;
    private boolean isAvailable;

}
