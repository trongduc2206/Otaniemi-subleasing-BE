package com.ducvt.subleasing.offer.services;

import com.ducvt.subleasing.offer.dto.OfferPageDto;
import com.ducvt.subleasing.offer.entities.Offer;
import com.ducvt.subleasing.offer.payload.requests.OfferCriteria;
import com.ducvt.subleasing.offer.payload.requests.OfferRequest;
import com.ducvt.subleasing.offer.payload.responses.OfferResponse;
import org.springframework.stereotype.Service;

@Service
public interface OfferService {
    OfferPageDto getAll(int page, int size);

    OfferPageDto filter(int page, int size, String apartmentType, String area, Integer priceLeq, Integer priceGeq);

    OfferResponse create(OfferRequest offerRequest);

    Offer getById(Long offerId);

    OfferPageDto getByUserId(int page, int size, Long userId);

    void removeOffer(Long offerId);
}
