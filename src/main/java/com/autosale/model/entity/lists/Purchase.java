package com.autosale.model.entity.lists;

import com.autosale.model.entity.user.BuyerProfile;
import com.autosale.model.entity.user.SellerProfile;
import com.autosale.model.entity.user.User;
import com.autosale.model.enums.PurchaseStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "listing_id")
    private CarListing listing;

    // Покупатель и продавец - оба User
    @ManyToOne
    @JoinColumn(name = "buyer_user_id")
    private User buyerUser;

    @ManyToOne
    @JoinColumn(name = "seller_user_id")
    private User sellerUser;

    // Дополнительные ссылки на профили
    @ManyToOne
    @JoinColumn(name = "buyer_profile_id")
    private BuyerProfile buyerProfile;

    @ManyToOne
    @JoinColumn(name = "seller_profile_id")
    private SellerProfile sellerProfile;

    private BigDecimal finalPrice;
    private LocalDateTime purchaseDate;
    private String contractNumber;
    private PurchaseStatus status = PurchaseStatus.COMPLETED;

    private String paymentMethod;
    private String transactionId;
    private boolean paymentConfirmed = true;
}
