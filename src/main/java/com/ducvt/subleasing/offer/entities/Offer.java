package com.ducvt.subleasing.offer.entities;

import com.ducvt.subleasing.account.models.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "offers")
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    private Date createdTime;

    private Date updatedTime;

    @NotNull
    private Integer status;

}
