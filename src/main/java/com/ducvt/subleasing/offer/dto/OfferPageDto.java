package com.ducvt.subleasing.offer.dto;

import com.ducvt.subleasing.offer.entities.Offer;
import lombok.Data;

import java.util.List;

@Data
public class OfferPageDto {
    private List<Offer> content;
    private long totalElements;
}
