package com.example.Voertuig.payload.request;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BookVehicleRequest {
    @NotBlank(message = "Vehicle id is mandatory")
    private Long id;
    @NotBlank(message = "BeginDate is mandatory")
    private CharSequence beginDate;
    @NotBlank(message = "EndDate is mandatory")
    private CharSequence endDate;

}
