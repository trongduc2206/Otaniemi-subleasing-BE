package com.ducvt.subleasing.offer.payload.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
public class OfferRequest {
    private Long userId;

    private String description;

    @NotNull
    private Integer apartmentType;

    private Integer housingType;

    private Float apartmentFloorArea;

    private Float roomFloorArea;

    private Integer furnished;

    private Integer floor;

    @NotNull
    private Integer monthlyPrice;

    private Integer actualPrice;

    private Date startDate;

    private Date endDate;

    private String address;

    private Integer deposit;

    private String postCode;

    @NotEmpty
    private String area;

    private Integer laundry;

}
