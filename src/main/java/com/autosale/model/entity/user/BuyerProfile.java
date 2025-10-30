package com.autosale.model.entity.user;

import com.autosale.model.entity.lists.Offer;
import com.autosale.model.entity.lists.Purchase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "buyer_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BuyerProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private BigDecimal budget;
    private String preferredContactMethod;
    private String deliveryAddress;
    private String preferredPaymentMethod;

    @OneToMany(mappedBy = "buyer")
    private List<Purchase> purchases;

    @OneToMany(mappedBy = "buyer")
    private List<Offer> offers;
}
