package com.example.Voertuig.payload.request;

import lombok.Data;

@Data
public class VehicleRequest {

    private String name;
    private boolean isAvailable;
}
