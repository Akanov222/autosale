package com.autosale.model.entity.lists;

import com.autosale.model.entity.car.Car;
import com.autosale.model.entity.user.SellerProfile;
import com.autosale.model.entity.user.User;
import com.autosale.model.enums.ListingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarListing {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "car_id")
    private Car car;

    // Владелец объявления - User (который может быть и продавцом и покупателем)
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Дополнительная ссылка на профиль продавца (если есть)
    @ManyToOne
    @JoinColumn(name = "seller_profile_id")
    private SellerProfile sellerProfile;

    private String title;
    private String description;
    private BigDecimal listingPrice;
    private ListingStatus status = ListingStatus.ACTIVE;
    private LocalDateTime listedAt;
    private LocalDateTime expiresAt;
    private Integer viewCount = 0;

    @OneToMany(mappedBy = "listing")
    private List<Offer> offers;
}
