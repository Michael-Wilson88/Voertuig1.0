package com.example.Voertuig.payload.request;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookVehicleRequest {

    @NotNull(message = "Vehicle id is mandatory")
    private Long vehicleId;
    @NotBlank(message = "BeginDate is mandatory")
    private CharSequence beginDate;
    @NotBlank(message = "EndDate is mandatory")
    private CharSequence endDate;

}
