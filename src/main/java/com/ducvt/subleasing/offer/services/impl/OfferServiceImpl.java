package com.ducvt.subleasing.offer.services.impl;

import com.ducvt.subleasing.account.models.User;
import com.ducvt.subleasing.account.repository.UserRepository;
import com.ducvt.subleasing.offer.dto.OfferPageDto;
import com.ducvt.subleasing.offer.entities.Offer;
import com.ducvt.subleasing.offer.payload.requests.OfferCriteria;
import com.ducvt.subleasing.offer.payload.requests.OfferRequest;
import com.ducvt.subleasing.offer.payload.responses.OfferResponse;
import com.ducvt.subleasing.offer.repositories.OfferRepository;
import com.ducvt.subleasing.offer.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
public class OfferServiceImpl implements OfferService {
    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public OfferPageDto getAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"createdTime"));
        Page<Offer> offerPage = offerRepository.findByStatus(1, pageable);
        OfferPageDto offerPageDto = new OfferPageDto();
        offerPageDto.setContent(offerPage.getContent());
        offerPageDto.setTotalElements(offerPage.getTotalElements());
        return offerPageDto;
    }
    static Specification<Offer> hasLaundry(Integer laundry) {
        return (root, cq, cb) -> cb.equal(root.get("laundry"), laundry);
    }
    static Specification<Offer> hasDeposit(Integer deposit) {
        return (root, cq, cb) -> deposit == null?null:cb.isNotNull(root.get("deposit"));
    }

    static Specification<Offer> isActive() {
        return (root, cq, cb) -> cb.equal(root.get("status"), 1);
    }

//    static Specification<Offer> isType(List<Integer> apartmentTypes){
//        return (root, cq, cb) -> apartmentTypes == null ?null:cb.isMember(apartmentTypes, root.get("apartmentType"));
//    }

    static Specification<Offer> isType(List<Integer> apartmentTypes){
        return new Specification<Offer>() {
            @Override
            public Predicate toPredicate(Root<Offer> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                if(apartmentTypes != null) {
                    Path<Integer> type = root.get("apartmentType");
                    return type.in(apartmentTypes);
                } else {
                    return null;
                }
            }
        };
    }


    static Specification<Offer> priceGeq(Integer priceGeq) {
        return (root, cq, cb) -> priceGeq == null ? null :cb.greaterThanOrEqualTo(root.get("monthlyPrice"), priceGeq);
    }

    static Specification<Offer> priceLeq(Integer priceLeq) {
        return (root, cq, cb) -> priceLeq == null ? null :cb.lessThanOrEqualTo(root.get("monthlyPrice"), priceLeq);
    }

    static Specification<Offer> inArea(String area) {
        return (root, cq, cb) -> area == null ? null :cb.like(cb.lower(root.get("area")),"%" + area.toLowerCase() +"%");
//        return (root, cq, cb) -> area == null ? null :cb.;
    }
    @Override
    public OfferPageDto filter(int page, int size, String apartmentType, String area, Integer priceLeq, Integer priceGeq) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC,"createdTime"));
        List<Integer> typeIntegerList = new ArrayList<>();
        if(apartmentType!= null && !apartmentType.isEmpty()) {
            List<String> typeStringList = Arrays.asList(apartmentType.split(","));
            for(String type : typeStringList) {
                typeIntegerList.add(Integer.parseInt(type));
            }
        }
        Specification<Offer> specification = Specification.where(isActive())
                .and(isType(apartmentType!=null?typeIntegerList:null))
                .and(inArea(area))
                .and(priceGeq(priceGeq))
                .and(priceLeq(priceLeq));
        Page<Offer> offerPage = offerRepository.findAll(specification, pageable);
        OfferPageDto offerPageDto = new OfferPageDto();
        offerPageDto.setContent(offerPage.getContent());
        offerPageDto.setTotalElements(offerPage.getTotalElements());
        return offerPageDto;
    }

    @Override
    public OfferResponse create(OfferRequest offerRequest) {
        Optional<User> userOptional = userRepository.findByUserId(offerRequest.getUserId());
        if(userOptional.isPresent()) {
            Offer offer = new Offer();
            offer.setUser(userOptional.get());
            offer.setDescription(offerRequest.getDescription());
            offer.setApartmentType(offerRequest.getApartmentType());
            offer.setHousingType(offerRequest.getHousingType());
            offer.setApartmentFloorArea(offerRequest.getApartmentFloorArea());
            offer.setRoomFloorArea(offerRequest.getRoomFloorArea());
            offer.setFurnished(offerRequest.getFurnished());
            offer.setFloor(offerRequest.getFloor());
            offer.setMonthlyPrice(offerRequest.getMonthlyPrice());
            if(offerRequest.getStartDate() != null && offerRequest.getEndDate() != null) {
                long diffInMillies = Math.abs(offerRequest.getEndDate().getTime() - offerRequest.getStartDate().getTime());
                long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
                long diffInMonths = Math.round(diffInDays/30);
                int actualPrice = (int) Math.floor(diffInMonths*offerRequest.getMonthlyPrice());
                offer.setActualPrice(actualPrice);
            }
            offer.setStartDate(offerRequest.getStartDate());
            offer.setEndDate(offerRequest.getEndDate());
            offer.setAddress(offerRequest.getAddress());
            offer.setDeposit(offerRequest.getDeposit());
            offer.setPostCode(offerRequest.getPostCode());
            offer.setArea(offerRequest.getArea());
            offer.setLaundry(offerRequest.getLaundry());
            offer.setStatus(1);
            offer.setCreatedTime(new Date());
            offer.setUpdatedTime(new Date());

            offerRepository.save(offer);
            OfferResponse offerResponse = new OfferResponse();
            offerResponse.setOfferId(offer.getOfferId());
            return offerResponse;
        } else {
            return null;
        }
    }


}
