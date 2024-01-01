package com.ducvt.subleasing.offer.controllers;

import com.ducvt.subleasing.fw.utils.ResponseFactory;
import com.ducvt.subleasing.offer.dto.OfferPageDto;
import com.ducvt.subleasing.offer.payload.requests.OfferCriteria;
import com.ducvt.subleasing.offer.payload.requests.OfferRequest;
import com.ducvt.subleasing.offer.payload.responses.OfferResponse;
import com.ducvt.subleasing.offer.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/offer")
public class OfferController {
    @Autowired
    OfferService offerService;

    @GetMapping
    public ResponseEntity getAll(@RequestParam int page, @RequestParam int size) {
        OfferPageDto offerPageDto = offerService.getAll(page, size);
        return ResponseFactory.success(offerPageDto);
    }

    @GetMapping("/filter")
//    public ResponseEntity getAll(@RequestParam int page, @RequestParam int size, @RequestBody OfferCriteria criteria) {
    public ResponseEntity getAll(@RequestParam int page, @RequestParam int size
            , @RequestParam(required = false) String apartmentType
            , @RequestParam(required = false) String area
            , @RequestParam(required = false) Integer priceLeq
            , @RequestParam(required = false) Integer priceGeq) {
        OfferPageDto offerPageDto = offerService.filter(page, size, apartmentType, area, priceLeq, priceGeq);
        return ResponseFactory.success(offerPageDto);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody @Valid OfferRequest offerRequest) {
        OfferResponse offerResponse = offerService.create(offerRequest);
        return ResponseFactory.success(offerResponse);
    }


    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        return ResponseFactory.success(offerService.getById(id));
    }

    @GetMapping("/manage")
    public ResponseEntity getAllByUser(@RequestParam int page, @RequestParam int size, @RequestParam Long userId) {
        OfferPageDto offerPageDto = offerService.getByUserId(page, size, userId);
        return ResponseFactory.success(offerPageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        offerService.removeOffer(id);
        return ResponseFactory.success(id);
    }


}
