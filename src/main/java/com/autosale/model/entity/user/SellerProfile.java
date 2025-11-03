package com.autosale.model.entity.user;

import com.autosale.model.entity.lists.CarListing;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

//@Entity
@Table(name = "seller_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SellerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String companyName;
    private String taxId;
    private BigDecimal rating = BigDecimal.ZERO;
    private int totalSales = 0;
    private String businessAddress;
    private String businessPhone;
    private LocalDateTime sellerSince;

    @OneToMany(mappedBy = "seller")
    private List<CarListing> listings;
}