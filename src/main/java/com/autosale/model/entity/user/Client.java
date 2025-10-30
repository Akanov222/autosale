package com.autosale.model.entity.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@DiscriminatorValue("CLIENT")
public class Client extends User {

    private String lastName;

    private String phone;

    private boolean isSeller = false;
    private boolean isBuyer = false;
    private boolean isAdmin = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SellerProfile sellerProfile;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BuyerProfile buyerProfile;

//    TODO super
}
