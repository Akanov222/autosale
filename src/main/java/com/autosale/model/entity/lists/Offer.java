package com.autosale.model.entity.lists;

import com.autosale.model.entity.user.BuyerProfile;
import com.autosale.model.entity.user.User;
import com.autosale.model.enums.OfferStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "listing_id")
    private CarListing listing;

    // Покупатель - User
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Дополнительная ссылка на профиль покупателя
    @ManyToOne
    @JoinColumn(name = "buyer_profile_id")
    private BuyerProfile buyerProfile;

    private BigDecimal offerAmount;
    private String message;
    private OfferStatus status = OfferStatus.PENDING;
    private LocalDateTime offeredAt;
    private LocalDateTime respondedAt;
}
