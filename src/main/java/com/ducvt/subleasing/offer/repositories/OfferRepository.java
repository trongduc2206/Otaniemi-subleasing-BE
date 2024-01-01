package com.ducvt.subleasing.offer.repositories;

import com.ducvt.subleasing.account.models.User;
import com.ducvt.subleasing.offer.entities.Offer;
import com.sun.istack.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>, JpaSpecificationExecutor<Offer> {

    Page<Offer> findByStatus(Integer status, Pageable pageable);

    Optional<Offer> findByOfferId(Long userId);

    Page<Offer> findByUserAndStatus(User user, int status, Pageable pageable);

//    Page<Offer> findByStatus(@Nullable Specification<Offer> specification, Integer status, Pageable pageable);

}
