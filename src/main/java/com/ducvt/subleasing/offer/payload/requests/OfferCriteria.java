package com.ducvt.subleasing.offer.payload.requests;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class OfferCriteria {
//    private Integer apartmentType;
    private List<Integer> apartmentType;

    private String area;

    private Integer priceLeq;

    private Integer priceGeq;
}
